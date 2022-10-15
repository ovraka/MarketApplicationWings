package com.assignment.marketapplicationwings.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.assignment.common.ext.BaseFragment
import com.assignment.marketapplicationwings.R
import com.assignment.marketapplicationwings.databinding.LayoutProductDetailFragmentBinding
import com.assignment.marketapplicationwings.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class ProductDetailFragment :
    BaseFragment<ProductDetailViewModel, LayoutProductDetailFragmentBinding>() {
    override val vm: ProductDetailViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_product_detail_fragment
    val navArgs by navArgs<ProductDetailFragmentArgs>()

    override fun initBinding(binding: LayoutProductDetailFragmentBinding) {
        super.initBinding(binding)

        val locale = Locale("in", "ID")
        val currency = NumberFormat.getCurrencyInstance(locale)

        binding.productName.text = navArgs.product.name
        binding.productDimension.text = navArgs.product.dimension

        if (navArgs.product.discount.toString().equals("0.0")) {
            binding.productPrice.text = currency.format(navArgs.product.price)
        } else {
            binding.productPrice.text =
                currency.format(navArgs.product.price - (navArgs.product.price * navArgs.product.discount / 100))
        }

        binding.toolbar.back.setOnClickListener {
            vm.popBackStack()
        }

        binding.buyButton.setOnClickListener {

        }
    }
}