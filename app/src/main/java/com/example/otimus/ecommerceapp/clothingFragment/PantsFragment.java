package com.example.otimus.ecommerceapp.clothingFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.activities.DetailActivity;
import com.example.otimus.ecommerceapp.adapters.ClothingAdapter;
import com.example.otimus.ecommerceapp.models.Products;
import com.example.otimus.ecommerceapp.rest.ApiClient;
import com.example.otimus.ecommerceapp.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PantsFragment extends Fragment {
     List<Products> clothingList;
     ClothingAdapter clothingAdapter;

    public PantsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_pants, container, false);

        RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.rec_pants);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        clothingList=new ArrayList<>();
//        clothingList=getList();

        clothingAdapter =new ClothingAdapter(clothingList, new ClothingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products item) {
                Intent intent=new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("data",item);
                startActivity(intent);
            }
        },getContext());
        recyclerView.setAdapter(clothingAdapter);

        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<List<Products>> call=apiInterface.getProducts(11);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                clothingList.addAll(response.body());
                clothingAdapter.notifyDataSetChanged();
                Log.d("newarrivals",clothingList.get(1).getProductName());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }

//    private List<ItemClothing> getList(){
//        List<ItemClothing> clothingsList=new ArrayList<>();
//        clothingsList.add(new ItemClothing(R.drawable.bagsand1,"Brown Leather","Rs.1120/-"));
//        clothingsList.add(new ItemClothing(R.drawable.bagsand2,"Gray Backpack","Rs.1120/-"));
//        clothingsList.add(new ItemClothing(R.drawable.bagsand3,"Maroon Wallet","Rs.1120/-"));
//        clothingsList.add(new ItemClothing(R.drawable.bagsand4," Grop Wallet","Rs.1120/-"));
//        clothingsList.add(new ItemClothing(R.drawable.bagsand5,"Jute Backpack","Rs.1120/-"));
//        clothingsList.add(new ItemClothing(R.drawable.bagsand6,"Laptop Bag","Rs.1120/-"));
//        clothingsList.add(new ItemClothing(R.drawable.bagsand7,"Check Wallet","Rs.1120/-"));
//
//
//        return clothingsList;
//    }


}
