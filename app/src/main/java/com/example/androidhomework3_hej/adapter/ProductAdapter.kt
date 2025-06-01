package com.example.androidhomework3_hej.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework3_hej.databinding.ItemProductBinding
import com.example.androidhomework3_hej.model.Product

class ProductViewHolder(
    val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root)

class ProductAdapter(
    val products: MutableList<Product>,
    val onAddToCart: (Product) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun getItemCount(): Int {
        return products.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        //add..................................
        
    }
}