package com.example.babatundeautos.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babatundeautos.R;
import com.example.babatundeautos.models.Car;
import com.example.babatundeautos.ProductDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;



public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    Context context;
    List<Car> carList;

    public CarAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.carName.setText(car.getName());
        holder.carDesc.setText(car.getDescription());
        holder.carPrice.setText("CA$" + car.getPrice());
        Picasso.get().load(car.getImageUrl()).into(holder.carImage);

        holder.btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(context, car.getName() + " added to cart", Toast.LENGTH_SHORT).show();
            // Add cart logic here
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("car", carList.get(position));
            context.startActivity(intent);
        });

        if (holder.carImage != null && car.getImageUrl() != null) {
            Picasso.get().load(car.getImageUrl()).into(holder.carImage);
        }

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        ImageView carImage;
        TextView carName, carDesc, carPrice;
        Button btnAddToCart;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.carImage);
            carName = itemView.findViewById(R.id.carName);
            carDesc = itemView.findViewById(R.id.carDesc);
            carPrice = itemView.findViewById(R.id.carPrice);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);

        }


    }
}
