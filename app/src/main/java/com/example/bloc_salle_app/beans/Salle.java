package com.example.bloc_salle_app.beans;

public class Salle {
    private int id;
    private String name;
    private String bloc;
    private String etat;
    public Salle(int id,String name,String bloc ,String etat){
        this.id = id;
        this.name = name;
        this.bloc = bloc;
        this.etat = etat;
    }
    public Salle(){

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

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
