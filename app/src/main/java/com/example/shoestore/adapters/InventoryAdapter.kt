package com.example.shoestore.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestore.databinding.ItemInventoryBinding
import com.example.shoestore.models.ShoeModel
import kotlinx.android.synthetic.main.activity_productview.view.*
import kotlinx.android.synthetic.main.item_inventory.view.*

class InventoryAdapter(
    private var inventory: MutableList<ShoeModel>,
    private val onClick: (ShoeModel) -> Unit,
    private val onHeartClick: (ShoeModel) -> Unit
): RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemInventoryBinding.inflate(layoutInflater, parent, false)
        return InventoryViewHolder(binding)
    }

    override fun getItemCount() = inventory.size

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val item = inventory[position]
        holder.bind(item)
        holder.itemView.shoeImage.setOnClickListener { onClick(item) }
        holder.itemView.heart.setOnClickListener { onHeartClick(item) }
    }

    fun update(newInventory: List<ShoeModel>) {
        notifyDataSetChanged()
    }

    class InventoryViewHolder(private val binding: ItemInventoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(shoe: ShoeModel) {
            binding.apply {
                shoeImage.setImageResource(shoe.image)
            }
        }
    }
}