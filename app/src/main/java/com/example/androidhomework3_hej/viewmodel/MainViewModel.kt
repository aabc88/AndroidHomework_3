package com.example.androidhomework3_hej.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework3_hej.model.Product

class MainViewModel : ViewModel() {
    val productList = MutableLiveData<MutableList<Product>>(mutableListOf())

    fun addToProductList(product: Product) {
        val currentList = productList.value ?: mutableListOf()
        currentList.add(product)
        productList.value = currentList
    }

    fun removeFromProductList(product: Product) {
        val currentList = productList.value ?: return
        currentList.remove(product)
        productList.value = currentList.toMutableList()
    }

    fun clearProductList() {
        productList.value = mutableListOf()
    }

}