package com.example.aplikasipetshop.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.ActivityUpdateBinding
import com.example.aplikasipetshop.ui.product.Products
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUpdateBinding

    private lateinit var firebaseRef: DatabaseReference
    private lateinit var storageRef: StorageReference

    private var uri: Uri? = null
    //private var imgUrl: String? = null
    //private lateinit var products: ArrayList<Products>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_update)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseRef = FirebaseDatabase.getInstance().getReference("product")
        storageRef = FirebaseStorage.getInstance().getReference("images")

        binding.edtUpdateName.setText(intent.getStringExtra("name").toString())
        binding.edtUpdatePrice.setText(intent.getStringExtra("price").toString())
        binding.edtUpdateStock.setText(intent.getStringExtra("stock").toString())
        binding.edtUpdateDesc.setText(intent.getStringExtra("desc").toString())
        binding.tvTestUpdate.text = intent.getStringExtra("id")

        //val img = products
        //val img: String? = null
        //val idProduct = binding.tvTestUpdate.text.toString()
        //val imgs = storageRef.child(idProduct)
        //Glide.with(this@UpdateActivity).load(imgs).into(binding.ivUpdate)
        //Picasso.get().load(idProduct[imgs]).into(binding.ivUpdate)
        //Picasso.get().load(currentItem.imgUri).into(ivProduct)

        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){
            binding.ivUpdate.setImageURI(it)
            if (it != null) {
                uri = it
            }
        }

        val id1 = binding.tvTestUpdate.text.toString()

        binding.btnUpdateImage.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.buttonFinishUpdate.setOnClickListener {
            updateData()

        }
    }

    private fun updateData() {
        val name = binding.edtUpdateName.text.toString()
        val price = binding.edtUpdatePrice.text.toString()
        val stock = binding.edtUpdateStock.text.toString()
        val desc = binding.edtUpdateDesc.text.toString()
        val id = binding.tvTestUpdate.text.toString()
        //val product = Products(id, name, price, stock, desc)
        var product: Products

        //val id = intent.getStringExtra("productId").toString()

        //firebaseRef.child(id).setValue(Products(id, name))

        //val productId = firebaseRef.push().key!!
        val productId = firebaseRef.push().key!!
        uri?.let {
            storageRef.child(id).putFile(it)
                .addOnSuccessListener { task ->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener { url ->
                            Toast.makeText(this, "Update Gambar Berhasil", Toast.LENGTH_SHORT).show()
                            val imgUrl = url.toString()

                            product = Products(id, name, price, stock, desc, imgUrl)
                            firebaseRef.child(id).setValue(product)
                                .addOnCompleteListener {
                                    val intentToEtalase = Intent(this, EtalaseActivity::class.java)
                                    Toast.makeText(this, "Tambah Produk Berhasil", Toast.LENGTH_SHORT).show()
                                    startActivity(intentToEtalase)
                                    finish()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "Tambah Produk Gagal ${it.message}", Toast.LENGTH_SHORT).show()
                                }
                        }
                }
        }

    }
}