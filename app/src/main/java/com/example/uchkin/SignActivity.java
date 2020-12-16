package com.example.uchkin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.uchkin.Transform.StringNoNull;
import static com.example.uchkin.Transform.Vibrate;
import static com.example.uchkin.UserStaticInfo.PASSWORD;
import static com.example.uchkin.UserStaticInfo.PROFILE_ID;
import static com.example.uchkin.UserStaticInfo.USERS_SIGN_IN_INFO;

public class SignActivity extends AppCompatActivity {

    private EditText LoginTextView, PasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        Init();


    }
    private void  Init(){
        LoginTextView = findViewById(R.id.LoginTextView);
        PasswordTextView = findViewById(R.id.PasswordTextView);
    }

    public void SignIn(View view) {
        if( StringNoNull(getPassword()) && StringNoNull(getLogin()) ) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(USERS_SIGN_IN_INFO);
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String login = getLogin();
                    Object value = dataSnapshot.child(login).child(PASSWORD).getValue();
                    if (value != null) {
                        if (value.toString().equals(getPassword())) {

                            goNext(dataSnapshot.child(login).child(PROFILE_ID).getValue().toString());
                        } else CantSignIn();

                    } else CantSignIn();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }
        else
        {
            Vibrate(SignActivity.this);

            Toast.makeText(SignActivity.this,
                    getResources().getText(R.string.NullParametersMessage),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private String getLogin() {
        return LoginTextView.getText().toString();
    }

    private void goNext(String profileId) {
        UserStaticInfo.profileId = profileId;
    }

    private void CantSignIn() {
        Toast.makeText(SignActivity.this,
                getResources().getText(R.string.CantSignInMessage),
                Toast.LENGTH_SHORT).show();
    }


    private String getPassword() {
        return  PasswordTextView.getText().toString();
    }
}