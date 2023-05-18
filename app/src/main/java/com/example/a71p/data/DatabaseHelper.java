package com.example.a71p.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.a71p.model.user;
import com.example.a71p.util.Util;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }
    // Add the getLocations() method to retrieve the list of locations
    public List<String> getLocations() {
        List<String> locations = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT DISTINCT " + Util.LOCATION + " FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String location = cursor.getString(0);
                locations.add(location);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // Log the retrieved locations
        for (String location : locations) {
            Log.d("DatabaseHelper", "Location: " + location);
        }

        return locations;
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE "
                + Util.TABLE_NAME + "("
                + Util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.POST_TYPE + " TEXT, "
                + Util.NAME + " TEXT, "
                + Util.PHONENUMBER + " TEXT, "
                + Util.ITEM_TITLE + " TEXT, "
                + Util.DESCRIPTION + " TEXT, "
                + Util.DATE + " TEXT, "
                + Util.LOCATION + " TEXT)";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;
        db.execSQL(DROP_USER_TABLE);

        onCreate(db);
    }


    public long insertUser(user user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contextValues = new ContentValues();
        contextValues.put(Util.POST_TYPE, user.getPost_type());
        contextValues.put(Util.NAME, user.getName());
        contextValues.put(Util.PHONENUMBER, user.getPhonenumber());
        contextValues.put(Util.ITEM_TITLE, user.getItem_title());
        contextValues.put(Util.DESCRIPTION, user.getDescription());
        contextValues.put(Util.DATE, user.getDate());
        contextValues.put(Util.LOCATION, user.getLocation());

        long newRowId = db.insert(Util.TABLE_NAME, null, contextValues);
        db.close();
        return newRowId;
    }

    public Cursor getallusers() {
        Log.d("Databasehelper", "Reading all items");
        //Get reference to database
        SQLiteDatabase db = this.getWritableDatabase();
        //create query
        String query = "Select * from " + Util.TABLE_NAME;
        //run the query
        Cursor result = db.rawQuery(query, null);
        //display
        return result;
    }

    public boolean fetchUser(String name, String phonenumber) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                Util.TABLE_NAME,
                new String[]{Util.USER_ID},
                Util.NAME + "=? AND "
                        + Util.PHONENUMBER + "=?",
                new String[]{name, phonenumber}, //Values of the columns to match
                null,
                null,
                null);

        int numberOfRows = cursor.getCount();

        db.close();

        if (numberOfRows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor fetchUserList(String name, String description) {
        String[] columns = new String[]{
                Util.USER_ID,
                Util.NAME,
                Util.DESCRIPTION
        };

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = null;
        String[] selectionArgs = null;

        // Check if name and/or description are provided for searching
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(description)) {
            selection = Util.NAME + "=? AND " + Util.DESCRIPTION + "=?";
            selectionArgs = new String[]{name, description};
        } else if (!TextUtils.isEmpty(name)) {
            selection = Util.NAME + "=?";
            selectionArgs = new String[]{name};
        } else if (!TextUtils.isEmpty(description)) {
            selection = Util.DESCRIPTION + "=?";
            selectionArgs = new String[]{description};
        }

        Cursor cursor = db.query(
                Util.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int userIdColumnIndex = cursor.getColumnIndex(Util.USER_ID);
                int userId = (userIdColumnIndex != -1) ? cursor.getInt(userIdColumnIndex) : 0;
                int itemNameColumnIndex = cursor.getColumnIndex(Util.NAME);
                String itemName = (itemNameColumnIndex != -1) ? cursor.getString(itemNameColumnIndex) : "";
                int itemDesColumnIndex = cursor.getColumnIndex(Util.DESCRIPTION);
                String itemDes = (itemDesColumnIndex != -1) ? cursor.getString(itemDesColumnIndex) : "";

                // Log the retrieved data
                Log.d("TAG", "User ID: " + userId + ", Item Name: " + itemName + ", Description: " + itemDes);
            } while (cursor.moveToNext());
        }

        // Close the cursor after processing the data
        cursor.close();

        // Return the cursor
        return cursor;
    }



}