package com.example.aplikasipetshop.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

class EmailInputLayout : TextInputLayout {

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

    private val inputEditText: EmailEditText = EmailEditText(context).apply {
        setInputLayout(this@EmailInputLayout)
    }

    private fun init() {
        addView(inputEditText)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Email"
    }
}