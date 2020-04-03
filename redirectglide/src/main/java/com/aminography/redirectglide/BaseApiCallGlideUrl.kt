package com.aminography.redirectglide

import com.aminography.redirectglide.OkHttpRedirectUrlFetcher.Companion.DEFAULT_MAX_REDIRECT
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.Headers
import java.net.URL

/**
 * An extension of [GlideUrl] which parses the Api result to extract the image url.
 */
abstract class BaseApiCallGlideUrl : RedirectGlideUrl {

    constructor(url: URL?) : super(url, DEFAULT_MAX_REDIRECT)

    constructor(url: String?) : super(url, DEFAULT_MAX_REDIRECT)

    constructor(url: URL?, headers: Headers?) : super(url, headers, DEFAULT_MAX_REDIRECT)

    constructor(url: String?, headers: Headers?) : super(url, headers, DEFAULT_MAX_REDIRECT)

    /**
     *
     * @param apiResult the string result of calling Api
     * @return extracted image url from the apiResult
     */
    abstract fun extractImageUrl(apiResult: String?): String?

}