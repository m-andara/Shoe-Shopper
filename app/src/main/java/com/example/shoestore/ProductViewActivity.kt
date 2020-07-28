package com.example.shoestore

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shoestore.databinding.ActivityProductviewBinding
import com.example.shoestore.repository.ShoeStoreRepository

class ProductViewActivity: AppCompatActivity() {

    companion object {
        const val PRODUCTID = "PRODUCTID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProductviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(PRODUCTID).orEmpty()
        val product = ShoeStoreRepository.getProductById(id)

        binding.apply {
            productImage.setImageResource(product.image)
            productDescription.text = product.description
            productPrice.text = "$".plus(product.cost)

            cartButton.setOnClickListener {
                makeToast()
                ShoeStoreRepository.getCart().addShoe(product)
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun makeToast() {
        val toast = Toast.makeText(this@ProductViewActivity, "Item added to cart", Toast.LENGTH_SHORT)
        toast.show()
    }
}