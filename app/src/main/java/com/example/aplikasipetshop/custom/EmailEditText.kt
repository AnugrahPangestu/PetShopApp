package com.example.aplikasipetshop.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import com.example.aplikasipetshop.utils.Utils
import com.google.android.material.textfield.TextInputEditText

class EmailEditText: TextInputEditText {

    private lateinit var emailImage: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init()
    }

    private var inputLayout: EmailInputLayout? = null

    private fun init() {
        setupInitEditText()
    }

    private fun setupInitEditText() {
        //emailImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_email_24) as Drawable
        //setButtonDrawables(topOfTheText = emailImage)
        //setButtonDrawables(startOfTheText = emailImage)

        inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                after: Int
            ) {
                inputLayout?.error = if (Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    null
                } else if (s.isEmpty()) {
                    "Masukkan Email"
                } else {
                    "Email tidak sesuai format"
                }
            }
        })
        Utils.keyboardClearFocus(this)
    }

    fun setInputLayout(inputLayout: EmailInputLayout){
        this.inputLayout = inputLayout
    }

}