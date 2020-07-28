package com.example.shoestore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestore.databinding.ItemCheckoutcartBinding
import com.example.shoestore.models.ShoeModel
import com.example.shoestore.repository.ShoeStoreRepository
import kotlinx.android.synthetic.main.item_checkoutcart.view.*
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round

class CheckoutcartAdapter(
    private val cartMap: MutableMap<String, Int>,
    private val cartItems: MutableSet<String>,
    private val onClick: (type: String, id: String, position: Int) -> Unit
): RecyclerView.Adapter<CheckoutcartAdapter.CheckoutcartViewHolder>() {

    val df = DecimalFormat("#.###")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutcartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCheckoutcartBinding.inflate(layoutInflater, parent, false)
        return CheckoutcartViewHolder(binding)
    }

    override fun getItemCount() = cartItems.size

    override fun onBindViewHolder(holder: CheckoutcartViewHolder, position: Int) {
        val product = ShoeStoreRepository.getProductById(cartItems.elementAt(position))
        val productCount = cartMap[product.id]!!
        df.roundingMode = RoundingMode.CEILING
        val productTotal = productCount * product.cost.toDouble()
        holder.bind(product, productCount, df.format(productTotal))
        holder.itemView.remove_cart.setOnClickListener { onClick("remove", product.id, position) }
        holder.itemView.add_cart.setOnClickListener { onClick("add", product.id, position) }
    }

    class CheckoutcartViewHolder(private val binding: ItemCheckoutcartBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ShoeModel, count: Int, total: String) {
            binding.apply {
                productImage.setImageResource(product.image)
                productName.text = "${product.nameBrand} ${product.model}"
                productTotal.text = "$" + total.toString()
                productCount.text = count.toString()
            }
        }
    }
}