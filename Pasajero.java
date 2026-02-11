import java.util.Random;

public class Pasajero implements Runnable {
    private PuestoInforme puestoInforme;
    private PuestoAerolinea puestoAerolinea;
    private static Random random = new Random();
    private int vuelo = random.nextInt(4) + 1;
    private int nombre;


    public Pasajero(PuestoInforme puestoI, int nombre){
        this.puestoInforme=puestoI;
        this.nombre=nombre;

    }

    @Override
    public void run() {     
            puestoAerolinea=puestoInforme.obtenerPuestoAerolinea(vuelo);
        try {
            System.out.println("Pasajero "+nombre+" intenta entrar al puesto de aerolinea "+puestoAerolinea.getNombre());
            puestoAerolinea.entradaPasajero();
        } catch (Exception ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        try {
            Thread.sleep(1000+ random.nextInt(2000)); // Entre 1 y 3 segundos
        } catch (InterruptedException ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        try {
            puestoAerolinea.salidaPasajero();
            System.out.println("Pasajero "+nombre+" sale del puesto de aerolinea "+puestoAerolinea.getNombre());
        } catch (Exception ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }


    }

    public void setAerolinea(PuestoAerolinea puestoA){
    this.puestoAerolinea=puestoA;

    }
     public int getVuelo(){
        return vuelo;

    }

    
}
