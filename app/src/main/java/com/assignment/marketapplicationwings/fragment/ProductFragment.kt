package com.assignment.marketapplicationwings.fragment

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.assignment.common.ext.AppResponse
import com.assignment.common.ext.BaseFragment
import com.assignment.marketapplicationwings.R
import com.assignment.marketapplicationwings.adapter.ProductAdapter
import com.assignment.marketapplicationwings.databinding.LayoutProductFragmentBinding
import com.assignment.marketapplicationwings.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : BaseFragment<ProductViewModel, LayoutProductFragmentBinding>() {
    override val vm: ProductViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_product_fragment
    private val adapter = ProductAdapter({
        vm.navigate(ProductFragmentDirections.productToProductDetail(it))
    }) { cart ->
        vm.insertIntoCart(vm.userData.value.orEmpty(), cart)
        vm.navigate(ProductFragmentDirections.productToThrow())
    }


    override fun initBinding(binding: LayoutProductFragmentBinding) {
        super.initBinding(binding)
        binding.recycler.adapter = adapter
        callback()
        vm.productData.observe(viewLifecycleOwner) {
            when (it) {
                is AppResponse.AppResponseSuccess ->
                    adapter.listData.submitList(it.data.content)

                is AppResponse.AppResponseError ->
                    Toast.makeText(this.context, "Failed load data", Toast.LENGTH_SHORT).show()
            }
        }
        vm.userData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) vm.getTokenData()
        }
        vm.tokenData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) vm.getTokenData()
            else vm.getAllProduct(it)
        }
        binding.checkout.setOnClickListener {
            vm.navigate(ProductFragmentDirections.productToCart())
        }
    }

    fun callback() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.moveTaskToBack(true)
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}