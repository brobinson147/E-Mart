package com.mutuma.emart

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartActivity : AppCompatActivity() {

    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var productCartList: MutableList<Product>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigationHandler.setupBottomNavigation(this@CartActivity, bottomNavigationView)

        productCartList = getSelectedProductsFromSharedPreferences()
        cartRecyclerView = findViewById(R.id.cartRecyclerView)
        cartRecyclerView.adapter = CartRecyclerViewAdapter(productCartList, this)
        cartRecyclerView.layoutManager = LinearLayoutManager(this)

        val checkout: Button = findViewById(R.id.buttonCheckout)
        checkout.setOnClickListener {
            val totalPrice = calculateTotalPrice()
            showCheckoutBottomSheet(totalPrice)
        }
    }

    private fun getSelectedProductsFromSharedPreferences(): MutableList<Product> {
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("cartProducts", null)
        val type = object : TypeToken<MutableList<Product>>() {}.type

        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
    }

    private fun calculateTotalPrice(): Double {
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("productQuantities", null)
        val type = object : TypeToken<Map<String, Int>>() {}.type
        val productQuantityMap: Map<String, Int> = gson.fromJson(json, type) ?: mapOf()

        var totalPrice = 0.0
        for (product in productCartList) {
            val quantity = productQuantityMap[product.productName] ?: 1
            totalPrice += (product.productPrice?.toDoubleOrNull() ?: 0.0) * quantity
        }
        return totalPrice
    }

    private fun showCheckoutBottomSheet(totalPrice: Double) {
        val checkoutBottomSheet = CheckoutBottomSheetFragment.newInstance(totalPrice)
        checkoutBottomSheet.show(supportFragmentManager, "CheckoutBottomSheet")
    }
}
