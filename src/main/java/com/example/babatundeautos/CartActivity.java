package com.example.babatundeautos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import com.example.babatundeautos.adapters.CartAdapter;
import com.example.babatundeautos.helpers.CartManager;
import com.example.babatundeautos.models.CartItem;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView cartRecyclerView;
    TextView totalText;
    Button btnCheckout;

    List<CartItem> cartItems;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalText = findViewById(R.id.totalText);
        btnCheckout = findViewById(R.id.btnCheckout);

        cartItems = CartManager.getCartItems();

        cartAdapter = new CartAdapter(this, cartItems, this::updateTotal);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        updateTotal();

        btnCheckout.setOnClickListener(v -> {
            if (cartItems.size() == 0) {
                Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
        });
    }

    private void updateTotal() {
        double total = CartManager.getTotalAmount();
        double tax = total * 0.13;
        totalText.setText("Total: CA$" + String.format("%.2f", total + tax));
    }
}
