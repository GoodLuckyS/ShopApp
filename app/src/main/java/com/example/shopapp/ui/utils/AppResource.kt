package com.example.shopapp.ui.utils

import com.example.shopapp.R
import com.example.shopapp.ui.models.SelectorUI
import java.util.*

object AppResource {
    val tagList = listOf(
        SelectorUI.Tag(R.drawable.ic_phone, "Phone", UUID.randomUUID().toString()),
        SelectorUI.Tag(R.drawable.ic_headphones, "Headphones", UUID.randomUUID().toString()),
        SelectorUI.Tag(R.drawable.ic_games, "Games", UUID.randomUUID().toString()),
        SelectorUI.Tag(R.drawable.ic_car, "Phone", UUID.randomUUID().toString()),
        SelectorUI.Tag(R.drawable.ic_furniture, "Furniture", UUID.randomUUID().toString()),
        SelectorUI.Tag(R.drawable.ic_kids, "Kids", UUID.randomUUID().toString()),
        SelectorUI.Tag(R.drawable.ic_kids, "Anything", UUID.randomUUID().toString()),
    )
}
