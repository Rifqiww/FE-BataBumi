package com.batabumi.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.batabumi.app.R
import kotlinx.coroutines.delay

// 🔹 Data class untuk menampung data user (siap dipakai backend)
data class UserProfile(
    val name: String,
    val email: String,
    val phone: String,
    val profilePicture: Int? = null // bisa diganti URL nanti
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    // 🔹 State untuk data user dan loading
    var userProfile by remember { mutableStateOf<UserProfile?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // 🔹 Simulasi ambil data dari backend (pakai delay)
    LaunchedEffect(Unit) {
        delay(1000) // misalnya loading dari API
        userProfile = UserProfile(
            name = "Ezekiel R. Marquez",
            email = "ezekiel.marquez@example.com",
            phone = "+62 812 3456 7890",
            profilePicture = R.drawable.profile_icon
        )
        isLoading = false
    }

    // 🔹 UI utama
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Gray)
        }
    } else {
        userProfile?.let { profile ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                // Header
                Text(
                    text = "Account Settings",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Profile Image
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp)
                ) {
                    Image(
                        painter = painterResource(id = profile.profilePicture ?: R.drawable.profile_icon),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF5F5F5))
                    )

                    IconButton(
                        onClick = { /* TODO: edit photo action */ },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(28.dp)
                            .background(Color.White, CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.edit_icon),
                            contentDescription = "Edit Icon",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Name
                Text(
                    text = profile.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Edit Profile Button
                Button(
                    onClick = { onNavigate("edit_profile") },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(0.7f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5F5)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Edit Profile",
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                Spacer(modifier = Modifier.height(12.dp))

                // Personal Information Section
                Text(
                    text = "Personal Information",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                ProfileMenuItem(
                    icon = Icons.Default.Person,
                    label = "Name: ${profile.name}"
                )

                ProfileMenuItem(
                    icon = Icons.Default.Email,
                    label = "Email: ${profile.email}"
                )

                ProfileMenuItem(
                    icon = Icons.Default.Phone,
                    label = "Phone: ${profile.phone}"
                )

                ProfileMenuItem(
                    icon = Icons.Default.Lock,
                    label = "Password",
                    onClick = { onNavigate("change_password") }
                )

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

                ProfileMenuItem(
                    icon = Icons.Default.Description,
                    label = "Terms and Conditions",
                    onClick = { onNavigate("terms") }
                )

                ProfileMenuItem(
                    icon = Icons.Default.SupportAgent,
                    label = "Customer Service",
                    onClick = { onNavigate("support") }
                )

                ProfileMenuItem(
                    icon = Icons.Default.HelpOutline,
                    label = "FAQ",
                    onClick = { onNavigate("faq") }
                )
            }
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick?.invoke() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Arrow",
            tint = Color.Gray
        )
    }
}