package com.example.simplenotepadddd;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainPresenter {
    private MainView view;
    private SharedPreferences sharedPreferences;

    public MainPresenter(MainView view, SharedPreferences sharedPreferences) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
    }

    public void loadNoteTitles() {
        Map<String, ?> savedNotes = sharedPreferences.getAll();
        List<String> noteTitles = new ArrayList<>(savedNotes.keySet());
        view.showNoteTitles(noteTitles);
    }

    public void onNoteSelected(String noteTitle) {
        view.navigateToNoteActivity(noteTitle);
    }

    public void onCreateNote() {
        view.navigateToCreateNoteActivity();
    }
}
