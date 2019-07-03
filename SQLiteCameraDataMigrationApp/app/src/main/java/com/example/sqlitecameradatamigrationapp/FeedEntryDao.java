package com.example.sqlitecameradatamigrationapp;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

class FeedEntryDao {

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_IMAGE + " TEXT )";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public void insert(String name, String phone, String email, String address, String image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, name);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, phone);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL, email);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS, address);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_IMAGE, image);

        FeedReaderDbHelper.getInstance().getDB().insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, contentValues);
    }

    public List<UserModel> getAllDetails() {
        List dbList = new ArrayList<UserModel>();
        Cursor cursor = FeedReaderDbHelper.getInstance().getDB().query(FeedReaderContract.FeedEntry.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            UserModel userModel = new UserModel();


            long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            userModel.setId(id);

            String name = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
            userModel.setName(name);

            String number = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));
            userModel.setNumber(number);

            String email = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL));
            userModel.setEmail(email);

            String address = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS));
            userModel.setAddress(address);

            String image = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_IMAGE));
            userModel.setImage(image);

            dbList.add(userModel);
        }

        return dbList;
    }

    public UserModel getDetailsById(long id) {
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor cursor = FeedReaderDbHelper.getInstance().getDB().query(FeedReaderContract.FeedEntry.TABLE_NAME, null, selection, selectionArg, null, null, null);

        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        UserModel dbModel = new UserModel();

        dbModel.setId(id);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
        dbModel.setName(name);

        String number = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));
        dbModel.setNumber(number);

        String email = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL));
        dbModel.setEmail(email);

        String address = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS));
        dbModel.setAddress(address);

        String image = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_IMAGE));
        dbModel.setImage(image);

        return dbModel;
    }

    public void update(long id, String name, String number, String email, String address,String image) {
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArg = new String[]{String.valueOf(id)};

        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, name);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, number);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL, email);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS, address);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_IMAGE, image);

        FeedReaderDbHelper.getInstance().getDB().update(FeedReaderContract.FeedEntry.TABLE_NAME, contentValues, selection, selectionArg);

    }

    public void delete(long id) {
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArg = new String[]{String.valueOf(id)};

        FeedReaderDbHelper.getInstance().getDB().delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArg);
    }

    public void deleteAll() {
        FeedReaderDbHelper.getInstance().getDB().delete(FeedReaderContract.FeedEntry.TABLE_NAME, null, null);
        //FeedReaderDbHelper.getInstance().getDB().execSQL("delete from "+ FeedReaderContract.FeedEntry.TABLE_NAME);

    }
}
