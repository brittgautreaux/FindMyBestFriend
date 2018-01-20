package com.example.britt_000.dogfinder.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.britt_000.dogfinder.model.User;

import java.util.Date;

/**
 * Created by britt_000 on 10/26/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "FindMyBestFriend.db";

    // User table
    private static final String TABLE_NAME_USER = "USER";
    private static final String COL_USER_ID = "ID";
    private static final String COL_USER_NAME = "NAME";
    private static final String COL_PASSWORD = "PASSWORD";
    private static final String COL_EMAIL = "EMAIL";
    private static final String COL_USER_CREATED_DATE = "CREATED_DATE";
    private static final String COL_USER_MODIFIED_DATE = "MODIFIED_DATE";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME_USER + " (" + COL_USER_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_USER_NAME + " TEXT, " +
            COL_PASSWORD + " TEXT, " + COL_EMAIL + " TEXT, " + COL_USER_CREATED_DATE + " TEXT, " +
            COL_USER_MODIFIED_DATE + " TEXT)";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_USER;

    // Pet table
    private static final String TABLE_NAME_PET = "PET";
    private static final String COL_PET_ID = "ID";
    private static final String COL_PET_NAME = "NAME";
    private static final String COL_PET_TYPE = "TYPE";
    private static final String COL_PET_BREED = "BREED";
    private static final String COL_PET_COLOR = "COLOR";
    private static final String COL_PET_AGE = "AGE";
    private static final String COL_PET_DESCRIPTION = "DESCRIPTION";
    private static final String COL_PET_CREATED_DATE = "CREATED_DATE";
    private static final String COL_PET_MODIFIED_DATE = "MODIFIED_DATE";

    private String CREATE_PET_TABLE = "CREATE TABLE " + TABLE_NAME_PET + " (" + COL_PET_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PET_NAME + " TEXT, " +
            COL_PET_TYPE + " TEXT, " + COL_PET_BREED + " TEXT, " + COL_USER_CREATED_DATE + " TEXT, " +
            COL_PET_COLOR + " TEXT, " + COL_PET_AGE + " TEXT, " + COL_PET_DESCRIPTION + " TEXT, " +
            COL_PET_CREATED_DATE + " TEXT, " + COL_PET_MODIFIED_DATE + " TEXT)";

    private String DROP_PET_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_PET;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PET_TABLE);
        onCreate(db);
    }
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_NAME, user.getName());
        values.put(COL_EMAIL, user.getEmail());
        values.put(COL_PASSWORD, user.getPassword());

        db.insert(TABLE_NAME_USER, null, values);
        db.close();
    }

    public boolean checkUser(String email){
        String [] columns = {
                COL_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_EMAIL + " = ?";
        String [] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_NAME_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password){
        String [] columns = {
                COL_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_EMAIL + " = ?" + " AND " + COL_PASSWORD + " = ?";
        String [] selectionArgs = { email, password };

        Cursor cursor = db.query(TABLE_NAME_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0){
            return true;
        }
        return false;
    }
}
