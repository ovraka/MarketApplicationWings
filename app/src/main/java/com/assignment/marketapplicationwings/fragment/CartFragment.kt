package com.assignment.marketapplicationwings.fragment

import androidx.fragment.app.viewModels
import com.assignment.common.ext.BaseFragment
import com.assignment.marketapplicationwings.R
import com.assignment.marketapplicationwings.adapter.CartAdapter
import com.assignment.marketapplicationwings.databinding.LayoutCheckoutFragmentBinding
import com.assignment.marketapplicationwings.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment: BaseFragment<CartViewModel, LayoutCheckoutFragmentBinding>() {
    override val vm: CartViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_checkout_fragment
    private val adapter = CartAdapter()

    override fun initBinding(binding: LayoutCheckoutFragmentBinding) {
        super.initBinding(binding)
        binding.recycler.adapter = adapter

        binding.checkout.setOnClickListener {
            vm.deleteCart()
        }
        vm.cartData?.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }
    }
}