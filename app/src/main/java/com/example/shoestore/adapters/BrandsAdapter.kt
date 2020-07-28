package com.example.shoestore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestore.databinding.ItemBrandBinding

class BrandsAdapter(
    private val brands: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBrandBinding.inflate(layoutInflater, parent, false)
        return BrandsViewHolder(binding)
    }

    override fun getItemCount() = brands.size

    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        val brand:String = brands[position]
        holder.bind(brand)
        holder.itemView.setOnClickListener { onClick(brand) }
    }

    class BrandsViewHolder(private val binding: ItemBrandBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(brandName: String) {
            binding.apply {
                brandTitle.text = brandName
            }
        }
    }

}