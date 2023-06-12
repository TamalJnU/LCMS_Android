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

import com.tamal.lcmsapp.entity.Leave;
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
                "name text, gender text, email text, address text, role text, startdate text)";
        db.execSQL(queryRegister);

        String leaveApplicationSave = "CREATE TABLE leave_application (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userId TEXT, " +
                "name TEXT, " +
                "email TEXT, " +
                "role TEXT,"+
                "apply_date TEXT," +
                "leave_from TEXT, " +
                "leave_to TEXT, " +
                "description TEXT )";
        db.execSQL(leaveApplicationSave);
    }

    public void addNewUser(User user) {
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

    public void addLeaveApplication(Leave leave) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", leave.getUserId());
        values.put("name", leave.getName());
        values.put("email", leave.getEmail());
        values.put("role", leave.getRole());
        values.put("apply_date", leave.getApply_date());
        values.put("leave_from", leave.getLeave_from());
        values.put("leave_to", leave.getLeave_to());
        values.put("description", leave.getDescription());

        db.insert("leave_application", null, values);
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
                user.put("role", c.getString(6));
                user.put("startdate", c.getString(7));

                userList.add(user);

            } while (c.moveToNext());

        }
        db.close();
        return userList;
    }

    public ArrayList<HashMap<String, String>> getLeaveList() {

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from  leave_application", null);

        ArrayList<HashMap<String, String>> leaveList = new ArrayList<>(c.getCount());
        HashMap<String, String> list;
        if (c.moveToFirst()) {

            do {
                list = new HashMap<>();
                list.put("id", c.getString(0));
                list.put("userId", c.getString(1));
                list.put("name", c.getString(2));
                list.put("rmail", c.getString(3));
                list.put("role", c.getString(4));
                list.put("apply_date", c.getString(5));
                list.put("leave_from", c.getString(6));
                list.put("leave_to", c.getString(7));
                list.put("description", c.getString(8));

                leaveList.add(list);

            } while (c.moveToNext());

        }
        db.close();
        return leaveList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int login(String role, String userId){

        int result = 0;
        String[] arr = new String[2];
        arr[0] = role;
        arr[1] = userId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from user where role=? and userId=? ", arr);
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
        values.put("id", user.getId());
        values.put("userId", user.getUserId());
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

    public boolean updateLeave(Leave leave) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", leave.getId());
        values.put("userId", leave.getUserId());
        values.put("name", leave.getName());
        values.put("email", leave.getEmail());
        values.put("role", leave.getRole());
        values.put("apply_date", leave.getApply_date());
        values.put("leave_from", leave.getLeave_from());
        values.put("leave_to", leave.getLeave_to());
        values.put("description", leave.getDescription());
        int result = db.update("leave_application", values, "id = ?", new String[]{leave.getId() + ""});
        db.close();

        return result > 0;
    };

    public boolean deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowCount = db.delete("user", "id = ?", new String[]{id + ""});
        db.close();
        return rowCount > 0;
    }

    public boolean deleteLeave(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowCount = db.delete("leave_application", "id = ?", new String[]{id + ""});
        db.close();
        return rowCount > 0;
    }

}

