package com.rtech.diapplication

import android.util.Log
import javax.inject.Inject

/*
@Developed by: Vishal Rana (job.vishalrana@gmail.com)
this class responsible for sending
email after registration of user
*/

interface NotificationService{
    fun sendNotification(email: String)
}

class EmailService @Inject constructor() : NotificationService{
    private val TAG = "EmailService"
    override fun sendNotification(email: String) {
        Log.d(TAG, "Email sent")
    }
}

class SMSService @Inject constructor() : NotificationService{
    private val TAG = "SMSService"
    override fun sendNotification(email: String) {
        Log.d(TAG, "SMS sent")
    }
}