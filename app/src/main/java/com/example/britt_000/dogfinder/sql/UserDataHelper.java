//package com.example.britt_000.dogfinder.sql;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import com.example.britt_000.dogfinder.model.User;
//
///**
// * Created by Britt on 1/14/2018.
// */
//
//public class UserDataHelper extends DatabaseHelper{
//
//    public void addUser(User user){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_NAME, user.getName());
//        values.put(COL_EMAIL, user.getEmail());
//        values.put(COL_PASSWORD, user.getPassword());
//
//        db.insert(TABLE_NAME, null, values);
//        db.close();
//    }
//
//    public boolean checkUser(String email){
//        String [] columns = {
//                COL_ID
//        };
//        SQLiteDatabase db = this.getWritableDatabase();
//        String selection = COL_EMAIL + " = ?";
//        String [] selectionArgs = { email };
//
//        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
//
//        if(cursorCount > 0){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean checkUser(String email, String password){
//        String [] columns = {
//                COL_ID
//        };
//        SQLiteDatabase db = this.getWritableDatabase();
//        String selection = COL_EMAIL + " = ?" + " AND " + COL_PASSWORD + " = ?";
//        String [] selectionArgs = { email, password };
//
//        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
//
//        if(cursorCount > 0){
//            return true;
//        }
//        return false;
//    }
//}
