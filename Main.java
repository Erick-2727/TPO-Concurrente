public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando simulación de puestos y clientes...");

        int numPuestos = 3;
        int numClientes = 10;
        
        // Crear puestos de aerolínea
        PuestoAerolinea[] puestosAerolinea = new PuestoAerolinea[numPuestos];
        for (int i = 0; i < numPuestos; i++) {
            puestosAerolinea[i] = new PuestoAerolinea(2, "Puesto-" + (i)); 
        }
        //Puesto de informe
        PuestoInforme puestoInforme=new PuestoInforme(puestosAerolinea);

        // Arrancar un guardia por puesto
        Guardia[] guardias = new Guardia[numPuestos];
        for (int i = 0; i < numPuestos; i++) {
            guardias[i] = new Guardia(puestosAerolinea[i]);
            new Thread(guardias[i], "Guardia-" + (i + 1)).start();
        }

        // Crear y arrancar clientes, asignados en round-robin a los puestos
        Cliente[] clientes = new Cliente[numClientes];
        for (int i = 0; i < numClientes; i++) {
            clientes[i] = new Cliente(puestoInforme, i + 1);
            new Thread(clientes[i], "Cliente-" + (i + 1)).start();
        }

        // Opcional: mantener el main vivo (si quieres que el programa no termine inmediatamente)
        // try {
        //     Thread.sleep(60000); // 60 seg
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }
}
