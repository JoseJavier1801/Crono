package org.example.Model;

public class Crono {
    private int hours;
    private int minutes;
    private int seconds;
    private int total;

    // Constructor vacío
    public Crono() {
    }

    // Constructor con parámetros
    public Crono(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        // Calcular el total en segundos
        this.total = hours * 3600 + minutes * 60 + seconds;
    }

    // Getters y setters
    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
        recalculateTotal();
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        recalculateTotal();
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        recalculateTotal();
    }

    public int getTotal() {
        return total;
    }

    private void recalculateTotal() {
        this.total = hours * 3600 + minutes * 60 + seconds;
    }
}