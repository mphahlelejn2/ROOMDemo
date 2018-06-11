package com.kamo.roomdemo.user;

import android.arch.lifecycle.LiveData;

import com.kamo.roomdemo.db.Application_Database;
import com.kamo.roomdemo.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;


public class UserRepositoryImp implements IUserRepository {

    private Application_Database db;

    public UserRepositoryImp(Application_Database db) {
        this.db = db;
    }

    @Override
    public Maybe<User> getUserByName(String name) {
        return Maybe.just( db.userDao().getUserByName(name));
    }

    @Override
    public Completable addUser(User user) {
        return Completable.create(emitter -> {
            db.userDao().addUser(user);
            emitter.onComplete();
        });
    }

    @Override
    public Completable deleteUser(User user) {
        return Completable.create(emitter -> {
            db.userDao().deleteUser(user);
            emitter.onComplete();
        });
    }

    @Override
    public LiveData<List<User>> getAllUser() {
        return db.userDao().getAllUsers();
    }

    @Override
    public Completable updateUser(User user) {
        return Completable.create(emitter -> {
            db.userDao().updateUser(user);
            emitter.onComplete();
        });
    }
}
