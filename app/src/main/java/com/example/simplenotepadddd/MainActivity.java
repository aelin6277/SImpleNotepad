package com.example.simplenotepadddd;

//MVP arkitektur. Använder shared preferences för att spara data. Använder interfaceklasser för att
// kommunicera mellan presenter och view. Leo los datos guardado en los presenterclasses.

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter presenter;
    private ListView noteListView;
    private TextView title1;
    private ArrayAdapter adapter;
    private List<String> noteTitlesAdapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);
        presenter = new MainPresenter(this, sharedPreferences);

        noteListView = findViewById(R.id.noteListView);//Pekar mot layouten

        noteTitlesAdapterList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteTitlesAdapterList);
        noteListView.setAdapter(adapter);

        presenter.loadNoteTitles();
        // klickhantering för listan
        //För att hantera klickhändelser för listan anvands en
        // OnItemClickListener för ListView.

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Hanterar klickhandelser for den valda anteckningen
                String selectedNoteTitle = noteTitlesAdapterList.get(position);

                //Visar den valda anteckningen i NoteActivity
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("noteTitle", selectedNoteTitle);

                //Starts/Shows the NoteActivity Activity
                startActivity(intent);
            }
        });

    }

    @Override
    public void showNoteTitles(List<String> noteTitles) {
        noteTitlesAdapterList.addAll(noteTitles); //The list of the adapter now has all the note Titles from sharedPreferences (Model)
        adapter.notifyDataSetChanged(); //Det som jag ber om i mainPresenter visas tack vare adaptern,
    }

    @Override
    public void navigateToNoteActivity(String noteTitle) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("noteTitle", noteTitle);
        startActivity(intent);
    }

    @Override
    public void navigateToCreateNoteActivity() {
        //intent to start the noteactivity
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }

    public void createNewNote(View view) {
        presenter.onCreateNote();
    }
}

//Jag måste nu implementera XML-layoutfiler för MainActivity och NoteActivity samt
// inkludera nödvändiga komponenter i dem. Kom ihåg att lägga till aktiviteterna i
// AndroidManifest.xml och hantera behörigheter för att spara data på enheten.
