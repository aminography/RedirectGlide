package com.aminography.redirectglide.sampleapp;

import com.aminography.redirectglide.BaseApiCallGlideUrl;
import com.bumptech.glide.load.model.Headers;

import java.net.URL;

public class MyApiCallGlideUrl extends BaseApiCallGlideUrl {

    public MyApiCallGlideUrl(URL url) {
        super(url);
    }

    public MyApiCallGlideUrl(String url) {
        super(url);
    }

    public MyApiCallGlideUrl(URL url, Headers headers) {
        super(url, headers);
    }

    public MyApiCallGlideUrl(String url, Headers headers) {
        super(url, headers);
    }

    @Override
    public String extractImageUrl(String apiResult) {
        return MyApiDataModel.getSourceUrl(apiResult);
    }

}
