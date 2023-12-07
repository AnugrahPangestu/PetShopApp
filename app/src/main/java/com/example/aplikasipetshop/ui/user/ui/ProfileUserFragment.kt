package com.example.aplikasipetshop.ui.user.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.aplikasipetshop.AuthenticationActivity
import com.example.aplikasipetshop.MainActivity
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.FragmentProfileBinding
import com.example.aplikasipetshop.databinding.FragmentProfileUserBinding
import com.example.aplikasipetshop.ui.LoginFragment
import com.example.aplikasipetshop.ui.UpdateProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ProfileUserFragment : Fragment() {

    private var _binding: FragmentProfileUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseRef: DatabaseReference
    private lateinit var storageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fireBaseUser = auth.currentUser

        //if (fireBaseUser != null) {
        //    binding.name.text = fireBaseUser.displayName
        //    binding.email.text = fireBaseUser.email
        //}else {
        //    val intentToLogin = Intent(activity, LoginFragment::class.java)
        //    startActivity(intentToLogin)
        //}

        binding.logoutBtn.setOnClickListener{
            auth.signOut()
            startActivity(Intent(activity, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            requireActivity().finish()
            Toast.makeText(requireContext(), getString(R.string.logout_success), Toast.LENGTH_SHORT).show()
        }

        binding.btnProfileUpdate.setOnClickListener {
            val intent = Intent(activity, UpdateProfileActivity::class.java)
            startActivity(intent)
        }

        firebaseRef = FirebaseDatabase.getInstance().getReference("profile")
        storageRef = FirebaseStorage.getInstance().getReference("profilePic")

        //val imageUri = requireActivity().intent.getParcelableExtra<Uri>("image")
        //Glide.with(this).load(imageUri).into(binding.ivProfile)

        //binding.tvAddressProfile.text = requireActivity().intent.getStringExtra("address")
        //binding.tvPhoneProfile.text = requireActivity().intent.getStringExtra("phone")
        //binding.tvQrcodeProfile.text = requireActivity().intent.getStringExtra("qrcode")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}