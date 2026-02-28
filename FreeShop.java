
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FreeShop {

    private Semaphore capacidad;
    private Semaphore cajas;
    private Random random = new Random();

    public FreeShop(int capacidad, int cajas) {
        this.capacidad = new Semaphore(capacidad);
        this.cajas = new Semaphore(cajas);

    }

    public void entrarFreeShop(int nombre) throws InterruptedException {
        System.out.println( Color.ROSA_FUERTE + "Pasajero " + nombre + " intenta entrar al free shop" + Color.RESET);
        if (this.capacidad.tryAcquire(2, TimeUnit.SECONDS)) {
            try {
                System.out.println(Color.ROSA_FUERTE + "Pasajero " + nombre + " entra al free shop..." + Color.RESET);
                int quiereComprar= random.nextInt(2);
                if(quiereComprar==1){
                    if(this.cajas.tryAcquire(1, TimeUnit.SECONDS)){
                        try {
                            System.out.println(Color.ROSA_FUERTE + "Pasajero " + nombre + " esta comprando en el free shop..." + Color.RESET);
                            Thread.sleep(1000 + random.nextInt(2000)); //tiempo de compra entre 1 y 3 segundos
                            System.out.println(Color.ROSA_FUERTE + "Pasajero " + nombre + " termina de comprar en el free shop..." + Color.RESET);
                        } finally {
                            this.cajas.release();
                        }
                    } else {
                        System.out.println(Color.ROSA_FUERTE + "Pasajero " + nombre + " ve que no hay cajas disponibles, se cansa de esperar y vuelve a la sala de espera de embarque" + Color.RESET);
                    }
                } else {
                    System.out.println(Color.ROSA_FUERTE + "Pasajero " + nombre + " decide no comprar nada en el free shop" + Color.RESET);

                }
            } finally {
                this.capacidad.release();
            }
        } else {
            System.out.println(Color.ROSA_FUERTE + "Pasajero " + nombre + " no pudo entrar al free shop porque no había espacio, se canso de esperar y vuelve a la sala de espera de embarque" + Color.RESET);
        }

        this.capacidad.tryAcquire();
        
    }
}
