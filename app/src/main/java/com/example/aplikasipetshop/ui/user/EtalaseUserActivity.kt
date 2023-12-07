package com.example.aplikasipetshop.ui.user

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.adapter.ListProductsAdapter
import com.example.aplikasipetshop.databinding.ActivityEtalaseBinding
import com.example.aplikasipetshop.databinding.ActivityEtalaseUserBinding
import com.example.aplikasipetshop.ui.DetailProductActivity
import com.example.aplikasipetshop.ui.product.Products
import com.example.aplikasipetshop.ui.terkait.MakananBasahActivity
import com.example.aplikasipetshop.ui.terkait.MakananKeringActivity
import com.example.aplikasipetshop.ui.user.ui.DetailProductUserActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference

class EtalaseUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEtalaseUserBinding

    private lateinit var productList: ArrayList<Products>
    private val productListSearch = ArrayList<Products>()
    private lateinit var firebaseRef: DatabaseReference
    private lateinit var firebaseRefProfile: DatabaseReference
    private lateinit var storageRef: StorageReference
    private lateinit var auth: FirebaseAuth

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEtalaseUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        val fireBaseUser = auth.currentUser

        if (fireBaseUser != null) {
            binding.tvNameProfileEtalase.text = fireBaseUser.displayName
        }

        firebaseRef = FirebaseDatabase.getInstance().getReference("product")
        firebaseRefProfile = FirebaseDatabase.getInstance().getReference("profile")
        productList = arrayListOf()

        fetchData()
        binding.rvProducts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@EtalaseUserActivity)
        }

        binding.btnTerkait.setOnClickListener {
            intent = Intent(this@EtalaseUserActivity, MakananKeringActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnTerkait1.setOnClickListener {
            intent = Intent(this@EtalaseUserActivity, MakananBasahActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun fetchData() {
        firebaseRef.addValueEventListener(object : ValueEventListener {
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

                rvAdapter.setOnItemClickListener(object : ListProductsAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@EtalaseUserActivity, DetailProductUserActivity::class.java)

                        //put extra
                        intent.putExtra("productId", productList[position].id)
                        intent.putExtra("name", productList[position].name)
                        intent.putExtra("price", productList[position].price)
                        intent.putExtra("stock", productList[position].stock)
                        intent.putExtra("desc", productList[position].desc)
                        val imageUri = Uri.parse(productList[position].imgUri)
                        intent.putExtra("image", imageUri)

                        startActivity(intent)
                    }

                })
                //val imageBitmap = MediaStore.Images.Media.getBitmap(this@EtalaseActivity.contentResolver, Uri.parse(productList[position].imgUri))
                //intent.putExtra("image", imageBitmap)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EtalaseUserActivity, "Error: $error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}