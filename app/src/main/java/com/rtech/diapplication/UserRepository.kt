package com.rtech.diapplication

import android.util.Log
import javax.inject.Inject

/*
@Developed by: Vishal Rana (job.vishalrana@gmail.com)
this class responsible for adding
user in respective DB
*/

interface UserRepository {
   fun addUser(email: String, pass: String)
}
class SqlRepository @Inject constructor() : UserRepository{
    private val TAG = "UserRepository"
    override fun addUser(email: String, pass: String) {
        Log.d(TAG, "Local-> Email: $email and Pass: $pass added successfully")
    }
}