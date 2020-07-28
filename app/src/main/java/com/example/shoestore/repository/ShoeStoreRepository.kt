package com.example.shoestore.repository

import com.example.shoestore.R
import com.example.shoestore.models.CheckoutCartModel
import com.example.shoestore.models.ShoeModel

object ShoeStoreRepository {

    private val brands = listOf("All", "Nike", "Adidas", "Jordan", "Reebok")
    private var shoeInventory = listOf<ShoeModel>(
        ShoeModel("Nike", "A", "Sports", "64.99", R.drawable.nike01, "n1"),
        ShoeModel("Nike", "B", "Sports", "59.99", R.drawable.nike02, "n2"),
        ShoeModel("Nike", "C", "Sports", "99.99", R.drawable.nike03, "n3"),
        ShoeModel("Adidas", "A", "Sports", "64.99", R.drawable.adidas01, "a1"),
        ShoeModel("Adidas", "B", "Sports", "59.99", R.drawable.adidas02, "a2"),
        ShoeModel("Adidas", "C", "Sports", "99.99", R.drawable.adidas03, "a3"),
        ShoeModel("Jordan", "A", "Sports", "64.99", R.drawable.jordan01, "j1"),
        ShoeModel("Jordan", "B", "Sports", "59.99", R.drawable.jordan02, "j2"),
        ShoeModel("Jordan", "C", "Sports", "99.99", R.drawable.jordan03, "j3"),
        ShoeModel("Reebok", "A", "Sports", "64.99", R.drawable.reebok01, "r1"),
        ShoeModel("Reebok", "B", "Sports", "59.99", R.drawable.reebok02, "r2"),
        ShoeModel("Reebok", "C", "Sports", "99.99", R.drawable.reebok03, "r3")
    )

    private val checkoutCart = CheckoutCartModel()

    fun getBrands() = brands

    fun getShoeInventory(brand: String): List<ShoeModel> {

        var filteredInventory = emptyList<ShoeModel>()
        when(brand) {
            "All" -> filteredInventory = shoeInventory
            else -> filteredInventory = shoeInventory.filter { item -> item.nameBrand.toLowerCase() == brand.toLowerCase() }
        }
        return filteredInventory
    }

    fun getProductById(id: String): ShoeModel = shoeInventory.filter { item -> item.id == id }[0]

    fun getCart() = checkoutCart
}