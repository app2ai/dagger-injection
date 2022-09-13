package com.rtech.diapplication.modules

import com.rtech.diapplication.EmailService
import com.rtech.diapplication.NotificationService
import com.rtech.diapplication.SMSService
import com.rtech.diapplication.annotation.EmailServiceAnn
import dagger.Binds
import dagger.Module

@Module
abstract class NotificationServiceModule {

    @Binds
    abstract fun getSmsService(sms: SMSService) : NotificationService

    @EmailServiceAnn
    @Binds
    abstract fun getEmailService(email: EmailService) : NotificationService

}