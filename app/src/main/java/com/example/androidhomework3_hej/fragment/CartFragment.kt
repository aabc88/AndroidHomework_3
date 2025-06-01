package com.example.androidhomework3_hej.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        //add.........초기 RecyclerView 구성......................
        
    }

    private fun observeCartItems() {
        //add........... flow 구독...............
       
    }

}