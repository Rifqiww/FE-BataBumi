package com.batabumi.app.data.repository

import com.batabumi.app.data.api.GroqApiClient
import com.batabumi.app.data.local.ChatDatabase
import com.batabumi.app.data.local.ChatMessageDao
import com.batabumi.app.data.model.ChatMessage
import com.batabumi.app.data.model.GroqMessage
import com.batabumi.app.data.model.GroqRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ChatRepository(
    private val database: ChatDatabase
) {
    private val chatMessageDao: ChatMessageDao = database.chatMessageDao()
    private val apiService = GroqApiClient.apiService

    fun getMessagesBySession(sessionId: String): Flow<List<ChatMessage>> {
        return chatMessageDao.getMessagesBySession(sessionId)
    }

    suspend fun insertMessage(message: ChatMessage): Long {
        return chatMessageDao.insertMessage(message)
    }

    suspend fun sendMessageToAI(
        userMessage: String,
        sessionId: String,
        apiKey: String
    ): Result<String> {
        return try {
            // Save user message
            val userChatMessage = ChatMessage(
                content = userMessage,
                isFromUser = true,
                sessionId = sessionId
            )
            insertMessage(userChatMessage)

            // Get conversation history for context
            val conversationHistory = chatMessageDao.getMessagesBySession(sessionId).first()
            val messages = conversationHistory.takeLast(10).map { chatMessage ->
                GroqMessage(
                    role = if (chatMessage.isFromUser) "user" else "assistant",
                    content = chatMessage.content
                )
            }

            // Add system message for construction consultant context
            val systemMessage = GroqMessage(
                role = "system",
                content = """Anda adalah seorang konsultan konstruksi profesional dengan pengalaman 20+ tahun. Spesialisasi Anda meliputi:

🏗️ **Bidang Keahlian:**
- Perencanaan proyek konstruksi
- Estimasi biaya dan anggaran
- Manajemen risiko konstruksi
- Kualitas material dan spesifikasi
- Keselamatan kerja (K3)
- Manajemen waktu dan jadwal
- Kontrol kualitas dan standar
- Teknologi konstruksi modern

📋 **Layanan Konsultasi:**
- Analisis kelayakan proyek
- Review desain dan spesifikasi
- Evaluasi kontraktor dan supplier
- Audit keselamatan lapangan
- Optimasi biaya konstruksi
- Manajemen perubahan proyek
- Training tim konstruksi

🎯 **Pendekatan Profesional:**
- Berikan saran yang praktis dan dapat diimplementasikan
- Pertimbangkan aspek keamanan, kualitas, dan biaya
- Berikan referensi standar dan regulasi yang berlaku
- Sertakan alternatif solusi jika memungkinkan
- Prioritaskan keselamatan dan kualitas

Selalu jawab dalam bahasa Indonesia yang profesional dan mudah dipahami."""
            )

            val request = GroqRequest(
                messages = listOf(systemMessage) + messages,
                model = "llama-3.1-8b-instant",
                temperature = 0.7,
                max_tokens = 1024
            )

            // Debug: Log the request
            android.util.Log.d("ChatRepository", "Sending request: $request")
            android.util.Log.d("ChatRepository", "API Key: ${apiKey.take(10)}...")
            
            val response = apiService.sendMessage(
                authorization = "Bearer $apiKey",
                request = request
            )

            if (response.isSuccessful) {
                val aiResponse = response.body()?.choices?.firstOrNull()?.message?.content
                if (aiResponse != null) {
                    // Save AI response
                    val aiChatMessage = ChatMessage(
                        content = aiResponse,
                        isFromUser = false,
                        sessionId = sessionId
                    )
                    insertMessage(aiChatMessage)
                    Result.success(aiResponse)
                } else {
                    Result.failure(Exception("Empty response from AI"))
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Result.failure(Exception("API call failed: ${response.code()} ${response.message()}. Error body: $errorBody"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun clearChatHistory(sessionId: String) {
        chatMessageDao.deleteMessagesBySession(sessionId)
    }
}