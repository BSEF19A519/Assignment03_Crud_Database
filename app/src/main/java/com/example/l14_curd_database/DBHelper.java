package com.example.l14_curd_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

        public static final String PRODUCT_ID = "ProductID";
        public static final String PRODUCT_NAME = "ProductName";
        public static final String PRODUCT_QUANTITY = "ProductQuantity";
        public static final String PRODUCT_PRICE = "ProductPrice";
        public static final String PRODUCT_TABLE = "ProductTable";



        public DBHelper(@Nullable Context context) {
            super(context, "MyDB.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableSTatement = "CREATE TABLE " + PRODUCT_TABLE + "(" +
                    PRODUCT_ID + "Text PRIMARY KEY AUTOINCREMENT, " + PRODUCT_NAME + " Text, "
                    + PRODUCT_QUANTITY + " Integer, " + PRODUCT_PRICE + " Integer) ";
            db.execSQL(createTableSTatement);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
            onCreate(db);
        }
        public void  AddProduct(ProductModel PModel){
            SQLiteDatabase db = this.getWritableDatabase();
            //Hash map, as we did in bundles
            ContentValues cv = new ContentValues();
            cv.put(PRODUCT_ID, PModel.getId());
            cv.put(PRODUCT_NAME, PModel.getName());
            cv.put(PRODUCT_QUANTITY, PModel.getQuantity());
            cv.put(PRODUCT_PRICE, PModel.getPrice());
            db.insert(PRODUCT_TABLE, null, cv);
            db.close();
         
        }
   String DeleteProduct(ProductModel PModel)
    {
        SQLiteDatabase db = this.getReadableDatabase();
//        db.execSQL("DELETE * FROM " + PRODUCT_TABLE+"WHERE "+PRODUCT_ID+"="+PModel.getId(), null);


        db.close();
        return "Product successfully deleted";
    }
    String UpdateProduct(ProductModel PModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("UPDATE " + PRODUCT_TABLE+" SET"+PRODUCT_NAME+"="+PModel.getName()+","+PRODUCT_QUANTITY+"="+PModel.getQuantity()+","+PRODUCT_PRICE+"="+PModel.getPrice()+" WHERE "+PRODUCT_ID+"="+PModel.getId(), null);
        String sql = "UPDATE " + PRODUCT_TABLE+" SET"+PRODUCT_NAME+"="+PModel.getName()+","+PRODUCT_QUANTITY+"="+PModel.getQuantity()+","+PRODUCT_PRICE+"="+PModel.getPrice()+" WHERE "+PRODUCT_ID+"="+PModel.getId();

        db.execSQL(sql);
        db.close();
        return "Product successfully updated";
    }
    public ArrayList<ProductModel> getAllProducts() {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery("SELECT * FROM " + PRODUCT_TABLE, null);

            ArrayList<ProductModel> productArrayList = new ArrayList<>();

            // moving our cursor to first position.
            if (cursorCourses.moveToFirst()) {
                do {

                    productArrayList.add(new ProductModel(cursorCourses.getString(1),cursorCourses.getString(2),
                            cursorCourses.getInt(3),
                            cursorCourses.getDouble(4)));
                } while (cursorCourses.moveToNext());

            }

            cursorCourses.close();
            return productArrayList;
        }



}
