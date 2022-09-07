package com.rtech.diapplication

import android.app.Application

class RegisterApplication : Application() {
    private val component = DaggerUserRegServiceComponent.builder().build()
    fun getUserRegistrationObject() : UserRegistrationService {
        return component.getUserRegService()
    }
}