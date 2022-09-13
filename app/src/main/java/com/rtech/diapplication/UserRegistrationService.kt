package com.rtech.diapplication

import com.rtech.diapplication.annotation.EmailServiceAnn
import javax.inject.Inject

class UserRegistrationService @Inject constructor(
    private val userRepository : UserRepository,
    @EmailServiceAnn private val notificationService : NotificationService
) {
    fun registerUser(email: String, pass: String) {
        userRepository.addUser(email, pass)
        notificationService.sendNotification(email)
    }
}