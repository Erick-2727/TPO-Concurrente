import java.util.Random;

public class Guardia implements Runnable {
    private PuestoAerolinea puestoAerolinea;
    private static Random random = new Random();
    private int vuelo= random.nextInt(4) + 1;

    public Guardia(PuestoAerolinea puestoA){
        this.puestoAerolinea=puestoA;


    }

    @Override
    public void run() {
        System.out.println(Color.CIAN+"El guardia empieza a trabajar"+Color.RESET);    
        while(true){
            try {
            puestoAerolinea.dejarEntrar();
        } catch (Exception ex) {
            System.getLogger(Pasajero.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        try {
            System.out.println(Color.CIAN+"El guardia se va a descansar"+Color.RESET);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }

    }

    public void setAerolinea(PuestoAerolinea puestoA){
    this.puestoAerolinea=puestoA;

    }
    public int getVuelo(){
        return vuelo;

    }

    
}

