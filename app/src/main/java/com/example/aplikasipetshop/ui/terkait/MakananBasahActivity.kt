package com.example.aplikasipetshop.ui.terkait

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasipetshop.databinding.ActivityMakananBasahBinding
import com.example.aplikasipetshop.databinding.ActivityMakananKeringBinding

class MakananBasahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMakananBasahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakananBasahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnTerkaitKering.setOnClickListener {
            intent = Intent(this@MakananBasahActivity, MakananKeringActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}