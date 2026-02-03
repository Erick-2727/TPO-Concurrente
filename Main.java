public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando simulación de puestos y clientes...");

        int numPuestos = 3;
        int numClientes = 10;

        // Crear puestos de aerolínea
        PuestoAerolinea[] puestos = new PuestoAerolinea[numPuestos];
        for (int i = 0; i < numPuestos; i++) {
            puestos[i] = new PuestoAerolinea(2, "Puesto-" + (i + 1)); // 2 espacios por puesto (ejemplo)
        }

        // Arrancar un guardia por puesto (hace dejarEntrar en loop)
        Guardia[] guardias = new Guardia[numPuestos];
        for (int i = 0; i < numPuestos; i++) {
            guardias[i] = new Guardia(puestos[i]);
            new Thread(guardias[i], "Guardia-" + (i + 1)).start();
        }

        // Crear y arrancar clientes, asignados en round-robin a los puestos
        Cliente[] clientes = new Cliente[numClientes];
        for (int i = 0; i < numClientes; i++) {
            PuestoAerolinea puestoAsignado = puestos[i % numPuestos];
            clientes[i] = new Cliente(puestoAsignado, i + 1);
            new Thread(clientes[i], "Cliente-" + (i + 1)).start();

            // Pequeña pausa para escalonar llegadas
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Opcional: mantener el main vivo (si quieres que el programa no termine inmediatamente)
        // try {
        //     Thread.sleep(60000); // 60 seg
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }
}
