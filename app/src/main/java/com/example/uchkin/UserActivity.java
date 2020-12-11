package com.example.uchkin;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.uchkin.UserStaticInfo.POSITION;
import static com.example.uchkin.UserStaticInfo.users;

public class UserActivity extends AppCompatActivity {

    private User activeUser;
    EditText NameTextView, StateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        int position = getIntent().getIntExtra(POSITION, 0 );
        activeUser = users.get(position);
        Init();
        setUserInto();

    }

    private void setUserInto() {
        NameTextView.setText(activeUser.getName());
        StateTextView.setText(activeUser.getState());

    }

    private void Init() {
        NameTextView = findViewById(R.id.NameTextView);
        StateTextView = findViewById(R.id.StateTextView);

    }

    public void Back(View view) {
    }

    public void Save(View view) {
        activeUser.setName(NameTextView.getText().toString());
        activeUser.setState(StateTextView.getText().toString());
        MainActivity.UpdateList();
    }
}