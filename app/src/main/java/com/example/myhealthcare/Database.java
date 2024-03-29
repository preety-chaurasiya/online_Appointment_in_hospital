package com.example.myhealthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class Database extends SQLiteOpenHelper {

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTableQuery = "CREATE TABLE users (Username TEXT, email TEXT, password TEXT)";
        db.execSQL(userTableQuery);

        String cartTableQuery = "CREATE TABLE cart (Username TEXT, product TEXT, price REAL, otype TEXT)";
        db.execSQL(cartTableQuery);

        String orderTableQuery = "CREATE TABLE orderplace (Username TEXT, fullname TEXT, address TEXT, contactno TEXT, pincode TEXT, date TEXT, time TEXT, price REAL, otype TEXT)";
        db.execSQL(orderTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // You can handle database upgrades here if needed.
    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("Username", username); // "username" to "Username"
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String[] str = new String[]{username, password}; // corrected array initialization
        SQLiteDatabase db = getReadableDatabase();
         Cursor c = db.rawQuery("SELECT * FROM users WHERE Username=? AND password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        c.close();
        db.close();
        return result;
    }

    public void addCart(String username, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("Username", username); // "username" to "Username"
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    public int checkCart(String username, String product) {
        int result = 0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM cart WHERE Username=? AND product=?", str); // corrected table name
        if (c.moveToFirst()) {
            result = 1;
        }

        db.close();
        return result;
    }

    public int removeCart(String username, String otype) {
        String[] str = new String[2];
        str[0] = username;
        str[1] =otype;
        SQLiteDatabase db = getWritableDatabase(); // use getWritableDatabase for delete operation
        db.delete("cart", "Username=? AND otype=?", str); // corrected table name and field name
        return 0;
    }

    public ArrayList getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("SELECT * FROM cart WHERE Username=? AND Otype=?", str); // corrected table name
        if(c.moveToFirst()) {
            do{
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);
            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }
    public void addOrder(String username, String fullname, String address, String contactno, String pincode, String date, String time, Float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contactno);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();


        Log.d("DatabaseDebug", "Order placed successfully");
    }




    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE Username= ?", str); // corrected table name
        if(c.moveToFirst()) {
            do{
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" +
                        c.getString(4) +"$"+ c.getString(5) + "$" + c.getString(6) + "$" +
                        c.getString(7) + "$" + c.getString(8));

            }while(c.moveToNext());
        }

        db.close();
        Log.d("DatabaseDebug", "Retrieved order data successfully"+ Arrays.toString(str));
        return arr;
    }

    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time) {
        int result = 0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE Username = ? AND fullname = ? AND address = ? AND contactno = ? AND date = ? AND time = ?", str);

        if (c.moveToFirst()) {
            result = 1;
        }

        c.close();
        db.close();

        Log.d("DatabaseDebug", "Checking appointment with parameters: " + Arrays.toString(str));


        return result;
    }




}
