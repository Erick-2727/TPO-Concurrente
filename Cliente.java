import java.util.Random;

public class Cliente implements Runnable {
    private PuestoAerolinea puestoAerolinea;
    private static Random random = new Random();
    private int vuelo = random.nextInt(4) + 1;


    public Cliente(PuestoAerolinea puestoA){
        this.puestoAerolinea=puestoA;


    }

    @Override
    public void run() {

        try {
            puestoAerolinea.entradaPasajero();
        } catch (Exception ex) {
            System.getLogger(Cliente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        try {
            Thread.sleep(1000+ random.nextInt(2000)); // Entre 1 y 3 segundos
        } catch (InterruptedException ex) {
            System.getLogger(Cliente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        try {
            puestoAerolinea.salidaPasajero();
        } catch (Exception ex) {
            System.getLogger(Cliente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }


    }

    public void setAerolinea(PuestoAerolinea puestoA){
    this.puestoAerolinea=puestoA;

    }
     public int getVuelo(){
        return vuelo;

    }

    
}
