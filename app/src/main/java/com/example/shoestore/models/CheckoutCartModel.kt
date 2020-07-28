package com.example.shoestore.models

import com.example.shoestore.repository.ShoeStoreRepository
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round

class CheckoutCartModel {
    private var shoes = mutableMapOf<String, Int>()
    private var shoeSet = mutableSetOf<String>()
    private var total: Double = 0.00
    val df = DecimalFormat("#.###")

    fun getTotal(): String {
        total = 0.00
        for((key, value) in shoes) {
            val product = ShoeStoreRepository.getProductById(key)
            df.roundingMode = RoundingMode.CEILING
            val totalCost = product.cost.toDouble() * value
            total += totalCost
        }

        return df.format(total)
    }

    fun getShoeCount() = shoeSet.size

    fun addShoe(shoe: ShoeModel) {
        if(shoes.containsKey(shoe.id)) {
            shoes[shoe.id] = shoes[shoe.id]?.plus(1)!!
        } else {
            shoes.put(shoe.id, 1)
        }
        shoeSet.add(shoe.id)
    }

    fun removeShoe(shoe: ShoeModel) {
        if(shoes.containsKey(shoe.id) && shoes[shoe.id]!! == 1) {
            shoeSet.remove(shoe.id)
            shoes.remove(shoe.id)
        } else {
            shoes[shoe.id] = shoes[shoe.id]?.minus(1)!!
        }
    }

    fun getShoeMap(): MutableMap<String, Int> {
        return shoes
    }

    fun getShoeItems(): MutableSet<String> {
        return shoeSet
    }

    fun getTotalCountItems(): Int {
        var totalCount = 0
        for((key, value) in shoes) {
            totalCount += value
        }

        return totalCount
    }

    fun reset() {
        shoes.clear()
        shoeSet.clear()
        total = 0.00
    }
}