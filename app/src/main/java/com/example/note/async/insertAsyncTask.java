package com.example.note.async;

import android.os.AsyncTask;

import com.example.note.models.Note;
import com.example.note.persistence.NoteDao;

public class insertAsyncTask extends AsyncTask<Note, Void, Void> {

    private NoteDao mNoteDao;

    public insertAsyncTask(NoteDao dao) {
        mNoteDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        mNoteDao.insertNotes(notes);
        return null;
    }
}
