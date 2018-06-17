package com.kamo.roomdemo.dao;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.kamo.roomdemo.db.Application_Database;
import com.kamo.roomdemo.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class IUserDaoTest {


    private IUserDao userDao;
    private Application_Database db;
    static final String name="kamogelo";
    static final String name2="kamogelo";

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, Application_Database.class).build();
        userDao = db.userDao();
    }

    @Test
    public void addUser() throws Exception {
        List<User> userList = LiveDataTestUtil.getValue(userDao.getAllUsers());
        assertTrue(userList.isEmpty());
        userDao.addUser(createUser(name));
         userList = LiveDataTestUtil.getValue(userDao.getAllUsers());
        assertEquals(userList.get(0).getName(),name);
    }


    @Test
    public void deleteUser() throws Exception {

        List<User> userList = LiveDataTestUtil.getValue(userDao.getAllUsers());
        assertTrue(userList.isEmpty());
        userDao.addUser(createUser(name));
        userList = LiveDataTestUtil.getValue(userDao.getAllUsers());
        assertEquals(userList.get(0).getName(),name);
        userDao.deleteUser(userList.get(0));
        userList = LiveDataTestUtil.getValue(userDao.getAllUsers());
        assertTrue(userList.isEmpty());

    }

    @Test
    public void getAllUsers() throws Exception {
        userDao.addUser(createUser(name));
        userDao.addUser(createUser(name2));
        List<User> userList = LiveDataTestUtil.getValue(userDao.getAllUsers());
        assertEquals(userList.size(),2);
    }

    @Test
    public void getUserByName() throws Exception {
        userDao.addUser(createUser(name));
        userDao.addUser(createUser(name2));
        User user=userDao.getUserByName(name);
        assertEquals(user.getName(),name);
    }

    @Test
    public void updateUser() throws Exception {
        String newName="new kamogelo";
        User user=createUser(name);
        userDao.addUser(user);
        user=userDao.getUserByName(name);
        user.setName(newName);
        userDao.updateUser(user);
        assertNotNull(userDao.getUserByName(newName));
    }

    public static User createUser(String name){
        User user=new User();
        user.setName(name);
        return user;
    }

}