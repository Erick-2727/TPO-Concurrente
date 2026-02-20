/**
 * este hilo se encarga de incrementar el reloj y notificar a los pasajeros de las boleterias
 * ya que estos esperan a que sea la hora de atencion para poder obtener sus pasajes.
 */
public class RelojHilo implements Runnable{
    private Reloj reloj;
    private PuestoInforme puestoInforme;
    //private Boleteria boleteria;
    
    public RelojHilo(Reloj reloj, PuestoInforme puestoInforme){//, Boleteria boleteria
        this.reloj = reloj;
        this.puestoInforme=puestoInforme;
        // this.boleteria = boleteria;
    }
    
    public void run(){
        try {
            while(true){
                if(this.reloj.actualizarHora()){
                    this.puestoInforme.abrirBoleteria();
                }
                 
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
