package com.example.l14_curd_database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class productsList extends AppCompatActivity {
ListView products;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productslist);
        products = findViewById(R.id.List);

        DBHelper dbHelper = new DBHelper(productsList.this);
        ArrayList<ProductModel> productArrayList =dbHelper.getAllProducts();
        MyAdapter adapter = new MyAdapter(this, productArrayList);
        ListView listView = findViewById(R.id.List);
        listView.setAdapter(adapter);
    }
}