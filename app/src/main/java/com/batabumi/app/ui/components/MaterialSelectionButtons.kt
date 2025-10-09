package com.batabumi.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.batabumi.app.data.model.ConstructionMaterial

@Composable
fun MaterialSelectionButtons(
    onMaterialSelected: (ConstructionMaterial) -> Unit,
    selectedMaterial: ConstructionMaterial? = null
) {
    val materials = listOf(
        MaterialButtonData(
            material = ConstructionMaterial.KERAMIK,
            icon = Icons.Default.Build,
            title = "Keramik",
            description = "Lantai, dinding, ubin",
            color = MaterialTheme.colorScheme.primary
        ),
        MaterialButtonData(
            material = ConstructionMaterial.KACA,
            icon = Icons.Default.Build,
            title = "Kaca",
            description = "Jendela, pintu, partisi",
            color = MaterialTheme.colorScheme.secondary
        ),
        MaterialButtonData(
            material = ConstructionMaterial.CAT_PLAFON_ATAP,
            icon = Icons.Default.Build,
            title = "Cat & Plafon",
            description = "Pengecatan, finishing",
            color = MaterialTheme.colorScheme.tertiary
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
                text = "🏗️ Pilih Material Konstruksi",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pilih material yang ingin Anda konsultasikan",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            // Grid layout untuk button material
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                materials.forEach { materialData ->
                    MaterialButton(
                        materialData = materialData,
                        isSelected = selectedMaterial == materialData.material,
                        onClick = { onMaterialSelected(materialData.material) }
                    )
                }
            }
        }
    }
}

@Composable
fun MaterialButton(
    materialData: MaterialButtonData,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = 2.dp,
                        color = materialData.color,
                        shape = RoundedCornerShape(12.dp)
                    )
                } else {
                    Modifier
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                materialData.color.copy(alpha = 0.1f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 4.dp else 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) {
                    materialData.color
                } else {
                    materialData.color.copy(alpha = 0.1f)
                }
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = materialData.icon,
                        contentDescription = materialData.title,
                        modifier = Modifier.size(24.dp),
                        tint = if (isSelected) {
                            Color.White
                        } else {
                            materialData.color
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = materialData.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) {
                        materialData.color
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = materialData.description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            
            // Selection indicator
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    modifier = Modifier.size(24.dp),
                    tint = materialData.color
                )
            }
        }
    }
}

data class MaterialButtonData(
    val material: ConstructionMaterial,
    val icon: ImageVector,
    val title: String,
    val description: String,
    val color: Color
)

@Composable
fun MaterialInfoCard(
    selectedMaterial: ConstructionMaterial,
    onStartConsultation: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "📋 ${selectedMaterial.displayName}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "Konsultasi tentang ${selectedMaterial.displayName.lowercase()}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Keywords
            Text(
                text = "Topik yang bisa dikonsultasikan:",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(selectedMaterial.keywords.take(6)) { keyword: String ->
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                        color = Color.Transparent
                    ) {
                        Text(
                            text = keyword,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Start consultation button
            Button(
                onClick = onStartConsultation,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Start Chat",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Mulai Konsultasi ${selectedMaterial.displayName}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
