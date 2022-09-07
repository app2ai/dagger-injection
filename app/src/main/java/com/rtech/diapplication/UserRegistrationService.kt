package com.rtech.diapplication

import javax.inject.Inject

// DI applied - Manual DI
class UserRegistrationService @Inject constructor(
    private val userRepository : UserRepository,
    private val emailService : EmailService
) {
    fun registerUser(email: String, pass: String) {
        userRepository.addUser(email, pass)
        emailService.sendEmail(email)
    }
}