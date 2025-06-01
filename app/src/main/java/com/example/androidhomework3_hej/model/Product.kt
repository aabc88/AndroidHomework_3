package com.example.androidhomework3_hej.model

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val imageRes: Int = 0 // 이미지 리소스
)
