package com.myubuntu.istestproject.views.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.myubuntu.istestproject.R
import com.myubuntu.istestproject.databinding.ActivityLoginBinding
import com.myubuntu.istestproject.model.User
import com.myubuntu.istestproject.utils.Methods
import com.myubuntu.istestproject.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
    lateinit var loginViewModel: LoginViewModel
    lateinit var binding: ActivityLoginBinding
    lateinit var loader: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        init()
    }

    private fun init(){
        loader = Methods.getLoader(this)
        initViewModel()

        bLogin.setOnClickListener {
            loader.show()
            loginViewModel.login().observe(this, Observer<User>{
               loader.dismiss()
                if(it.hasError) Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                else {
                    User.saveUser(it, this)
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                }
            })
        }

        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, R.string.feature_not_available, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViewModel(){
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.init()
        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel
    }
}