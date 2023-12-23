package com.example.jetsub.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("menu")
data class MenuEntity(

    @PrimaryKey
    val image: Int,
    val title: String,
    val national: String,
    val desc: String,
    val category: Int,
    val onCart: Int,
)
