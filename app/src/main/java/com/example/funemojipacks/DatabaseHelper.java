package com.example.funemojipacks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Method;

/*
e.g.
Write in MainActivity.java


DatabaseHelper memeDb;

onCreate(){

...
memeDb = new DatabaseHelper(this);

}

public void addUser(View view) {
    boolean isInserted = memeDb.insertUser(username.getText().toString(),
    pwd.getText().toString());
    if (isInserted)
        System.out.printlin("A new record is created.");
    else
        System.out.printlin("Failed to add a new record.");

 */

/*
多行注释：操作（如 插入数据）
单行注释：具体表的操作
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Meme.db";

    private static final String User_TABLE_NAME = "User_table";
    private static final String Pic_TABLE_NAME = "Pic_table";
    private static final String Shared_TABLE_NAME = "Shared_table";
    private static final String Liked_TABLE_NAME = "Liked_table";

    private static final String Tab1_COL_1 = "User_ID";
    private static final String Tab1_COL_2 = "User_Name";
    private static final String Tab1_COL_3 = "User_Pwd";

    private static final String Tab2_COL_1 = "Pic_ID";
    private static final String Tab2_COL_2 = "Pic";
    private static final String Tab2_COL_3 = "Pic_Num_Liked";

    private static final String Tab3_COL_1 = "Shared_ID";
    private static final String Tab3_COL_2 = "Shared_User_ID";// Foreign Key
    private static final String Tab3_COL_3 = "Shared_Pic_ID";// Foreign Key

    private static final String Tab4_COL_1 = "Liked_ID";
    private static final String Tab4_COL_2 = "Liked_User_ID";// Foreign Key
    private static final String Tab4_COL_3 = "Liked_Pic_ID";// Foreign Key

    private static final int DATABASED_VERSION = 1;

    public DatabaseHelper(Context context) {
        // super(context, name, factor, version)
        super(context, DATABASE_NAME, null, DATABASED_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // username should be unique
        db.execSQL("create table " + User_TABLE_NAME + "(User_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "User_Name TEXT UNIQUE, User_Pwd TEXT)");
        db.execSQL("create table " + Pic_TABLE_NAME + "(Pic_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Pic BLOB, Pic_Num_Liked INTEGER)");
        db.execSQL("create table " + Shared_TABLE_NAME + "(Shared_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Shared_User_ID INTEGER, Shared_Pic_ID INTEGER,"
                + "FOREIGN KEY(Shared_User_ID) REFERENCES User_table(User_ID),"
                + "FOREIGN KEY(Shared_Pic_ID) REFERENCES Pic_table(Pic_ID))");
        db.execSQL("create table " + Liked_TABLE_NAME + "(Liked_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Liked_User_ID INTEGER, Liked_Pic_ID INTEGER,"
                + "FOREIGN KEY(Liked_User_ID) REFERENCES User_table(User_ID),"
                + "FOREIGN KEY(Liked_Pic_ID) REFERENCES Pic_table(Pic_ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Pic_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Shared_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Liked_TABLE_NAME);
        onCreate(db);
    }

    /*
        Insert Records
     */

   // Method to insert a record to the UserTable
    public boolean insertUser(String username, String userpwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tab1_COL_2, username);
        contentValues.put(Tab1_COL_3, userpwd);
        long result = db.insert(User_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // Method to insert a record to the PicTable
    public boolean insertPic(byte[] pic, String likenum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tab2_COL_2, pic);
        contentValues.put(Tab2_COL_3, likenum);
        long result = db.insert(Pic_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // Method to insert a record to the SharedTable
    public boolean insertShare(String userid, String picid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tab3_COL_2, userid);
        contentValues.put(Tab3_COL_3, picid);
        long result = db.insert(Shared_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // Method to insert a record to the LikedTable
    public boolean insertLike(String userid, String picid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tab4_COL_2, userid);
        contentValues.put(Tab4_COL_3, picid);
        long result = db.insert(Liked_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    /*
        showAllRecords
        默认降序
     */
    public Cursor getAllData(String tablename) {
        // 找出不同表的列id字符串名
        String colStr = "";
        if(tablename.equals("User_table")){
            colStr = Tab1_COL_1;
        }
        if(tablename.equals("Pic_table")){
            colStr = Tab2_COL_1;
        }
        if(tablename.equals("Shared_table")){
            colStr = Tab3_COL_1;
        }
        if(tablename.equals("Liked_table")){
            colStr = Tab4_COL_1;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        // asc 升序， desc 降序
        // Cursor res = db.rawQuery("SELECT * FROM " + tablename, null);
        Cursor res = db.rawQuery("SELECT * FROM " + tablename + " ORDER BY " + colStr + " DESC", null);
        return res;
    }

    /*
        Find records
     */

    // Method to find password in the user table
    public Cursor findUserRecord (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT User_Pwd FROM " + User_TABLE_NAME +" WHERE User_Name = '" + username + "'", null);
        return res;
    }

    // Method to find userID in the user table
    // input: username
    public Cursor findUserID (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT User_ID FROM " + User_TABLE_NAME +" WHERE User_Name = '" + username + "'", null);
        return res;
    }

    // Method to get just added user ID
    public int getJustAddedUserId(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur=db.rawQuery("select LAST_INSERT_ROWID() FROM " + User_TABLE_NAME,null);
        cur.moveToFirst();
        int id = cur.getInt(0);
        return id;
    }

    // Method to get just added picture ID
    public int getJustAddedPicID() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("select LAST_INSERT_ROWID() FROM " + Pic_TABLE_NAME, null);
        cur.moveToFirst();
        int id = cur.getInt(0);
        return id;
    }

    // Method to get picture shared by specific user
    public Cursor getSharedImg(String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT Pic_table.Pic, Pic_table.Pic_ID FROM Pic_table, Shared_table"
                + " WHERE Shared_table.Shared_User_ID = " +  "'" + userid +  "'"
                + " AND Shared_table.Shared_Pic_ID = Pic_table.Pic_ID", null);
        return res;
    }

    // Method to get picture liked by specific user
    public Cursor getLikedImg(String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT Pic_table.Pic, Pic_table.Pic_ID FROM Pic_table, Liked_table"
                + " WHERE Liked_table.Liked_User_ID = " +  "'" + userid +  "'"
                + " AND Liked_table.Liked_Pic_ID = Pic_table.Pic_ID", null);
        return res;
    }


    /*
        Update Records
     */

    // Method to update a record to the UserTable
    public boolean updateUser(String userid, String username, String userpwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tab1_COL_2, username);
        contentValues.put(Tab1_COL_3, userpwd);
        db.update(User_TABLE_NAME, contentValues, "User_ID = ?", new String[] {userid});
        return true;
    }

    // Method to update a record to the PicTable
    public boolean updatePic(String id, byte[] pic, String likenum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tab2_COL_2, pic);
        contentValues.put(Tab2_COL_3, likenum);
        db.update(Pic_TABLE_NAME, contentValues, "Pic_ID = ?", new String[] {id});
        return true;
    }

    // Method to update a record to the SharedTable
    public boolean updateShare(String picid, byte[] pic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // contentValues.put(Tab3_COL_2, userid);
        contentValues.put(Tab3_COL_3, picid);
        db.update(Shared_TABLE_NAME, contentValues, "Shared_ID = ?", new String[] {picid});
        return true;
    }

    // Method to update a record to the LikedTable
    public boolean updateLike(String picid, String likenum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tab4_COL_2, picid);
        contentValues.put(Tab4_COL_3, likenum);
        db.update(Liked_TABLE_NAME, contentValues, "Shared_Pic_ID = ?", new String[] {picid});
        return true;
    }



    /*
        Method to delete a record
     */
    public Integer deleteData (String tablename, String id) {
        // 找出不同表的列id字符串名
        String colStr = "";
        if(tablename.equals("User_table")){
            colStr = Tab1_COL_1;
        }
        if(tablename.equals("Pic_table")){
            colStr = Tab2_COL_1;
        }
        if(tablename.equals("Shared_table")){
            colStr = Tab3_COL_1;
        }
        if(tablename.equals("Liked_table")){
            colStr = Tab4_COL_1;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tablename, colStr + " = ?", new String[] {id});
    }

}
