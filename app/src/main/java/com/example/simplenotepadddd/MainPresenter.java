package com.example.simplenotepadddd;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainPresenter { //=i presenter anges de metoder som kommer hantera data mellan model och view.
    private MainView view;
    private SharedPreferences sharedPreferences;

    public MainPresenter(MainView view, SharedPreferences sharedPreferences) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
    }

    public void loadNoteTitles() { //=har alla notetitles i shared preferences
        Map<String, ?> savedNotes = sharedPreferences.getAll();
        List<String> noteTitles = new ArrayList<>(savedNotes.keySet());
        view.showNoteTitles(noteTitles); //="View, visa dem"
    }

    public void onNoteSelected(String noteTitle) {
        view.navigateToNoteActivity(noteTitle);
    }

    public void onCreateNote() {
        view.navigateToCreateNoteActivity();
    }
}
