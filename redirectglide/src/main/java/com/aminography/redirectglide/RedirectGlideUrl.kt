package com.aminography.redirectglide

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.Headers
import java.net.URL

/**
 * An extension of [GlideUrl] which holds maximum number of possible redirection.
 */
open class RedirectGlideUrl : GlideUrl {

    var maxRedirectCount: Int
        private set

    constructor(url: URL?, maxRedirectCount: Int) : super(url) {
        this.maxRedirectCount = maxRedirectCount
    }

    constructor(url: String?, maxRedirectCount: Int) : super(url) {
        this.maxRedirectCount = maxRedirectCount
    }

    constructor(url: URL?, headers: Headers?, maxRedirectCount: Int) : super(url, headers) {
        this.maxRedirectCount = maxRedirectCount
    }

    constructor(url: String?, headers: Headers?, maxRedirectCount: Int) : super(url, headers) {
        this.maxRedirectCount = maxRedirectCount
    }

}