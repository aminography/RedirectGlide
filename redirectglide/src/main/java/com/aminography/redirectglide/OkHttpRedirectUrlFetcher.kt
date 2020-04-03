package com.aminography.redirectglide

import android.util.Log
import com.bumptech.glide.load.HttpException
import com.bumptech.glide.load.data.DataFetcher.DataCallback
import com.bumptech.glide.load.model.GlideUrl
import okhttp3.*
import java.io.IOException

/**
 * Fetches a direct url to the initial [GlideUrl] using the okhttp library.
 */
open class OkHttpRedirectUrlFetcher(
    private val client: Call.Factory,
    protected var url: GlideUrl
) : Callback {

    protected var responseBody: ResponseBody? = null
    protected var callback: DataCallback<in GlideUrl>? = null

    // call may be accessed on the main thread while the object is in use on other threads. All other
    // accesses to variables may occur on different threads, but only one at a time.
    @Volatile
    private var call: Call? = null

    private var redirectCount = DEFAULT_MAX_REDIRECT

    fun loadData(callback: DataCallback<in GlideUrl>) {
        val requestBuilder = Request.Builder().get().url(url.toStringUrl())
        for ((key, value) in url.headers) {
            requestBuilder.addHeader(key, value)
        }
        val request = requestBuilder.build()
        this.callback = callback
        call = client.newCall(request)
        call?.enqueue(this)
    }

    override fun onFailure(call: Call, e: IOException) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "OkHttp failed to obtain result", e)
        }
        callback?.onLoadFailed(e)
    }

    override fun onResponse(call: Call, response: Response) {
        when (val statusCode = response.code) {
            301, 302, 303, 307, 308 -> {
                response.close()
                if (--redirectCount >= 0) {
                    val redirectUrl = response.header(LOCATION)
                    if (Log.isLoggable(TAG, Log.DEBUG)) {
                        Log.d(TAG, "Redirect url retrieved: $redirectUrl")
                    }
                    url = GlideUrl(redirectUrl)
                    callback?.let { loadData(it) }
                } else {
                    callback?.onLoadFailed(RedirectException(statusCode, "Redirects too many times!"))
                }
                consumeResponse(response)
            }
            else -> consumeResponse(response)
        }
    }

    open fun consumeResponse(response: Response) {
        if (response.isSuccessful) {
            callback?.onDataReady(url)
        } else {
            callback?.onLoadFailed(HttpException(response.message, response.code))
        }
    }

    fun cleanup() {
        responseBody?.close()
        callback = null
    }

    fun cancel() {
        call?.cancel()
    }

    companion object {
        private const val TAG = "OkHttpRedirectUrl"
        private const val LOCATION = "Location"
        const val DEFAULT_MAX_REDIRECT = 5
    }

    init {
        if (url is RedirectGlideUrl) {
            redirectCount = (url as RedirectGlideUrl).maxRedirectCount
        }
    }

}