package com.example.l14_curd_database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editID,editName,editQuantity,editPrice;
    Button btnAdd,btnProducts;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editID=findViewById(R.id.productID);
       editName=findViewById(R.id.productName);
       editQuantity=findViewById(R.id.productQuantity);
       editPrice=findViewById(R.id.productPrice);
       btnAdd=findViewById(R.id.addProduct);
       btnProducts=findViewById(R.id.viewProducts);

       btnAdd.setOnClickListener(new View.OnClickListener() {
          ProductModel products;
           @Override
           public void onClick(View view) {
               try {
                   products = new ProductModel(editID.getText().toString(),editName.getText().toString(), Integer.parseInt(editQuantity.getText().toString()), Double.parseDouble(editPrice.getText().toString()));
               }
               catch (Exception e){
                   Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
               }
               DBHelper dbHelper  = new DBHelper(MainActivity.this);
               dbHelper.AddProduct(products);
           }
       });
        btnProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,  productsList.class);
                startActivity(intent);
            }
        });


    }
}