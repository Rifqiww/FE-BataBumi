package com.batabumi.app.config

object ApiConfig {
    // Replace this with your actual Groq API key
    // You can get your API key from: https://console.groq.com/keys
    const val GROQ_API_KEY = "GROQ_API_KEY"
    
    // Groq API Configuration
    const val BASE_URL = "https://api.groq.com/openai/"
    const val DEFAULT_MODEL = "llama-3.1-8b-instant"
    const val TEMPERATURE = 0.7
    const val MAX_TOKENS = 1024
}
