package com.kamo.roomdemo.user;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;

import com.kamo.roomdemo.entity.User;

import java.util.List;

public interface IUser {
    interface View{
        void addNewUser(User user);
        Activity getActivity();
        void deleteUser(User user);
        RecyclerView getRecyclerView();
        void filter(String newText);
    }
    interface IUserViewModel {
        LiveData<List<User>> getListOfUsersFromDatabase();
        void getUserFromDatabase(String name);
        void deleteUserFromDatabase(User user);
        void updateUserOnDatabase(User user);
        void addUserToDatabase(User user);
    }
}
