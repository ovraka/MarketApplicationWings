package com.assignment.marketapplicationwings.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.assignment.common.ext.BaseFragment
import com.assignment.common.ext.Count
import com.assignment.marketapplicationwings.R
import com.assignment.marketapplicationwings.adapter.CartAdapter
import com.assignment.marketapplicationwings.databinding.LayoutCheckoutFragmentBinding
import com.assignment.marketapplicationwings.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*
import java.util.zip.CheckedOutputStream

@AndroidEntryPoint
class CartFragment: BaseFragment<CartViewModel, LayoutCheckoutFragmentBinding>() {
    override val vm: CartViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_checkout_fragment
    val locale = Locale("in", "ID")
    val currency = NumberFormat.getCurrencyInstance(locale)
    private val adapter = CartAdapter{
        vm.subTotal[it.productCode] = it
        val total = vm.subTotal.map {
            Count.getSubTotal(it.value)
        }.sum()
        binding.totalPrice.text = currency.format(total)
    }

    override fun initBinding(binding: LayoutCheckoutFragmentBinding) {
        super.initBinding(binding)
        binding.recycler.adapter = adapter

        binding.checkout.setOnClickListener {
            vm.deleteCart()
            Toast.makeText(context, "Terimakasih product anda sedang dalam pengemasan", 1).show()
            vm.popBackStack()
        }
        vm.cartData?.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
            it.forEach {  cart ->
                vm.subTotal[cart.productCode] = cart
            }
            var totalPrice = 0.0
            vm.subTotal.forEach{
                totalPrice += it.value.quantity * it.value.price
            }

            binding.totalPrice.text = currency.format(totalPrice)
        }

    }
}