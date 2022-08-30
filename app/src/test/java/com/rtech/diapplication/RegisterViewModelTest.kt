package com.rtech.diapplication

import android.util.Patterns
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RegisterViewModelTest {

    @get:Rule
    val mockRule = MockKRule(this)

    @MockK
    lateinit var viewModel : RegisterViewModel

    @Before
    fun setup() {
        viewModel = RegisterViewModel()
    }

    @Test
    fun `user enter invalid email id`() {
        viewModel.validateEmail("test@t", true)
        coEvery {
            Patterns.EMAIL_ADDRESS
        } returns emailPatternMatcher()
        val result = viewModel.validateEmailLiveData.value
        Assert.assertEquals("This email is invalid", result, RegisterViewModel.PatternMatcher.NotMatched)
    }

    private fun emailPatternMatcher() = Patterns.EMAIL_ADDRESS
}