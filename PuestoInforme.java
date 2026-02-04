import java.util.concurrent.Semaphore;

public class PuestoInforme {
    private PuestoAerolinea[] puestosA;
    private int cantPuestos;
    private Semaphore mutex = new Semaphore(1,true);


    public PuestoInforme(PuestoAerolinea[] puestosA){
        this.puestosA=puestosA;
        this.cantPuestos=puestosA.length;


    }
    public PuestoAerolinea obtenerPuestoAerolinea(int vuelo){ 
        //Obtiene la aerolinea
        int numAerolinea=vuelo%cantPuestos;
  
        try {
            //Mutex que simula el personal que lo atiende
            mutex.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
                      System.out.println("aeroliena asignada " + numAerolinea);
        PuestoAerolinea temp =this.puestosA[numAerolinea];
        mutex.release();

        return temp;
    }
    
}
