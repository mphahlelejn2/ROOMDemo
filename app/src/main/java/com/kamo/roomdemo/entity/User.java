package com.kamo.roomdemo.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import static com.kamo.roomdemo.entity.User.MY_USER;


@Entity(tableName = MY_USER)
public class User {

    public static final String MY_USER="My_User";
    public static final String DI="id";
    public static final String NAME="name";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=DI)
    private int id;
    @ColumnInfo(name=NAME)
    private String name;
    private String surname;
    private String email;
    private String phone;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
