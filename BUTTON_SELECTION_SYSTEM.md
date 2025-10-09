# 🏗️ Sistem Button Selection untuk Material Konstruksi

## 📋 Overview

Sistem ini menggunakan button selection untuk memilih material konstruksi secara langsung, bukan menganalisis session chat. Ini lebih sederhana dan user-friendly.

## 🎯 Material yang Didukung

### **1. KERAMIK** 🧱
- **Icon**: Build
- **Description**: Lantai, dinding, ubin
- **Keywords**: keramik, tile, ubin, lantai keramik, granit, marmer

### **2. KACA** 🪟
- **Icon**: Visibility  
- **Description**: Jendela, pintu, partisi
- **Keywords**: kaca, glass, jendela, pintu kaca, kaca tempered

### **3. CAT_PLAFON_ATAP** 🎨
- **Icon**: Brush
- **Description**: Pengecatan, finishing
- **Keywords**: cat, paint, plafon, atap, finishing

## 🎨 UI Components

### **1. MaterialSelectionButtons.kt**
- Button grid untuk memilih material
- Visual feedback untuk selection
- Color coding untuk setiap material

### **2. MaterialInfoCard.kt**
- Informasi detail material yang dipilih
- Template pertanyaan untuk material
- Button untuk memulai konsultasi

### **3. ChatScreen.kt (Updated)**
- Integrasi button selection
- State management untuk selected material
- Conditional rendering berdasarkan selection

## 📊 JSON Output Structure

### **Button Selection JSON:**
```json
{
  "sessionId": "session_material_001",
  "startTime": "2024-01-15 10:30:00",
  "endTime": "2024-01-15 10:30:00",
  "duration": "0 menit",
  "messageCount": 0,
  "summary": {
    "sessionId": "session_material_001",
    "timestamp": "2024-01-15 10:30:00",
    "consultationTopic": "Konsultasi [Material]",
    "materialAnalysis": {
      "materialType": "KERAMIK|KACA|CAT_PLAFON_ATAP",
      "materialDisplayName": "Material Name",
      "confidence": "100.00%",
      "matchedKeywords": ["keyword1", "keyword2", "keyword3"],
      "reasoning": "Material dipilih langsung oleh user: [Material]",
      "isRecommended": true
    },
    "userQuestions": [],
    "aiResponses": [],
    "keyPoints": [
      "Key point 1",
      "Key point 2", 
      "Key point 3"
    ],
    "recommendations": [
      "Recommendation 1",
      "Recommendation 2",
      "Recommendation 3"
    ]
  }
}
```

## 🔧 Service Components

### **1. MaterialSelectionService.kt**
- Generate JSON berdasarkan button selection
- Pre-defined key points dan recommendations
- Template pertanyaan untuk setiap material

### **2. MaterialSelectionButtons.kt**
- UI component untuk button selection
- Visual feedback dan state management
- Color coding dan icons

## 📝 Contoh Output JSON

### **KERAMIK Selection:**
```json
{
  "materialAnalysis": {
    "materialType": "KERAMIK",
    "materialDisplayName": "Keramik",
    "confidence": "100.00%",
    "matchedKeywords": ["keramik", "tile", "ubin"],
    "reasoning": "Material dipilih langsung oleh user: Keramik",
    "isRecommended": true
  },
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
```

### **KACA Selection:**
```json
{
  "materialAnalysis": {
    "materialType": "KACA",
    "materialDisplayName": "Kaca",
    "confidence": "100.00%",
    "matchedKeywords": ["kaca", "glass", "jendela"],
    "reasoning": "Material dipilih langsung oleh user: Kaca",
    "isRecommended": true
  },
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
```

### **CAT_PLAFON_ATAP Selection:**
```json
{
  "materialAnalysis": {
    "materialType": "CAT_PLAFON_ATAP",
    "materialDisplayName": "Cat Plafon Atap",
    "confidence": "100.00%",
    "matchedKeywords": ["cat", "paint", "plafon"],
    "reasoning": "Material dipilih langsung oleh user: Cat Plafon Atap",
    "isRecommended": true
  },
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
```

## 🚀 Cara Penggunaan

### **1. User Flow:**
1. User membuka aplikasi
2. Melihat 3 button material (Keramik, Kaca, Cat & Plafon)
3. Memilih salah satu material
4. Melihat informasi detail material
5. Klik "Mulai Konsultasi" untuk chat dengan AI
6. JSON output dihasilkan berdasarkan pilihan

### **2. Code Implementation:**
```kotlin
// Material selection
MaterialSelectionButtons(
    onMaterialSelected = { material ->
        selectedMaterial = material
    },
    selectedMaterial = selectedMaterial
)

// Generate JSON
val service = MaterialSelectionService()
val jsonOutput = service.generateMaterialJson(
    sessionId = "session_001",
    selectedMaterial = ConstructionMaterial.KERAMIK
)
```

## ✅ Keuntungan Button Selection

### **1. User Experience:**
- ✅ Lebih sederhana dan intuitif
- ✅ Tidak perlu menganalisis chat history
- ✅ Langsung ke point (material selection)
- ✅ Visual feedback yang jelas

### **2. Technical:**
- ✅ Confidence 100% (tidak ada ambiguity)
- ✅ JSON output konsisten
- ✅ Tidak perlu NLP analysis
- ✅ Faster processing

### **3. Data Quality:**
- ✅ Data terstruktur dengan baik
- ✅ Key points dan recommendations predefined
- ✅ Template pertanyaan tersedia
- ✅ Material info lengkap

## 📋 Template Pertanyaan

### **KERAMIK:**
- "Bagaimana cara memilih keramik yang berkualitas?"
- "Berapa biaya pemasangan keramik per meter?"
- "Apa perbedaan keramik granit dan marmer?"

### **KACA:**
- "Jenis kaca apa yang cocok untuk jendela rumah?"
- "Bagaimana cara merawat kaca agar tetap bening?"
- "Apa keuntungan kaca tempered vs kaca biasa?"

### **CAT_PLAFON_ATAP:**
- "Berapa biaya pengecatan plafon rumah?"
- "Jenis cat apa yang cocok untuk plafon?"
- "Bagaimana cara menghitung kebutuhan cat?"

---
*Sistem button selection memberikan pengalaman yang lebih langsung dan efisien untuk konsultasi material konstruksi* 🏗️
