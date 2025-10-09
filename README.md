# Construction Consultant AI Chatbot

This Android app features a construction consultant AI chatbot powered by Groq AI. The chatbot provides expert advice on construction projects, building materials, safety protocols, cost estimation, and construction best practices.

## Features

- **Real-time Chat Interface**: Clean, modern chat UI built with Jetpack Compose
- **AI-Powered Responses**: Uses Groq's Llama3-8b model for intelligent construction advice
- **Chat History**: Messages are stored locally using Room database
- **Construction-Focused**: Specialized system prompt for construction consulting
- **Error Handling**: Robust error handling with user-friendly error messages

## Setup Instructions

### 1. Get Your Groq API Key

1. Visit [Groq Console](https://console.groq.com/keys)
2. Sign up or log in to your account
3. Create a new API key
4. Copy your API key

### 2. Configure the API Key

1. Open `app/src/main/java/com/batabumi/app/config/ApiConfig.kt`
2. Replace `YOUR_GROQ_API_KEY_HERE` with your actual API key:

```kotlin
const val GROQ_API_KEY = "gsk_your_actual_api_key_here"
```

### 3. Build and Run

1. Sync your project with Gradle files
2. Build the project
3. Run on your device or emulator

## Project Structure

```
app/src/main/java/com/batabumi/app/
├── config/
│   └── ApiConfig.kt              # API configuration
├── data/
│   ├── api/
│   │   ├── GroqApiClient.kt      # Retrofit client setup
│   │   └── GroqApiService.kt     # API service interface
│   ├── local/
│   │   ├── ChatDatabase.kt       # Room database
│   │   └── ChatMessageDao.kt     # Data access object
│   ├── model/
│   │   ├── ChatMessage.kt        # Chat message entity
│   │   ├── GroqModels.kt         # API request/response models
│   │   └── Converters.kt         # Room type converters
│   └── repository/
│       └── ChatRepository.kt     # Data repository
├── ui/
│   ├── navigation/
│   │   └── AppNavigation.kt      # Navigation setup
│   └── screens/
│       └── ChatScreen.kt          # Main chat UI
├── viewmodel/
│   └── ChatViewModel.kt          # ViewModel for chat state
└── MainActivity.kt                # Main activity
```

## Dependencies

The app uses the following key dependencies:

- **Jetpack Compose**: Modern UI toolkit
- **Room**: Local database for chat history
- **Retrofit**: HTTP client for API calls
- **Coroutines**: Asynchronous programming
- **Navigation Compose**: Navigation between screens
- **Material3**: Material Design components

## Usage

1. Launch the app
2. Start typing your construction-related questions
3. The AI will respond with expert advice
4. Use the clear button to start a new conversation
5. Chat history is automatically saved locally

## Example Questions

- "What are the best practices for concrete mixing?"
- "How do I calculate material costs for a 1000 sq ft house?"
- "What safety protocols should I follow during excavation?"
- "What's the difference between load-bearing and non-load-bearing walls?"
- "How do I estimate the timeline for a residential construction project?"

## Troubleshooting

### API Key Issues
- Ensure your API key is correctly set in `ApiConfig.kt`
- Check that your API key has sufficient credits
- Verify the API key format starts with `gsk_`

### Build Issues
- Clean and rebuild the project
- Ensure all dependencies are properly synced
- Check that your Android SDK is up to date

### Network Issues
- Ensure your device has internet connectivity
- Check if your network blocks API calls
- Verify Groq API service status

## Security Notes

- Never commit your API key to version control
- Consider using environment variables or secure storage for production
- The API key is currently stored in the source code for development purposes only
