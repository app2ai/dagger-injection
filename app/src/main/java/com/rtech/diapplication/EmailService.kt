package com.rtech.diapplication

import android.util.Log

/*
@Developed by: Vishal Rana (job.vishalrana@gmail.com)
this class responsible for sending
email after registration of user
*/

class EmailService {
    private val TAG = "EmailService"
    fun sendEmail(email: String) {
        Log.d(TAG, "Email sent to: $email")
    }
}