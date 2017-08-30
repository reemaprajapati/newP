package com.example.otimus.ecommerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otimus.ecommerceapp.models.ItemClothing;
import com.example.otimus.ecommerceapp.R;

public class DetailActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart) {

            startActivity(new Intent(getApplicationContext(),CartActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        setTitle("Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        ItemClothing itemClothing = (ItemClothing) intent.getSerializableExtra("data");
        Log.d("data", itemClothing.getClothing_name());

        TextView detail_name, detail_price;
        ImageView detail_image;

        detail_name=(TextView)findViewById(R.id.detail_name);
        detail_price=(TextView)findViewById(R.id.detail_price);
        detail_image=(ImageView) findViewById(R.id.detail_image);

        detail_name.setText(itemClothing.getClothing_name());
        detail_price.setText(itemClothing.getClothing_price());
        detail_image.setImageResource(itemClothing.getClothing_image());

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorlayout);

        Button btn_addtocart=(Button)findViewById(R.id.btn_addtocart);
        btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Added to Cart", Snackbar.LENGTH_SHORT);

                snackbar.show();
            }
        });
        Button btn_buynow=(Button)findViewById(R.id.btn_buynow);

        btn_buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CheckOutActivity.class));
            }
        });







    }
}