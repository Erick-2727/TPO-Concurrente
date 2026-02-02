
import java.util.concurrent.Semaphore;

public class PuestoAerolinea {

    private Semaphore espacios;
    private Semaphore guardia = new Semaphore(0);
    private String nombre;

    public PuestoAerolinea(int cantidadLugares, String nombre) {
        this.espacios = new Semaphore(cantidadLugares, true);
        this.nombre = nombre;
    }

    public void entradaPasajero() throws Exception {
        //entra el pasajero, si hay espacio toma el semaforo
        espacios.acquire();

    }

    public void salidaPasajero() throws Exception {
        //se va el pasajero y avisa al guardia que hay espacio
        guardia.release();

    }

    public void dejarEntrar() throws Exception {
        guardia.acquire();

        espacios.release();

    }

    public String getNombre() {
        return this.nombre;
    }
}
