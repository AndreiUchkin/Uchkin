package com.example.uchkin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.uchkin.UserStaticInfo.POSITION;
import static com.example.uchkin.UserStaticInfo.users;

public class UserActivity extends AppCompatActivity {

    private User activeUser;
    EditText NameTextView, StateTextView, AgeTextView;

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
        AgeTextView.setText(String.valueOf(activeUser.getAge()));


    }

    private void Init() {
        NameTextView = findViewById(R.id.NameTextView);
        StateTextView = findViewById(R.id.StateTextView);
        AgeTextView = findViewById(R.id.AgeTextView);


    }

    public void Back(View view) {
    }

    public void Save(View view) {
        activeUser.setName(NameTextView.getText().toString());
        activeUser.setState(StateTextView.getText().toString());
        activeUser.setAge(Integer.parseInt(AgeTextView.getText().toString()));
        String age = AgeTextView.getText().toString();
        try {
            activeUser.setAge(Integer.parseInt(age));
        }
        catch (Exception NumberFormaException)
        {
            activeUser.setAge(activeUser.getAge());
        }
        MainActivity.UpdateListAndUserPanel(activeUser);
        finish();
    }
}