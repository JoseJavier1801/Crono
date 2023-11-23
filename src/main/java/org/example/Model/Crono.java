package org.example.Model;

public class Crono {
    private int milisegundos;
    private int minutes;
    private int seconds;
    private  int id;

    public Crono(int milisegundos, int minutes, int seconds, int id) {
        this.milisegundos = milisegundos;
        this.minutes = minutes;
        this.seconds = seconds;
        this.id = id;
    }
    public Crono(int milisegundos, int minutes, int seconds) {
        this.milisegundos = milisegundos;
        this.minutes = minutes;
        this.seconds = seconds;


    }

    public Crono() {

    }

    public int getMilisegundos() {
        return milisegundos;
    }

    public void setMilisegundos(int milisegundos) {
        this.milisegundos = milisegundos;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Crono{" +
                "milisegundos=" + milisegundos +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                ", id=" + id +
                '}';
    }
}