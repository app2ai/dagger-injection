package com.rtech.diapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.rtech.diapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TextWatcher {
    lateinit var binding: ActivityMainBinding
    private val rViewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
