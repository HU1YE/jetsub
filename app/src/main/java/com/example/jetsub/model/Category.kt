package com.example.jetsub.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.jetsub.R

data class Category(
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int
)

val dummyCategory = listOf(
    R.drawable.f1_icon to R.string.category_all,
    R.drawable.redbull_icon to R.string.category_redbull,
    R.drawable.asmart_icon to R.string.category_astonmartin,
    R.drawable.mc_icon to R.string.category_mclaren,
    R.drawable.merc_icon to R.string.category_mercedes,
    R.drawable.alpine_icon to R.string.category_alpine,
    R.drawable.haas_icon to R.string.category_haas,
    R.drawable.williams_icon to R.string.category_williams,
    R.drawable.ap_icon to R.string.category_alphatauri,
    R.drawable.ferarri_icon to R.string.category_ferarri,
    R.drawable.alfa_icon to R.string.category_scuderia,
).map { Category(it.first, it.second)}