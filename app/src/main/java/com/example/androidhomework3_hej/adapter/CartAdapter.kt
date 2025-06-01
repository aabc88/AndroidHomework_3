package com.example.androidhomework3_hej.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework3_hej.databinding.ItemCartBinding
import com.example.androidhomework3_hej.model.CartItem

class CartViewHolder(
    val binding: ItemCartBinding
) : RecyclerView.ViewHolder(binding.root)

class CartAdapter(
    val items: MutableList<CartItem>,
    private val onRemoveFromCart: (Int) -> Unit
) : RecyclerView.Adapter<CartViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        //add..................................
        holder.binding.run {
            textItemName.text = items[position].name
            textItemPrice.text = "${items[position].price}원"
            textItemQuantity.text = "${items[position].quantity}개"
            var total = items[position].price * items[position].quantity
            textItemTotal.text = "${total}원"
            buttonRemoveFromCart.setOnClickListener {
                onRemoveFromCart(items[position].id)
            }
        }
    }
}
