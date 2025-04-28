package com.example.babatundeautos;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.babatundeautos.helpers.CartManager;

public class CheckoutActivity extends AppCompatActivity {

    EditText firstName, lastName, email, phone, address, cardNumber, expiryDate, cvv;
    Button btnSubmitOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        cardNumber = findViewById(R.id.cardNumber);
        expiryDate = findViewById(R.id.expiryDate);
        cvv = findViewById(R.id.cvv);
        btnSubmitOrder = findViewById(R.id.btnSubmitOrder);

        btnSubmitOrder.setOnClickListener(v -> {
            if (validateInputs()) {
                // Simulate order processing
                CartManager.clearCart();
                Intent intent = new Intent(CheckoutActivity.this, ThankYouActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(firstName.getText())) {
            firstName.setError("Required");
            return false;
        }

        if (TextUtils.isEmpty(lastName.getText())) {
            lastName.setError("Required");
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
            email.setError("Invalid email");
            return false;
        }

        if (phone.getText().toString().length() < 10) {
            phone.setError("Enter valid phone");
            return false;
        }

        if (TextUtils.isEmpty(address.getText())) {
            address.setError("Required");
            return false;
        }

        if (cardNumber.getText().toString().length() != 16) {
            cardNumber.setError("Card number must be 16 digits");
            return false;
        }

        if (!expiryDate.getText().toString().matches("(0[1-9]|1[0-2])\\/\\d{2}")) {
            expiryDate.setError("Enter expiry like MM/YY");
            return false;
        }

        if (cvv.getText().toString().length() != 3) {
            cvv.setError("CVV must be 3 digits");
            return false;
        }

        return true;
    }
}
