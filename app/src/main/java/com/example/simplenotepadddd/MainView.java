package com.example.simplenotepadddd;

import android.view.View;
import android.widget.AdapterView;

import java.util.List;

public interface MainView {
    void showNoteTitles(List<String> noteTitles);
    void navigateToNoteActivity(String noteTitle);
    void navigateToCreateNoteActivity();
}


