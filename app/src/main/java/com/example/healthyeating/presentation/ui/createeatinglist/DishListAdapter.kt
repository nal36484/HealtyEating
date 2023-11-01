package com.example.healthyeating.presentation.ui.createeatinglist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthyeating.databinding.RecyclerViewDishesBinding
import com.example.healthyeating.domain.entities.Dish

class DishListAdapter(
    private val deleteClickListener: OnDeleteClickListener
) : RecyclerView.Adapter<DishListAdapter.DishViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DishListAdapter.DishViewHolder {
        val binding = RecyclerViewDishesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishListAdapter.DishViewHolder, position: Int) {
        holder.bind(differ.currentList[position], deleteClickListener)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class DishViewHolder(private val binding: RecyclerViewDishesBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(dish: Dish, listener: OnDeleteClickListener) {
            binding.apply {
                this.dish.text = dish.name
                weight.text = "${dish.weight} гр"
                quantity.text = "${dish.quantity} шт"
                delete.setOnClickListener {
                    listener.deleteDish(dish)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}