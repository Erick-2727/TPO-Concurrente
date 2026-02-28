
public class ConductorTren implements Runnable {

    private Tren tren;
    private int cantTerminales;

    public ConductorTren(Tren tren, int cantTerminales) {
        this.cantTerminales = cantTerminales;
        this.tren = tren;
    }

    @Override
    public void run() {
        while (true) {
            try {
            //Inicia el viaje del tren, para cada terminal espera a que los pasajeros bajen y luego de un tiempo se detiene en la siguiente terminal, al finalizar el viaje se reinicia el proceso 
                this.tren.arrancarViaje();
                for (int i = 0; i < cantTerminales; i++) {
                    this.tren.pararTerminal(i);
                    Thread.sleep(1000);

                }
                this.tren.finalizarViaje();

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
