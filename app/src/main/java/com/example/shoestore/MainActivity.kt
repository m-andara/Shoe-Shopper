package com.example.shoestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestore.adapters.BrandsAdapter
import com.example.shoestore.adapters.InventoryAdapter
import com.example.shoestore.databinding.ActivityMainBinding
import com.example.shoestore.models.ShoeModel
import com.example.shoestore.repository.ShoeStoreRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var inventory = mutableListOf<ShoeModel>()
        inventory.addAll(ShoeStoreRepository.getShoeInventory("All"))
        val brands = ShoeStoreRepository.getBrands()

        binding.apply {

            val inventoryAdapter = InventoryAdapter(inventory, {
                    Shoe ->
                val intent = Intent(this@MainActivity, ProductViewActivity::class.java)
                intent.putExtra(ProductViewActivity.PRODUCTID, Shoe.id)
                startActivity(intent)
            }, {
                    shoe ->
                    makeToast()
                    ShoeStoreRepository.getCart().addShoe(shoe)
                }
            )

            val brandsAdapter = BrandsAdapter(brands) {
                brand ->
                inventory.clear()
                inventory.addAll(ShoeStoreRepository.getShoeInventory(brand))
                inventoryList.adapter?.notifyDataSetChanged()
            }

            inventoryList.adapter = inventoryAdapter
            brandList.adapter = brandsAdapter

            brandList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            inventoryList.layoutManager = GridLayoutManager(this@MainActivity, 2)
            brandList.setHasFixedSize(true)

            cartMenu.setOnClickListener {
                val intent = Intent(this@MainActivity, CheckoutcartActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun makeToast() {
        val toast = Toast.makeText(this@MainActivity, "Item added to cart", Toast.LENGTH_SHORT)
        toast.show()
    }
}