package com.example.aplikasipetshop.ui.user

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.FragmentRegisterBinding
import com.example.aplikasipetshop.databinding.FragmentRegisterUserBinding
import com.example.aplikasipetshop.utils.UtilsContext.dpToPx
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

class RegisterUserFragment : Fragment() {

    companion object {
        private const val TAG = "RegisterFragment"
    }

    private var _binding: FragmentRegisterUserBinding? = null
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
        _binding = FragmentRegisterUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        playAnimation()
    }

    private fun setupView() {

        binding.apply {
            val editTextEmail = edtInputEmailRegister.editText
            val editTextPassword = edtInputPasswordRegister.editText

            editTextEmail?.id = R.id.edt_register_email
            editTextPassword?.id = R.id.edt_register_password

            edtRegisterName.doAfterTextChanged {
                buttonStatus()
            }
            editTextEmail?.doAfterTextChanged {
                buttonStatus()
            }
            editTextPassword?.doAfterTextChanged {
                buttonStatus()
            }

            btnRegister.setOnClickListener {
                //Toast.makeText(activity,"Register",Toast.LENGTH_SHORT).show()
                val name: String = edtRegisterName.text.toString()
                val email: String = editTextEmail?.text.toString()
                val password: String = editTextPassword?.text.toString()

                showLoading(true)
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            showLoading(false)
                            val userUpdateProfile = userProfileChangeRequest {
                                displayName = name
                            }
                            val user = task.result.user
                            user!!.updateProfile(userUpdateProfile)
                                .addOnCompleteListener {
                                    Toast.makeText(activity, "Register Success", Toast.LENGTH_SHORT).show()
                                    findNavController().navigateUp()
                                }
                                .addOnFailureListener { error ->
                                    Toast.makeText(activity, "Register Failed", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    .addOnFailureListener { error2 ->
                        showLoading(false)
                        Toast.makeText(
                            activity,
                            "Authentication Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

            btnToLogin.setOnClickListener {
                findNavController().navigateUp()
            }
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
            val isNameError = edtRegisterName.text.isNullOrEmpty()
            val isEmailError =
                edtInputEmailRegister.editText?.text.isNullOrEmpty() || edtInputEmailRegister.error != null
            val isPasswordError =
                edtInputPasswordRegister.editText?.text.isNullOrEmpty() || edtInputPasswordRegister.error != null

            btnRegister.isEnabled =
                !(isNameError || isEmailError || isPasswordError)
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivAppRegister, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val nameFade = ObjectAnimator.ofFloat(binding.edtInputName, View.ALPHA, 1f).setDuration(700)
        val emailFade = ObjectAnimator.ofFloat(binding.edtInputEmailRegister, View.ALPHA, 1f).setDuration(500)
        val passwordFade = ObjectAnimator.ofFloat(binding.edtInputPasswordRegister, View.ALPHA, 1f).setDuration(500)
        val buttonRegister = ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(500)

        val textToLoginFade = ObjectAnimator.ofFloat(binding.tvToLogin, View.ALPHA, 1f).setDuration(0)
        val textLoginY = ObjectAnimator.ofFloat(binding.tvToLogin, View.TRANSLATION_Y, requireActivity().dpToPx(40f), 0f).apply {
            duration = 500
        }

        val buttonToLoginFade = ObjectAnimator.ofFloat(binding.btnToLogin, View.ALPHA, 1f).setDuration(0)
        val buttonLoginY = ObjectAnimator.ofFloat(binding.btnToLogin, View.TRANSLATION_Y, requireActivity().dpToPx(40f), 0f).apply {
            duration = 500
        }

        val togetherLogin = AnimatorSet().apply {
            playTogether(textToLoginFade, textLoginY, buttonToLoginFade, buttonLoginY)
        }

        val groupInput = AnimatorSet().apply {
            playSequentially(
                nameFade,
                emailFade,
                passwordFade,
                buttonRegister,
                togetherLogin
            )
        }

        AnimatorSet().apply {
            playTogether(groupInput)
            start()
        }
    }

}