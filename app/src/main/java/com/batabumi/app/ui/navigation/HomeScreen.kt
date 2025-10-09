package com.batabumi.app.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.batabumi.app.R
import com.batabumi.app.ui.components.BottomNavigationBar
import com.batabumi.app.ui.theme.YellowBright

data class ServiceCategory(
    val title: String,
    val imageRes: Int,
    val route: String
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToService: (String) -> Unit = {}
) {
    val serviceCategories = listOf(
        ServiceCategory("Tukang Konstruksi", R.drawable.tukang_konstruksi, "tukang_konstruksi"),
        ServiceCategory("Renov Rumah", R.drawable.renov_rumah, "renov_rumah"),
        ServiceCategory("Tukang Perbaikan", R.drawable.tukang_perbaikan, "tukang_perbaikan"),
        ServiceCategory("Tukang Bangunan", R.drawable.tukang_bangunan, "tukang_bangunan"),
        ServiceCategory("Tukang Konstruksi 2", R.drawable.tukang_konstruksi_2, "tukang_konstruksi_2"),
        ServiceCategory("Tukang Konstruksi 3", R.drawable.tukang_kontruksi_3, "tukang_konstruksi_3")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // 🔍 Search bar + filter
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
                placeholder = { Text("Search for services") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                shape = RoundedCornerShape(50.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
                )
            )


                IconButton(onClick = { /* TODO: Notifications */ }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.Gray
                    )
                }

                IconButton(onClick = { /* TODO: Filter */ }) {
                    Icon(
                        imageVector = Icons.Outlined.FilterList,
                        contentDescription = "Filter",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🧱 Grid kategori tukang
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(serviceCategories) { category ->
                    ServiceCategoryCard(
                        category = category,
                        onClick = { onNavigateToService(category.route) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 💬 AI Consultation Button
            Button(
                onClick = { /* TODO: AI Consultation */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = YellowBright
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "AI Consultation",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "AI Consultation",
                    fontSize = 16.sp
                )
            }
    }
}

@Composable
fun ServiceCategoryCard(
    category: ServiceCategory,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(category.imageRes)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
        )
    }
}
