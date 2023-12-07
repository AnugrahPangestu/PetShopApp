package com.example.aplikasipetshop.ui.terkait

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasipetshop.databinding.ActivityMakananKeringBinding

class MakananKeringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMakananKeringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakananKeringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnKeBasah.setOnClickListener {
            intent = Intent(this@MakananKeringActivity, MakananBasahActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}