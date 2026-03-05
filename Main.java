public class Main {
    public static void main(String[] args) {

        Reloj reloj=new Reloj();
        int cantAerolineas = 3;
        int numClientes = 30;        
        int cantTerminales=5;
        int cantPuertasEmbarque=4;
        Tren tren=new Tren(6, cantTerminales);
//Arreglo freeshop
        FreeShop[] freeShop=new FreeShop[cantTerminales];
 for (int i = 0; i < cantTerminales; i++) {
            freeShop[i] = new FreeShop(5, 2, i);
        }


        //FreeShop freeShop=new FreeShop(5,2);
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
