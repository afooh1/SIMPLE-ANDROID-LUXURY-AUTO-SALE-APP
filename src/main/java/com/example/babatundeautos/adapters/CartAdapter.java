package com.example.babatundeautos.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babatundeautos.R;
import com.example.babatundeautos.helpers.CartManager;
import com.example.babatundeautos.models.CartItem;


import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<CartItem> cartItems;
    Runnable updateTotalCallback;

    public CartAdapter(Context context, List<CartItem> cartItems, Runnable updateTotalCallback) {
        this.context = context;
        this.cartItems = cartItems;
        this.updateTotalCallback = updateTotalCallback;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.itemName.setText(item.getCar().getName());
        holder.itemPrice.setText("Unit: CA$" + item.getCar().getPrice() + " | Total: CA$" + item.getTotalPrice());
        holder.editQuantity.setText(String.valueOf(item.getQuantity()));

        holder.btnRemove.setOnClickListener(v -> {
            CartManager.removeItem(position);
            notifyDataSetChanged();
            updateTotalCallback.run();
        });

        holder.editQuantity.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String newQty = holder.editQuantity.getText().toString();
                if (!TextUtils.isEmpty(newQty)) {
                    CartManager.updateQuantity(position, Integer.parseInt(newQty));
                    notifyDataSetChanged();
                    updateTotalCallback.run();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice;
        EditText editQuantity;
        Button btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            editQuantity = itemView.findViewById(R.id.editQuantity);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
