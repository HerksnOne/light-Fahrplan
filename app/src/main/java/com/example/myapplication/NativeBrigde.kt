package com.example.myapplication

import android.util.Base64
import android.webkit.JavascriptInterface
import android.webkit.WebView
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class NativeBridge(private val webView: WebView) {
    private val executor = Executors.newFixedThreadPool(3)

    @JavascriptInterface
    fun request(id: String, method: String, url: String, headersJson: String, body: String) {
        executor.execute {
            var status = 0
            var text: String
            try {
                val conn = URL(url).openConnection() as HttpURLConnection
                conn.requestMethod = method
                conn.connectTimeout = 15000
                conn.readTimeout = 20000
                conn.setRequestProperty("Accept-Language", "de")
                conn.setRequestProperty("User-Agent", "zug-light-phone")
                val headers = JSONObject(headersJson)
                headers.keys().forEach { key -> conn.setRequestProperty(key, headers.getString(key)) }
                if (body.isNotEmpty()) {
                    conn.doOutput = true
                    conn.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }
                }
                status = conn.responseCode
                val stream = if (status in 200..299) conn.inputStream else conn.errorStream
                text = stream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() } ?: ""
            } catch (e: Exception) {
                text = e.toString()
            }
            val b64 = Base64.encodeToString(text.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
            webView.post {
                webView.evaluateJavascript("window.__nativeResponse('$id',$status,'$b64')", null)
            }
        }
    }
}