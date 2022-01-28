package com.example.bloc_salle_app.beans;

public class Bloc {
    private int id;
    private String name;

    public Bloc(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Bloc(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
