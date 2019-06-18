package com.example.roomsample;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private UserRepository mRepository;

    public UserViewModel() {
        mRepository = new UserRepository();
    }

    LiveData<List<User>> getAllUsers() {

        return mRepository.getAllUsers();
    }

    User findById(int id) {
        return mRepository.getUserById(id);
    }

    public void insert(User user) {
        mRepository.insert(user);
    }

    public void delete(User user) {
        mRepository.delete(user);
    }

    public void updated(User user) {
        mRepository.update(user);
    }


    public void deleteAll() {
        mRepository.deleteAll();
    }
}