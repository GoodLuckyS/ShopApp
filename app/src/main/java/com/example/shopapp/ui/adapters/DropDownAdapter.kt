package com.example.shopapp.ui.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.example.shopapp.R
import com.example.shopapp.ui.models.ProductUIModel
import com.example.shopapp.ui.models.ProductsAndCategoryUI
import com.example.shopapp.ui.models.mapToDropDownAdapter

class DropDownAdapter(
    context: Context,
    private val res: Int = R.layout.dropdown,
    private val tvId: Int = R.id.tvName,
    private val data: List<ProductsAndCategoryUI> = mutableListOf(),
) :
    ArrayAdapter<ProductUIModel>(
        context,
        res,
        tvId,
        data.mapToDropDownAdapter()
    )