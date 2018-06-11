package com.kamo.roomdemo.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kamo.roomdemo.entity.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Jeffrey.Mphahlele on 3/19/2018.
 */

@Dao
public interface IUserDao {

    @Insert(onConflict=REPLACE)
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("Select * from "+User.MY_USER)
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM " + User.MY_USER+ " WHERE " + User.NAME + " = :name")
    User getUserByName(String name);

    @Update(onConflict = REPLACE)
    void updateUser(User user);
}
