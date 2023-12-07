package com.example.aplikasipetshop.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.ActivityDetailProductBinding
import com.example.aplikasipetshop.ui.product.Products
import com.example.aplikasipetshop.ui.shop.ShopActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import java.net.URLEncoder

class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding
    private lateinit var tvProductId: TextView
    private lateinit var productList: ArrayList<Products>

    private lateinit var firebaseRef: DatabaseReference
    private lateinit var storageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail_product)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseRef = FirebaseDatabase.getInstance().getReference("product")
        storageRef = FirebaseStorage.getInstance().getReference("images")

        setValueToViews()
        //toUpdateActivity()
        //deleteProduct()
        wa()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_update -> {
                updateItem()
            }
            R.id.action_delete -> {
                deleteItem()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem(){
        val idDelete = binding.tvId.text
        val intent = Intent(this@DetailProductActivity, EtalaseActivity::class.java)
        //val id = "-NkhZYaQVwB36lmj7sAe"

        storageRef.child(idDelete.toString()).delete()

        firebaseRef.child(idDelete.toString()).removeValue()
            .addOnSuccessListener {
                Toast.makeText(this@DetailProductActivity, "Sukses Hapus", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this@DetailProductActivity, "Gagal Hapus", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateItem() {
        val intent = Intent(this@DetailProductActivity, UpdateActivity::class.java)

        val name = binding.tvDetailNameProduct.text
        val price = binding.tvDetailPrice.text
        val stock = binding.tvDetailStock.text
        val desc = binding.tvDetailDesc.text
        val id = binding.tvId.text

        //val imageUpdate = storageRef.child(id.toString())

        //val imageToUpdate = Uri.parse(productList[position].imgUri)
        val imageUry = intent.getParcelableExtra<Uri>("image")
        val imageToUpdate = Uri.parse(imageUry.toString())
        //val image = Glide.with(this@DetailProductActivity).load(imageUri).into(binding.ivDetail)

        //Sent to update activity
        intent.putExtra("name", name)
        intent.putExtra("price", price)
        intent.putExtra("stock", stock)
        intent.putExtra("desc", desc)
        intent.putExtra("id", id)

        intent.putExtra("imageUr", imageToUpdate)

        startActivity(intent)
        finish()
    }

    private fun deleteProduct(){
        binding.buttonDeleteDetail.setOnClickListener {
            val idDelete = binding.tvId.text
            val intent = Intent(this@DetailProductActivity, EtalaseActivity::class.java)
            //val id = "-NkhZYaQVwB36lmj7sAe"

            storageRef.child(idDelete.toString()).delete()

            firebaseRef.child(idDelete.toString()).removeValue()
                .addOnSuccessListener {
                    Toast.makeText(this@DetailProductActivity, "Sukses Hapus", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this@DetailProductActivity, "Gagal Hapus", Toast.LENGTH_SHORT).show()
                }

        }
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


    private fun toUpdateActivity(){
        binding.buttonUpdateDetail.setOnClickListener {
            val intent = Intent(this@DetailProductActivity, UpdateActivity::class.java)

            val name = binding.tvDetailNameProduct.text
            val price = binding.tvDetailPrice.text
            val stock = binding.tvDetailStock.text
            val desc = binding.tvDetailDesc.text
            val id = binding.tvId.text

            //val imageUpdate = storageRef.child(id.toString())

            //val imageToUpdate = Uri.parse(productList[position].imgUri)
            val imageUry = intent.getParcelableExtra<Uri>("image")
            val imageToUpdate = Uri.parse(imageUry.toString())
            //val image = Glide.with(this@DetailProductActivity).load(imageUri).into(binding.ivDetail)

            //Sent to update activity
            intent.putExtra("name", name)
            intent.putExtra("price", price)
            intent.putExtra("stock", stock)
            intent.putExtra("desc", desc)
            intent.putExtra("id", id)

            intent.putExtra("imageUr", imageToUpdate)

            startActivity(intent)
        }
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