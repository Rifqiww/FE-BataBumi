package com.batabumi.app.data.serializer

import com.batabumi.app.data.model.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Serializer untuk mengkonversi data konsultasi ke JSON
 */
class JsonSerializer {
    
    private val gson: Gson = GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()
    
    /**
     * Mengkonversi ChatHistory ke JSON
     */
    fun serializeChatHistory(chatHistory: ChatHistory): String {
        val jsonData = ChatHistoryJson(
            sessionId = chatHistory.sessionId,
            startTime = formatTimestamp(chatHistory.startTime),
            endTime = formatTimestamp(chatHistory.endTime),
            duration = calculateDuration(chatHistory.startTime, chatHistory.endTime),
            messageCount = chatHistory.messages.size,
            summary = chatHistory.summary?.let { serializeSummary(it) }
        )
        
        return gson.toJson(jsonData)
    }
    
    /**
     * Mengkonversi ConsultationSummary ke JSON
     */
    fun serializeSummary(summary: ConsultationSummary): SummaryJson {
        return SummaryJson(
            sessionId = summary.sessionId,
            timestamp = formatTimestamp(summary.timestamp),
            consultationTopic = summary.consultationTopic,
            materialAnalysis = summary.materialAnalysis?.let { serializeMaterialAnalysis(it) },
            userQuestions = summary.userQuestions,
            aiResponses = summary.aiResponses,
            keyPoints = summary.keyPoints,
            recommendations = summary.recommendations
        )
    }
    
    /**
     * Mengkonversi MaterialAnalysis ke JSON
     */
    fun serializeMaterialAnalysis(analysis: MaterialAnalysis): MaterialAnalysisJson {
        return MaterialAnalysisJson(
            materialType = analysis.materialType.name,
            materialDisplayName = analysis.materialType.displayName,
            confidence = String.format("%.2f", analysis.confidence * 100) + "%",
            matchedKeywords = analysis.matchedKeywords,
            reasoning = analysis.reasoning,
            isRecommended = analysis.confidence > 0.5f
        )
    }
    
    /**
     * Format timestamp ke string yang readable
     */
    private fun formatTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
    
    /**
     * Hitung durasi dalam menit
     */
    private fun calculateDuration(startTime: Long, endTime: Long): String {
        val durationMs = endTime - startTime
        val durationMinutes = durationMs / (1000 * 60)
        return "${durationMinutes} menit"
    }
}

/**
 * Data class untuk JSON output ChatHistory
 */
data class ChatHistoryJson(
    val sessionId: String,
    val startTime: String,
    val endTime: String,
    val duration: String,
    val messageCount: Int,
    val summary: SummaryJson?
)

/**
 * Data class untuk JSON output Summary
 */
data class SummaryJson(
    val sessionId: String,
    val timestamp: String,
    val consultationTopic: String,
    val materialAnalysis: MaterialAnalysisJson?,
    val userQuestions: List<String>,
    val aiResponses: List<String>,
    val keyPoints: List<String>,
    val recommendations: List<String>
)

/**
 * Data class untuk JSON output MaterialAnalysis
 */
data class MaterialAnalysisJson(
    val materialType: String,
    val materialDisplayName: String,
    val confidence: String,
    val matchedKeywords: List<String>,
    val reasoning: String,
    val isRecommended: Boolean
)
