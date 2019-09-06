package com.example.mdoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBconnection extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mDoc.db";
    public DBconnection(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_SPECIALIZATION = "CREATE TABLE " + DatabaseContract.Specialization.TABLE_NAME + " ( "
                                            + DatabaseContract.Specialization._ID + " INTEGER PRIMARY KEY, "
                                            + DatabaseContract.Specialization.SPECIALIZATION_NAME + " TEXT, "
                                            + DatabaseContract.Specialization.SPECIALIZATION_DEPARTMENT + " TEXT,"
                                            + DatabaseContract.Specialization.SPECIALIZATION_DESCRIPTION + " TEXT " + " )";


        sqLiteDatabase.execSQL(SQL_CREATE_SPECIALIZATION);


        String SQL_CREATE_REGISTER = "CREATE TABLE " + DatabaseContract.register.TABLE_NAME +"("
                                      +DatabaseContract.register.REGISTER_FIRSTNAME +" TEXT, "
                                      +DatabaseContract.register.REGISTER_LASTNAME  + " TEXT, "
                                      +DatabaseContract.register.REGISTER_TYPE  + " TEXT, "
                                      +DatabaseContract.register.REGISTER_NIC   + " STRING PRIMARY KEY, "
                                      +DatabaseContract.register.REGISTER_EMAIL  + " TEXT,"
                                      +DatabaseContract.register.REGISTER_PASSWORD + " TEXT,"
                                      +DatabaseContract.register.REGISTER_CONTACTNUM +" TEXT,"
                                      +DatabaseContract.register.REGISTER_REGISTRATIONNO +" TEXT,"
                                      +DatabaseContract.register.REGISTER_STATUS +" TEXT" + ")";

        sqLiteDatabase.execSQL(SQL_CREATE_REGISTER);

        String SQL_CREATE_LOGIN =" CREATE TABLE " +DatabaseContract.login.TABLE_NAME +"("
                                 +DatabaseContract.login._ID + " INTEGER PRIMARY KEY, "
                                 +DatabaseContract.login.LOGIN_USERNAME + " TEXT,"
                                 +DatabaseContract.login.LOGIN_PASSWORD + " TEXT" +")";

        sqLiteDatabase.execSQL(SQL_CREATE_LOGIN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Specialization.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.register.TABLE_NAME );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.login.TABLE_NAME);
    }

    public long addNewSpecialization(DaoSpecialization specialization)
    {
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.Specialization.SPECIALIZATION_NAME,specialization.getSpecializationName());
        values.put(DatabaseContract.Specialization.SPECIALIZATION_DEPARTMENT,specialization.getSpecializationDepartment());
        values.put(DatabaseContract.Specialization.SPECIALIZATION_DESCRIPTION,specialization.getSpecializationDescription());

        long result = sd.insert(DatabaseContract.Specialization.TABLE_NAME,null,values);
        return result;

    }

    public boolean addregisterInfo(Daoregister register)
    {
        SQLiteDatabase reg = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.register.REGISTER_FIRSTNAME,register.getLastname());
        values.put(DatabaseContract.register.REGISTER_LASTNAME,register.getLastname());
        values.put(DatabaseContract.register.REGISTER_TYPE,register.getType());
        values.put(DatabaseContract.register.REGISTER_NIC,register.getNic());
        values.put(DatabaseContract.register.REGISTER_EMAIL,register.getEmail());
        values.put(DatabaseContract.register.REGISTER_PASSWORD,register.getPassword());
        values.put(DatabaseContract.register.REGISTER_CONTACTNUM,register.getContactnum());

        long result = reg.insert(DatabaseContract.register.TABLE_NAME,null,values);

        if(result > 0)
        {
            return true;
        }else{

            return false;
        }

    }

    public boolean addloginInfo(Daologin login)
    {

        SQLiteDatabase log = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.login.LOGIN_USERNAME,login.getUsername());
        values.put(DatabaseContract.login.LOGIN_PASSWORD,login.getPassword());

        long resultlog = log.insert(DatabaseContract.login.TABLE_NAME,null,values);

        if(resultlog > 0)
        {
            return true;
        }else{

            return false;
        }

    }

    public List<DaoSpecialization> getAllSpecialization()
    {
        DaoSpecialization specialization = new DaoSpecialization();
        SQLiteDatabase sd= getReadableDatabase();
        String[] projection = {DatabaseContract.Specialization.SPECIALIZATION_NAME};
        Cursor cursor = sd.query(DatabaseContract.Specialization.TABLE_NAME,projection,null,null,null,null,null);
        List<DaoSpecialization> specializationList = new ArrayList<>();

        while(cursor.moveToNext())
        {
            String specialName= cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Specialization.SPECIALIZATION_NAME));
            //specializationList.add(specialization);
            DaoSpecialization specialization1 = new DaoSpecialization(specialName);
            specializationList.add(specialization1);
        }

        return specializationList;
    }

    public Boolean checkSpecializationExist()
    {
        return true;
    }



}


