package org.example.Model;

public class Cronometro extends Thread {

    private boolean running; // Indica si el cronómetro está en ejecución
    private boolean stopped; // Indica si el cronómetro está detenido
    private int minutes, seconds, miliseconds; // Almacena el tiempo actual del cronómetro
    private CronometroListener listener; // Listener para notificar cambios en el tiempo

    private Object lock = new Object(); // Objeto de bloqueo para sincronización de hilos

    /**
     * Establece un {@link CronometroListener} para recibir actualizaciones en cada cambio de tiempo.
     *
     * @param listener El objeto que implementa la interfaz {@link CronometroListener}.
     */
    public void setListener(CronometroListener listener) {
        this.listener = listener;
    }

    /**
     * Constructor de la clase Cronometro.
     * Inicializa las variables de tiempo y estado del cronómetro.
     */
    public Cronometro() {
        running = false;
        stopped = false;
        minutes = 0;
        seconds = 0;
        miliseconds = 0;
    }

    /**
     * Método principal del hilo que ejecuta el cronómetro.
     * Incrementa el tiempo en milisegundos y notifica al listener en cada cambio.
     */
    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(25); // Espera 25 milisegundos
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

    /**
     * Inicia el cronómetro. Si el cronómetro se detuvo previamente, lo reinicia.
     * Utiliza un bloqueo para garantizar la sincronización entre hilos.
     */
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

    /**
     * Reinicia el cronómetro deteniéndolo y restableciendo el tiempo a cero.
     */
    public void resetAndStartCronometro() {
        resetCronometro();
    }

    /**
     * Detiene el cronómetro y marca el estado como detenido.
     * Utiliza un bloqueo para garantizar la sincronización entre hilos.
     */
    public void stopCronometro() {
        synchronized (lock) {
            running = false;
            stopped = true;
        }
    }

    /**
     * Reinicia el cronómetro estableciendo el tiempo a cero y deteniéndolo.
     */
    public void resetCronometro() {
        minutes = 0;
        seconds = 0;
        miliseconds = 0;
        stopCronometro();
    }

    /**
     * Obtiene los minutos actuales del cronómetro.
     *
     * @return Los minutos actuales del cronómetro.
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Obtiene los segundos actuales del cronómetro.
     *
     * @return Los segundos actuales del cronómetro.
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Obtiene los milisegundos actuales del cronómetro.
     *
     * @return Los milisegundos actuales del cronómetro.
     */
    public int getMiliseconds() {
        return miliseconds;
    }
}