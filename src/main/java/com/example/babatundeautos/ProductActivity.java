package com.example.babatundeautos;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import com.example.babatundeautos.adapters.CarAdapter;
import com.example.babatundeautos.models.Car;
import com.google.firebase.firestore.*;

import java.util.*;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Car> carList;
    CarAdapter carAdapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        recyclerView = findViewById(R.id.recyclerView);
        carList = new ArrayList<>();
        carAdapter = new CarAdapter(this, carList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(carAdapter);

        db = FirebaseFirestore.getInstance();
        loadCarsFromFirestore();
    }

    private void loadCarsFromFirestore() {
        db.collection("cars")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            Car car = doc.toObject(Car.class);
                            carList.add(car);
                        }
                        carAdapter.notifyDataSetChanged();
                    }
                });
    }
}
