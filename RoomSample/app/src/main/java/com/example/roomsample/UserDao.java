package com.example.roomsample;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllStoredUsers();

    @Query("SELECT * FROM User WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM User where uid LIKE  :userId")
    User findById(int userId);

    @Query("DELETE FROM User")
    void deleteAll();

    /*
    @Query("SELECT * FROM user WHERE name LIKE :first AND last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);
*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... users);

    @Delete
    void delete(User user);

    @Query("UPDATE User SET name = :name, phone = :phone, email = :email, address=:address WHERE uid =:id")
    void update(String name, String phone, String email, String address, int id);

    @Update
    void update(User user);
}

