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
        

    }


}