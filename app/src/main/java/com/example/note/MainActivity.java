package com.example.note;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.util.Log;

import com.example.note.adapters.NotesRecyclerAdapter;
import com.example.note.models.Note;
import com.example.note.persistence.NoteDao;
import com.example.note.persistence.NoteDatabase;
import com.example.note.persistence.NoteRepository;
import com.example.note.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener {

    private static final String TAG = "MainActivity";

    // Ui Components

    private RecyclerView mRecyclerview;

    // vars
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;
    private NoteRepository mNoteRepository;
    private Note mNoteInitial;
    private boolean mIsNewNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerview = findViewById(R.id.recyclerView);

        mNoteRepository = new NoteRepository(this);


        initRecyclerView();
        retrieveNotes();

        setSupportActionBar((Toolbar) findViewById(R.id.notes_toolbar));
        setTitle("Producten");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Note eerste = new Note("Product name", "Product description", "Date");
                NoteDatabase.getNoteDao().insertNotes(eerste);
            }
        }).start();

        //NoteDatabase myDataBase = NoteDatabase.create()
        // 0913788@hr.nl
        // mijn email: 0965671@hr.nl






    }

    private void retrieveNotes(){
        mNoteRepository.retrieveNoteTask().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if(mNotes.size() > 0){
                    mNotes.clear();
                }
                if(notes != null){
                    mNotes.addAll(notes);
                }
                mNotesRecyclerAdapter.notifyDataSetChanged();
            }
        });

    }


    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerview.addItemDecoration(itemDecorator);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
        mRecyclerview.setAdapter(mNotesRecyclerAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked." + position);

        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("selected_note", mNotes.get(position));
        startActivity(intent);

    }
}
