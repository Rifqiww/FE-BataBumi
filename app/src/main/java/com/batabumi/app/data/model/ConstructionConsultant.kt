package com.batabumi.app.data.model

/**
 * Konfigurasi khusus untuk Konsultan Konstruksi AI
 */
object ConstructionConsultant {
    
    /**
     * Template pertanyaan untuk berbagai bidang konstruksi
     */
    object QuestionTemplates {
        const val COST_ESTIMATION = "Bagaimana cara menghitung estimasi biaya untuk proyek {project_type}?"
        const val SAFETY_PROTOCOL = "Apa saja protokol keselamatan yang harus diterapkan untuk {activity}?"
        const val MATERIAL_SELECTION = "Material apa yang paling cocok untuk {application} dengan budget {budget_range}?"
        const val PROJECT_PLANNING = "Bagaimana cara merencanakan jadwal proyek {project_type} yang efisien?"
        const val RISK_MANAGEMENT = "Risiko apa saja yang perlu dipertimbangkan untuk proyek {project_type}?"
        const val QUALITY_CONTROL = "Bagaimana cara memastikan kualitas {material/process} sesuai standar?"
        const val REGULATORY_COMPLIANCE = "Regulasi apa saja yang harus dipatuhi untuk {project_type}?"
        const val TECHNOLOGY_ADVICE = "Teknologi konstruksi modern apa yang bisa diterapkan untuk {project_type}?"
    }
    
    /**
     * Bidang keahlian konsultan
     */
    object Expertise {
        const val RESIDENTIAL = "Konstruksi Perumahan"
        const val COMMERCIAL = "Konstruksi Komersial"
        const val INFRASTRUCTURE = "Infrastruktur"
        const val RENOVATION = "Renovasi"
        const val INDUSTRIAL = "Konstruksi Industri"
        const val SUSTAINABLE = "Konstruksi Berkelanjutan"
    }
    
    /**
     * Jenis layanan konsultasi
     */
    object Services {
        const val FEASIBILITY_ANALYSIS = "Analisis Kelayakan Proyek"
        const val COST_OPTIMIZATION = "Optimasi Biaya"
        const val SAFETY_AUDIT = "Audit Keselamatan"
        const val QUALITY_ASSURANCE = "Jaminan Kualitas"
        const val PROJECT_MANAGEMENT = "Manajemen Proyek"
        const val RISK_ASSESSMENT = "Penilaian Risiko"
        const val REGULATORY_GUIDANCE = "Panduan Regulasi"
        const val TECHNOLOGY_CONSULTING = "Konsultasi Teknologi"
    }
    
    /**
     * Standar dan regulasi yang dikuasai
     */
    object Standards {
        const val SNI = "Standar Nasional Indonesia (SNI)"
        const val PUPR = "Kementerian PUPR"
        const val K3 = "Keselamatan dan Kesehatan Kerja"
        const val ISO = "ISO Construction Standards"
        const val GREEN_BUILDING = "Green Building Standards"
    }
    
    /**
     * Generate prompt berdasarkan jenis konsultasi
     */
    fun generateConsultationPrompt(
        projectType: String,
        serviceType: String,
        specificQuestion: String
    ): String {
        return """
        Proyek: $projectType
        Layanan: $serviceType
        
        Pertanyaan spesifik: $specificQuestion
        
        Sebagai konsultan konstruksi profesional, berikan analisis mendalam yang mencakup:
        1. Aspek teknis dan implementasi
        2. Pertimbangan biaya dan efisiensi
        3. Aspek keselamatan dan risiko
        4. Rekomendasi praktis
        5. Alternatif solusi jika ada
        """.trimIndent()
    }
}
