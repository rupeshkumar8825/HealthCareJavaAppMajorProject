package com.example.healthcareappmajorproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // default database is the sqlite database.
        // if the database does not exist already then this callback function will be
        // called in which we will be creating a new table as well
        String query = "create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // whenever we update the database version then this callback is called.
        // in this we could either alter some table or schema of db and delete some
        // of the data as well
        // for now we do not need this hence we will be skipping this
    }

    public void register(String username, String email, String password)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);

        // we will get a writable database now as we need to add this new entry in the user
        // table
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, contentValues);
        db.close();
    }

    public int login(String username, String password)
    {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;

        // check whether
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", str);
        if(cursor.moveToFirst())
        {
            result = 1;
        }

        return result;
    }
}
