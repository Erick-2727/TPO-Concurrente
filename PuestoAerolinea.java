
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
        System.out.println("Se ocupa espacio en el puesto de la aerolinea "+nombre);

    }

    public void salidaPasajero() throws Exception {
        //se va el pasajero y avisa al guardia que hay espacio
        System.out.println("Se libera espacio en el puesto de la aerolinea "+nombre);
        guardia.release();
        

    }

    public void dejarEntrar() throws Exception {
        guardia.acquire();
        System.out.println("Guardia deja entrar en puesto "+nombre);
        espacios.release();

    }

    public String getNombre() {
        return this.nombre;
    }
}
