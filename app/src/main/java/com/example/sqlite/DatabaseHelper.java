package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="contatcsDb";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="contacts";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PHONE="phone";
    private static final String KEY_AGE="age";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+
                "("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_NAME+" TEXT, "+KEY_PHONE+" TEXT, "+KEY_AGE+" TEXT"+");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public void addContact(String name,String phone_number,String age){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_PHONE,phone_number);
        values.put(KEY_AGE,age);
        database.insert(TABLE_NAME,null,values);
    }

    public ArrayList<ContactModel> fetchContacts(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        ArrayList<ContactModel> arrayList=new ArrayList<>();
        while(cursor.moveToNext()){
            ContactModel model=new ContactModel();
            model.id=cursor.getInt(0);
            model.name=cursor.getString(1);
            model.number=cursor.getString(2);
            model.age=cursor.getString(3);

            arrayList.add(model);
        }
        return arrayList;
    }
}
