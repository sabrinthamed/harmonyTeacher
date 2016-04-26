package com.example.jhoang.mysqldemo;

/**
 * Created by chong on 4/23/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class Music_MvtAdapter {

    public static final String MYDATABASE_NAME = "Music_Mvt.db";
    public static final String MYDATABASE_TABLE = "MUSICMVT_TABLE";
    public static final int MYDATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_CONTENT1 = "MVTNUM";
    public static final String KEY_CONTENT2 = "NAMEOFMVT";
    public static final String KEY_CONTENT3 = "INSTRUMENT";
    public static final String KEY_CONTENT4 = "SHOWID";

    //create table MY_DATABASE (ID integer primary key, Content text not null);
    private static final String SCRIPT_CREATE_DATABASE =
            "create table " + MYDATABASE_TABLE + " ("
                    + KEY_ID + " integer primary key autoincrement, "
                    + KEY_CONTENT1 + " text not null, "
                    + KEY_CONTENT2 + " text not null, "
                    + KEY_CONTENT3 + " text not null, "
                    + KEY_CONTENT4 + " text not null);";

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Context context;

    public Music_MvtAdapter(Context c){
        context = c;
    }

   /* public Music_BookAdapter openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }*/

    public Music_MvtAdapter openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        sqLiteHelper.close();
    }

    /*public long insert(String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CONTENT1, content1);
        contentValues.put(KEY_CONTENT2, content2);
        return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
    }

    public int deleteAll(){
        return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
    }

    public void delete_byID(int id){
        sqLiteDatabase.delete(MYDATABASE_TABLE, KEY_ID+"="+id, null);
    }

    public void update_byID(int id, String v1, String v2){
        ContentValues values = new ContentValues();
        values.put(KEY_CONTENT1, v1);
        values.put(KEY_CONTENT2, v2);
        sqLiteDatabase.update(MYDATABASE_TABLE, values, KEY_ID+"="+id, null);
    }*/

    public Cursor queueAll(){
        String[] columns = new String[]{KEY_ID, KEY_CONTENT1, KEY_CONTENT2, KEY_CONTENT3, KEY_CONTENT4};
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
                null, null, null, null, null);

        return cursor;
    }

    public Cursor getAllData(String select) {
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM MUSICMVT_TABLE WHERE SHOWID = ? ", new String[]{select});
        return res;
    }

    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name,
                            CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(SCRIPT_CREATE_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }

    }

}
