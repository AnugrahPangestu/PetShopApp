package com.example.aplikasipetshop.ui.user.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.FragmentHomeBinding
import com.example.aplikasipetshop.databinding.FragmentHomeUserBinding
import com.example.aplikasipetshop.ui.EtalaseActivity
import com.example.aplikasipetshop.ui.FaqActivity
import com.example.aplikasipetshop.ui.TipsActivity
import com.example.aplikasipetshop.ui.maps.MapsActivity
import com.example.aplikasipetshop.ui.search.SearchActivity
import com.example.aplikasipetshop.ui.user.EtalaseUserActivity
import com.google.firebase.auth.FirebaseAuth

class HomeUserFragment : Fragment() {

    private var _binding: FragmentHomeUserBinding? = null
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
        // Inflate the layout for this fragment
        _binding = FragmentHomeUserBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.materialCardView2.setOnClickListener {
            val intentToEtalase = Intent(activity, EtalaseUserActivity::class.java)
            startActivity(intentToEtalase)
        }

        binding.faq.setOnClickListener {
            val intentToFaq = Intent(activity, FaqActivity::class.java)
            startActivity(intentToFaq)
        }

        binding.tips.setOnClickListener {
            val intentToTips = Intent(activity, TipsActivity::class.java)
            startActivity(intentToTips)
        }

        binding.btnMaps.setOnClickListener {
            val intentToMaps = Intent(activity, MapsActivity::class.java)
            startActivity(intentToMaps)
        }

        val fireBaseUser = auth.currentUser

        //if (fireBaseUser != null) {
        //    binding.tvNameDashboard.text = fireBaseUser.displayName
        //}
        //binding.tvNameDashboard.text = fireBaseUser?.displayName

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }

}