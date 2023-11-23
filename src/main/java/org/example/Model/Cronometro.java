package org.example.Model;

public class Cronometro extends Thread {
    private boolean running;
    private boolean stopped;
    private int minutes, seconds, miliseconds;
    private CronometroListener listener;

    private Object lock = new Object();

    public void setListener(CronometroListener listener) {
        this.listener = listener;
    }

    public Cronometro() {
        running = false;
        stopped = false;
        minutes = 0;
        seconds = 0;
        miliseconds = 0;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(25); // Espera 100 milisegundos
                miliseconds++;

                if (miliseconds == 60) {
                    miliseconds = 0;
                    seconds++;

                    if (seconds == 60) {
                        seconds = 0;
                        minutes++;
                    }
                }

                // Notifica al listener después de incrementar los segundos
                if (listener != null) {
                    listener.onTimeChanged(minutes, seconds, miliseconds);
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
        minutes = 00;
        seconds = 00;
        miliseconds = 00;
        stopCronometro();
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMiliseconds() {
        return miliseconds;
    }
}
