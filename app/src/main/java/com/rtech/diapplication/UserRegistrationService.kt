package com.rtech.diapplication

class UserRegistrationService {
    private val userRepository = UserRepository()
    private val emailService = EmailService()

    fun registerUser(email: String, pass: String) {
        userRepository.addUser(email, pass)
        emailService.sendEmail(email)
    }
}