package com.example.uchkin;

import java.util.ArrayList;
import java.util.List;

public class UserStaticInfo {
    public final static  String POSITION = "position";
    public static List<User> users = new ArrayList<>();
    public UserStaticInfo() {
        if (users.size() == 0)
            AddUsersInList();
    }
    private void AddUsersInList() {
        users.add (new User ("Andrei", "Russia", 19, 0));
        users.add (new User ("Andrei", "Russia", 19, 1));
        users.add (new User ("Andrei", "Russia", 19, 2));
        users.add (new User ("Andrei", "Russia", 19, 0));}



}
