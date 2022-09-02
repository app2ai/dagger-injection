package com.rtech.diapplication

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// DI applied
class RegisterViewModel constructor(private val userRegSer : UserRegistrationService) : ViewModel() {

    private var _validateEmailLiveData = MutableLiveData<PatternMatcher>()
    val validateEmailLiveData: LiveData<PatternMatcher> = _validateEmailLiveData

    private var _validatePasswordLiveData = MutableLiveData<PatternMatcher>()
    val validatePasswordLiveData: LiveData<PatternMatcher> = _validatePasswordLiveData

    private var _registerEnableLiveData = MutableLiveData<Boolean>()
    val registerEnableLiveData: LiveData<Boolean> = _registerEnableLiveData

    private var _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun validateEmail(text: String, isFocused: Boolean) {
        if (isFocused) {
            if (Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                _validateEmailLiveData.value = PatternMatcher.Matched
            } else {
                _validateEmailLiveData.value = PatternMatcher.NotMatched
            }
        }
        enableContinue()
    }

    fun validatePasswordPattern(text: String, isFocused: Boolean) {
        if (isFocused) {
            if (text.length >= MIN_PASSWORD_LENGTH) {
                _validatePasswordLiveData.value = PatternMatcher.Matched
            } else {
                _validatePasswordLiveData.value = PatternMatcher.NotMatched
            }
        }
        enableContinue()
    }

    private fun enableContinue() {
        _registerEnableLiveData.value =
            _validateEmailLiveData.value == PatternMatcher.Matched && _validatePasswordLiveData.value == PatternMatcher.Matched
    }

    fun registerUser(email: String, pass: String) {
        viewModelScope.launch {
            _loadingLiveData.value = true
            delay(2500)
            userRegSer.registerUser(email, pass)
            delay(1500)
            _loadingLiveData.value = false
        }
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 6

        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val userRegistrationService = RegisterApplication().getUserRegistrationObject()
                RegisterViewModel(userRegistrationService)
            }
        }
    }

    // classes for pattern identifier
    sealed class PatternMatcher {
        object Matched : PatternMatcher()
        object NotMatched : PatternMatcher()
    }
}