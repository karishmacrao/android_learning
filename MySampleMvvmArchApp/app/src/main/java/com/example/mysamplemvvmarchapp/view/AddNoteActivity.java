package com.example.mysamplemvvmarchapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.mysamplemvvmarchapp.R;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE="com.example.mysamplemvvmarchapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION="com.example.mysamplemvvmarchapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY="com.example.mysamplemvvmarchapp.EXTRA_PRIORITY";
    private EditText titleET, descriptionET;
    private NumberPicker priorityNP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        titleET = findViewById(R.id.etTitle);
        descriptionET = findViewById(R.id.etDescription);
        priorityNP = findViewById(R.id.npPriority);
        priorityNP.setMinValue(1);
        priorityNP.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        setTitle("Add Note");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveNote:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote()
    {
        String title = titleET.getText().toString();
        String description= descriptionET.getText().toString();
        int priority = priorityNP.getValue();
        if(title.trim().isEmpty()|| description.trim().isEmpty())
        {
             titleET.setError("Enter appropriate title and description");
             return;
        }
        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);
        setResult(RESULT_OK,data);
        finish();
    }
}
