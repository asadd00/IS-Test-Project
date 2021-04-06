package com.myubuntu.istestproject.views.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.myubuntu.istestproject.R
import com.myubuntu.istestproject.model.User

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init(){
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val user = User.getUser(this@SplashActivity)
                if(user?.token == null || user.token.isEmpty())
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                else
                    startActivity(Intent(this@SplashActivity, HomeActivity::class.java))

                finish()
            }
        , 1000)
    }
}