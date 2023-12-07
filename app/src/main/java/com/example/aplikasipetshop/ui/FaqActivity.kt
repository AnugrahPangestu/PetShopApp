package com.example.aplikasipetshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.faqntips.FaqAndTips
import com.example.aplikasipetshop.faqntips.FaqnTipsAdapter

class FaqActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        supportActionBar?.hide()

    }
}