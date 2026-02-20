public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando simulación de puestos y clientes...");

        int numPuestos = 5;
        int numClientes = 35;
        Reloj reloj=new Reloj();
        
        // Crear puestos de aerolínea
        PuestoAerolinea[] puestosAerolinea = new PuestoAerolinea[numPuestos];
        for (int i = 0; i < numPuestos; i++) {
            puestosAerolinea[i] = new PuestoAerolinea(4, "Puesto-" + (i),3,4); 
        }
        //Puesto de informe
        PuestoInforme puestoInforme=new PuestoInforme(puestosAerolinea,reloj);
        //Reloj
        RelojHilo relojHilo=new RelojHilo(reloj,puestoInforme);
        new Thread(relojHilo).start();

        //Arrancar un guardia por puesto
        Guardia[] guardias = new Guardia[numPuestos];
        for (int i = 0; i < numPuestos; i++) {
            guardias[i] = new Guardia(puestosAerolinea[i]);
            new Thread(guardias[i], "Guardia-" + (i + 1)).start();
        }

        // Crear y arrancar clientes, asignados en round-robin a los puestos
        Pasajero[] clientes = new Pasajero[numClientes];
        for (int i = 0; i < numClientes; i++) {
            clientes[i] = new Pasajero(puestoInforme, i + 1,numPuestos);
            new Thread(clientes[i], "Pasajero-" + (i + 1)).start();
        }

        // Opcional: mantener el main vivo (si quieres que el programa no termine inmediatamente)
        // try {
        //     Thread.sleep(60000); // 60 seg
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }
}
