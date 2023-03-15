package com.example.pe01_se151101.adapter;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pe01_se151101.dto.CarDTO;
import com.example.pe01_se151101.provider.CarProvider;

public class DataAdapter extends SQLiteOpenHelper {

    private ContentResolver contentResolver;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CarSQLite";
    public static final String TABLE_NAME = "Cars";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";

    public DataAdapter(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        contentResolver = context.getContentResolver();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_BOOKS_TABLE = "CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_PRICE + " INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public String loadDataAdapter() {
        String result = "";
        String query = "SELECT* FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            int result_2 = cursor.getInt(2);
            result += String.valueOf(result_0) + "        " + result_1 + "        " + result_2 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addDataAdapter(CarDTO Car) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, Car.getName());
        values.put(COLUMN_PRICE, Car.getPrice());
        contentResolver.insert(CarProvider.CONTENT_URI, values);
    }


    public boolean deleteDataAdapter(int id){
        boolean result = false;
        String selection ="ID = \"" + id + "\"";
        int rowDeleted = contentResolver.delete(CarProvider.CONTENT_URI,selection,null);
        if (rowDeleted > 0) {
            result = true;
        }
        return result;
    }

    public boolean updateDataAdapter(int ID, String name, int price) {
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_PRICE, price);
        boolean result = false;
        String selection = "ID = \"" + ID + "\"";
        int rowsUpdated =
                contentResolver.update(CarProvider.CONTENT_URI,args,selection,null);
        if (rowsUpdated > 0)
            result = true;
        return result;
    }
}

