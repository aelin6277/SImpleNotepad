package com.example.simplenotepadddd;

import java.util.List;

public interface MainView {
    void showNoteTitles(List<String> noteTitles);
    void navigateToNoteActivity(String noteTitle);
    void navigateToCreateNoteActivity();
}


