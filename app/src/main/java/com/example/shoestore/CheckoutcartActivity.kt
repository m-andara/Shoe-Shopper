package com.example.shoestore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoestore.adapters.CheckoutcartAdapter
import com.example.shoestore.databinding.ActivityCheckoutcartBinding
import com.example.shoestore.models.ShoeModel
import com.example.shoestore.repository.ShoeStoreRepository
import kotlinx.android.synthetic.main.activity_checkoutcart.view.*

class CheckoutcartActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCheckoutcartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cartMap: MutableMap<String, Int>
        val cartItems: MutableSet<String>
        cartMap = ShoeStoreRepository.getCart().getShoeMap()
        cartItems = ShoeStoreRepository.getCart().getShoeItems()

        setTotalAndCount(binding)
        binding.cartList.apply {
            val adapter = CheckoutcartAdapter(cartMap, cartItems) {
                type, id, position ->
                val shoe = ShoeStoreRepository.getProductById(id)
                if (type == "add") ShoeStoreRepository.getCart().addShoe(shoe) else ShoeStoreRepository.getCart().removeShoe(shoe)
                setTotalAndCount(binding)
                cart_list.adapter?.notifyDataSetChanged()
            }
            cart_list.adapter = adapter
            cart_list.layoutManager = LinearLayoutManager(this@CheckoutcartActivity)
            setHasFixedSize(true)
        }
        binding.purchaseButton.setOnClickListener {
            if(ShoeStoreRepository.getCart().getShoeCount() > 0) {
                val intent = Intent(this@CheckoutcartActivity, PurchaseSuccessfulActivity::class.java)
                intent.putExtra("TOTAL", ShoeStoreRepository.getCart().getTotal())
                ShoeStoreRepository.getCart().reset()
                setTotalAndCount(binding)
                binding.cartList.adapter?.notifyDataSetChanged()
                startActivity(intent)
            }
        }
    }

    fun setTotalAndCount(binding: ActivityCheckoutcartBinding) {
        binding.apply {
            cartItemCount.text = "Total ".plus(ShoeStoreRepository.getCart().getTotalCountItems()).plus(" items")
            cartTotalprice.text = "$".plus(ShoeStoreRepository.getCart().getTotal())
        }
    }
}