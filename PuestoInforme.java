
import java.util.concurrent.Semaphore;

public class PuestoInforme {

    private PuestoAerolinea[] puestosA;
    private int cantPuestos;
    private Semaphore mutex = new Semaphore(1, true);
    private Reloj reloj;

    public PuestoInforme(PuestoAerolinea[] puestosA, Reloj reloj) {
        this.puestosA = puestosA;
        this.cantPuestos = puestosA.length;
        this.reloj = reloj;

    }

    public PuestoAerolinea obtenerPuestoAerolinea(int vuelo) {
        //Obtiene la aerolinea
        int numAerolinea = vuelo % cantPuestos;
        while (this.reloj.getHora() <= 6 || this.reloj.getHora() >= 22) {
            System.out.println("esta esperando a que abra el aeropuero. hora: " + this.reloj.getHora() + ":00");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // buen hábito
                break;
            }
        }
        try {
            //Mutex que simula el personal que lo atiende
            mutex.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("aeroliena asignada " + numAerolinea);
        PuestoAerolinea temp = this.puestosA[numAerolinea];
        mutex.release();

        return temp;
    }

}
