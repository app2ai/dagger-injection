package com.rtech.diapplication

import javax.inject.Inject
import javax.inject.Named

class UserRegistrationService @Inject constructor(
    private val userRepository : UserRepository,
    @Named("Email") private val notificationService : NotificationService
) {
    fun registerUser(email: String, pass: String) {
        userRepository.addUser(email, pass)
        notificationService.sendNotification(email)
    }
}