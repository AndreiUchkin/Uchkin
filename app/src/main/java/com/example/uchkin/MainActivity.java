package com.example.uchkin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.uchkin.UserStaticInfo.POSITION;
import static com.example.uchkin.UserStaticInfo.users;

public class MainActivity extends AppCompatActivity {
ListView listView;
Context context;
LayoutInflater layoutInflater;

static UserListAdapter userListAdapter;
FrameLayout UserPanel;
static TextView NameTextView;
    static TextView StateTextView;
    static TextView AgeTextView;
    private int positionActiveUser;


    public static void UpdateListAndUserPanel(User user) {
        userListAdapter.notifyDataSetChanged();
        InitPanel(user);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddUsersInList();
        new UserStaticInfo();
        Init();
    }

    private void AddUsersInList() {
    }


    private void Init() {
        UserPanel = findViewById(R.id.userPanel);
        NameTextView = findViewById(R.id.NameTextView);
        StateTextView = findViewById(R.id.StateTextView);
        AgeTextView = findViewById(R.id.AgeTextView);;
        listView = findViewById(R.id.listView);
        context = this;
        layoutInflater = LayoutInflater.from(context);
        userListAdapter = new UserListAdapter();
        listView.setAdapter(userListAdapter);
    }

    public void BackToList(View view) {
        UserVisibility(false);
        
    }

    private void UserVisibility(boolean b) {
        if(b)
            UserPanel.setVisibility(View.VISIBLE);
        else
            UserPanel.setVisibility(View.GONE);

    }

    public void EditUser(View view) {
        GoToUserProfile(positionActiveUser);
    }

    private class UserListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public User getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            User currentUser = getItem(position);
            convertView = layoutInflater.inflate(R.layout.item_user,parent, false);
            TextView nameView = convertView.findViewById(R.id.NameTextView);
            TextView stateView = convertView.findViewById(R.id.StateTextView);
            nameView.setText(currentUser.getName());
            stateView.setText(currentUser.getState());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positionActiveUser = position;
                    InitPanel(getItem(position));
                    UserVisibility( true);
                }
            });
            //получем StateRound из  currentView(который содержит R.layout.item_user)
            FrameLayout StateRound = convertView.findViewById(R.id.StateRound);
//установка отображения статуса сигнала
            switch (currentUser.getStateSignal())
            {
                case 0:
                    StateRound.setBackgroundResource(R.drawable.back_offline);
                    break;
                case 1:
                    StateRound.setBackgroundResource(R.drawable.back_online);
                    break;
                case 2:
                    StateRound.setBackgroundResource(R.drawable.back_departed);
                    break;
            }
            return convertView;



        }
    }



    private static void InitPanel(User item) {
        NameTextView.setText(item.getName());
        StateTextView.setText(item.getState());
        AgeTextView.setText(String.valueOf(item.getAge()));
    }
    public void GoToUserProfile(int position)
    {
        Intent intent = new Intent(context,UserActivity.class);
        intent.putExtra(POSITION, position);
        startActivity(intent);
    }
    public  void Back(View view){
        onBackPressed();
    }


}