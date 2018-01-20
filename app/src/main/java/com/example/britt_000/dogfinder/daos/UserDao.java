package com.example.britt_000.dogfinder.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.britt_000.dogfinder.model.User;

import java.util.List;

/**
 * Created by Britt on 1/18/2018.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where name LIKE  :name")
    User findByName(String name);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert
    void insertAll(User... users);

    @Update
    void updateUsers(User... users);

    @Delete
    void delete(User user);
}
