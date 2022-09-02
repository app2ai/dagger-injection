package com.rtech.diapplication

// DI applied - Manual DI
class UserRegistrationService constructor(
    private val userRepository : UserRepository,
    private val emailService : EmailService
) {
    fun registerUser(email: String, pass: String) {
        userRepository.addUser(email, pass)
        emailService.sendEmail(email)
    }
}