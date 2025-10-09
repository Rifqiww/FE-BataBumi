package com.batabumi.app.data.service

import com.batabumi.app.data.model.*
import com.batabumi.app.data.serializer.JsonSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Service untuk menangani material selection dan menghasilkan JSON output
 */
class MaterialSelectionService {
    
    private val jsonSerializer = JsonSerializer()
    
    /**
     * Menghasilkan JSON output berdasarkan material yang dipilih
     */
    suspend fun generateMaterialJson(
        sessionId: String,
        selectedMaterial: ConstructionMaterial,
        userQuestions: List<String> = emptyList(),
        aiResponses: List<String> = emptyList()
    ): String = withContext(Dispatchers.IO) {
        
        // Buat material analysis berdasarkan pilihan user
        val materialAnalysis = MaterialAnalysis(
            materialType = selectedMaterial,
            confidence = 1.0f, // 100% karena dipilih langsung
            matchedKeywords = selectedMaterial.keywords.take(3),
            reasoning = "Material dipilih langsung oleh user: ${selectedMaterial.displayName}"
        )
        
        // Buat summary
        val summary = ConsultationSummary(
            sessionId = sessionId,
            timestamp = System.currentTimeMillis(),
            userQuestions = userQuestions,
            aiResponses = aiResponses,
            materialAnalysis = materialAnalysis,
            consultationTopic = "Konsultasi ${selectedMaterial.displayName}",
            keyPoints = generateKeyPoints(selectedMaterial),
            recommendations = generateRecommendations(selectedMaterial)
        )
        
        // Buat chat history
        val chatHistory = ChatHistory(
            sessionId = sessionId,
            startTime = System.currentTimeMillis(),
            endTime = System.currentTimeMillis(),
            messages = emptyList(), // Kosong karena menggunakan button selection
            summary = summary
        )
        
        // Serialize ke JSON
        jsonSerializer.serializeChatHistory(chatHistory)
    }
    
    /**
     * Generate key points berdasarkan material yang dipilih
     */
    private fun generateKeyPoints(material: ConstructionMaterial): List<String> {
        return when (material) {
            ConstructionMaterial.KERAMIK -> listOf(
                "Pilih keramik dengan ketebalan minimal 8mm untuk daya tahan optimal",
                "Perhatikan PEI rating untuk menentukan tingkat kekerasan sesuai kebutuhan",
                "Pilih keramik dengan tingkat penyerapan air rendah (kurang dari 3%)"
            )
            ConstructionMaterial.KACA -> listOf(
                "Kaca tempered lebih aman karena tidak pecah menjadi serpihan tajam",
                "Kaca laminated memiliki lapisan film yang mencegah pecah total",
                "Pembersihan rutin dengan cairan khusus kaca sangat penting"
            )
            ConstructionMaterial.CAT_PLAFON_ATAP -> listOf(
                "Cat plafon harus memiliki formula anti jamur untuk mencegah tumbuhnya jamur",
                "Daya tutup cat mempengaruhi jumlah cat yang dibutuhkan",
                "Pengecatan plafon memerlukan minimal 2 lapisan untuk hasil optimal"
            )
        }
    }
    
    /**
     * Generate recommendations berdasarkan material yang dipilih
     */
    private fun generateRecommendations(material: ConstructionMaterial): List<String> {
        return when (material) {
            ConstructionMaterial.KERAMIK -> listOf(
                "Gunakan keramik dengan ukuran 40x40cm atau 60x60cm untuk ruang tamu",
                "Pilih keramik dengan tekstur anti slip untuk area basah seperti kamar mandi",
                "Pastikan menggunakan nat yang berkualitas untuk mencegah retak"
            )
            ConstructionMaterial.KACA -> listOf(
                "Gunakan kaca tempered untuk jendela dengan risiko tinggi",
                "Pilih kaca dengan ketebalan minimal 6mm untuk jendela standar",
                "Lakukan pembersihan kaca minimal 2 kali seminggu"
            )
            ConstructionMaterial.CAT_PLAFON_ATAP -> listOf(
                "Gunakan cat dengan daya tutup tinggi untuk menghemat biaya",
                "Pilih cat dengan formula anti jamur untuk area lembab",
                "Lakukan pengecatan dalam kondisi cuaca yang baik"
            )
        }
    }
    
    /**
     * Mendapatkan template pertanyaan berdasarkan material
     */
    fun getMaterialQuestions(material: ConstructionMaterial): List<String> {
        return when (material) {
            ConstructionMaterial.KERAMIK -> listOf(
                "Bagaimana cara memilih keramik yang berkualitas?",
                "Berapa biaya pemasangan keramik per meter?",
                "Apa perbedaan keramik granit dan marmer?",
                "Bagaimana cara merawat keramik lantai?"
            )
            ConstructionMaterial.KACA -> listOf(
                "Jenis kaca apa yang cocok untuk jendela rumah?",
                "Bagaimana cara merawat kaca agar tetap bening?",
                "Apa keuntungan kaca tempered vs kaca biasa?",
                "Berapa biaya pemasangan kaca jendela?"
            )
            ConstructionMaterial.CAT_PLAFON_ATAP -> listOf(
                "Berapa biaya pengecatan plafon rumah?",
                "Jenis cat apa yang cocok untuk plafon?",
                "Bagaimana cara menghitung kebutuhan cat?",
                "Apa perbedaan cat interior dan eksterior?"
            )
        }
    }
    
    /**
     * Mendapatkan informasi material
     */
    fun getMaterialInfo(material: ConstructionMaterial): String {
        return when (material) {
            ConstructionMaterial.KERAMIK -> 
                "Keramik adalah material finishing yang populer untuk lantai dan dinding. Tersedia dalam berbagai ukuran, warna, dan tekstur."
            ConstructionMaterial.KACA -> 
                "Kaca digunakan untuk jendela, pintu, dan partisi. Memiliki berbagai jenis seperti tempered, laminated, dan kaca bening."
            ConstructionMaterial.CAT_PLAFON_ATAP -> 
                "Cat digunakan untuk finishing plafon dan atap. Memiliki berbagai formula seperti anti jamur, anti noda, dan tahan cuaca."
        }
    }
}
