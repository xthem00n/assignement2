package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class hoteldatabase extends SQLiteOpenHelper
{
    public static final String NAME_OF_DATA = "user.db";
    public static final String NAME_OF_TABLE = "clients";
    public static final String col_1 = "ID";
    public static final String col_2 = "name";
    public static final String col_3 = "email";
    public static final String col_4= "phone";
    public static final String col_5= "adults_number";
    public static final String col_6= "days_number";
    public static final String col_7= "price";

    public hoteldatabase(Context context)
    {
       super(context,NAME_OF_DATA,null,1);
    }


    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + NAME_OF_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT,email TEXT,phone TEXT,adults_number TEXT,days_number TEXT,price TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion )
    {
        db.execSQL("DROP TABLE IF EXISTS " + NAME_OF_DATA);
        onCreate(db);
    }

    public boolean insertData(String name, String phone, String email,String adultsnumber,String days,String PRICE)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,phone);
        contentValues.put(col_4,email);
        contentValues.put(col_5,adultsnumber);
        contentValues.put(col_6,days);
        contentValues.put(col_7,PRICE);


        long result = db.insert(NAME_OF_TABLE,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }


    public boolean updateData(String ID, String name, String phone, String email,int adultsnumber,int days,int PRICE)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(col_1,ID);
        contentValues.put(col_2,name);
        contentValues.put(col_3,phone);
        contentValues.put(col_4,email);
        contentValues.put(col_5,adultsnumber);
        contentValues.put(col_6,days);
        contentValues.put(col_7,PRICE);

        db.update(NAME_OF_TABLE, contentValues,"ID=?",new String[]{ID});

        return true;
    }

    public Integer deleteData(String ID)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(NAME_OF_TABLE,"ID=?",new String[]{ID});
    }


    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from " +NAME_OF_TABLE,null); return cursor;
    }
}

