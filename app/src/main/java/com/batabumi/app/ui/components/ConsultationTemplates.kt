package com.batabumi.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
// Import ikon spesifik yang digunakan
// AttachMoney, Event, GppGood, Build, Warning, CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import com.batabumi.app.data.model.ConstructionConsultant

@Composable
fun ConsultationTemplates(
    onTemplateSelected: (String) -> Unit
) {
    val templates = listOf(
        ConsultationTemplate(
            icon = Icons.Default.Build,
            title = "Estimasi Biaya",
            description = "Hitung biaya proyek konstruksi",
            question = "Bagaimana cara menghitung estimasi biaya untuk proyek konstruksi?"
        ),
        ConsultationTemplate(
            // Menggunakan GppBad jika tersedia, atau alternatif Security
            icon = Icons.Default.Build,
            title = "Keselamatan Kerja",
            description = "Protokol K3 di lapangan",
            question = "Apa saja protokol keselamatan yang harus diterapkan di lapangan konstruksi?"
        ),
        ConsultationTemplate(
            icon = Icons.Default.Build,
            title = "Pemilihan Material",
            description = "Material terbaik untuk proyek",
            question = "Material apa yang paling cocok untuk konstruksi rumah tinggal?"
        ),
        ConsultationTemplate(
            icon = Icons.Default.Build,
            title = "Perencanaan Proyek",
            description = "Jadwal dan timeline proyek",
            question = "Bagaimana cara merencanakan jadwal proyek konstruksi yang efisien?"
        ),
        ConsultationTemplate(
            icon = Icons.Default.Warning,
            title = "Manajemen Risiko",
            description = "Identifikasi dan mitigasi risiko",
            question = "Risiko apa saja yang perlu dipertimbangkan dalam proyek konstruksi?"
        ),
        ConsultationTemplate(
            icon = Icons.Default.CheckCircle,
            title = "Kontrol Kualitas",
            description = "Standar kualitas konstruksi",
            question = "Bagaimana cara memastikan kualitas konstruksi sesuai standar?"
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "💡 Template Konsultasi",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pilih topik konsultasi untuk memulai percakapan",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(templates) { template ->
                    TemplateCard(
                        template = template,
                        onClick = { onTemplateSelected(template.question) }
                    )
                }
            }
        }
    }
}

@Composable
fun TemplateCard(
    template: ConsultationTemplate,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = template.icon,
                contentDescription = template.title,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = template.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = template.description,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                lineHeight = 12.sp
            )
        }
    }
}

data class ConsultationTemplate(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val question: String
)

@Composable
fun ExpertiseAreas() {
    val expertiseAreas = listOf(
        "🏠 Perumahan",
        "🏢 Komersial", 
        "🛣️ Infrastruktur",
        "🔧 Renovasi",
        "🏭 Industri",
        "🌱 Berkelanjutan"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "🎯 Bidang Keahlian",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(expertiseAreas) { area ->
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                        color = Color.Transparent
                    ) {
                        Text(
                            text = area,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
