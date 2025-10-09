package com.batabumi.app.data.model

/**
 * Enum untuk material konstruksi yang didukung
 */
enum class ConstructionMaterial(val displayName: String, val keywords: List<String>) {
    KERAMIK(
        displayName = "Keramik",
        keywords = listOf(
            "keramik", "tile", "ubin", "lantai keramik", "dinding keramik",
            "keramik lantai", "keramik dinding", "granit", "marmer",
            "porcelain", "ceramic", "flooring", "wall tile"
        )
    ),
    KACA(
        displayName = "Kaca",
        keywords = listOf(
            "kaca", "glass", "jendela", "pintu kaca", "kaca jendela",
            "kaca pintu", "kaca tembus pandang", "kaca bening", "kaca cermin",
            "kaca tempered", "kaca laminated", "window", "glass door"
        )
    ),
    CAT_PLAFON_ATAP(
        displayName = "Cat Plafon Atap",
        keywords = listOf(
            "cat", "paint", "cat plafon", "cat atap", "cat dinding",
            "cat interior", "cat eksterior", "cat tembok", "cat kayu",
            "cat metal", "primer", "undercoat", "topcoat", "finishing",
            "plafon", "atap", "ceiling", "roof", "wall paint"
        )
    )
}

/**
 * Data class untuk menyimpan hasil analisis material
 */
data class MaterialAnalysis(
    val materialType: ConstructionMaterial,
    val confidence: Float, // 0.0 - 1.0
    val matchedKeywords: List<String>,
    val reasoning: String
)

/**
 * Data class untuk menyimpan summary konsultasi
 */
data class ConsultationSummary(
    val sessionId: String,
    val timestamp: Long,
    val userQuestions: List<String>,
    val aiResponses: List<String>,
    val materialAnalysis: MaterialAnalysis?,
    val consultationTopic: String,
    val keyPoints: List<String>,
    val recommendations: List<String>
)

/**
 * Data class untuk menyimpan history chat lengkap
 */
data class ChatHistory(
    val sessionId: String,
    val startTime: Long,
    val endTime: Long,
    val messages: List<ChatMessage>,
    val summary: ConsultationSummary?
)
