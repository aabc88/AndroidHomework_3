package com.example.androidhomework3_hej.data

import com.example.androidhomework3_hej.model.CartAction
import com.example.androidhomework3_hej.model.CartItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReactiveShoppingCart(val scope: CoroutineScope) {
    private val _cartActions = MutableSharedFlow<CartAction>()

    val items: StateFlow<List<CartItem>> = _cartActions
        .scan(emptyList<CartItem>()) { currentItems, action ->
            when (action) {
                is CartAction.AddItem -> {
                    val existing = currentItems.find { it.id == action.item.id }
                    if (existing != null) {
                        currentItems.map { item ->
                            if (item.id == action.item.id) {
                                item.copy(quantity = item.quantity + action.item.quantity)
                            } else {
                                item
                            }
                        }
                    } else {
                        currentItems + action.item
                    }
                }
                is CartAction.RemoveItem -> {
                    currentItems.filter { it.id != action.itemId }
                }
            }
        }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val total: StateFlow<Int> = items
        .map { items -> items.sumOf { it.price * it.quantity } }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    fun addItem(item: CartItem) {
        scope.launch {
            _cartActions.emit(CartAction.AddItem(item))
        }
    }

    fun removeItem(itemId: Int) {
        scope.launch {
            _cartActions.emit(CartAction.RemoveItem(itemId))
        }
    }
}