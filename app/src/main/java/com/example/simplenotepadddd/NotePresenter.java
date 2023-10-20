package com.example.simplenotepadddd;

import android.content.SharedPreferences;

public class NotePresenter {
    private NoteView view;
    private SharedPreferences sharedPreferences;

    public NotePresenter(NoteView view, SharedPreferences sharedPreferences) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
    }

    public void loadNoteData(String noteTitle) {
        String noteText = sharedPreferences.getString(noteTitle, "");
        view.showNoteTitle(noteTitle);
        view.showNoteText(noteText);
    }

    public void saveNote(String noteTitle, String noteText) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(noteTitle, noteText);
        editor.apply();
        view.navigateToMain();
    }

    public void deleteNote(String noteTitle) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(noteTitle);
        editor.apply();
        view.navigateToMain();
    }
}
