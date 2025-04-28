package com.example.babatundeautos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ThankYouActivity extends AppCompatActivity {
    Button btnBackToShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        btnBackToShop = findViewById(R.id.btnBackToShop);
        btnBackToShop.setOnClickListener(v -> {
            startActivity(new Intent(ThankYouActivity.this, ProductActivity.class));
            finish();
        });
    }
}
