package com.learning.myfinances;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.ICUUncheckedIOException;

import androidx.annotation.Nullable;

//db helper class that will create db and save information into the db.
public class DBHandler extends SQLiteOpenHelper {

    //declaring column names
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accounts.db";
    public static final String TABLE_NAME = "accounts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ACC_TYPE = "account_type";
    public static final String COLUMN_ACCNUM = "account_number";
    public static final String COLUMN_INITIAL_BALANCE = "initial_balance";
    public static final String COLUMN_CURRENT_BALANCE = "current_balance";
    public static final String COLUMN_PAYMENT_AMOUNT = "payment_amount";
    public static final String COLUMN_INTEREST_RATE = "interest_rate";

    //constructor, creates the db
    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override//on load setup the db table columns and expected data types
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ACC_TYPE + " TEXT, " +
                COLUMN_ACCNUM + " REAL, " + COLUMN_CURRENT_BALANCE + " REAL, " +
                COLUMN_INITIAL_BALANCE + " REAL, " + COLUMN_INTEREST_RATE + " REAL, " +
                COLUMN_PAYMENT_AMOUNT + " REAL)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //helper function to add data to the dbb
    public void addToDB( String accType, double initBalance, double currentBalance,
                         double interestRate, double paymentAmt, int accNumber){

        //setting up object that will store the values of the edit fields in a container with key value pairs for us to put into the db table
        ContentValues values = new ContentValues();

        //inserting data in container
        values.put(COLUMN_ACC_TYPE, accType);
        values.put(COLUMN_ACCNUM, accNumber);
        values.put(COLUMN_CURRENT_BALANCE, currentBalance);
        values.put(COLUMN_INITIAL_BALANCE, initBalance);
        values.put(COLUMN_INTEREST_RATE, interestRate);
        values.put(COLUMN_PAYMENT_AMOUNT, paymentAmt);

        SQLiteDatabase db = this.getWritableDatabase();

        //inserting into the table and closing the db
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
