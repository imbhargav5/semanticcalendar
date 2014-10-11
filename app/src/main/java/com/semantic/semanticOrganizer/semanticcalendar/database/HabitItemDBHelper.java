package com.semantic.semanticOrganizer.semanticcalendar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.semantic.semanticOrganizer.semanticcalendar.helpers.DBHelper;
import com.semantic.semanticOrganizer.semanticcalendar.models.Habit;
import com.semantic.semanticOrganizer.semanticcalendar.models.Tag;

/**
 * Created by Admin on 16-09-2014.
 */
public class HabitItemDBHelper {

    private final static String TABLE = DBHelper.HABITS_TABLE;
    private final static String TAG = "HabitSave";

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public HabitItemDBHelper(Context context) {
        this.context = context;

    }

    public HabitItemDBHelper open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();

    }


    public Cursor fetchAllHabits() {
        return database.query(TABLE, null, null, null, null, null, null);
    }

    public Habit getHabit(int id) {
        Cursor cursor = database.query(TABLE,null, DBHelper.COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Habit habit =cursorToHabit(cursor);
        // return contact
        return habit;
    }



    public int updateHabit(Habit habit, String habitText ,Integer habitTag) {

        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.HABIT_TEXT, habitText);
        values.put(DBHelper.HABIT_TAG, habitTag);



        // updating row
        database.update(TABLE, values, DBHelper.COLUMN_ID + " = ?",
                new String[] { String.valueOf(habit.getId()) });
        Toast.makeText(context,"Habit "+ habitText+" updated", Toast.LENGTH_LONG).show();


        return 0;
    }


    public void saveHabit(Habit habit) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID, (Integer.toString(Integer.parseInt(getPrevHabitId(TABLE)) + 1)));
        values.put(DBHelper.HABIT_TEXT, habit.getHabitText());

        //values.put("image_path", draft.getDraftImagePath());
        //TODO Location Insertion
        Log.d(TAG, values.toString());
        database.insert(TABLE, null, values);
        Toast.makeText(context,"Habit "+ habit.getHabitText()+" saved", Toast.LENGTH_LONG).show();

    }
    private String getPrevHabitId(String tableName) {
        try {
            Cursor cr = database.query(tableName, null, null, null, null, null, null);
            cr.moveToLast();
            return cr.getString(cr.getColumnIndex("id"));
        } catch (Exception e) {
            return "-1";
        }
    }

    public Habit cursorToHabit(Cursor cursor) {
//        + COLUMN_ID+ " integer primary key autoincrement, "
//                + HABIT_TEXT + " text not null, "
//                + HABIT_QUESTION+ " text , "
//                + HABIT_IS_ARCHIVED + " BOOLEAN DEFAULT 0, "
//                + COLUMN_CREATED_TIME +" DATETIME DEFAULT (DATETIME(current_timestamp, 'localtime')), "
//                + HABIT_TYPE + " integer DEFAULT 1, "
//                + HABIT_DAYS_CODE + " integer DEFAULT null, "
//                + HABIT_FREQUENCY + " integer DEFAULT null, "
//                + HABIT_DURATION + " integer DEFAULT null, "
//                + HABIT_TAG + " integer DEFAULT null, "
//                + HABIT_REQUEST_ID + " integer DEFAULT null"



        Habit habit = new Habit();
        habit.setId(cursor.getInt(0));
        habit.setHabitText(cursor.getString(1));
        habit.setHabitQuestion(cursor.getString(2));
        habit.setIsArchived(cursor.getInt(3)>0);

        habit.setCreatedTime(cursor.getString(4));
        if (!cursor.isNull(5)){

            habit.setHabitType(Habit.Type.values()[cursor.getInt(5)]);
        }
        if (!cursor.isNull(6)){
            habit.setDaysCode(cursor.getInt(6));
        }
        if (!cursor.isNull(7)){

            habit.setFrequency(cursor.getInt(7));
        }
        if (!cursor.isNull(8)){
            habit.setDuration(cursor.getInt(8));
        }

        if (!cursor.isNull(9)){
            habit.setTag(cursor.getInt(9));
        }

        if (!cursor.isNull(10)){
            habit.setRequestId(cursor.getInt(10));
        }


        return habit;

    }

    public Cursor fetchAllHabitsInTag(Tag tag) {
        return database.query(TABLE, null, DBHelper.HABIT_TAG +  "=" + tag.getTagId(), null, null, null, null);
    }

    public Cursor fetchAllHabitsSandbox() {
        return database.query(TABLE, null, DBHelper.HABIT_TAG +  " is null" , null, null, null, null);
    }
}