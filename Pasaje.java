public class Pasaje {
    private int nroPasaje;
    private int aerolinea;
    private int hora;
    private int puertaEmbarque;
    private int terminal;

    public Pasaje(int nroPasaje, int aerolina, int hora){
        this.nroPasaje = nroPasaje;
        this.aerolinea = aerolina;
        this.hora = hora;
    }

    public String toString(){
        return "Nro pasaje: #"+nroPasaje+" \t / aerolinea #"+aerolinea+" \t / terminal #"+this.terminal+" \t / puerta embarque #"+this.puertaEmbarque+" \t / hora "+this.hora+"hs";
    }

    public int getAerolinea(){
        return this.aerolinea;
    }

    public int getHora(){
        return hora;
    }

    public int getTerminal(){
        return terminal;
    }

    public int getPuerta(){
        return puertaEmbarque;
    }
    public void completarDatos(int terminal,int puerta){
        this.terminal=terminal;
        this.puertaEmbarque=puerta;
    }
    
    
}
