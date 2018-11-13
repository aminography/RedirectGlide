package com.aminography.redirectglide;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;

import java.net.URL;

/**
 * An extension of {@link GlideUrl} which parses the Api result to extract the image url.
 */
public abstract class BaseApiCallGlideUrl extends RedirectGlideUrl {

    public BaseApiCallGlideUrl(URL url) {
        super(url, OkHttpRedirectUrlFetcher.DEFAULT_MAX_REDIRECT);
    }

    public BaseApiCallGlideUrl(String url) {
        super(url, OkHttpRedirectUrlFetcher.DEFAULT_MAX_REDIRECT);
    }

    public BaseApiCallGlideUrl(URL url, Headers headers) {
        super(url, headers, OkHttpRedirectUrlFetcher.DEFAULT_MAX_REDIRECT);
    }

    public BaseApiCallGlideUrl(String url, Headers headers) {
        super(url, headers, OkHttpRedirectUrlFetcher.DEFAULT_MAX_REDIRECT);
    }

    /**
     *
     * @param apiResult the string result of calling Api
     * @return extracted image url from the apiResult
     */
    public abstract String extractImageUrl(String apiResult);

}
