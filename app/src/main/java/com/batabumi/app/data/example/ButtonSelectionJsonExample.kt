package com.batabumi.app.data.example

/**
 * Contoh output JSON dari button selection (bukan session chat)
 */
object ButtonSelectionJsonExample {
    
    /**
     * Contoh JSON untuk KERAMIK (button selection)
     */
    val KERAMIK_BUTTON_JSON = """
    {
      "sessionId": "session_keramik_001",
      "startTime": "2024-01-15 10:30:00",
      "endTime": "2024-01-15 10:30:00",
      "duration": "0 menit",
      "messageCount": 0,
      "summary": {
        "sessionId": "session_keramik_001",
        "timestamp": "2024-01-15 10:30:00",
        "consultationTopic": "Konsultasi Keramik",
        "materialAnalysis": {
          "materialType": "KERAMIK",
          "materialDisplayName": "Keramik",
          "confidence": "100.00%",
          "matchedKeywords": [
            "keramik",
            "tile",
            "ubin"
          ],
          "reasoning": "Material dipilih langsung oleh user: Keramik",
          "isRecommended": true
        },
        "userQuestions": [],
        "aiResponses": [],
        "keyPoints": [
          "Pilih keramik dengan ketebalan minimal 8mm untuk daya tahan optimal",
          "Perhatikan PEI rating untuk menentukan tingkat kekerasan sesuai kebutuhan",
          "Pilih keramik dengan tingkat penyerapan air rendah (kurang dari 3%)"
        ],
        "recommendations": [
          "Gunakan keramik dengan ukuran 40x40cm atau 60x60cm untuk ruang tamu",
          "Pilih keramik dengan tekstur anti slip untuk area basah seperti kamar mandi",
          "Pastikan menggunakan nat yang berkualitas untuk mencegah retak"
        ]
      }
    }
    """.trimIndent()
    
    /**
     * Contoh JSON untuk KACA (button selection)
     */
    val KACA_BUTTON_JSON = """
    {
      "sessionId": "session_kaca_002",
      "startTime": "2024-01-15 14:20:00",
      "endTime": "2024-01-15 14:20:00",
      "duration": "0 menit",
      "messageCount": 0,
      "summary": {
        "sessionId": "session_kaca_002",
        "timestamp": "2024-01-15 14:20:00",
        "consultationTopic": "Konsultasi Kaca",
        "materialAnalysis": {
          "materialType": "KACA",
          "materialDisplayName": "Kaca",
          "confidence": "100.00%",
          "matchedKeywords": [
            "kaca",
            "glass",
            "jendela"
          ],
          "reasoning": "Material dipilih langsung oleh user: Kaca",
          "isRecommended": true
        },
        "userQuestions": [],
        "aiResponses": [],
        "keyPoints": [
          "Kaca tempered lebih aman karena tidak pecah menjadi serpihan tajam",
          "Kaca laminated memiliki lapisan film yang mencegah pecah total",
          "Pembersihan rutin dengan cairan khusus kaca sangat penting"
        ],
        "recommendations": [
          "Gunakan kaca tempered untuk jendela dengan risiko tinggi",
          "Pilih kaca dengan ketebalan minimal 6mm untuk jendela standar",
          "Lakukan pembersihan kaca minimal 2 kali seminggu"
        ]
      }
    }
    """.trimIndent()
    
    /**
     * Contoh JSON untuk CAT_PLAFON_ATAP (button selection)
     */
    val CAT_PLAFON_ATAP_BUTTON_JSON = """
    {
      "sessionId": "session_cat_003",
      "startTime": "2024-01-15 16:00:00",
      "endTime": "2024-01-15 16:00:00",
      "duration": "0 menit",
      "messageCount": 0,
      "summary": {
        "sessionId": "session_cat_003",
        "timestamp": "2024-01-15 16:00:00",
        "consultationTopic": "Konsultasi Cat Plafon Atap",
        "materialAnalysis": {
          "materialType": "CAT_PLAFON_ATAP",
          "materialDisplayName": "Cat Plafon Atap",
          "confidence": "100.00%",
          "matchedKeywords": [
            "cat",
            "paint",
            "plafon"
          ],
          "reasoning": "Material dipilih langsung oleh user: Cat Plafon Atap",
          "isRecommended": true
        },
        "userQuestions": [],
        "aiResponses": [],
        "keyPoints": [
          "Cat plafon harus memiliki formula anti jamur untuk mencegah tumbuhnya jamur",
          "Daya tutup cat mempengaruhi jumlah cat yang dibutuhkan",
          "Pengecatan plafon memerlukan minimal 2 lapisan untuk hasil optimal"
        ],
        "recommendations": [
          "Gunakan cat dengan daya tutup tinggi untuk menghemat biaya",
          "Pilih cat dengan formula anti jamur untuk area lembab",
          "Lakukan pengecatan dalam kondisi cuaca yang baik"
        ]
      }
    }
    """.trimIndent()
    
    /**
     * Template pertanyaan untuk setiap material
     */
    val MATERIAL_QUESTIONS = mapOf(
        "KERAMIK" to listOf(
            "Bagaimana cara memilih keramik yang berkualitas?",
            "Berapa biaya pemasangan keramik per meter?",
            "Apa perbedaan keramik granit dan marmer?",
            "Bagaimana cara merawat keramik lantai?"
        ),
        "KACA" to listOf(
            "Jenis kaca apa yang cocok untuk jendela rumah?",
            "Bagaimana cara merawat kaca agar tetap bening?",
            "Apa keuntungan kaca tempered vs kaca biasa?",
            "Berapa biaya pemasangan kaca jendela?"
        ),
        "CAT_PLAFON_ATAP" to listOf(
            "Berapa biaya pengecatan plafon rumah?",
            "Jenis cat apa yang cocok untuk plafon?",
            "Bagaimana cara menghitung kebutuhan cat?",
            "Apa perbedaan cat interior dan eksterior?"
        )
    )
    
    /**
     * Informasi material
     */
    val MATERIAL_INFO = mapOf(
        "KERAMIK" to "Keramik adalah material finishing yang populer untuk lantai dan dinding. Tersedia dalam berbagai ukuran, warna, dan tekstur.",
        "KACA" to "Kaca digunakan untuk jendela, pintu, dan partisi. Memiliki berbagai jenis seperti tempered, laminated, dan kaca bening.",
        "CAT_PLAFON_ATAP" to "Cat digunakan untuk finishing plafon dan atap. Memiliki berbagai formula seperti anti jamur, anti noda, dan tahan cuaca."
    )
}
