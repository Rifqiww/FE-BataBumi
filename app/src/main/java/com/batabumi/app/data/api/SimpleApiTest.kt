package com.batabumi.app.data.api

import android.util.Log
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response

object SimpleApiTest {
    private const val TAG = "SimpleApiTest"
    
    fun testGroqApi(apiKey: String) {
        runBlocking {
            try {
                val client = OkHttpClient()
                
                val json = """
                {
                    "messages": [
                        {
                            "role": "user",
                            "content": "Hello, this is a test message"
                        }
                    ],
                    "model": "llama-3.1-8b-instant",
                    "temperature": 0.7,
                    "max_tokens": 50
                }
                """.trimIndent()
                
                val request = Request.Builder()
                    .url("https://api.groq.com/openai/v1/chat/completions")
                    .addHeader("Authorization", "Bearer $apiKey")
                    .addHeader("Content-Type", "application/json")
                    .post(json.toRequestBody("application/json".toMediaType()))
                    .build()
                
                val response: Response = client.newCall(request).execute()
                
                Log.d(TAG, "Response code: ${response.code}")
                Log.d(TAG, "Response body: ${response.body?.string()}")
                
                response.close()
                
            } catch (e: Exception) {
                Log.e(TAG, "Test failed: ${e.message}")
            }
        }
    }
}
