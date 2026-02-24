
import java.util.Random;

public class Pasajero implements Runnable {

    private PuestoInforme puestoInforme;
    private PuestoAerolinea puestoAerolinea;
    private static Random random = new Random();
    private int nombre;
    private int cantAerolineas;
    private Pasaje pasaje;

    public Pasajero(PuestoInforme puestoI, int nombre, int cantAerolineas) {
        this.puestoInforme = puestoI;
        this.nombre = nombre;
        this.cantAerolineas = cantAerolineas;

    }

    @Override
    public void run() {
        int hora = this.random.nextInt(12, 20);
        int nroPasaje = random.nextInt(50) + 1;
        int numAerolinea = random.nextInt(cantAerolineas);
        this.pasaje = new Pasaje(nroPasaje, numAerolinea, hora);

        try {
            System.out.println(Color.ROJO+"Pasajero " + nombre + " intenta entrar al puesto de informe"+Color.RESET);
            puestoAerolinea = puestoInforme.obtenerPuestoAerolinea(numAerolinea);
        } catch (Exception ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        try {
            System.out.println(Color.NARANJA+"Pasajero " + nombre + " intenta entrar al puesto de aerolinea " + puestoAerolinea.getNombre()+Color.RESET);
            puestoAerolinea.entradaPasajero();
        } catch (Exception ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        System.out.println(Color.AMARILLO+"Pasajero " + nombre + " Esperando en la fila del puesto de atencion ..."+Color.RESET);
        try {
            Thread.sleep(1000 + random.nextInt(2000)); // Entre 1 y 3 segundos
        } catch (InterruptedException ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        try {
            puestoAerolinea.salidaPasajero(pasaje);
            System.out.println(Color.VERDE+"Pasajero " + nombre + " sale del puesto de aerolinea " + puestoAerolinea.getNombre()+Color.RESET);
              System.out.println(Color.VERDE+"Pasajero "+nombre+" "+this.pasaje.toString()+Color.RESET);
        } catch (Exception ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void setAerolinea(PuestoAerolinea puestoA) {
        this.puestoAerolinea = puestoA;

    }
}
