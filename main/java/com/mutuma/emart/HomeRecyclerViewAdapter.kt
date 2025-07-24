package com.mutuma.emart

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeRecyclerViewAdapter(
    var productList: MutableList<Product>,
    private val context: Context
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ProductViewHolder>() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productNameTextView: TextView = itemView.findViewById(R.id.productName)
        var productDescriptionTextView: TextView = itemView.findViewById(R.id.productDescription)
        var productPriceTextView: TextView = itemView.findViewById(R.id.productPrice)
        var productImageView: ImageView = itemView.findViewById(R.id.productImage)
        var buttonAddToCart: Button = itemView.findViewById(R.id.buttonAddToCart)
        var imageViewFavorite: ImageView = itemView.findViewById(R.id.imageViewFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_recyclerview, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        // Set data
        holder.productNameTextView.text = product.productName
        holder.productDescriptionTextView.text = product.productDescription
        holder.productPriceTextView.text = "€ ${product.productPrice}"
        Glide.with(holder.productImageView)
            .load(product.productImageUrl)
            .into(holder.productImageView)

        // Check and set favorite icon
        if (product.isFavorite) {
            holder.imageViewFavorite.setImageResource(R.drawable.ic_favorite_filled) // Use your filled favorite icon
        } else {
            holder.imageViewFavorite.setImageResource(R.drawable.ic_favorite) // Use your outline favorite icon
        }

        // Handle favorite icon click
        holder.imageViewFavorite.setOnClickListener {
            product.isFavorite = !product.isFavorite
            notifyItemChanged(position)
            saveFavoriteProduct(product)
        }

        // Handle add to cart button click
        holder.buttonAddToCart.setOnClickListener {
            saveSelectedProduct(product)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    private fun saveSelectedProduct(product: Product) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = sharedPreferences.getString("cartProducts", null)
        val type = object : TypeToken<MutableList<Product>>() {}.type
        val cartProducts: MutableList<Product> = if (json != null) {
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
        cartProducts.add(product)
        val updatedJson = gson.toJson(cartProducts)
        editor.putString("cartProducts", updatedJson)
        editor.apply()
    }

    private fun saveFavoriteProduct(product: Product) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = sharedPreferences.getString("favoriteProducts", null)
        val type = object : TypeToken<MutableList<Product>>() {}.type
        val favoriteProducts: MutableList<Product> = if (json != null) {
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
        if (product.isFavorite && !favoriteProducts.contains(product)) {
            favoriteProducts.add(product)
        } else {
            favoriteProducts.remove(product)
        }
        val updatedJson = gson.toJson(favoriteProducts)
        editor.putString("favoriteProducts", updatedJson)
        editor.apply()
    }
}
