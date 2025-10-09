package com.batabumi.app.data.api

import com.batabumi.app.data.model.GroqRequest
import com.batabumi.app.data.model.GroqResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GroqApiService {
    @POST("v1/chat/completions")
    suspend fun sendMessage(
        @Header("Authorization") authorization: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: GroqRequest
    ): Response<GroqResponse>
}
