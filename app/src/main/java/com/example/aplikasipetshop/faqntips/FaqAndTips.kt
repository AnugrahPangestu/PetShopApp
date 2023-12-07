package com.example.aplikasipetshop.faqntips

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FaqAndTips(
    val title: String? = null,
    val text: String? = null
): Parcelable