package com.aminography.redirectglide.sampleapp;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class MyApiDataModel {

    @SerializedName("media_details")
    MediaDetails mediaDetails;

    public static String getSourceUrl(String json) {
        return new Gson().fromJson(json, MyApiDataModel.class).mediaDetails.sizes.full.sourceUrl;
    }

    public class MediaDetails {
        @SerializedName("sizes")
        Sizes sizes;
    }

    public class Sizes {
        @SerializedName("full")
        Full full;
    }

    public class Full {
        @SerializedName("source_url")
        String sourceUrl;
    }

}
