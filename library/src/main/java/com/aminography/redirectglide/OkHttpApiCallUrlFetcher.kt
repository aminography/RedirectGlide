package com.aminography.redirectglide

import com.bumptech.glide.load.HttpException
import com.bumptech.glide.load.model.GlideUrl
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * Fetches an [GlideUrl] using the okhttp library.
 */
class OkHttpApiCallUrlFetcher(
    client: Call.Factory,
    url: BaseApiCallGlideUrl
) : OkHttpRedirectUrlFetcher(client, url), Callback {

    override fun consumeResponse(response: Response) {
        responseBody = response.body
        if (response.isSuccessful && responseBody != null) {
            try {
                val sourceUrl = (url as BaseApiCallGlideUrl).extractImageUrl(responseBody?.string())
                callback?.onDataReady(GlideUrl(sourceUrl))
            } catch (e: IOException) {
                callback?.onLoadFailed(HttpException(response.message, response.code))
                e.printStackTrace()
            }
        } else {
            callback?.onLoadFailed(HttpException(response.message, response.code))
        }
    }

}