package com.song3.cozytrain

import android.annotation.SuppressLint
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webView)

        webView.settings.run {
            // 웹뷰 자바스크립트 허용
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            setSupportMultipleWindows(true)
        }

        webView.webViewClient = SslWebViewConnect()
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl(BuildConfig.COZY_TRAIN_URL)
    }

    override fun onBackPressed() { // 뒤로가기 기능 구현

        val myWebView: WebView = findViewById(R.id.webView)
        if (myWebView.canGoBack()) {
            myWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }


}