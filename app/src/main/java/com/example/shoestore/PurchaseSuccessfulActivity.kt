package com.example.shoestore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shoestore.databinding.ActivitySuccessfulpurchaseBinding


class PurchaseSuccessfulActivity: AppCompatActivity() {

    companion object {
        const val TOTAL = "TOTAL"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySuccessfulpurchaseBinding.inflate(layoutInflater)
        val total = intent.getStringExtra(TOTAL).orEmpty()
        setContentView(binding.root)

        binding.apply {
            totalPrice.text = "$".plus(total)
            homeButton.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@PurchaseSuccessfulActivity, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}