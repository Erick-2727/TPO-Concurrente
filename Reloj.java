/**
 * el reloj permite a los hilos poder consultar la hora actual en el aeropuerto
 * usa metodos sincronizados para evitar que se incremente cuando lo esten consultando.
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reloj {
    private int hora = 6;
    private final Lock lock = new ReentrantLock(true); 
    private final Condition abierto = lock.newCondition();

    // llamar periódicamente desde RelojHilo
    public void actualizarHora() {
        lock.lock();
        try {
            int prev = hora;
            hora = (hora + 1) % 24;
            boolean antesCerrado = !abierto(prev);
            boolean ahoraAbierto = abierto(hora);
                System.out.println("Reloj hora: " + hora);       
            // si cambiamos de cerrado -> abierto, notificar a los que esperan
            if (antesCerrado && ahoraAbierto) {
                abierto.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void esperarApertura() throws InterruptedException {
        lock.lock();
        try {
            while (!abierto(hora)) {
                abierto.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public int getHora() {
        lock.lock();
        try {
            return hora;
        } finally {
            lock.unlock();
        }
    }

    private boolean abierto(int h) {
        return h >= 6 && h < 22;
    }
}