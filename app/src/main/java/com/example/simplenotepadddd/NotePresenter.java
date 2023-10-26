package com.example.simplenotepadddd;

import android.content.SharedPreferences; //i presenter anges de metoder som kommer

import java.util.Optional;
// hantera data mellan model och view.

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

    public void saveOrUpdateNote(String noteTitle, String noteText, Optional oldNoteTitle) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (oldNoteTitle.isPresent()) {
            //the note already existed, it is an update
            editor.remove(noteTitle); // remove the old note
            editor.apply();
        }
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
