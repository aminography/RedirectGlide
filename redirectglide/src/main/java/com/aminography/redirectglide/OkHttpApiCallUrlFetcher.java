package com.aminography.redirectglide;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.model.GlideUrl;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Fetches an {@link GlideUrl} using the okhttp library.
 */
public class OkHttpApiCallUrlFetcher extends OkHttpRedirectUrlFetcher implements okhttp3.Callback {

    public OkHttpApiCallUrlFetcher(Call.Factory client, BaseApiCallGlideUrl url) {
        super(client, url);
    }

    @Override
    public void consumeResponse(@NonNull Response response) {
        responseBody = response.body();
        if (response.isSuccessful() && responseBody != null) {
            try {
                String sourceUrl = ((BaseApiCallGlideUrl) url).extractImageUrl(responseBody.string());
                callback.onDataReady(new GlideUrl(sourceUrl));
            } catch (IOException e) {
                callback.onLoadFailed(new HttpException(response.message(), response.code()));
                e.printStackTrace();
            }
        } else {
            callback.onLoadFailed(new HttpException(response.message(), response.code()));
        }
    }

}
