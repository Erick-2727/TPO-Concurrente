
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tren {

    private Lock mutex = new ReentrantLock();
    private Condition conductor;
    private Condition pasajerosEspera;
    private boolean[] paradas;
    private int[] pasajerosPorParada;
    private Condition[] paradasWait;
    private CyclicBarrier barrier;
    private boolean conduccion = false;

    ;

    public Tren(int cantLugares, int terminales) {
        barrier = new CyclicBarrier(cantLugares, () -> {
            // Este código se ejecuta cuando todos los hilos llegan a la barrera
            System.out.println("El vagon parte de la estacion...");
        });
        this.conductor = mutex.newCondition();
        this.pasajerosEspera = mutex.newCondition();

        this.conduccion = false;

        this.paradas = new boolean[terminales];
        for (int i = 0; i < paradas.length; i++) {
            paradas[i] = false;
        }

        this.pasajerosPorParada = new int[terminales];
        for (int i = 0; i < pasajerosPorParada.length; i++) {
            pasajerosPorParada[i] = 0;
        }

        paradasWait = new Condition[terminales];
        for (int k = 0; k < paradasWait.length; k++) {
            paradasWait[k] = mutex.newCondition();
        }

    }

    public void entrarTren(int terminal) throws InterruptedException, BrokenBarrierException {
        //si el vagon esta en viaje esperaran
        this.mutex.lock();
        while (this.conduccion) {
            this.pasajerosEspera.await();
        }
        this.mutex.unlock();

        this.barrier.await();

        this.mutex.lock();
        this.conduccion = true;
        this.conductor.signal();
        //evaluamos si tenemos que bajar en una terminal
        while (!this.paradas[terminal]) {
            System.out.println("Esparando a bajar. Terminal " + terminal);
            this.paradasWait[terminal].await();
        }
        System.out.println("Bajando en la terminal: " + terminal);
        this.mutex.unlock();

    }

    public void arrancarViaje() throws InterruptedException {
        this.mutex.lock();
        while (!conduccion) {
            this.conductor.await();
        }
        this.mutex.unlock();
    }

    public void pararTerminal(int i) throws InterruptedException {
        this.mutex.lock();
        this.paradas[i] = true;
        this.paradasWait[i].signalAll();
        this.mutex.unlock();
    }

    public void finalizarViaje() throws InterruptedException {
        this.mutex.lock();
        for (int j = 0; j < paradas.length; j++) {
            paradas[j] = false;
        }
        this.conduccion = false;
        this.pasajerosEspera.signalAll();
        this.mutex.unlock();
    }

}
