package com.example.l14_curd_database;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<ProductModel> {
    public MyAdapter(Context context, ArrayList<ProductModel> productArrayList)
    {
        super(context, 0, productArrayList);
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ProductModel product = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.productlayout, parent, false);
        TextView textViewName =  convertView.findViewById(R.id.name);
        TextView textViewID =  convertView.findViewById(R.id.id);
        TextView textViewQuantity = convertView.findViewById(R.id.quantity);
        TextView textViewPrice = convertView.findViewById(R.id.price);
        Button btnDelete,btnUpdate;
        btnDelete=convertView.findViewById(R.id.deleteProduct);
        btnUpdate=convertView.findViewById(R.id.updateProduct);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper  = new DBHelper(getContext());
                Toast.makeText(getContext(),dbHelper.DeleteProduct(product).toString(), Toast.LENGTH_SHORT).show();
//                dbHelper.DeleteProduct(product);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper  = new DBHelper(getContext());
                Toast.makeText(getContext(),dbHelper.UpdateProduct(product).toString(), Toast.LENGTH_SHORT).show();
//                dbHelper.UpdateProduct(product);
            }
        });

        textViewID.setText(product.getId());
        textViewName.setText(product.getName());
        textViewQuantity.setText(product.getQuantity());
        textViewPrice.setText((int) product.getPrice());
        return convertView;
    }


}
