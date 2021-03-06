package com.example.otimus.ecommerceapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.models.Products;
import com.example.otimus.ecommerceapp.view.QuantityCounter;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.otimus.ecommerceapp.rest.ApiClient.BASE_URL;

/**
 * Created by Otimus on 9/3/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    List<Products> cartList;
    private final OnItemClickListener listener;
    Context context;

    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);

        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartAdapter.CartViewHolder holder, int position) {
        holder.productName.setText(cartList.get(position).getProductName());
        holder.productPrice.setText("$ " + String.valueOf(cartList.get(position).getProductPrice()));
        Picasso.with(context).load(BASE_URL + "images/" + cartList.get(position).getProductImage())
                .into(holder.productImage);

        holder.quantityCounter.setQuantity(cartList.get(position).getQuantity());
        holder.quantityCounter.setQuantityChangeListener(newQuantity -> {
            cartList.get(holder.getAdapterPosition()).setQuantity(newQuantity);
            listener.onQuantityChanged(cartList);
        });

        holder.btDelete.setOnClickListener(view -> {
                    listener.onDeleteItem(cartList.get(holder.getAdapterPosition()));
                    cartList.remove(holder.getAdapterPosition());
                    notifyDataSetChanged();
                }
        );

    }

    @Override
    public int getItemCount() {
        return cartList == null ? 0 : cartList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Products item);

        void onQuantityChanged(List<Products> cartList);

        void onDeleteItem(Products products);
    }

    public CartAdapter(List<Products> cartList, OnItemClickListener listener, Context context) {
        this.listener = listener;
        this.cartList = cartList;
        this.context = context;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productQuantity;
        ImageView productImage;
        ImageButton btDelete;
        QuantityCounter quantityCounter;

        public CartViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.tvc_productName);
            productPrice = (TextView) itemView.findViewById(R.id.tvc_productPrice);
            quantityCounter = (QuantityCounter) itemView.findViewById(R.id.quantityCounter);
            productImage = (ImageView) itemView.findViewById(R.id.ivc_imageIcon);
            btDelete = (ImageButton) itemView.findViewById(R.id.btDelete);
        }

    }
}
