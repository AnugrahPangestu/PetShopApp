package com.example.aplikasipetshop.ui.user.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.ActivityDetailProductBinding
import com.example.aplikasipetshop.databinding.ActivityDetailProductUserBinding
import com.example.aplikasipetshop.ui.product.Products
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class DetailProductUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductUserBinding
    private lateinit var tvProductId: TextView
    private lateinit var productList: ArrayList<Products>

    private lateinit var firebaseRef: DatabaseReference
    private lateinit var storageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseRef = FirebaseDatabase.getInstance().getReference("product")
        storageRef = FirebaseStorage.getInstance().getReference("images")

        setValueToViews()
        //toUpdateActivity()
        //deleteProduct()
        wa()
    }

    private fun setValueToViews() {
        binding.tvDetailNameProduct.text = intent.getStringExtra("name")
        binding.tvDetailPrice.text = intent.getStringExtra("price")
        binding.tvDetailStock.text = intent.getStringExtra("stock")
        binding.tvDetailDesc.text = intent.getStringExtra("desc")
        binding.tvId.text = intent.getStringExtra("productId").toString()

        val imageUri = intent.getParcelableExtra<Uri>("image")
        Glide.with(this).load(imageUri).into(binding.ivDetail)
    }

    private fun wa(){
        binding.btnToWa.setOnClickListener {
            val phoneNumber = "6285788214960"
            val name = binding.tvDetailNameProduct.text
            val msg = "Haloo, Apakah produk peliharan dengan merk $name masih ada?"
            val goToWa = Intent(Intent.ACTION_VIEW, Uri.parse("whatsapp://send?phone=$phoneNumber&text=$msg"))
            startActivity(goToWa)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Tidak ada aplikasi yang dapat menangani intent ini", Toast.LENGTH_SHORT).show()
            }
        }
    }
}