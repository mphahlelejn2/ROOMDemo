package com.kamo.roomdemo.user;


import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kamo.roomdemo.R;
import com.kamo.roomdemo.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;


public interface IUserRepository {
    Maybe<User> getUserByName(String name);
    Completable addUser(User user);
    Completable deleteUser(User user);
    LiveData<List<User>> getAllUser();
    Completable updateUser(User user);
}
