package com.example.britt_000.dogfinder.sql;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.britt_000.dogfinder.daos.UserDao;
import com.example.britt_000.dogfinder.model.User;

/**
 * Created by Britt on 1/18/2018.
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
