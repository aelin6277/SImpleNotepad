package com.example.simplenotepadddd;

//MVP arkitektur. Använder shared preferences för att spara data. Använder interfaceklasser för att
// kommunikation mellan VIEWS MainActivity och NoteActivity ska ske samt mellan PRESENTERklasserna
// MainPresenter och NotePresenter

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter presenter;
    private ListView noteListView;
    private TextView title1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);
        presenter = new MainPresenter(this, sharedPreferences);

        noteListView = findViewById(R.id.noteListView);
        // Implementera adapter och klickhantering för listan
        // ...
    }

    @Override
    public void showNoteTitles(List<String> noteTitles) {
        // Uppdatera listan med anteckningstitlar
        // ...
    }

    @Override
    public void navigateToNoteActivity(String noteTitle) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("noteTitle", noteTitle);
        startActivity(intent);
    }

    @Override
    public void navigateToCreateNoteActivity() {
        // Starta aktivitet för att skapa ny anteckning
        // ...
    }
}

//Jag måste nu implementera XML-layoutfiler för MainActivity och NoteActivity samt
// inkludera nödvändiga komponenter i dem. Kom ihåg att lägga till aktiviteterna i
// AndroidManifest.xml och hantera behörigheter för att spara data på enheten.
