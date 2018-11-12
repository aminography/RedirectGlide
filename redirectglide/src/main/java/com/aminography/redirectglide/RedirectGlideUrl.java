package com.aminography.redirectglide;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;

import java.net.URL;

/**
 * An extension of {@link GlideUrl} which holds maximum number of possible redirection.
 */
public class RedirectGlideUrl extends GlideUrl {

    private int maxRedirectCount;

    public RedirectGlideUrl(URL url, int maxRedirectCount) {
        super(url);
        this.maxRedirectCount = maxRedirectCount;
    }

    public RedirectGlideUrl(String url, int maxRedirectCount) {
        super(url);
        this.maxRedirectCount = maxRedirectCount;
    }

    public RedirectGlideUrl(URL url, Headers headers, int maxRedirectCount) {
        super(url, headers);
        this.maxRedirectCount = maxRedirectCount;
    }

    public RedirectGlideUrl(String url, Headers headers, int maxRedirectCount) {
        super(url, headers);
        this.maxRedirectCount = maxRedirectCount;
    }

    public int getMaxRedirectCount() {
        return maxRedirectCount;
    }

}
