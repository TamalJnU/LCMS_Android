package com.tamal.lcmsapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.tamal.lcmsapp.entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public Database(@Nullable Context context, @Nullable String name, int version, @Nullable SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    private static final String dbName = "androiddb";
    private static final int dbVersion = 1;
    private static final String tracksColumn = "tracks";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryRegister = "create table user (id integer primary key autoincrement, userId text, " +
                "name text, gender text, email text, address text, department text, startdate text)";
        db.execSQL(queryRegister);
    }

    public void addNewEmployee(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", user.getUserId());
        values.put("name", user.getName());
        values.put("gender", user.getGender());
        values.put("email", user.getEmail());
        values.put("address", user.getAddress());
        values.put("role", user.getRole());
        values.put("startdate", user.getStartDate());

        db.insert("user", null, values);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAllUsers() {
        HashMap<String, String> user;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from user ", null);

        ArrayList<HashMap<String, String>> userList = new ArrayList<>(c.getCount());

        if (c.moveToFirst()) {

            do {
                user = new HashMap<>();
                user.put("id", c.getString(0));
                user.put("userId", c.getString(1));
                user.put("name", c.getString(2));
                user.put("gender", c.getString(3));
                user.put("email", c.getString(4));
                user.put("address", c.getString(5));
                user.put("role", c.getString(7));
                user.put("startdate", c.getString(8));

                userList.add(user);

            } while (c.moveToNext());

        }
        db.close();
        return userList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int login(String email, String password){

        int result = 0;
        String[] arr = new String[2];
        arr[0] = email;
        arr[1] = password;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from user where name=? and password=? ", arr);
        if (c.moveToFirst()){
            return 1;
        }
        return 0;
    }

//    public ArrayList<HashMap<String ,String>> getEmployees() {
//        HashMap<String ,String> user;
//        SQLiteDatabase db = this.getWritableDatabase();
//        @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from employee ", null);
//
//        ArrayList<HashMap<String ,String>> userList = new ArrayList<>(c.getCount());
//
//        if (c.moveToFirst()){
//
//            do {
//                user = new HashMap<>();
//                user.put("id",c.getString(0));
//                user.put("name",c.getString(1));
//                user.put("gender",c.getString(2));
//                user.put("education",c.getString(3));
//                user.put("email",c.getString(4));
//                user.put("password",c.getString(5));
//
//                userList.add(employee);
//
//            } while (c.moveToNext());
//
//        }
//        db.close();
//        return userList;
//    }

    public boolean updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", user.getName());
        values.put("name", user.getName());
        values.put("gender", user.getGender());
        values.put("email", user.getEmail());
        values.put("address", user.getAddress());
        values.put("role", user.getRole());
        values.put("startdate", user.getStartDate());
        int result = db.update("user", values, "id = ?", new String[]{user.getId() + ""});
        db.close();
        return result > 0;
    }

    public boolean deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowCount = db.delete("user", "id = ?", new String[]{id + ""});
        db.close();
        return rowCount > 0;
    }

}

