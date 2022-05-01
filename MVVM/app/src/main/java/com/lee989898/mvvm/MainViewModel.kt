package com.lee989898.mvvm

import android.service.autofill.Validators.and
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class MainViewModel : ViewModel() {
    private val email = MutableLiveData("")
    private val pwd = MutableLiveData("")
    private val emailCheck = MutableLiveData(false)
    private val pwdCheck = MutableLiveData(false)
    private val loginButton: MediatorLiveData<Boolean> = MediatorLiveData()
    private val loginSuccess = MutableLiveData(false)

    init {
        initLoginButton(emailCheck)
        initLoginButton(pwdCheck)
    }

    fun setEmail(setEmail: String) {
        email.value = setEmail
    }

    fun setPwd(setPwd: String) {
        pwd.value = setPwd
    }

    fun setLoginSuccess(success: Boolean) {
        loginSuccess.value = success
    }

    fun getEnabledLoginButton(): Boolean {
        return (emailCheck.value == true) and (pwdCheck.value == true)
    }

    fun emailCheck(email: String) {
        emailCheck.value = Patterns.EMAIL_ADDRESS.matcher(email!!).matches()
    }

    fun pwdCheck(pwd: String) {
        pwdCheck.value =
            Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", pwd)
    }

    private fun initLoginButton(check: MutableLiveData<Boolean>) {
        loginButton.addSource(check) {
            loginButton.value = getEnabledLoginButton()
        }
    }

    fun getEmail(): LiveData<String> = email
    fun getPwd(): LiveData<String> = pwd
    fun getEmailCheck(): LiveData<Boolean> = emailCheck
    fun getPwdCheck(): LiveData<Boolean> = pwdCheck
    fun getLoginButton(): MediatorLiveData<Boolean> = loginButton
    fun getLoginSuccess(): LiveData<Boolean> = loginSuccess

}