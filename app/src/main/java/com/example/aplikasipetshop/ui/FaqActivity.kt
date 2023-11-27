package com.example.aplikasipetshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasipetshop.R

class FaqActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        supportActionBar?.hide()
    }
}