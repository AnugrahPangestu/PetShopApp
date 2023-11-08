package com.example.aplikasipetshop.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.aplikasipetshop.AuthenticationActivity
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fireBaseUser = auth.currentUser

        if (fireBaseUser != null) {
            binding.name.text = fireBaseUser.displayName
            binding.email.text = fireBaseUser.email
        }else {
            val intentToLogin = Intent(activity, LoginFragment::class.java)
            startActivity(intentToLogin)
        }

        binding.logoutBtn.setOnClickListener{
            auth.signOut()
            startActivity(Intent(activity, AuthenticationActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            requireActivity().finish()
            Toast.makeText(requireContext(), getString(R.string.logout_success), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}