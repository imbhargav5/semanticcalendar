package com.semantic.semanticOrganizer.semanticcalendar.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin on 15-09-2014.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG="Data-base";


//Common Columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CREATED_TIME = "createdTime";

    //Declare Tables
    public static final String TASKS_TABLE ="tasks";
    public static final String TAGS_TABLE ="tags";

    public static final String NOTES_TABLE ="notes";
    public static final String TODO_TABLE ="todos";
    public static final String HABIT_TABLE ="habits";


    //TAsks Columns

    public static final String TASK_TITLE ="title";


//Note COlumns
    public static final String NOTE_DESCRIPTION ="description";
    public static final String NOTE_TAG ="tag_id";

//TODO COlumns
public static final String TODO_DESCRIPTION ="description";
public static final String TODO_IS_COMPLETED ="isCompleted";

//Tags Columns

    public static final String TAG_TITLE ="title";
    public static final String TAG_DESCRIPTION ="description";
    public static final String TAG_IS_ARCHIVED ="isArchived";


    private static final String DATABASE_NAME = "to_organize_db";
    private static final int DATABASE_VERSION = 10;

    private static final String CREATE_TABLE_TASKS = "create table if not exists "
            + TASKS_TABLE + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + TASK_TITLE + " text not null, "
            + COLUMN_CREATED_TIME +" DATETIME DEFAULT CURRENT_TIMESTAMP"
            +");";

    private static final String CREATE_TABLE_NOTES = "create table if not exists "
            + NOTES_TABLE + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + NOTE_DESCRIPTION+ " text not null, "
            + COLUMN_CREATED_TIME +" DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + NOTE_TAG + " integer"
            +");";

 private static final String CREATE_TABLE_TODOS = "create table if not exists "
            + TODO_TABLE + "("
         + COLUMN_ID+ " integer primary key autoincrement, "
         + TODO_DESCRIPTION + " text not null, "
         + TODO_IS_COMPLETED + " BOOLEAN DEFAULT FALSE, "
         + COLUMN_CREATED_TIME +" DATETIME DEFAULT CURRENT_TIMESTAMP"

         +");";



    private static final String CREATE_TABLE_TAGS = "create table if not exists "
            + TAGS_TABLE + "("
            + COLUMN_ID+ " integer primary key autoincrement, "
            + TAG_TITLE + " text not null, "
            + TAG_DESCRIPTION + " text, "
            + TAG_IS_ARCHIVED + " BOOLEAN DEFAULT FALSE, "
            + COLUMN_CREATED_TIME +" DATETIME DEFAULT CURRENT_TIMESTAMP"

            +");";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,CREATE_TABLE_TASKS);
        Log.d(TAG,CREATE_TABLE_NOTES);
        Log.d(TAG,CREATE_TABLE_TODOS);
        Log.d(TAG,CREATE_TABLE_TAGS);
        db.execSQL(CREATE_TABLE_TASKS);
        db.execSQL(CREATE_TABLE_NOTES);
        db.execSQL(CREATE_TABLE_TODOS);
        db.execSQL(CREATE_TABLE_TAGS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data"
        );
        db.execSQL("DROP TABLE IF EXISTS " + TASKS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TAGS_TABLE);
        onCreate(db);
    }
}
