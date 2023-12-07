package com.example.aplikasipetshop.ui.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasipetshop.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}