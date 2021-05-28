package com.project.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;
    public static final String DATABASE_NAME = "allData";
    public static final String GENERAL_ITEMS = "generalItems";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS " + GENERAL_ITEMS + "(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, priceBefore TEXT, discountedPrice TEXT, discount TEXT, imageLink TEXT, productLink TEXT NOT NULL, tag TEXT NOT NULL, rating FLOAT, ratingCount TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GENERAL_ITEMS);
        db.execSQL("create table IF NOT EXISTS " + GENERAL_ITEMS + "(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, priceBefore TEXT, discountedPrice TEXT, discount TEXT, imageLink TEXT, productLink TEXT NOT NULL, tag TEXT NOT NULL, rating FLOAT, ratingCount TEXT)");
    }

    public Cursor getData(String tableName){
        try{
            SQLiteDatabase db = getReadableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM " + tableName,null);
            return res;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void insertGeneralItems(Product product){
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("title",product.getTitle());
            contentValues.put("priceBefore",product.getPriceBefore());
            contentValues.put("discountedPrice",product.getDiscountedPrice());
            contentValues.put("discount",product.getDiscount());
            contentValues.put("imageLink",product.getImageLink());
            contentValues.put("productLink",product.getProductLink());
            contentValues.put("tag",product.getTag());
            contentValues.put("rating",product.getRating());
            contentValues.put("ratingCount",product.getRatingCount());
            db.insert(GENERAL_ITEMS,null,contentValues);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public boolean deleteGeneralItems(String productLink){
        try{
            SQLiteDatabase db = getWritableDatabase();
            return db.delete(GENERAL_ITEMS,  "productLink = '" + productLink + "'", null) > 0;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean checkGeneral(String productLink){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + GENERAL_ITEMS + " WHERE productLink = '" + productLink + "'",null);
        if (res.getCount() > 0 ){
            return true;
        }else{
            return false;
        }
    }
}
