package com.mutuma.emart

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoritesActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var favoriteProducts: MutableList<Product>
    private lateinit var favoritesRecyclerView: RecyclerView
    private lateinit var favoritesAdapter: FavoritesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigationHandler.setupBottomNavigation(this@FavoritesActivity, bottomNavigationView)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        favoriteProducts = loadFavoriteProducts()

        favoritesRecyclerView = findViewById(R.id.favRecyclerView)
        favoritesRecyclerView.layoutManager = LinearLayoutManager(this)
        favoritesAdapter = FavoritesRecyclerViewAdapter(favoriteProducts, this)
        favoritesRecyclerView.adapter = favoritesAdapter
    }

    private fun loadFavoriteProducts(): MutableList<Product> {
        val gson = Gson()
        val json = sharedPreferences.getString("favoriteProducts", null)
        val type = object : TypeToken<MutableList<Product>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }
}
