package com.batabumi.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batabumi.app.data.model.ChatMessage
import com.batabumi.app.viewmodel.ChatViewModel
import com.batabumi.app.ui.components.ConsultationTemplates
import com.batabumi.app.ui.components.ExpertiseAreas
import com.batabumi.app.ui.components.MaterialSelectionButtons
import com.batabumi.app.ui.components.MaterialInfoCard
import com.batabumi.app.data.model.ConstructionMaterial
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    apiKey: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var messageText by remember { mutableStateOf("") }
    var selectedMaterial by remember { mutableStateOf<ConstructionMaterial?>(null) }
    val listState = rememberLazyListState()

    // Auto-scroll to bottom when new messages arrive
    LaunchedEffect(uiState.messages.size) {
        if (uiState.messages.isNotEmpty()) {
            listState.animateScrollToItem(uiState.messages.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Konsultan Konstruksi AI",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            actions = {
                IconButton(onClick = { viewModel.startNewChat() }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "New Chat",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = { viewModel.clearChat() }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Chat",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            )
        )

        // Chat Messages
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            if (uiState.messages.isEmpty()) {
                item {
                    WelcomeMessage()
                }
                item {
                    ExpertiseAreas()
                }
                item {
                    MaterialSelectionButtons(
                        onMaterialSelected = { material ->
                            selectedMaterial = material
                        },
                        selectedMaterial = selectedMaterial
                    )
                }
                selectedMaterial?.let { material ->
                    item {
                        MaterialInfoCard(
                            selectedMaterial = material,
                            onStartConsultation = {
                                messageText = "Saya ingin berkonsultasi tentang ${material.displayName.lowercase()}"
                            }
                        )
                    }
                }
                item {
                    ConsultationTemplates { templateQuestion ->
                        messageText = templateQuestion
                    }
                }
            }

            items(uiState.messages) { message ->
                MessageBubble(message = message)
            }

            if (uiState.isLoading) {
                item {
                    TypingIndicator()
                }
            }
        }

        // Error Message
        uiState.error?.let { error ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Error: $error",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(onClick = { viewModel.clearError() }) {
                        Text("Dismiss")
                    }
                }
            }
        }

        // Message Input
        MessageInput(
            messageText = messageText,
            onMessageTextChange = { messageText = it },
            onSendMessage = {
                if (messageText.isNotBlank()) {
                    viewModel.sendMessage(messageText, apiKey)
                    messageText = ""
                }
            },
            isLoading = uiState.isLoading
        )
    }
}

@Composable
fun WelcomeMessage() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🏗️ Konsultan Konstruksi AI",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Konsultasikan proyek konstruksi Anda! Saya dapat membantu dengan perencanaan, estimasi biaya, manajemen risiko, keselamatan kerja, dan praktik terbaik konstruksi.",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
fun MessageBubble(message: ChatMessage) {
    val isUser = message.isFromUser
    val bubbleColor = if (isUser) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }
    val textColor = if (isUser) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .padding(horizontal = 4.dp),
            colors = CardDefaults.cardColors(containerColor = bubbleColor),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (isUser) 16.dp else 4.dp,
                bottomEnd = if (isUser) 4.dp else 16.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = message.content,
                    color = textColor,
                    fontSize = 16.sp,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(message.timestamp),
                    color = textColor.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun TypingIndicator() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Konsultan sedang mengetik...",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(8.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun MessageInput(
    messageText: String,
    onMessageTextChange: (String) -> Unit,
    onSendMessage: () -> Unit,
    isLoading: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = onMessageTextChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = {
                    Text(
                        text = "Tanyakan tentang konstruksi...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                enabled = !isLoading,
                maxLines = 4,
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )

            // --- START: CORRECTED CODE ---
            FloatingActionButton(
                onClick = {
                    // Only execute the action if the button should be "enabled"
                    if (messageText.isNotBlank() && !isLoading) {
                        onSendMessage()
                    }
                },
                modifier = Modifier.size(48.dp),
                containerColor = if (messageText.isNotBlank() && !isLoading) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f) // Use a disabled color
                },
                contentColor = if (messageText.isNotBlank() && !isLoading) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f) // Use a disabled content color
                }
                // The 'enabled' parameter is removed
            ) {
                // --- END: CORRECTED CODE ---
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send Message",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

