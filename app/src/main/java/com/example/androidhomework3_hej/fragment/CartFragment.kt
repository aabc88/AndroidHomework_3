package com.example.androidhomework3_hej.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework3_hej.adapter.CartAdapter
import com.example.androidhomework3_hej.databinding.FragmentCartBinding
import com.example.androidhomework3_hej.model.CartItem
import com.example.androidhomework3_hej.viewmodel.MainViewModel

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    lateinit var cartItems: MutableList<CartItem>

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        cartItems = mutableListOf()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeCartItems()
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter(cartItems) { productId ->
            val product = viewModel.productList.value?.find { it.id == productId }
            product?.let { viewModel.removeFromProductList(it) }
        }
        binding.recyclerViewCart.run {
            adapter = this@CartFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeCartItems() {
        viewModel.productList.observe(viewLifecycleOwner) { updatedList ->
            val grouped = updatedList.groupBy { it.name }
            cartItems.clear()
            cartItems.addAll(
                grouped.map { (name, products) ->
                    val first = products.first()
                    CartItem(
                        id = first.id,
                        name = name,
                        price = first.price,
                        quantity = products.size
                    )
                }
            )
            val totalAmount = cartItems.sumOf { it.price * it.quantity }
            binding.textCartTotalAmount.text = "총액: ${String.format("%,d원", totalAmount)}"
            adapter.notifyDataSetChanged()
        }
    }

}