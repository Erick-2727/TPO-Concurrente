
import java.util.Random;
import java.util.concurrent.Semaphore;
   @SuppressWarnings("FieldMayBeFinal")
public class PuestoAerolinea {
    private Random random = new Random(); 
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
        System.out.println(Color.AZUL+""+Thread.currentThread().getName()+" entra en el puesto de la aerolinea "+nombre+" para Check-In"+Color.RESET);

    }

    public void salidaPasajero(Pasaje pasaje) throws Exception {
        //se va el pasajero y avisa al guardia que hay espacio
        int tempTerminal=this.random.nextInt(this.cantTerminales);
        int tempPuerta=this.random.nextInt(this.cantPuertasEmbarque);
        pasaje.completarDatos(tempTerminal,tempPuerta);
        System.out.println(Color.VERDE+Thread.currentThread().getName()+" termina el Check-In en el puesto de la aerolinea "+nombre+Color.RESET);
        

        guardia.release();
        

    }

    public void dejarEntrar() throws Exception {
        guardia.acquire();
        System.out.println(Color.CIAN+"Guardia trabaja y deja entrar en puesto "+nombre+Color.RESET);
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
