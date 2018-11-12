package com.aminography.redirectglide;

import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Fetches a direct url to the initial {@link GlideUrl} using the okhttp library.
 */
public class OkHttpRedirectUrlFetcher implements okhttp3.Callback {

    private static final String TAG = "OkHttpRedirectUrl";
    private static final String LOCATION = "Location";
    private static final int DEFAULT_MAX_REDIRECT = 5;

    private final Call.Factory client;
    private GlideUrl url;
    private DataFetcher.DataCallback<? super GlideUrl> callback;
    // call may be accessed on the main thread while the object is in use on other threads. All other
    // accesses to variables may occur on different threads, but only one at a time.
    private volatile Call call;
    private int redirectCount = DEFAULT_MAX_REDIRECT;

    // Public API.
    @SuppressWarnings("WeakerAccess")
    public OkHttpRedirectUrlFetcher(Call.Factory client, GlideUrl url) {
        this.client = client;
        this.url = url;
        if (url instanceof RedirectGlideUrl) {
            redirectCount = ((RedirectGlideUrl) url).getMaxRedirectCount();
        }
    }

    public void loadData(@NonNull final DataFetcher.DataCallback<? super GlideUrl> callback) {
        Request.Builder requestBuilder = new Request.Builder().get().url(url.toStringUrl());
        for (Map.Entry<String, String> headerEntry : url.getHeaders().entrySet()) {
            String key = headerEntry.getKey();
            requestBuilder.addHeader(key, headerEntry.getValue());
        }
        Request request = requestBuilder.build();
        this.callback = callback;

        call = client.newCall(request);
        call.enqueue(this);
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "OkHttp failed to obtain result", e);
        }
        callback.onLoadFailed(e);
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) {
        int statusCode = response.code();
        switch (statusCode) {
            case 301: // Moved Permanently
            case 302: // Found (Previously "Moved temporarily")
            case 303: // See Other (since HTTP/1.1)
            case 307: // Temporary Redirect (since HTTP/1.1)
            case 308: // Permanent Redirect (RFC 7538)
                response.close();
                if (--redirectCount >= 0) {
                    String redirectUrl = response.header(LOCATION);
                    if (Log.isLoggable(TAG, Log.DEBUG)) {
                        Log.d(TAG, "Redirect url retrieved: " + redirectUrl);
                    }
                    url = new GlideUrl(redirectUrl);
                    loadData(callback);
                } else {
                    callback.onLoadFailed(new RedirectException(statusCode, "Redirects too many times!"));
                }
            default:
                if (response.isSuccessful()) {
                    callback.onDataReady(url);
                } else {
                    callback.onLoadFailed(new HttpException(response.message(), response.code()));
                }
        }
    }

    void cleanup() {
        callback = null;
    }

    void cancel() {
        Call local = call;
        if (local != null) {
            local.cancel();
        }
    }

}
