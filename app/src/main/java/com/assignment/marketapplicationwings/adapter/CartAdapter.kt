package com.assignment.marketapplicationwings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.common.table.CartTable
import com.assignment.marketapplicationwings.databinding.LayoutCheckoutItemBinding

class CartAdapter() : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    val differ = AsyncListDiffer(this, itemCallback)

    inner class CartViewHolder(val binding: LayoutCheckoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CartTable) {
            binding.data = data
            binding.price.text = data.price.toString()
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