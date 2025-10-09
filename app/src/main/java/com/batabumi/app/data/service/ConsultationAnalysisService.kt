package com.batabumi.app.data.service

import com.batabumi.app.data.analyzer.MaterialAnalyzer
import com.batabumi.app.data.model.*
import com.batabumi.app.data.serializer.JsonSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Service untuk menganalisis dan menyimpan history konsultasi
 */
class ConsultationAnalysisService {
    
    private val materialAnalyzer = MaterialAnalyzer()
    private val jsonSerializer = JsonSerializer()
    
    /**
     * Menganalisis session chat dan menghasilkan JSON output
     */
    suspend fun analyzeSession(
        sessionId: String,
        messages: List<ChatMessage>
    ): String = withContext(Dispatchers.IO) {
        
        if (messages.isEmpty()) {
            return@withContext createEmptySessionJson(sessionId)
        }
        
        // Buat summary dari konsultasi
        val summary = materialAnalyzer.createSummary(sessionId, messages)
        
        // Buat ChatHistory
        val chatHistory = ChatHistory(
            sessionId = sessionId,
            startTime = messages.first().timestamp.time,
            endTime = messages.last().timestamp.time,
            messages = messages,
            summary = summary
        )
        
        // Serialize ke JSON
        jsonSerializer.serializeChatHistory(chatHistory)
    }
    
    /**
     * Menganalisis material dari teks konsultasi
     */
    suspend fun analyzeMaterialFromText(text: String): MaterialAnalysis? = withContext(Dispatchers.IO) {
        val mockMessage = ChatMessage(
            content = text,
            isFromUser = true,
            sessionId = "analysis"
        )
        
        materialAnalyzer.analyzeConsultation(listOf(mockMessage))
    }
    
    /**
     * Mendapatkan rekomendasi material berdasarkan analisis
     */
    fun getMaterialRecommendation(analysis: MaterialAnalysis?): String {
        return when {
            analysis == null -> "Tidak ada material yang teridentifikasi dari konsultasi"
            analysis.confidence < 0.3f -> "Konsultasi tidak spesifik untuk material tertentu"
            analysis.confidence >= 0.5f -> "Direkomendasikan: ${analysis.materialType.displayName} (${String.format("%.1f", analysis.confidence * 100)}% confidence)"
            else -> "Kemungkinan: ${analysis.materialType.displayName} (${String.format("%.1f", analysis.confidence * 100)}% confidence)"
        }
    }
    
    /**
     * Membuat JSON untuk session kosong
     */
    private fun createEmptySessionJson(sessionId: String): String {
        return """
        {
          "sessionId": "$sessionId",
          "startTime": "${System.currentTimeMillis()}",
          "endTime": "${System.currentTimeMillis()}",
          "duration": "0 menit",
          "messageCount": 0,
          "summary": null,
          "error": "Tidak ada pesan dalam session ini"
        }
        """.trimIndent()
    }
    
    /**
     * Mendapatkan statistik analisis material
     */
    suspend fun getMaterialStatistics(sessions: List<String>): Map<ConstructionMaterial, Int> = withContext(Dispatchers.IO) {
        val stats = mutableMapOf<ConstructionMaterial, Int>()
        
        ConstructionMaterial.values().forEach { material ->
            stats[material] = 0
        }
        
        // Implementasi untuk menghitung statistik dari multiple sessions
        // Ini akan diimplementasikan berdasarkan kebutuhan
        
        stats
    }
}
