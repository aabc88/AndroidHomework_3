package com.example.androidhomework3_hej.model

sealed class CartAction {
    data class AddItem(val item: CartItem) : CartAction()
    data class RemoveItem(val itemId: Int) : CartAction()
}