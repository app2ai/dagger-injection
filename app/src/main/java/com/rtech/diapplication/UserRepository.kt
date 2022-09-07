package com.rtech.diapplication

import android.util.Log
import javax.inject.Inject

/*
@Developed by: Vishal Rana (job.vishalrana@gmail.com)
this class responsible for adding
user in respective DB
*/

class UserRepository @Inject constructor(){
    private val TAG = "UserRepository"
    fun addUser(email: String, pass: String) {
        Log.d(TAG, "Email: $email and Pass: $pass added successfully")
    }
}