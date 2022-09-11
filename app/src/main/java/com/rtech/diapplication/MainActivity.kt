package com.rtech.diapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.viewModels
import com.rtech.diapplication.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity(), TextWatcher {

    @Inject lateinit var userRegistrationService: UserRegistrationService
    private lateinit var binding: ActivityMainBinding
    private val rViewModel by viewModels<RegisterViewModel> {
        RegisterViewModel.factory(userRegistrationService)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DaggerUserRegServiceComponent.builder().build().injectViewObjects(this@MainActivity)
    }

    override fun onStart() {
        super.onStart()
        initiateViews()
        observeData()
    }

    private fun observeData() {
        rViewModel.validateEmailLiveData.observe(this) { pattern ->
            when (pattern) {
                RegisterViewModel.PatternMatcher.Matched -> {
                    binding.etEmail.invalidate()
                }
                RegisterViewModel.PatternMatcher.NotMatched -> {
                    binding.etEmail.error = "Email id invalid"
                }
            }
        }
        rViewModel.validatePasswordLiveData.observe(this) { pattern ->
            when (pattern) {
                RegisterViewModel.PatternMatcher.Matched -> {
                    binding.etPass.invalidate()
                }
                RegisterViewModel.PatternMatcher.NotMatched -> {
                    binding.etPass.error = "Password is short"
                }
            }
        }
        rViewModel.registerEnableLiveData.observe(this) {
            binding.btnRegister.isEnabled = it
        }
        rViewModel.loadingLiveData.observe(this) { isLoading ->
            binding.btnRegister.apply {
                isClickable = !isLoading
                text = if (isLoading) "Loading..." else "Register"
            }
        }
    }

    private fun initiateViews() {
        binding.btnRegister.setOnClickListener {
            Toast.makeText(this, "You are registered successfully", Toast.LENGTH_LONG).show()
        }
        binding.etPass.addTextChangedListener(this)
        binding.etEmail.addTextChangedListener(this)
        binding.btnRegister.setOnClickListener {
            rViewModel.registerUser(binding.etEmail.text.toString(), binding.etPass.text.toString())
        }
        binding.rgNotification.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(rg: RadioGroup?, pos: Int) {
                when(pos) {
                    0 -> {
                        // Select sms as Notification
                    }
                    1 -> {
                        // Select email as Notification
                    }
                    else -> {
                        // No Notification
                    }
                }
            }
        })
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(p0: Editable?) {}
    override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (binding.etPass.isFocused)
            rViewModel.validatePasswordPattern(txt.toString(), true)
        if (binding.etEmail.isFocused)
            rViewModel.validateEmail(txt.toString(), true)
    }
}
