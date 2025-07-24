package com.mutuma.emart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class CheckoutBottomSheetFragment : DialogFragment() {
    private lateinit var totalPriceTextView: TextView
    private lateinit var confirmButton: TextView

    companion object {
        private const val ARG_TOTAL_PRICE = "totalPrice"

        fun newInstance(totalPrice: Double): CheckoutBottomSheetFragment {
            val fragment = CheckoutBottomSheetFragment()
            val args = Bundle()
            args.putDouble(ARG_TOTAL_PRICE, totalPrice)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_checkout, container, false)
        totalPriceTextView = view.findViewById(R.id.totalPriceTextView)
        confirmButton = view.findViewById(R.id.confirmButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val totalPrice = it.getDouble(ARG_TOTAL_PRICE, 0.0)
            totalPriceTextView.text = String.format("Total Price: € %.2f", totalPrice)
        }

        confirmButton.setOnClickListener {
            // Simulate order confirmation with a toast notification
            Toast.makeText(requireContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show()

            // Clear cart items and update shared preferences
            clearCartItems()

            // Dismiss the bottom sheet
            dismiss()
        }
    }

    private fun clearCartItems() {
        // Update shared preferences to clear cart items
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("cartProducts")
        editor.apply()
    }
}
