package com.aminography.redirectglide.sampleapp

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class MyApiDataModel {

    @SerializedName("media_details")
    var mediaDetails: MediaDetails? = null

    inner class MediaDetails {
        @SerializedName("sizes")
        var sizes: Sizes? = null
    }

    inner class Sizes {
        @SerializedName("full")
        var full: Full? = null
    }

    inner class Full {
        @SerializedName("source_url")
        var sourceUrl: String? = null
    }

    companion object {
        @JvmStatic
        fun getSourceUrl(json: String?): String? {
            return Gson().fromJson(json, MyApiDataModel::class.java).mediaDetails?.sizes?.full?.sourceUrl
        }
    }
}