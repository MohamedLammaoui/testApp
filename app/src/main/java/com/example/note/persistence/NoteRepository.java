package com.example.note.persistence;

import android.content.Context;

import com.example.note.models.Note;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {

    private NoteDatabase mNoteDataBase;

    public NoteRepository(Context context) {
        mNoteDataBase = NoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Note note)
    {
    }

    public void updateNote(Note note)
    {

    }

    public LiveData<List<Note>> retrieveNoteTask()
    {
        return mNoteDataBase.getNoteDao().getNotes();
    }

    public void deleteNote(Note note){

    }
}
