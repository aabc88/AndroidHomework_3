package com.example.androidhomework3_hej.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework3_hej.adapter.ProductAdapter
import com.example.androidhomework3_hej.databinding.FragmentProductListBinding
import com.example.androidhomework3_hej.model.CartItem
import com.example.androidhomework3_hej.model.Product
import com.example.androidhomework3_hej.viewmodel.MainViewModel

class ProductListFragment : Fragment() {

    lateinit var binding: FragmentProductListBinding

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: ProductAdapter

    lateinit var products: MutableList<Product>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)

        // 상품 목록 (더미 데이터)
        products = mutableListOf(
            Product(1, "Product 1", 1000),
            Product(2, "Product 2", 2000),
            Product(3, "Product 3", 3000),
            Product(4, "Product 4", 4000),
            Product(5, "Product 5", 5000),
            Product(6, "Product 6", 6000)
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeCartStatus()
    }

    private fun setupRecyclerView() {
        //add............... 초기 recyclerview 구성
        adapter = ProductAdapter(products) {
            viewModel.addToProductList(it)
        }
        binding.recyclerViewProducts.run {
            adapter = this@ProductListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun observeCartStatus() {
        //add............. flow 구독...................
        viewModel.itemList.observe(viewLifecycleOwner) { productList ->
            val grouped = productList.groupBy { it.name }
            val cartItems = grouped.map { (_, group) ->
                val first = group.first()
                val quantity = group.size
                val total = quantity * first.price
                CartItem(first.id, first.name, first.price, quantity)
            }
            val totalCount = cartItems.sumOf { it.quantity }
            val totalPrice = cartItems.sumOf { it.price * it.quantity }
            binding.textCartItemCount.text = "상품 수: $totalCount"
            binding.textCartTotal.text = "총액: ${String.format("%,d원", totalPrice)}"
        }
    }
}
