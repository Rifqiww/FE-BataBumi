package com.batabumi.app.data.api

import android.util.Log
import kotlinx.coroutines.runBlocking

object GroqApiTest {
    private const val TAG = "GroqApiTest"
    
    fun testApiKey(apiKey: String) {
        runBlocking {
            try {
                val testRequest = com.batabumi.app.data.model.GroqRequest(
                    messages = listOf(
                        com.batabumi.app.data.model.GroqMessage(
                            role = "user",
                            content = "Hello, test message"
                        )
                    ),
                    model = "llama3-8b-8192",
                    temperature = 0.7,
                    max_tokens = 50
                )
                
                val response = GroqApiClient.apiService.sendMessage(
                    authorization = "Bearer $apiKey",
                    request = testRequest
                )
                
                Log.d(TAG, "Response code: ${response.code()}")
                Log.d(TAG, "Response body: ${response.body()}")
                Log.d(TAG, "Error body: ${response.errorBody()?.string()}")
                
            } catch (e: Exception) {
                Log.e(TAG, "Test failed: ${e.message}")
            }
        }
    }
}
