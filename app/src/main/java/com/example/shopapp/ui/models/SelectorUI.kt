package com.example.shopapp.ui.models

import androidx.annotation.DrawableRes
import com.example.shopapp.ui.base.BaseUIModel
import java.util.*

sealed class SelectorUI : BaseUIModel<String> {

    data class Color(val colorHex: String, override val id: String = UUID.randomUUID().toString()) :
        SelectorUI()

    data class Tag(
        @DrawableRes val imageId: Int,
        val title: String, override val id: String = UUID.randomUUID().toString(),
    ) : SelectorUI()

    data class Image(val url: String, override val id: String = UUID.randomUUID().toString()) :
        SelectorUI()

}

fun String.toSelectorUIColor() = SelectorUI.Color(this)
fun String.toSelectorUIImage() = SelectorUI.Image(this)
