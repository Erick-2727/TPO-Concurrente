
public class PuestoInforme {
    private PuestoAerolinea[] puestosA;
    private Reloj reloj;

    public PuestoInforme(PuestoAerolinea[] puestosA, Reloj reloj) {
        this.puestosA = puestosA;
        this.reloj = reloj;

    }
     public synchronized void abrirBoleteria()throws InterruptedException{
        this.notifyAll();
    }

    public synchronized PuestoAerolinea obtenerPuestoAerolinea(int numAerolinea) throws InterruptedException {
        //Obtiene la aerolinea
        while (this.reloj.getHora() <= 6 || this.reloj.getHora() >= 22) {
            System.out.println(Thread.currentThread().getName() +" esta esperando a que abra el aeropuero. hora: " + this.reloj.getHora() + ":00");
            this.wait();
        }

        System.out.println(Thread.currentThread().getName() +":puesto de aeroliena asignada " + numAerolinea);
        PuestoAerolinea temp = this.puestosA[numAerolinea];

        return temp;
    }

}
