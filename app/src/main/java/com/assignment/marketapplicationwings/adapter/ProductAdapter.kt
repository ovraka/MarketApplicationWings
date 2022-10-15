package com.assignment.marketapplicationwings.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.common.response.product.Content
import com.assignment.marketapplicationwings.databinding.LayoutProductItemBinding
import java.text.NumberFormat
import java.util.*

class ProductAdapter(
    val navigateToProductDetail: (Content) -> Unit,
    val insertToCart: (Content) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    val listData = AsyncListDiffer<Content>(this, differ)

    inner class ProductViewHolder(val binding: LayoutProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(content: Content) {
            binding.productName.text = content.name

            val locale = Locale("in", "ID")
            val currency = NumberFormat.getCurrencyInstance(locale)

            if (content.discount.toString().equals("0.0")) {
                binding.discountPrice.visibility = View.GONE
                binding.price.text = currency.format(content.price)
            } else {
                binding.discountPrice.text = currency.format(content.price)
                binding.discountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.price.text =
                    currency.format((content.price - (content.price * content.discount / 100)))
            }
            binding.itemWrapper.setOnClickListener {
                navigateToProductDetail(content)
            }
            binding.buyButton.setOnClickListener {
                try {
                    content?.let { it -> insertToCart(it) }
                    Toast.makeText(
                        binding.root.context,
                        "Dimasukkan ke keranjang",
                        1
                    ).show()

                } catch (e: Exception) {
                    Toast.makeText(
                        binding.root.context,
                        "Gagal dimasukkan ke keranjang",
                        1
                    ).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            LayoutProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(listData.currentList[position])
    }

    override fun getItemCount(): Int {
        return listData.currentList.size
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem.productCode == newItem.productCode
            }

            override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem == newItem
            }

        }
    }
}