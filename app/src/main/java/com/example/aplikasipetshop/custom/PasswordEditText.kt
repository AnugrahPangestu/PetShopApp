package com.example.aplikasipetshop.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import com.example.aplikasipetshop.utils.Utils
import com.google.android.material.textfield.TextInputEditText

class PasswordEditText : TextInputEditText {
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

    private var inputLayout: PasswordInputLayout? = null

    private fun init() {
        setupInitEditText()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        typeface = Typeface.DEFAULT
    }

    private fun setupInitEditText() {
        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                inputLayout?.error = if (s.isEmpty()) {
                    "Masukkan Password"
                } else if (s.length < 8) {
                    "Minimal 8 karakter"
                } else {
                    null
                }

            }
        })

        Utils.keyboardClearFocus(this)
    }

    fun setInputLayout(inputLayout: PasswordInputLayout) {
        this.inputLayout = inputLayout
    }
}