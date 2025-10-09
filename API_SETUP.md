# Construction Consultant AI - API Setup Guide

## Groq API Setup

This app uses Groq's API for AI text generation. Follow these steps to set up your API key:

### 1. Get Your Groq API Key

1. Visit [Groq Console](https://console.groq.com/keys)
2. Sign up or log in to your account
3. Create a new API key
4. Copy the API key

### 2. Configure the API Key

1. Open the file: `app/src/main/java/com/batabumi/app/config/ApiConfig.kt`
2. Replace `YOUR_GROQ_API_KEY_HERE` with your actual API key:

```kotlin
object ApiConfig {
    // Replace this with your actual Groq API key
    const val GROQ_API_KEY = "gsk_your_actual_api_key_here"
    
    // ... rest of the configuration
}
```

### 3. Features

- **Construction-focused AI**: The AI is specifically trained to help with construction consultation
- **Chat History**: Messages are saved locally using Room database
- **Modern UI**: Material 3 design with construction-themed colors
- **Real-time Chat**: Send messages and get instant AI responses
- **New Chat**: Start fresh conversations
- **Clear Chat**: Clear current conversation history

### 4. Usage

1. Launch the app
2. Type your construction-related questions
3. Get expert AI advice on:
   - Building materials
   - Safety protocols
   - Cost estimation
   - Construction best practices
   - Project planning

### 5. API Configuration

The app is configured to use:
- **Model**: llama3-8b-8192 (fast and efficient)
- **Temperature**: 0.7 (balanced creativity)
- **Max Tokens**: 1024 (sufficient for detailed responses)

You can modify these settings in `ApiConfig.kt` if needed.
