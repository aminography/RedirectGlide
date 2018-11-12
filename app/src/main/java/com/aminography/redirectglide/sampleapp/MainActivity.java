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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);

//        String sourceUrl = "https://goo.gl/9ctWqR";
        String sourceUrl = "https://bit.ly/2zeMrFB";

        textView.setText(String.format(Locale.getDefault(), "Source Url: %s", sourceUrl));

        GlideApp.with(getApplicationContext())
                .load(sourceUrl)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(imageView);

//        Glide.with(getApplicationContext()).load(sourceUrl).into(imageView);
    }

}
