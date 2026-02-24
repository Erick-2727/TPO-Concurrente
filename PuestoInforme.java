import java.util.concurrent.Semaphore;

public class PuestoInforme {
    private PuestoAerolinea[] puestosA;
    private int cantPuestos;
    private final Semaphore mutex = new Semaphore(1, true);
    private final Reloj reloj;

    public PuestoInforme(PuestoAerolinea[] puestosA, Reloj reloj) {
        this.puestosA = puestosA;
        this.cantPuestos = puestosA.length;
        this.reloj = reloj;
    }

    public PuestoAerolinea obtenerPuestoAerolinea(int numAerolinea) throws InterruptedException {
        // Espera bloqueada hasta que el aeropuerto esté abierto
        reloj.esperarApertura();

        // sección crítica que simula el empleado de informes
        mutex.acquire();
        try {
            System.out.println(Thread.currentThread().getName() + ": puesto de aeroliena asignada " + numAerolinea);
            return this.puestosA[numAerolinea];
        } finally {
            mutex.release();
        }
    }
}
