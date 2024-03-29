package com.semantic.semanticOrganizer.semanticcalendar.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.semantic.semanticOrganizer.semanticcalendar.R;
import com.semantic.semanticOrganizer.semanticcalendar.database.HabitDBHelper;
import com.semantic.semanticOrganizer.semanticcalendar.helpers.DBHelper;
import com.semantic.semanticOrganizer.semanticcalendar.models.Habit;

import java.util.ArrayList;
import java.util.List;

public class ListHabitsActivity extends Activity {
    private HabitDBHelper habitDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_habits);

        final List<Habit> habitList = new ArrayList<Habit>();

        habitDBHelper = new HabitDBHelper(this);
        ArrayAdapter<Habit> adapter = new ArrayAdapter<Habit>(this,
                R.layout.tag_list_item,R.id.text1, Habit.getAllHabits(habitList,this)){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(R.id.text1);
                TextView text2 = (TextView) view.findViewById(R.id.text2);

                text1.setText(habitList.get(position).getHabitText());
                text2.setText(habitList.get(position).getCreatedTime());
                return view;
            }

        };
        ListView list = (ListView) findViewById(R.id.habitsListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Habit habit= (Habit) parent.getAdapter().getItem(position);
                Intent intent = new Intent(getApplicationContext(), UpdateHabitActivity.class);
                intent.putExtra(DBHelper.HABIT_TEXT, habit.getHabitText());
                intent.putExtra(DBHelper.HABIT_QUESTION, habit.getHabitQuestion());
                intent.putExtra(DBHelper.HABIT_DURATION, habit.getDuration());
                intent.putExtra(DBHelper.HABIT_DAYS_CODE, habit.getDaysCode());
                intent.putExtra(DBHelper.HABIT_FREQUENCY, habit.getFrequency());
                intent.putExtra(DBHelper.HABIT_IS_ARCHIVED, habit.getIsArchived());
                intent.putExtra(DBHelper.HABIT_TYPE, habit.getHabitType());
                intent.putExtra(DBHelper.COLUMN_CREATED_TIME, habit.getCreatedTime());
                intent.putExtra(DBHelper.HABIT_REQUEST_ID, habit.getRequestId());
                intent.putExtra(DBHelper.COLUMN_ID,habit.getId());
                startActivity(intent);
            }
        });
        list.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing_actvitiy_refactor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
