package com.example.note;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.note.models.Note;
import com.example.note.persistence.NoteDatabase;
import com.example.note.persistence.NoteRepository;

import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NoteActivity";

    // UI Components
    private LineEditText mLinedEditText;
    private EditText mEditTitle;
    private TextView mViewTitle;
    private ImageButton mBackArrow;


    // Vars

    private boolean mIsNewNote;
    private Note mInitialNote;
    private RelativeLayout mBackArrowContainer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mLinedEditText = findViewById(R.id.note_text);
        mEditTitle = findViewById(R.id.note_edit_title);
        mViewTitle = findViewById(R.id.note_text_title);
        mBackArrowContainer = findViewById(R.id.back_arrow_container);
        mBackArrow = findViewById(R.id.toolbar_back_arrow);

        if (getIncomingIntent()) {
            setNewNoteProperties();

        } else {
            setNoteProperties();
        }

        setListeners();
    }


    private void setListeners(){
        mBackArrow.setOnClickListener(this);
    }

    private boolean getIncomingIntent(){
        if(getIntent().hasExtra("selected_note")){

            mInitialNote = getIntent().getParcelableExtra("selected_note");

            mIsNewNote = false;

            return false;
        }

        mIsNewNote = true;
        return true;
    }

    private void setNoteProperties(){
        mViewTitle.setText(mInitialNote.getTitle());
        mEditTitle.setText(mInitialNote.getTitle());
        mLinedEditText.setText(mInitialNote.getContent());
    }

    private void setNewNoteProperties(){
        mViewTitle.setText("Note Title");
        mEditTitle.setText("Note Title");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_arrow: {
                finish();
                break;
            }

        }
    }
}