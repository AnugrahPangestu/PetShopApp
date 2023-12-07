package com.example.aplikasipetshop.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.adapter.ListProductsAdapter
import com.example.aplikasipetshop.databinding.ActivityEtalaseBinding
import com.example.aplikasipetshop.ui.product.AddProductActivity
import com.example.aplikasipetshop.ui.product.Products
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import java.util.*
import kotlin.collections.ArrayList

class EtalaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEtalaseBinding

    private lateinit var productList: ArrayList<Products>
    private val productListSearch = ArrayList<Products>()
    private lateinit var firebaseRef: DatabaseReference
    private lateinit var firebaseRefProfile: DatabaseReference
    private lateinit var storageRef: StorageReference
    private lateinit var auth: FirebaseAuth

    private lateinit var searchView: SearchView

    private lateinit var adapter: ListProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEtalaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@EtalaseActivity, AddProductActivity::class.java)
            startActivity(intent)
            finish()
        }

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
            layoutManager = LinearLayoutManager(this@EtalaseActivity)
        }

        //val searchView = findViewById<SearchView>(R.id.search_icon)
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

                rvAdapter.setOnItemClickListener(object : ListProductsAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@EtalaseActivity, DetailProductActivity::class.java)

                        //put extra
                        intent.putExtra("productId", productList[position].id)
                        intent.putExtra("name", productList[position].name)
                        intent.putExtra("price", productList[position].price)
                        intent.putExtra("stock", productList[position].stock)
                        intent.putExtra("desc", productList[position].desc)
                        val imageUri = Uri.parse(productList[position].imgUri)
                        intent.putExtra("image", imageUri)

                        startActivity(intent)
                        finish()
                    }

                })
                //val imageBitmap = MediaStore.Images.Media.getBitmap(this@EtalaseActivity.contentResolver, Uri.parse(productList[position].imgUri))
                //intent.putExtra("image", imageBitmap)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EtalaseActivity, "Error: $error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun cari(text: String){
        val filterListed : ArrayList<Products> = ArrayList()
        for (i in productList) {
            if (i.name!!.toLowerCase().contains(text.toLowerCase())){
                filterListed.add(i)
            }
        }
        //adapter.filterList(filterListed)
    }

    private fun search(){

        val rvAdapter = ListProductsAdapter(productList)
        binding.rvProducts.adapter = rvAdapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchList = ArrayList<Products>()

                if (newText != null) {
                    for (i in productListSearch) {
                        if (i.name?.lowercase(Locale.ROOT)!!.contains(newText)){
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()){
                        Toast.makeText(this@EtalaseActivity, "Produk tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }else{
                        //rvAdapter.onApplySearch(searchList)

                    }
                }

                return true
            }

        })
    }
}