package com.example.sqlitecameradatamigrationapp;

import android.provider.BaseColumns;

public class FeedReaderContract {
public FeedReaderContract(){}
public static class FeedEntry implements BaseColumns
{
    public static final String TABLE_NAME = "employee";
    public static final String COLUMN_NAME_TITLE = "name";
    public static final String COLUMN_NAME_SUBTITLE = "phone";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_ADDRESS = "address";
    public static final String COLUMN_NAME_IMAGE = "address";
}
}
