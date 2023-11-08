package com.example.aplikasipetshop.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

class PasswordInputLayout : TextInputLayout {

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

    private val inputEditText: PasswordEditText = PasswordEditText(context).apply {
        setInputLayout(this@PasswordInputLayout)
    }

    private fun init() {
        addView(inputEditText)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        endIconMode = END_ICON_PASSWORD_TOGGLE
        typeface = Typeface.DEFAULT
        hint = "Password"
    }
}