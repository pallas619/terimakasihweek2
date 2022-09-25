package com.example.terimakasihweek2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listener()
    }

    private fun listener(){
        Handler().postDelayed({
            val splash = Intent(this, home::class.java)
            startActivity(splash)
        }, 3000)
    }
}