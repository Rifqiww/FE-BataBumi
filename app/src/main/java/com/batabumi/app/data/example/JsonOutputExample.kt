package com.batabumi.app.data.example

/**
 * Contoh output JSON yang akan dihasilkan dari analisis konsultasi
 */
object JsonOutputExample {
    
    /**
     * Contoh JSON untuk konsultasi tentang KERAMIK
     */
    val KERAMIK_EXAMPLE = """
    {
      "sessionId": "session_12345",
      "startTime": "2024-01-15 10:30:00",
      "endTime": "2024-01-15 10:45:00",
      "duration": "15 menit",
      "messageCount": 8,
      "summary": {
        "sessionId": "session_12345",
        "timestamp": "2024-01-15 10:45:00",
        "consultationTopic": "Pemilihan Material",
        "materialAnalysis": {
          "materialType": "KERAMIK",
          "materialDisplayName": "Keramik",
          "confidence": "85.00%",
          "matchedKeywords": [
            "keramik",
            "tile",
            "lantai keramik",
            "keramik lantai"
          ],
          "reasoning": "Ditemukan 4 kata kunci: keramik, tile, lantai keramik, keramik lantai",
          "isRecommended": true
        },
        "userQuestions": [
          "Material apa yang cocok untuk lantai rumah?",
          "Bagaimana cara memilih keramik yang berkualitas?",
          "Berapa biaya pemasangan keramik per meter?"
        ],
        "aiResponses": [
          "Untuk lantai rumah, keramik adalah pilihan yang sangat baik karena tahan lama, mudah dibersihkan, dan memiliki berbagai pilihan desain...",
          "Dalam memilih keramik berkualitas, perhatikan beberapa faktor penting: ketebalan minimal 8mm, tingkat kekerasan (PEI rating), dan ketahanan terhadap noda...",
          "Biaya pemasangan keramik bervariasi tergantung ukuran dan jenis keramik. Untuk keramik standar 40x40cm, biaya pemasangan berkisar Rp 25.000-40.000 per m²..."
        ],
        "keyPoints": [
          "Keramik lantai harus memiliki ketebalan minimal 8mm untuk daya tahan optimal",
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
     * Contoh JSON untuk konsultasi tentang KACA
     */
    val KACA_EXAMPLE = """
    {
      "sessionId": "session_67890",
      "startTime": "2024-01-15 14:20:00",
      "endTime": "2024-01-15 14:35:00",
      "duration": "15 menit",
      "messageCount": 6,
      "summary": {
        "sessionId": "session_67890",
        "timestamp": "2024-01-15 14:35:00",
        "consultationTopic": "Pemilihan Material",
        "materialAnalysis": {
          "materialType": "KACA",
          "materialDisplayName": "Kaca",
          "confidence": "75.00%",
          "matchedKeywords": [
            "kaca",
            "jendela",
            "kaca jendela",
            "glass"
          ],
          "reasoning": "Ditemukan 4 kata kunci: kaca, jendela, kaca jendela, glass",
          "isRecommended": true
        },
        "userQuestions": [
          "Jenis kaca apa yang cocok untuk jendela rumah?",
          "Bagaimana cara merawat kaca agar tetap bening?"
        ],
        "aiResponses": [
          "Untuk jendela rumah, disarankan menggunakan kaca tempered atau kaca laminated untuk keamanan dan ketahanan...",
          "Perawatan kaca yang baik meliputi pembersihan rutin dengan cairan pembersih khusus kaca, hindari penggunaan bahan abrasif..."
        ],
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
     * Contoh JSON untuk konsultasi tentang CAT_PLAFON_ATAP
     */
    val CAT_PLAFON_ATAP_EXAMPLE = """
    {
      "sessionId": "session_11111",
      "startTime": "2024-01-15 16:00:00",
      "endTime": "2024-01-15 16:20:00",
      "duration": "20 menit",
      "messageCount": 10,
      "summary": {
        "sessionId": "session_11111",
        "timestamp": "2024-01-15 16:20:00",
        "consultationTopic": "Estimasi Biaya",
        "materialAnalysis": {
          "materialType": "CAT_PLAFON_ATAP",
          "materialDisplayName": "Cat Plafon Atap",
          "confidence": "90.00%",
          "matchedKeywords": [
            "cat",
            "cat plafon",
            "cat atap",
            "paint",
            "finishing"
          ],
          "reasoning": "Ditemukan 5 kata kunci: cat, cat plafon, cat atap, paint, finishing",
          "isRecommended": true
        },
        "userQuestions": [
          "Berapa biaya pengecatan plafon rumah?",
          "Jenis cat apa yang cocok untuk plafon?",
          "Bagaimana cara menghitung kebutuhan cat?"
        ],
        "aiResponses": [
          "Biaya pengecatan plafon bervariasi tergantung jenis cat dan luas area. Untuk cat tembok standar, biaya berkisar Rp 15.000-25.000 per m²...",
          "Untuk plafon, disarankan menggunakan cat tembok dengan formula anti jamur dan anti noda. Pilih cat dengan daya tutup yang baik...",
          "Perhitungan kebutuhan cat dapat dilakukan dengan rumus: (Luas area ÷ Daya tutup cat) × Jumlah lapisan..."
        ],
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
     * Contoh JSON untuk konsultasi yang tidak cocok dengan enum
     */
    val NO_MATCH_EXAMPLE = """
    {
      "sessionId": "session_99999",
      "startTime": "2024-01-15 18:00:00",
      "endTime": "2024-01-15 18:10:00",
      "duration": "10 menit",
      "messageCount": 4,
      "summary": {
        "sessionId": "session_99999",
        "timestamp": "2024-01-15 18:10:00",
        "consultationTopic": "Keselamatan Kerja",
        "materialAnalysis": null,
        "userQuestions": [
          "Apa saja protokol keselamatan di lapangan konstruksi?"
        ],
        "aiResponses": [
          "Protokol keselamatan di lapangan konstruksi meliputi penggunaan APD yang sesuai, penandaan area bahaya, dan pelatihan keselamatan rutin..."
        ],
        "keyPoints": [
          "Penggunaan APD (Alat Pelindung Diri) wajib untuk semua pekerja",
          "Area bahaya harus ditandai dengan jelas",
          "Pelatihan keselamatan harus dilakukan secara berkala"
        ],
        "recommendations": [
          "Lakukan inspeksi keselamatan harian",
          "Pastikan semua pekerja memiliki sertifikat K3",
          "Sediakan kotak P3K di setiap area kerja"
        ]
      }
    }
    """.trimIndent()
}
