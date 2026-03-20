
public class PuestoInforme {
    private PuestoAerolinea[] puestosA;

    public PuestoInforme(PuestoAerolinea[] puestosA, Reloj reloj) {
        this.puestosA = puestosA;
    }

    public synchronized PuestoAerolinea obtenerPuestoAerolinea(int numAerolinea) throws InterruptedException {
       
            System.out.println(Thread.currentThread().getName() + ": puesto de aeroliena asignada " + numAerolinea);
            return this.puestosA[numAerolinea];
       
    }
}
