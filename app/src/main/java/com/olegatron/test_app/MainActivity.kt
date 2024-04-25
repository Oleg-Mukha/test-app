package com.olegatron.test_app

import okhttp3.OkHttpClient
import okhttp3.Request
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = findViewById<TextView>(R.id.hello);
        text.text = fetchHtml("https://edoko.app/")

        fetchHtml("https://edoko.app/")
    }

    fun fetchHtml(url: String) : String {
        // Create OkHttpClient instance
        val client = OkHttpClient()
        var htmlContent = ""
        // Build the request
        val request = Request.Builder()
            .url(url)
            .build()

        // Enqueue the request
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()  // Handle failure
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    htmlContent = response.body?.string().toString()  // Get the HTML content
                    // Handle the HTML content here, e.g., print or use in UI
                    println(htmlContent)
                }
            }
        })
        return htmlContent
    }
}