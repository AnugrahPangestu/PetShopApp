package com.example.aplikasipetshop.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.adapter.ListProductsAdapter
import com.example.aplikasipetshop.databinding.ActivityEtalaseBinding
import com.example.aplikasipetshop.ui.product.AddProductActivity
import com.example.aplikasipetshop.ui.product.Products
import com.google.firebase.database.*

class EtalaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEtalaseBinding

    private lateinit var productList: ArrayList<Products>
    private lateinit var firebaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEtalaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@EtalaseActivity, AddProductActivity::class.java)
            startActivity(intent)
        }

        firebaseRef = FirebaseDatabase.getInstance().getReference("product")
        productList = arrayListOf()

        fetchData()
        binding.rvProducts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@EtalaseActivity)
        }

    }

    private fun fetchData() {
        firebaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                if (snapshot.exists()){
                    for (productSnap in snapshot.children){
                        val products = productSnap.getValue(Products::class.java)
                        productList.add(products!!)
                    }
                }
                val rvAdapter = ListProductsAdapter(productList)
                binding.rvProducts.adapter = rvAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EtalaseActivity, "Error: $error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}