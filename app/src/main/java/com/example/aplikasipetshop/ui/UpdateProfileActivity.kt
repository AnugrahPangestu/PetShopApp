package com.example.aplikasipetshop.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.aplikasipetshop.databinding.ActivityUpdateProfileBinding
import com.example.aplikasipetshop.ui.product.Products
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProfileBinding
    private lateinit var firebaseRef : DatabaseReference
    private lateinit var storageRef: StorageReference

    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseRef = FirebaseDatabase.getInstance().getReference("profile")
        storageRef = FirebaseStorage.getInstance().getReference("profilePic")

        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){
            binding.addImageQr.setImageURI(it)
            if (it != null) {
                uri = it

            }
        }

        binding.btnAddImageQr.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.buttonFinishUpdateProfile.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val address = binding.edtAddress.text.toString()
        val phone = binding.edtPhoneNumber.text.toString()


        if (address.isEmpty()) binding.edtAddress.error = "Nama produk tidak boleh kosong"
        if (phone.isEmpty()) binding.edtPhoneNumber.error = "Harga tidak boleh kosong"

        val profileId = firebaseRef.push().key!!
        //val product = Products(productId, name, price, stock, desc)
        var profile : UserProfiles
        //var product : Products

        uri?.let {
            storageRef.child(profileId).putFile(it)
                .addOnSuccessListener { task ->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener { url ->
                            Toast.makeText(this, "Tambah Gambar Berhasil", Toast.LENGTH_SHORT).show()
                            val imgUrl = url.toString()

                            profile = UserProfiles(profileId, address, phone, imgUrl)

                            firebaseRef.child(profileId).setValue(profile)
                                .addOnCompleteListener {
                                    val intentToProfile = Intent(this, ProfileFragment::class.java)
                                    Toast.makeText(this, "Tambah Produk Berhasil", Toast.LENGTH_SHORT).show()

                                    startActivity(intentToProfile)
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "Tambah Produk Gagal ${it.message}", Toast.LENGTH_SHORT).show()
                                }

                        }
                }
        }
    }
}