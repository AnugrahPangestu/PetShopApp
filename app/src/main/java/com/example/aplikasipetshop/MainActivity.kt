package com.example.aplikasipetshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasipetshop.databinding.ActivityMainBinding
import com.example.aplikasipetshop.ui.user.AuthenUserActivity
import com.example.aplikasipetshop.ui.user.HomeUserActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnForAdmin.setOnClickListener {
                val intentToAuth = Intent(this@MainActivity, AuthenticationActivity::class.java)
                startActivity(intentToAuth)
            }

            btnForUser.setOnClickListener {
                val intentToAuthUser = Intent(this@MainActivity, HomeUserActivity::class.java)
                startActivity(intentToAuthUser)
            }
        }


    }
}