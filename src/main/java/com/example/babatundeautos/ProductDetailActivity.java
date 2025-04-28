package com.example.babatundeautos;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.babatundeautos.helpers.CartManager;
import com.example.babatundeautos.models.Car;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView carImage;
    TextView carName, carPrice, carDesc;
    EditText editQuantity;
    Button btnAddToCart, btnGoToCart;

    Car selectedCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        carImage = findViewById(R.id.carImage);
        carName = findViewById(R.id.carName);
        carPrice = findViewById(R.id.carPrice);
        carDesc = findViewById(R.id.carDesc);
        editQuantity = findViewById(R.id.editQuantity);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnGoToCart = findViewById(R.id.btnGoToCart);

        // Get selected car from Intent
        selectedCar = (Car) getIntent().getSerializableExtra("car");

        if (selectedCar != null) {
            carName.setText(selectedCar.getName());
            carDesc.setText(selectedCar.getDescription());
            carPrice.setText("$" + selectedCar.getPrice());
            Picasso.get().load(selectedCar.getImageUrl()).into(carImage);
        }

        btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(this, "Clicked Add to Cart", Toast.LENGTH_SHORT).show(); // test

            if (validateQuantity()) {
                addToCart();
                Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });


        btnGoToCart.setOnClickListener(v -> {
            if (validateQuantity()) {
                addToCart();
                startActivity(new Intent(ProductDetailActivity.this, CartActivity.class));
            }
        });
    }

    private boolean validateQuantity() {
        String quantityStr = editQuantity.getText().toString();
        if (TextUtils.isEmpty(quantityStr) || Integer.parseInt(quantityStr) <= 0) {
            editQuantity.setError("Enter a valid quantity");
            return false;
        }
        return true;
    }

    private void addToCart() {
        int quantity = Integer.parseInt(editQuantity.getText().toString());
        CartManager.addToCart(selectedCar, quantity);
    }
}
