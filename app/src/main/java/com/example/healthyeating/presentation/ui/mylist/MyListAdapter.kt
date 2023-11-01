package com.example.healthyeating.presentation.ui.mylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthyeating.databinding.RecyclerViewTotalBinding
import com.example.healthyeating.domain.entities.Product

class MyListAdapter : RecyclerView.Adapter<MyListAdapter.MyListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyListAdapter.MyListViewHolder {
        val binding = RecyclerViewTotalBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyListAdapter.MyListViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class MyListViewHolder(private val binding: RecyclerViewTotalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                dish.text = product.name
                squirrels.text = product.squirrels
                fats.text = product.fats
                carbohydrates.text = product.carbohydrates
                kilocalories.text = product.kilocalories
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}