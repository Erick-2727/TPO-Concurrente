
import java.util.Random;
import java.util.concurrent.Semaphore;
public class PuestoAerolinea {
    private static Random random = new Random();
    private Semaphore espacios;
    private Semaphore guardia = new Semaphore(0);
    private String nombre;
    private int cantTerminales;
    private int cantPuertasEmbarque;

    public PuestoAerolinea(int cantidadLugares, String nombre,int cantTerminales,int cantPuertasEmbarque) {
        this.espacios = new Semaphore(cantidadLugares, true);
        this.nombre = nombre;
        this.cantPuertasEmbarque=cantPuertasEmbarque;
        this.cantTerminales=cantTerminales;
    }

    public void entradaPasajero() throws Exception {
        //entra el pasajero, si hay espacio toma el semaforo
        espacios.acquire();
        System.out.println(Thread.currentThread().getName()+" entra en el puesto de la aerolinea "+nombre+" para Check-In");

    }

    public void salidaPasajero(Pasaje pasaje) throws Exception {
        //se va el pasajero y avisa al guardia que hay espacio
        int tempTerminal=this.random.nextInt(this.cantTerminales)+1;
        int tempPuerta=this.random.nextInt(this.cantPuertasEmbarque)+1;
        pasaje.completarDatos(tempTerminal,tempPuerta);
        System.out.println(Thread.currentThread().getName()+" termina el Check-In en el puesto de la aerolinea "+nombre);
        

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

    public int getCantTerminales() {
        return cantTerminales;
    }

    public int getCantPuertasEmbarque() {
        return cantPuertasEmbarque;
    }

}
