package com.example.aplikasipetshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        supportActionBar?.hide()
    }
}