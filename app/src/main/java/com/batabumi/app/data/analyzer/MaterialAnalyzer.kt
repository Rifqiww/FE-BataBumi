package com.batabumi.app.data.analyzer

import com.batabumi.app.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Analyzer untuk menganalisis konsultasi dan menentukan material konstruksi
 */
class MaterialAnalyzer {
    
    /**
     * Menganalisis history chat untuk menentukan material yang dibahas
     */
    suspend fun analyzeConsultation(
        messages: List<ChatMessage>
    ): MaterialAnalysis? = withContext(Dispatchers.IO) {
        
        // Gabungkan semua pesan user menjadi satu teks
        val userMessages = messages
            .filter { it.isFromUser }
            .joinToString(" ") { it.content.lowercase() }
        
        if (userMessages.isBlank()) return@withContext null
        
        // Analisis untuk setiap material
        val analysisResults = ConstructionMaterial.values().map { material ->
            val matchedKeywords = material.keywords.filter { keyword ->
                userMessages.contains(keyword.lowercase())
            }
            
            val confidence = if (material.keywords.isNotEmpty()) {
                matchedKeywords.size.toFloat() / material.keywords.size
            } else 0f
            
            MaterialAnalysis(
                materialType = material,
                confidence = confidence,
                matchedKeywords = matchedKeywords,
                reasoning = generateReasoning(material, matchedKeywords, userMessages)
            )
        }
        
        // Pilih material dengan confidence tertinggi
        val bestMatch = analysisResults.maxByOrNull { it.confidence }
        
        // Hanya return jika confidence > 0.3 (30%)
        if (bestMatch?.confidence ?: 0f > 0.3f) {
            bestMatch
        } else null
    }
    
    /**
     * Membuat summary dari konsultasi
     */
    suspend fun createSummary(
        sessionId: String,
        messages: List<ChatMessage>
    ): ConsultationSummary = withContext(Dispatchers.IO) {
        
        val userQuestions = messages
            .filter { it.isFromUser }
            .map { it.content }
        
        val aiResponses = messages
            .filter { !it.isFromUser }
            .map { it.content }
        
        val materialAnalysis = analyzeConsultation(messages)
        
        val consultationTopic = determineTopic(userQuestions)
        val keyPoints = extractKeyPoints(aiResponses)
        val recommendations = extractRecommendations(aiResponses)
        
        ConsultationSummary(
            sessionId = sessionId,
            timestamp = System.currentTimeMillis(),
            userQuestions = userQuestions,
            aiResponses = aiResponses,
            materialAnalysis = materialAnalysis,
            consultationTopic = consultationTopic,
            keyPoints = keyPoints,
            recommendations = recommendations
        )
    }
    
    /**
     * Menentukan topik konsultasi
     */
    private fun determineTopic(questions: List<String>): String {
        val allText = questions.joinToString(" ").lowercase()
        
        return when {
            allText.contains("estimasi") || allText.contains("biaya") || allText.contains("harga") -> 
                "Estimasi Biaya"
            allText.contains("keselamatan") || allText.contains("k3") || allText.contains("safety") -> 
                "Keselamatan Kerja"
            allText.contains("material") || allText.contains("bahan") -> 
                "Pemilihan Material"
            allText.contains("rencana") || allText.contains("jadwal") || allText.contains("timeline") -> 
                "Perencanaan Proyek"
            allText.contains("risiko") || allText.contains("bahaya") -> 
                "Manajemen Risiko"
            allText.contains("kualitas") || allText.contains("standar") -> 
                "Kontrol Kualitas"
            else -> "Konsultasi Umum"
        }
    }
    
    /**
     * Mengekstrak poin-poin penting dari respons AI
     */
    private fun extractKeyPoints(responses: List<String>): List<String> {
        val keyPoints = mutableListOf<String>()
        
        responses.forEach { response ->
            // Cari kalimat yang mengandung kata kunci penting
            val sentences = response.split(".", "!", "?")
            sentences.forEach { sentence ->
                val trimmed = sentence.trim()
                if (trimmed.length > 20 && (
                    trimmed.contains("penting") ||
                    trimmed.contains("perlu") ||
                    trimmed.contains("harus") ||
                    trimmed.contains("disarankan") ||
                    trimmed.contains("dianjurkan")
                )) {
                    keyPoints.add(trimmed)
                }
            }
        }
        
        return keyPoints.take(5) // Maksimal 5 poin
    }
    
    /**
     * Mengekstrak rekomendasi dari respons AI
     */
    private fun extractRecommendations(responses: List<String>): List<String> {
        val recommendations = mutableListOf<String>()
        
        responses.forEach { response ->
            val sentences = response.split(".", "!", "?")
            sentences.forEach { sentence ->
                val trimmed = sentence.trim()
                if (trimmed.length > 15 && (
                    trimmed.contains("rekomendasi") ||
                    trimmed.contains("saran") ||
                    trimmed.contains("sebaiknya") ||
                    trimmed.contains("disarankan") ||
                    trimmed.contains("dianjurkan")
                )) {
                    recommendations.add(trimmed)
                }
            }
        }
        
        return recommendations.take(3) // Maksimal 3 rekomendasi
    }
    
    /**
     * Generate reasoning untuk analisis material
     */
    private fun generateReasoning(
        material: ConstructionMaterial,
        matchedKeywords: List<String>,
        userText: String
    ): String {
        return when {
            matchedKeywords.isEmpty() -> 
                "Tidak ditemukan kata kunci yang relevan dengan ${material.displayName}"
            matchedKeywords.size == 1 -> 
                "Ditemukan 1 kata kunci: ${matchedKeywords.first()}"
            else -> 
                "Ditemukan ${matchedKeywords.size} kata kunci: ${matchedKeywords.joinToString(", ")}"
        }
    }
}
