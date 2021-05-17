package com.eminsimsek.newsfeedapp.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eminsimsek.newsfeedapp.R
import com.eminsimsek.newsfeedapp.model.ArticlesItem
import kotlinx.android.synthetic.main.activity_news_source.*

class NewsSource : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_source)

        val newsLink : String? = intent.getStringExtra("chosenNewsLink")
        webViewSource.settings.javaScriptEnabled = true
        if(newsLink != null)
        webViewSource.loadUrl(newsLink)

        imageViewBackSource.setOnClickListener {
            onBackPressed()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}