/**
 * el reloj permite a los hilos poder consultar la hora actual en el aeropuerto
 * usa metodos sincronizados para evitar que se incremente cuando lo esten consultando.
 */
public class Reloj{
    private int hora=6;

    public synchronized void actualizarHora(){
        try{
            this.hora = (this.hora + 1)%24;
            if((this.hora%2)==0){
                 System.out.println(" hora: "+hora);

            }
           
        }catch(Exception ex){
             System.getLogger(ex.getMessage());
        }
    }

    public synchronized int getHora(){
        return this.hora;
    }
}