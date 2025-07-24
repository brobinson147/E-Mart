package com.mutuma.emart

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartRecyclerViewAdapter(
    var productList: MutableList<Product>,
    private val context: Context
) : RecyclerView.Adapter<CartRecyclerViewAdapter.ProductViewHolder>() {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productNameTextView: TextView = itemView.findViewById(R.id.productCartName)
        var productPriceTextView: TextView = itemView.findViewById(R.id.productCartPrice)
        var productImageView: ImageView = itemView.findViewById(R.id.productCartImage)
        var productCartAdd: ImageButton = itemView.findViewById(R.id.plusebutton)
        var productCartSubtract: ImageButton = itemView.findViewById(R.id.minusbutton)
        var productAmountTextView: TextView = itemView.findViewById(R.id.productAmountTextView)
        var productDeleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_recyclerview, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productNameTextView.text = product.productName
        holder.productPriceTextView.text = "€ ${product.productPrice}"
        Glide.with(holder.productImageView.context)
            .load(product.productImageUrl)
            .into(holder.productImageView)

        val productQuantityMap = getProductQuantities()
        val quantity = productQuantityMap[product.productName] ?: 1
        holder.productAmountTextView.text = quantity.toString()

        holder.productCartAdd.setOnClickListener {
            var amount = holder.productAmountTextView.text.toString().toInt()
            amount += 1
            holder.productAmountTextView.text = amount.toString()
            productQuantityMap[product.productName ?: ""] = amount
            updateProductQuantitiesInSharedPreferences(productQuantityMap)
        }

        holder.productCartSubtract.setOnClickListener {
            var amount = holder.productAmountTextView.text.toString().toInt()
            if (amount > 1) {
                amount -= 1
                holder.productAmountTextView.text = amount.toString()
                productQuantityMap[product.productName ?: ""] = amount
                updateProductQuantitiesInSharedPreferences(productQuantityMap)
            }
        }

        holder.productDeleteButton.setOnClickListener {
            removeProductAt(position)
        }
    }

    private fun removeProductAt(position: Int) {
        val product = productList.removeAt(position)
        notifyItemRemoved(position)
        val productQuantityMap = getProductQuantities()
        productQuantityMap.remove(product.productName)
        updateProductQuantitiesInSharedPreferences(productQuantityMap)
        updateCartProductsInSharedPreferences()
    }

    private fun getProductQuantities(): MutableMap<String, Int> {
        val json = sharedPreferences.getString("productQuantities", null)
        val type = object : TypeToken<MutableMap<String, Int>>() {}.type
        return gson.fromJson(json, type) ?: mutableMapOf()
    }

    private fun updateProductQuantitiesInSharedPreferences(productQuantityMap: MutableMap<String, Int>) {
        val editor = sharedPreferences.edit()
        val productQuantitiesJson = gson.toJson(productQuantityMap)
        editor.putString("productQuantities", productQuantitiesJson)
        editor.apply()
    }

    private fun updateCartProductsInSharedPreferences() {
        val editor = sharedPreferences.edit()
        val productListJson = gson.toJson(productList)
        editor.putString("cartProducts", productListJson)
        editor.apply()
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
