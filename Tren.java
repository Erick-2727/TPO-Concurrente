
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@SuppressWarnings("FieldMayBeFinal")
public class Tren {

    @SuppressWarnings("FieldMayBeFinal")
    private Lock mutex = new ReentrantLock();
    private Condition conductor;
    private Condition pasajerosEspera;
    private boolean[] paradas;
    private int[] pasajerosPorParada;
    private Condition[] paradasWait;
    private CyclicBarrier barrier;
    private boolean enConduccion = false;
    private boolean puedeConducir = false;

    ;

    public Tren(int cantAsientos, int cantTerminales) {
        barrier = new CyclicBarrier(cantAsientos, () -> {
            // Este código se ejecuta cuando todos los hilos llegan a la barrera
            System.out.println(Color.MAGENTA + "El tren parte hacia las terminales..." + Color.RESET);
        });
        this.conductor = mutex.newCondition();
        this.pasajerosEspera = mutex.newCondition();

        this.paradas = new boolean[cantTerminales];
        for (int i = 0; i < paradas.length; i++) {
            paradas[i] = false;
        }

        this.pasajerosPorParada = new int[cantTerminales];
        for (int i = 0; i < pasajerosPorParada.length; i++) {
            pasajerosPorParada[i] = 0;
        }

        paradasWait = new Condition[cantTerminales];
        for (int k = 0; k < paradasWait.length; k++) {
            paradasWait[k] = mutex.newCondition();
        }

    }
    //metodo para pasajero
    @SuppressWarnings("LockAcquiredButNotSafelyReleased")
    public void entrarTren(int terminal,int nombre) throws InterruptedException, BrokenBarrierException {
        //si el vagon esta en viaje esperaran
        this.mutex.lock();
        while (this.enConduccion) {
            this.pasajerosEspera.await();
        }
        this.mutex.unlock();

        this.barrier.await();

        this.mutex.lock();
        this.enConduccion = true;
        this.puedeConducir = true;
        this.conductor.signal();
        //evaluamos si tenemos que bajar en una terminal
         System.out.println(Color.MAGENTA+"Pasajero " + nombre + " Esperando a bajar. Terminal " + terminal+Color.RESET);
        while (!this.paradas[terminal]) {           
            this.paradasWait[terminal].await();
        }
        System.out.println(Color.MAGENTA+"Pasajero " + nombre + " bajando en la terminal: " + terminal+Color.RESET);
        this.mutex.unlock();

    }

    @SuppressWarnings("LockAcquiredButNotSafelyReleased")
    public void arrancarViaje() throws InterruptedException {
        this.mutex.lock();
        //conductor pregunta si puede conducir, si es falso espera
        while (!this.puedeConducir) {
            this.conductor.await();
        }
        this.mutex.unlock();
    }

    @SuppressWarnings("LockAcquiredButNotSafelyReleased")
    public void pararTerminal(int i) throws InterruptedException {
        this.mutex.lock();
        this.paradas[i] = true;
        this.paradasWait[i].signalAll();
        this.mutex.unlock();
    }

    @SuppressWarnings("LockAcquiredButNotSafelyReleased")
    public void finalizarViaje() throws InterruptedException {
        this.mutex.lock();
        for (int j = 0; j < paradas.length; j++) {
            paradas[j] = false;
        }
        this.enConduccion = false;
        this.puedeConducir = false;
        this.pasajerosEspera.signalAll();
        this.mutex.unlock();
    }

}
