package com.example.aplikasipetshop.ui.user

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.Navigation
import com.example.aplikasipetshop.HomeActivity
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.FragmentLoginBinding
import com.example.aplikasipetshop.databinding.FragmentLoginUserBinding
import com.example.aplikasipetshop.ui.test.LoginTestActivity
import com.example.aplikasipetshop.utils.UtilsContext.dpToPx
import com.google.firebase.auth.FirebaseAuth

class LoginUserFragment : Fragment() {

    private var _binding: FragmentLoginUserBinding? = null
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
        //return inflater.inflate(R.layout.fragment_login_user, container, false)
        _binding = FragmentLoginUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        playAnimation()
    }

    private fun setupView() {
        binding.apply {
            val editTextEmail = edtInputEmailLogin.editText
            val editTextPassword = edtInputPasswordLogin.editText

            editTextEmail?.id = R.id.edt_login_email
            editTextPassword?.id = R.id.edt_login_password

            editTextEmail?.doAfterTextChanged {
                buttonStatus()
            }

            editTextPassword?.doAfterTextChanged {
                buttonStatus()
            }

            btnLogin.setOnClickListener {
                val email: String = editTextEmail?.text.toString()
                val password: String = editTextPassword?.text.toString()

                showLoading(true)
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(requireActivity()) {
                        showLoading(false)
                        val intent = Intent(activity, HomeUserActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        requireActivity().finish()
                        Toast.makeText(activity, "Login Success", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { error ->
                        showLoading(false)
                        Toast.makeText(
                            activity,
                            error.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
            btnToRegister.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginUserFragment_to_registerUserFragment))
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        }
        else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun buttonStatus() {
        binding.apply {
            val isEmailError =
                edtInputEmailLogin.editText?.text.isNullOrEmpty() || edtInputEmailLogin.error != null
            val isPasswordError =
                edtInputPasswordLogin.editText?.text.isNullOrEmpty() || edtInputPasswordLogin.error != null
            btnLogin.isEnabled = !(isEmailError || isPasswordError)
        }
    }

    private fun playAnimation() {

        ObjectAnimator.ofFloat(binding.ivApp, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val email = ObjectAnimator.ofFloat(binding.edtInputEmailLogin, View.ALPHA, 1f).setDuration(700)
        val password = ObjectAnimator.ofFloat(binding.edtInputPasswordLogin, View.ALPHA, 1f).setDuration(700)
        val buttonLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(700)

        val textRegisterFade = ObjectAnimator.ofFloat(binding.tvToRegister, View.ALPHA, 1f).setDuration(0)
        val textRegisterY = ObjectAnimator.ofFloat(binding.tvToRegister, View.TRANSLATION_Y, requireActivity().dpToPx(40f), 0f).apply {
            duration = 1000
        }
        val buttonRegisterFade = ObjectAnimator.ofFloat(binding.btnToRegister, View.ALPHA, 1f).setDuration(0)
        val buttonRegisterY = ObjectAnimator.ofFloat(binding.btnToRegister, View.TRANSLATION_Y, requireActivity().dpToPx(40f), 0f).apply {
            duration = 1000
        }

        val togetherRegister = AnimatorSet().apply {
            playTogether(
                email,
                password,
                buttonLogin,
                textRegisterFade,
                textRegisterY,
                buttonRegisterFade,
                buttonRegisterY
            )
        }
        AnimatorSet().apply {
            playSequentially(togetherRegister)
            start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}