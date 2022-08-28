package com.lee989898.shimhwastudy

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {

    private var _userInfo: UserInfo? = null
    val userInfo get() = _userInfo

    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()
    private val _emailFormatCheck = MutableLiveData<Boolean>()
    val emailFormatCheck: LiveData<Boolean> get() = _emailFormatCheck
    private val _passwordFormatCheck = MutableLiveData<Boolean>()
    val passwordFormatCheck: LiveData<Boolean> get() = _passwordFormatCheck
    private val _isLoginSuccess = MediatorLiveData<Boolean>()
    val isLoginSuccess: MediatorLiveData<Boolean> get() = _isLoginSuccess
    private val _isCompletedSignIn = MutableLiveData<Boolean>()
    val isCompletedSignIn: LiveData<Boolean> get() = _isCompletedSignIn

    init {
        initLoginButton(_emailFormatCheck)
        initLoginButton(_passwordFormatCheck)
    }

    fun checkEmailFormat() {
        _emailFormatCheck.value =
            Patterns.EMAIL_ADDRESS.matcher(userEmail.value.toString().trim()).matches()
    }

    fun checkPasswordFormat() {
        val passwordPattern =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{7,15}.$")
        _passwordFormatCheck.value =
            passwordPattern.matcher(userPassword.value.toString().trim()).matches()
    }

    fun signIn() {
        _userInfo = UserInfo(userEmail.value.toString(), userPassword.value.toString())
        _isCompletedSignIn.value = true
    }

    private fun initLoginButton(formatCheck: MutableLiveData<Boolean>) {
        _isLoginSuccess.addSource(formatCheck) {
            _isLoginSuccess.value = getEnabledLoginButton()
        }
    }

    private fun getEnabledLoginButton(): Boolean {
        return (_emailFormatCheck.value == true) and (_passwordFormatCheck.value == true)
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}