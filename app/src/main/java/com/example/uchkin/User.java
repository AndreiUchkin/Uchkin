package com.example.uchkin;

public class User {
    public String getName() {
        return Name;

    }
    public  int getStateSignal(){
        return  StateSignal;
    }


    public void setName(String name) {
        Name = name;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    private String Name, State;
    private int Age, StateSignal;

    public void setStateSignal(int stateSignal) {
        StateSignal = stateSignal;
    }

    public User(String name, String state, int age, int stateSignal) {
        Name = name;
        State = state;
        Age = age;
        StateSignal = stateSignal;
    }
}
