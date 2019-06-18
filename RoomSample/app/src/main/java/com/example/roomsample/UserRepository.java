package com.example.roomsample;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {
    private UserDao mUserDao;

    UserRepository() {
        UserRoomDatabase db = UserRoomDatabase.getInstance();
        mUserDao = db.userDao();
    }

    LiveData<List<User>> getAllUsers() {

        return mUserDao.getAllStoredUsers();
    }

    public void insert(User user) {
        mUserDao.insert(user);
    }

    public void delete(User user) {
        mUserDao.delete(user);
    }

    User getUserById(int id) {
        return mUserDao.findById(id);
    }

    public void update(User user) {
        mUserDao.update(user);
    }

    public void deleteAll() {
        mUserDao.deleteAll();
    }
}

