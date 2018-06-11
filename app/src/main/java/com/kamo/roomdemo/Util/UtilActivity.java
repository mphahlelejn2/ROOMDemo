package com.kamo.roomdemo.Util;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kamo.roomdemo.user.UserFragment;

public class UtilActivity extends AppCompatActivity {
    public static void addFragment(FragmentManager fragmentManager, UserFragment fragment, int fragmentId) {
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(fragmentId,fragment);
        transaction.commit();
    }
}
