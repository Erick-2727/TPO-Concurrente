
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

@SuppressWarnings("FieldMayBeFinal")
public class Pasajero implements Runnable {

    private PuestoInforme puestoInforme;
    private PuestoAerolinea puestoAerolinea;
    private Random random = new Random();
    private int nombre;
    private int cantAerolineas;
    private Pasaje pasaje;
    private Tren tren;
    private FreeShop freeShop;
    private Reloj reloj;

    public Pasajero(PuestoInforme puestoI, int nombre, int cantAerolineas, Tren tren, FreeShop freeShop, Reloj reloj) {
        this.puestoInforme = puestoI;
        this.nombre = nombre;
        this.cantAerolineas = cantAerolineas;
        this.tren = tren;
        this.freeShop = freeShop;
        this.reloj = reloj;
    }

    @Override
    public void run() {
        int hora = this.random.nextInt(12, 20);
        int nroPasaje = random.nextInt(50) + 1;
        int numAerolinea = random.nextInt(cantAerolineas);
        this.pasaje = new Pasaje(nroPasaje, numAerolinea, hora);

        try {
            //llamada al metodo obtenerPuestoAerolinea para tener acceso a los metodos de esa aerolinea
            System.out.println(Color.ROJO + "Pasajero " + nombre + " intenta entrar al puesto de informe" + Color.RESET);
            puestoAerolinea = puestoInforme.obtenerPuestoAerolinea(numAerolinea);
        } catch (InterruptedException ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        try {
            //entrada al puesto de aerolinea asignado
            System.out.println(Color.NARANJA + "Pasajero " + nombre + " intenta entrar al puesto de aerolinea " + puestoAerolinea.getNombre() + Color.RESET);
            puestoAerolinea.entradaPasajero();
        } catch (Exception ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        System.out.println(Color.AMARILLO + "Pasajero " + nombre + " Esperando en la fila del puesto de atencion ..." + Color.RESET);
        try {
            Thread.sleep(1000 + random.nextInt(2000)); //sleep que representa el tiempo de espera para ser atendido Entre 1 y 3 segundos
        } catch (InterruptedException ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        try {
            //completado de los datos del pasaje(terminal y puerta de embarque) y salida del puesto de aerolinea
            puestoAerolinea.salidaPasajero(pasaje);
            System.out.println(Color.VERDE + "Pasajero " + nombre + " sale del puesto de aerolinea " + puestoAerolinea.getNombre() + Color.RESET);
            System.out.println(Color.VERDE + "Pasajero " + nombre + " " + this.pasaje.toString() + Color.RESET);
        } catch (Exception ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        //entrada al tren para ir a la terminal
        try {
            tren.entrarTren(this.pasaje.getTerminal(), this.nombre);
        } catch (InterruptedException | BrokenBarrierException ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        int entrarFreeShop = this.random.nextInt(2);
        if (entrarFreeShop == 1 && this.pasaje.getHora() - this.reloj.getHora() >= 2) {
            try {
                this.freeShop.entrarFreeShop(this.nombre);
            } catch (InterruptedException ex) {
                System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }

        } else {
            System.out.println(Color.ROSA_FUERTE + "Pasajero " + nombre + " decide no entrar al free shop" + Color.RESET);
        }
        int horasEsperaEmbarque = this.pasaje.getHora() - this.reloj.getHora() - 1;
        if (horasEsperaEmbarque > 0) {
            System.out.println(Color.ROSA_FUERTE + "Pasajero " + nombre + " espera " + horasEsperaEmbarque + " horas para el embarque de su vuelo de las " + this.pasaje.getHora() + " horas" + Color.RESET);
            try {
                Thread.sleep(horasEsperaEmbarque * 5000);
                System.out.println(Color.AZUL_CIELO + "Pasajero " + nombre + " se dirige a la puerta de embarque para abordar su vuelo de las " + this.pasaje.getHora() + " horas" + Color.RESET);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
