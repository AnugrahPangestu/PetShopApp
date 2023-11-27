package com.example.aplikasipetshop.ui.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.ActivityAddProductBinding
import com.example.aplikasipetshop.ui.EtalaseActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var firebaseRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseRef = FirebaseDatabase.getInstance().getReference("product")

        binding.button.setOnClickListener {
            saveData()
        }

    }

    private fun saveData() {
        val name = binding.edtProductName.text.toString()
        val price = binding.edtProductPrice.text.toString()
        val stock = binding.edtStock.text.toString()
        val desc = binding.edtDesc.text.toString()

        if (name.isEmpty()) binding.edtProductName.error = "Nama produk tidak boleh kosong"
        if (price.isEmpty()) binding.edtProductName.error = "Harga tidak boleh kosong"
        if (stock.isEmpty()) binding.edtProductName.error = "Stok produk tidak boleh kosong"
        if (desc.isEmpty()) binding.edtProductName.error = "Deskripsi produk tidak boleh kosong"

        val productId = firebaseRef.push().key!!
        val product = Products(productId, name, price, stock, desc)

        firebaseRef.child(productId).setValue(product)
            .addOnCompleteListener {
                val intentToEtalase = Intent(this, EtalaseActivity::class.java)
                Toast.makeText(this, "Tambah Produk Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(intentToEtalase)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Tambah Produk Gagal ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}