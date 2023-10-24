package com.example.simplenotepadddd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity implements NoteView {
    private NotePresenter presenter;
    private EditText titleEditText;
    private EditText textEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        SharedPreferences sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);
        presenter = new NotePresenter(this, sharedPreferences);

        titleEditText = findViewById(R.id.titleEditText);
        textEditText = findViewById(R.id.textEditText);

        Intent intent = getIntent();
        if (intent.hasExtra("noteTitle")) {
            //This means that the user clicked a note on the noteList
            String noteTitle = intent.getStringExtra("noteTitle");//get the noteTitle that the user clicked
            presenter.loadNoteData(noteTitle);//ask the presenter to load (and show) the selected Note from the user
        }
    }

    @Override
    public void showNoteTitle(String noteTitle) {
        titleEditText.setText(noteTitle);
    }

    @Override
    public void showNoteText(String noteText) {
        textEditText.setText(noteText);
    }

    @Override
    public void navigateToMain() {
        //go back to the main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveNote(View view) {
        presenter.saveNote(titleEditText.getText().toString(), textEditText.getText().toString());
    }
}
