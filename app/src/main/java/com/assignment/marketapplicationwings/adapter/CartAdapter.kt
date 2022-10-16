package com.assignment.marketapplicationwings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.common.ext.Count
import com.assignment.common.table.CartTable
import com.assignment.marketapplicationwings.databinding.LayoutCheckoutItemBinding
import java.text.NumberFormat
import java.util.*

class CartAdapter(val setTotalPrice: (CartTable) -> Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    val differ = AsyncListDiffer(this, itemCallback)

    inner class CartViewHolder(val binding: LayoutCheckoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CartTable) {
            binding.data = data

            binding.remove.isEnabled = false

            binding.itemQuantity.setText("1")

            val locale = Locale("in", "ID")
            val currency = NumberFormat.getCurrencyInstance(locale)
            binding.subTotal.text = currency.format(Count.getSubTotal(data))
            binding.itemQuantity.addTextChangedListener {
                val totalPrice = if (!it.isNullOrBlank()) {
                    data.quantity = it.toString().toInt()
                    Count.getSubTotal(data)
                } else {
                    0.0
                }
                binding.remove.isEnabled = it.toString().toInt() > 1
                binding.subTotal.text = currency.format(totalPrice)
                setTotalPrice(data)
            }
            binding.add.setOnClickListener {
                data.quantity += 1
                binding.itemQuantity.setText(data.quantity.toString())
            }
            binding.remove.setOnClickListener {
                data.quantity -= 1
                binding.itemQuantity.setText(data.quantity.toString())

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder =
        CartViewHolder(
            LayoutCheckoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<CartTable>() {
            override fun areItemsTheSame(oldItem: CartTable, newItem: CartTable): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CartTable, newItem: CartTable): Boolean {
                return false
            }

        }
    }
}