package com.rtech.diapplication.modules

import com.rtech.diapplication.EmailService
import com.rtech.diapplication.NotificationService
import com.rtech.diapplication.SMSService
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NotificationServiceModule {

    @Provides
    fun getSmsService() : NotificationService {
        return SMSService()
    }

    @Named(value = "Email")
    @Provides
    fun getEmailService() : NotificationService {
        return EmailService()
    }

}