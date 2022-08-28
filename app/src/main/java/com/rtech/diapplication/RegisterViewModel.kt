package com.rtech.diapplication

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    // DI Step 2: Create ViewModel
    private var _validateEmailLiveData = MutableLiveData<PatternMatcher>()
    val validateEmailLiveData: LiveData<PatternMatcher> = _validateEmailLiveData

    private var _validatePasswordLiveData = MutableLiveData<PatternMatcher>()
    val validatePasswordLiveData: LiveData<PatternMatcher> = _validatePasswordLiveData

    private var _registerEnableLiveData = MutableLiveData<Boolean>()
    val registerEnableLiveData: LiveData<Boolean> = _registerEnableLiveData

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

    companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    // classes for pattern identifier
    sealed class PatternMatcher {
        object Matched : PatternMatcher()
        object NotMatched : PatternMatcher()
    }
}