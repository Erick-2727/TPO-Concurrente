public class Main {
public static final String RESET = "\u001B[0m";
    public static final String NEGRITA   = "\u001B[1m";
    public static final String NEGRO     = "\u001B[30m";
    public static final String ROJO      = "\u001B[31m";
    public static final String VERDE     = "\u001B[32m";
    public static final String AMARILLO  = "\u001B[33m";
    public static final String NARANJA = "\u001B[38;5;208m";
    public static final String AZUL      = "\u001B[34m";
    public static final String MAGENTA   = "\u001B[35m";
    public static final String CIAN      = "\u001B[36m";
    public static final String BLANCO    = "\u001B[37m";

    public static final String ROSA_FUERTE   = "\u001B[38;5;198m";
    public static final String TURQUESA      = "\u001B[38;5;44m";
    public static final String LIMA          = "\u001B[38;5;118m";
    public static final String VIOLETA       = "\u001B[38;5;93m";
    public static final String DORADO        = "\u001B[38;5;220m";
    public static final String GRIS_CLARO    = "\u001B[38;5;250m";
    public static final String ROJO_OSCURO   = "\u001B[38;5;124m";
    public static final String AZUL_CIELO    = "\u001B[38;5;117m";
    public static final String VERDE_MENTA   = "\u001B[38;5;121m";
    public static final String FUCSIA        = "\u001B[38;5;201m";

    public static void main(String[] args) {




        //prueba
    System.out.println(NEGRITA + "Texto en Negrita" + RESET);

        System.out.println(NEGRO + "Texto en Negro" + RESET);
        System.out.println(ROJO + "Texto en Rojo" + RESET);
        System.out.println(VERDE + "Texto en Verde" + RESET);
        System.out.println(AMARILLO + "Texto en Amarillo" + RESET);
        System.out.println(NARANJA + "Texto en Naranja" + RESET);
        System.out.println(AZUL + "Texto en Azul" + RESET);
        System.out.println(MAGENTA + "Texto en Magenta" + RESET);
        System.out.println(CIAN + "Texto en Cian" + RESET);
        System.out.println(BLANCO + "Texto en Blanco" + RESET);

        System.out.println(ROSA_FUERTE + "Texto en Rosa Fuerte" + RESET);
        System.out.println(TURQUESA + "Texto en Turquesa" + RESET);
        System.out.println(LIMA + "Texto en Lima" + RESET);
        System.out.println(VIOLETA + "Texto en Violeta" + RESET);
        System.out.println(DORADO + "Texto en Dorado" + RESET);
        System.out.println(GRIS_CLARO + "Texto en Gris Claro" + RESET);
        System.out.println(ROJO_OSCURO + "Texto en Rojo Oscuro" + RESET);
        System.out.println(AZUL_CIELO + "Texto en Azul Cielo" + RESET);
        System.out.println(VERDE_MENTA + "Texto en Verde Menta" + RESET);
        System.out.println(FUCSIA + "Texto en Fucsia" + RESET);
        //fin prueba
        Reloj reloj=new Reloj();
        int cantAerolineas = 3;
        int numClientes = 65;        
        int cantTerminales=5;
        int cantPuertasEmbarque=4;
        Tren tren=new Tren(6, cantTerminales);
        FreeShop freeShop=new FreeShop(5,2);
        //Reloj
           new Thread(new RelojHilo(reloj)).start();

        System.out.println("Iniciando Aeropuerto VIAJE BONITO...");
        // Crear puestos de aerolínea
        PuestoAerolinea[] puestosAerolinea = new PuestoAerolinea[cantAerolineas];
        for (int i = 0; i < cantAerolineas; i++) {
            puestosAerolinea[i] = new PuestoAerolinea(4, "Puesto-" + (i),cantTerminales,cantPuertasEmbarque); 
        }
        //Puesto de informe
        PuestoInforme puestoInforme=new PuestoInforme(puestosAerolinea,reloj);     

        //Arrancar un guardia por puesto
        Guardia[] guardias = new Guardia[cantAerolineas];
        for (int i = 0; i < cantAerolineas; i++) {
            guardias[i] = new Guardia(puestosAerolinea[i]);
            new Thread(guardias[i], "Guardia-" + (i + 1)).start();
        }

        // Crear y arrancar clientes, asignados en round-robin a los puestos
        Pasajero[] clientes = new Pasajero[numClientes];
        for (int i = 0; i < numClientes; i++) {
            clientes[i] = new Pasajero(puestoInforme, i + 1,cantAerolineas,tren,freeShop,reloj);
            new Thread(clientes[i], "Pasajero-" + (i + 1)).start();
        }
        //crear conductor de tren
        new Thread(new ConductorTren(tren,cantTerminales), "Conductor" ).start();
    }
}
