package com.example.aplikasipetshop.utils

import android.view.inputmethod.EditorInfo
import com.google.android.material.textfield.TextInputEditText

class Utils {
    companion object{
        fun keyboardClearFocus(editText: TextInputEditText) {
            editText.setOnEditorActionListener { _, actionId: Int, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    editText.clearFocus()
                }
                false
            }
        }
    }
}