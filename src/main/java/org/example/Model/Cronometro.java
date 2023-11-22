package org.example.Model;

public class Cronometro extends Thread {
    private boolean running;
    private boolean stopped;
    private int hours, minutes, seconds;
    private CronometroListener listener;

    private Object lock = new Object();

    public void setListener(CronometroListener listener) {
        this.listener = listener;
    }

    public Cronometro() {
        running = false;
        stopped = false;
        hours = 0;
        minutes = 0;
        seconds = 0;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(25); // Espera 100 milisegundos
                seconds++;

                if (seconds == 60) {
                    seconds = 0;
                    minutes++;

                    if (minutes == 60) {
                        minutes = 0;
                        hours++;
                    }
                }

                // Notifica al listener después de incrementar los segundos
                if (listener != null) {
                    listener.onTimeChanged(hours, minutes, seconds);
                }
            } catch (InterruptedException e) {
                // Interrupción del hilo, simplemente sale del bucle
                break;
            }
        }
    }

    public void startCronometro() {
        synchronized (lock) {
            if (stopped) {
                stopped = false;
                running = true;
            } else {
                running = true;
                start();
            }
        }
    }

    public void resetAndStartCronometro() {
        resetCronometro();
    }

    public void stopCronometro() {
        synchronized (lock) {
            running = false;
            stopped = true;
        }
    }

    public void resetCronometro() {
        hours = 0;
        minutes = 0;
        seconds = 0;
        stopCronometro();
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
