# 🏗️ Sistem Analisis Konsultasi Konstruksi

## 📋 Overview

Sistem ini menganalisis history chat/konsultasi antara user dengan AI bot, menyimpan hasilnya, dan membuat summary yang dapat dicocokkan dengan enum material konstruksi.

## 🎯 Enum Material yang Didukung

### **1. KERAMIK**
- **Keywords**: keramik, tile, ubin, lantai keramik, dinding keramik, granit, marmer, porcelain, ceramic, flooring, wall tile
- **Use Case**: Konsultasi tentang lantai, dinding, ubin, material finishing

### **2. KACA**
- **Keywords**: kaca, glass, jendela, pintu kaca, kaca jendela, kaca pintu, kaca tembus pandang, kaca bening, kaca cermin, kaca tempered, kaca laminated, window, glass door
- **Use Case**: Konsultasi tentang jendela, pintu, partisi, material transparan

### **3. CAT_PLAFON_ATAP**
- **Keywords**: cat, paint, cat plafon, cat atap, cat dinding, cat interior, cat eksterior, cat tembok, cat kayu, cat metal, primer, undercoat, topcoat, finishing, plafon, atap, ceiling, roof, wall paint
- **Use Case**: Konsultasi tentang pengecatan, finishing, plafon, atap

## 📊 Struktur Data JSON Output

### **ChatHistory JSON Structure:**
```json
{
  "sessionId": "string",
  "startTime": "yyyy-MM-dd HH:mm:ss",
  "endTime": "yyyy-MM-dd HH:mm:ss", 
  "duration": "X menit",
  "messageCount": number,
  "summary": {
    "sessionId": "string",
    "timestamp": "yyyy-MM-dd HH:mm:ss",
    "consultationTopic": "string",
    "materialAnalysis": {
      "materialType": "KERAMIK|KACA|CAT_PLAFON_ATAP",
      "materialDisplayName": "string",
      "confidence": "XX.XX%",
      "matchedKeywords": ["keyword1", "keyword2"],
      "reasoning": "string",
      "isRecommended": boolean
    },
    "userQuestions": ["question1", "question2"],
    "aiResponses": ["response1", "response2"],
    "keyPoints": ["point1", "point2"],
    "recommendations": ["rec1", "rec2"]
  }
}
```

## 🔧 Komponen Sistem

### **1. ConstructionMaterial.kt**
- Enum untuk material konstruksi
- Data class untuk analisis material
- Data class untuk summary konsultasi
- Data class untuk history chat

### **2. MaterialAnalyzer.kt**
- Analisis teks untuk menentukan material
- Pembuatan summary konsultasi
- Ekstraksi key points dan rekomendasi
- Penentuan topik konsultasi

### **3. JsonSerializer.kt**
- Konversi data ke format JSON
- Formatting timestamp dan durasi
- Serialisasi material analysis
- Output JSON yang terstruktur

### **4. ConsultationAnalysisService.kt**
- Service utama untuk analisis
- Integrasi semua komponen
- Statistik material
- Rekomendasi material

## 📝 Contoh Output JSON

### **Konsultasi KERAMIK:**
```json
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
      "matchedKeywords": ["keramik", "tile", "lantai keramik"],
      "reasoning": "Ditemukan 3 kata kunci: keramik, tile, lantai keramik",
      "isRecommended": true
    },
    "userQuestions": [
      "Material apa yang cocok untuk lantai rumah?",
      "Bagaimana cara memilih keramik yang berkualitas?"
    ],
    "aiResponses": [
      "Untuk lantai rumah, keramik adalah pilihan yang sangat baik...",
      "Dalam memilih keramik berkualitas, perhatikan ketebalan minimal 8mm..."
    ],
    "keyPoints": [
      "Keramik lantai harus memiliki ketebalan minimal 8mm",
      "Perhatikan PEI rating untuk tingkat kekerasan"
    ],
    "recommendations": [
      "Gunakan keramik dengan ukuran 40x40cm untuk ruang tamu",
      "Pilih keramik anti slip untuk area basah"
    ]
  }
}
```

### **Konsultasi KACA:**
```json
{
  "materialAnalysis": {
    "materialType": "KACA",
    "materialDisplayName": "Kaca",
    "confidence": "75.00%",
    "matchedKeywords": ["kaca", "jendela", "kaca jendela", "glass"],
    "reasoning": "Ditemukan 4 kata kunci: kaca, jendela, kaca jendela, glass",
    "isRecommended": true
  }
}
```

### **Konsultasi CAT_PLAFON_ATAP:**
```json
{
  "materialAnalysis": {
    "materialType": "CAT_PLAFON_ATAP",
    "materialDisplayName": "Cat Plafon Atap",
    "confidence": "90.00%",
    "matchedKeywords": ["cat", "cat plafon", "cat atap", "paint", "finishing"],
    "reasoning": "Ditemukan 5 kata kunci: cat, cat plafon, cat atap, paint, finishing",
    "isRecommended": true
  }
}
```

## 🚀 Cara Penggunaan

### **1. Analisis Session Chat:**
```kotlin
val service = ConsultationAnalysisService()
val jsonOutput = service.analyzeSession(sessionId, messages)
```

### **2. Analisis Material dari Teks:**
```kotlin
val analysis = service.analyzeMaterialFromText("Saya butuh saran untuk pemilihan keramik lantai")
```

### **3. Rekomendasi Material:**
```kotlin
val recommendation = service.getMaterialRecommendation(analysis)
```

## 📈 Fitur Analisis

### **1. Keyword Matching**
- Pencocokan kata kunci dengan confidence score
- Threshold minimum 30% untuk validasi
- Reasoning untuk setiap analisis

### **2. Topic Detection**
- Estimasi Biaya
- Keselamatan Kerja
- Pemilihan Material
- Perencanaan Proyek
- Manajemen Risiko
- Kontrol Kualitas

### **3. Content Extraction**
- Key points dari respons AI
- Rekomendasi spesifik
- User questions tracking
- AI responses analysis

## 🎯 Output yang Dihasilkan

1. **JSON History Chat** - Lengkap dengan metadata
2. **Material Analysis** - Cocok dengan enum yang ditentukan
3. **Summary Konsultasi** - Key points dan rekomendasi
4. **Confidence Score** - Tingkat kepercayaan analisis
5. **Reasoning** - Penjelasan mengapa material dipilih

---
*Sistem ini memungkinkan analisis otomatis konsultasi konstruksi dan klasifikasi berdasarkan material yang dibahas* 🏗️
