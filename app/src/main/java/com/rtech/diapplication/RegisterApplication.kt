package com.rtech.diapplication

import android.app.Application

class RegisterApplication : Application() {
    fun getUserRegistrationObject() : UserRegistrationService {
        return UserRegistrationService(userRepository, emailService)
    }

    val emailService = EmailService()
    private val userRepository = UserRepository()
}