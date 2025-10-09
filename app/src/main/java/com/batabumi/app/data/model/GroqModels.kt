package com.batabumi.app.data.model

import com.google.gson.annotations.SerializedName

data class GroqRequest(
    val messages: List<GroqMessage>,
    val model: String = "llama-3.1-8b-instant",
    val temperature: Double = 0.7,
    val max_tokens: Int = 1024,
    val top_p: Double = 1.0,
    val stream: Boolean = false
)

data class GroqMessage(
    val role: String,
    val content: String
)

data class GroqResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val choices: List<GroqChoice>,
    val usage: GroqUsage
)

data class GroqChoice(
    val index: Int,
    val message: GroqMessage,
    val finish_reason: String
)

data class GroqUsage(
    val prompt_tokens: Int,
    val completion_tokens: Int,
    val total_tokens: Int
)
