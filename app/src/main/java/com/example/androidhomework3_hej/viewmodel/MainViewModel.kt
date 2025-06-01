package com.example.androidhomework3_hej.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework3_hej.model.Product

class MainViewModel : ViewModel() {
    val itemList = MutableLiveData<MutableList<Product>>(mutableListOf())

    fun addToProductList(product: Product) {
        val currentList = itemList.value ?: mutableListOf()
        currentList.add(product)
        itemList.value = currentList
    }

    fun removeFromProductList(product: Product) {
        val currentList = itemList.value ?: return
        currentList.remove(product)
        itemList.value = currentList.toMutableList()
    }
}
