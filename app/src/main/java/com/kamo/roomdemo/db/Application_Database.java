package com.kamo.roomdemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kamo.roomdemo.dao.IUserDao;
import com.kamo.roomdemo.entity.User;


@Database(entities = {User.class}, version = 1)
public abstract class Application_Database  extends RoomDatabase {
    public abstract IUserDao userDao();
}
