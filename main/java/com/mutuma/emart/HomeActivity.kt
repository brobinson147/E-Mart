package com.mutuma.emart

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class HomeActivity : AppCompatActivity() {

    private lateinit var homeRecyclerView: RecyclerView
    private lateinit var productList: MutableList<Product>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigationHandler.setupBottomNavigation(this@HomeActivity, bottomNavigationView)

        productList = mutableListOf()
        homeRecyclerView = findViewById(R.id.homeRecyclerView)
        homeRecyclerView.adapter = HomeRecyclerViewAdapter(productList, this@HomeActivity)
        homeRecyclerView.layoutManager = GridLayoutManager(this@HomeActivity, 2)

        databaseReference = FirebaseDatabase.getInstance().getReference("products")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                productList.clear()
                for (snapshot in dataSnapshot.children) {
                    val product = snapshot.getValue(Product::class.java)
                    product?.let { productList.add(it) }
                }
                homeRecyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@HomeActivity, "Error getting Products", Toast.LENGTH_SHORT).show()
            }
        })

//        val addButton: FloatingActionButton = findViewById(R.id.productAddBtn)
//        addButton.setOnClickListener {
//            showBottomSheetDialog()
//        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_add_product, null)
        bottomSheetDialog.setContentView(bottomSheetView)

        val productImageUrl = bottomSheetView.findViewById<EditText>(R.id.productImageUrl)
        val productName = bottomSheetView.findViewById<EditText>(R.id.productName)
        val productPrice = bottomSheetView.findViewById<EditText>(R.id.productPrice)
        val productDescription = bottomSheetView.findViewById<EditText>(R.id.productDescription)
        val submitButton = bottomSheetView.findViewById<Button>(R.id.submitProductButton)

        submitButton.setOnClickListener {
            val product = Product(
                productImageUrl = productImageUrl.text.toString(),
                productName = productName.text.toString(),
                productPrice = productPrice.text.toString(),
                productDescription = productDescription.text.toString()
            )
            addProductToDatabase(product)
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    private fun addProductToDatabase(product: Product) {
        val productId = databaseReference.push().key
        productId?.let {
            databaseReference.child(it).setValue(product)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to add product", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
