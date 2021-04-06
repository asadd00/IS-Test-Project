package com.myubuntu.istestproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myubuntu.istestproject.R
import com.myubuntu.istestproject.model.User
import com.myubuntu.istestproject.repository.LoginRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.log

class LoginViewModel: ViewModel() {
    private lateinit var loginRepo: LoginRepository

    val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val errUsername = MutableLiveData<String>(null)
    val errPassword = MutableLiveData<String>(null)

    fun init(){
        loginRepo = LoginRepository()
    }

    fun login(): MutableLiveData<User>{
        if(isDataValid())
            return loginRepo.login(username.value.toString(), password.value.toString())
        else {
            val user = User()
            user.hasError = true
            return MutableLiveData(user)
        }
    }

    fun isDataValid() : Boolean {
        var error = false

        if(username.value.toString().isEmpty()){
            errUsername.value = "Username is required"
            error = true
        }
        if(password.value.toString().isEmpty()){
            errPassword.value = "Password is required"
            error = true
        }

        return !error
    }
}