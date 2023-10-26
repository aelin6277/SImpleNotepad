package com.example.simplenotepadddd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Optional;

public class NoteActivity extends AppCompatActivity implements NoteView {
    private NotePresenter presenter;
    private EditText titleEditText;
    private EditText textEditText;
    private String currentTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        SharedPreferences sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);
        presenter = new NotePresenter(this, sharedPreferences);

        titleEditText = findViewById(R.id.titleEditText);//Points the variable titleEditText to the xml's titleEditText
        textEditText = findViewById(R.id.textEditText);//Points the variable textEditText to the xml's textEditText

        Intent intent = getIntent();
        if (intent.hasExtra("noteTitle")) {
            //This means that the user clicked a note on the noteList
            currentTitle = intent.getStringExtra("noteTitle");//get the noteTitle that the user clicked
            presenter.loadNoteData(currentTitle);//ask the presenter to load (and show) the selected// Note from the user
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
        Optional<String> oldTitleOptional;
        if (currentTitle != null) {
            //editing a note
            oldTitleOptional = Optional.of(currentTitle);
        } else {
            // creating a new note
            oldTitleOptional = Optional.empty();
        }
        presenter.saveOrUpdateNote(
                titleEditText.getText().toString(),
                textEditText.getText().toString(),
                //set the old note title in case of update;  Optional.of(oldTitle).
                // In case of new note, use :
                oldTitleOptional
        );
        currentTitle = null; //clean current title just in case
    }

    public void deleteNote(View view) {
        presenter.deleteNote(titleEditText.getText().toString());

    }
}
