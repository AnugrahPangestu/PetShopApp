package com.example.aplikasipetshop.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aplikasipetshop.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.materialCardView2.setOnClickListener {
            val intentToEtalase = Intent(activity, EtalaseActivity::class.java)
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }
}