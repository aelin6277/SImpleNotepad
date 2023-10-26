package com.example.simplenotepadddd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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

        titleEditText = findViewById(R.id.titleEditText);//Pekar variabeln titleEditText till xml-filens titleEditText
        textEditText = findViewById(R.id.textEditText);//Pekar variabeln textEditText till xml-filens textEditText

        Intent intent = getIntent();
        if (intent.hasExtra("noteTitle")) {
            //Det betyder att användaren klickade på en Note på noteList
            currentTitle = intent.getStringExtra("noteTitle");//hämta noteTitle som användaren klickade på
            presenter.loadNoteData(currentTitle);//be Presenter att ladda (och visa) den valda Note från användaren
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
        //gå tillbaka till main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveNote(View view) {
        Optional<String> oldTitleOptional;
        if (currentTitle != null) {
            //redigera en note
            oldTitleOptional = Optional.of(currentTitle);
        } else {
            //skapa en ny note
            oldTitleOptional = Optional.empty();
        }
        presenter.saveOrUpdateNote(
                titleEditText.getText().toString(),
                textEditText.getText().toString(),
                oldTitleOptional
        );
        currentTitle = null; //rencurrentTitle för säkerhets skull
    }

    public void deleteNote(View view) {
        presenter.deleteNote(titleEditText.getText().toString());

    }
}
