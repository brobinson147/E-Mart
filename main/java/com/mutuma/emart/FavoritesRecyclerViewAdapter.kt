package com.mutuma.emart

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class FavoritesRecyclerViewAdapter(
    private val favoriteProducts: MutableList<Product>, // Changed to MutableList
    private val context: Context
) : RecyclerView.Adapter<FavoritesRecyclerViewAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productNameTextView: TextView = itemView.findViewById(R.id.productName)
        var productDescriptionTextView: TextView = itemView.findViewById(R.id.productDescription)
        var productPriceTextView: TextView = itemView.findViewById(R.id.productPrice)
        var productImageView: ImageView = itemView.findViewById(R.id.productImage)
        var productDelete: ImageView = itemView.findViewById(R.id.deleteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fav_item, parent, false)
        return FavoriteViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val product = favoriteProducts[position]

        // Set data
        holder.productNameTextView.text = product.productName
        holder.productDescriptionTextView.text = product.productDescription
        holder.productPriceTextView.text = "€ ${product.productPrice}"
        Glide.with(holder.productImageView)
            .load(product.productImageUrl)
            .into(holder.productImageView)

        // Handle delete button click
        holder.productDelete.setOnClickListener {

            // Remove item from SharedPreferences
            removeFavoriteProductFromSharedPreferences(product)
            // Remove item from list
            favoriteProducts.removeAt(position)
            // Notify adapter
            notifyItemRemoved(position)

        }
    }

    private fun removeFavoriteProductFromSharedPreferences(product: Product) {
        val editor = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit()
        val gson = Gson()
        val json = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            .getString("favoriteProducts", null)
        val type = object : TypeToken<MutableList<Product>>() {}.type
        val favoriteProducts: MutableList<Product> = if (json != null) {
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }

        // Remove the product from SharedPreferences list
        favoriteProducts.remove(product)

        // Update SharedPreferences
        val updatedJson = gson.toJson(favoriteProducts)
        editor.putString("favoriteProducts", updatedJson)
        editor.apply()
    }


    override fun getItemCount(): Int {
        return favoriteProducts.size
    }



}
