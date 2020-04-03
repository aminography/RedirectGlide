package com.aminography.redirectglide.sampleapp

import com.aminography.redirectglide.BaseApiCallGlideUrl
import com.aminography.redirectglide.sampleapp.MyApiDataModel.Companion.getSourceUrl
import com.bumptech.glide.load.model.Headers
import java.net.URL

class MyApiCallGlideUrl : BaseApiCallGlideUrl {

    constructor(url: URL?) : super(url)

    constructor(url: String?) : super(url)

    constructor(url: URL?, headers: Headers?) : super(url, headers)

    constructor(url: String?, headers: Headers?) : super(url, headers)

    override fun extractImageUrl(apiResult: String?): String? {
        return getSourceUrl(apiResult)
    }
}