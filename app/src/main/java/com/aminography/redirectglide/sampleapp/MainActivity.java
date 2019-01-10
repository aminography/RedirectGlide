package com.aminography.redirectglide.sampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.aminography.redirectglide.GlideApp;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        loadIndirectUrl();
//        loadApiCallUrl();
    }

    private void loadIndirectUrl() {
        String sourceUrl = "https://goo.gl/9ctWqR";
//        String sourceUrl = "https://bit.ly/2zeMrFB";
        textView.setText(String.format(Locale.getDefault(), "Source Url: %s", sourceUrl));

        GlideApp.with(getApplicationContext())
                .load(sourceUrl)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)) // every time download the image for test
                .into(imageView);

//        Glide.with(getApplicationContext()).load(sourceUrl).into(imageView);
    }

    private void loadApiCallUrl() {
        String sourceUrl = "https://www.myfitbytes.com/wp-json/wp/v2/media/2811";
        textView.setText(String.format(Locale.getDefault(), "Source Url: %s", sourceUrl));

        GlideApp.with(getApplicationContext())
                .load(new MyApiCallGlideUrl(sourceUrl))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)) // every time download the image for test
                .into(imageView);
    }

}
