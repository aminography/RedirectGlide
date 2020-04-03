package com.aminography.redirectglide.sampleapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aminography.redirectglide.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadIndirectUrl()
//        loadApiCallUrl();
    }

    private fun loadIndirectUrl() {
        val sourceUrl = "https://goo.gl/9ctWqR"
//        String sourceUrl = "https://bit.ly/2zeMrFB";
        textView.text = String.format(Locale.getDefault(), "Source Url: %s", sourceUrl)

        GlideApp.with(applicationContext)
            .load(sourceUrl)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)) // every time download the image for test
            .into(imageView)

//        Glide.with(getApplicationContext()).load(sourceUrl).into(imageView);
    }

    private fun loadApiCallUrl() {
        val sourceUrl = "https://www.myfitbytes.com/wp-json/wp/v2/media/2811"
        textView.text = String.format(Locale.getDefault(), "Source Url: %s", sourceUrl)

        GlideApp.with(applicationContext)
            .load(MyApiCallGlideUrl(sourceUrl))
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)) // every time download the image for test
            .into(imageView)
    }
}