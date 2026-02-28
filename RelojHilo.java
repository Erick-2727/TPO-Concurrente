/**
 * este hilo se encarga de incrementar el reloj y notificar a los pasajeros de las boleterias
 * ya que estos esperan a que sea la hora de atencion para poder obtener sus pasajes.
 */
public class RelojHilo implements Runnable{
    private Reloj reloj;
    
    public RelojHilo(Reloj reloj){
        this.reloj = reloj;
    }
    
    public void run(){
        try {
            int i=0;
            while(i<20){
                this.reloj.actualizarHora();
                Thread.sleep(5000);
                i++;
            }
        } catch (Exception e) {
            // manejar interrupciones/errores si corresponde
            e.printStackTrace();
        }
    }
}
