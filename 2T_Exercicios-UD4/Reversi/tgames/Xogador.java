package tgames;

public class Xogador {
    protected Ficha ficha; // Ficha asociada ao xogador

    /**
     * Construtor: Crea un obxecto Xogador
     * @param ficha  - Ficha asociada ao xogador
     */
    public Xogador(Ficha ficha) {
        this.ficha=ficha;
    }

    /**
     * xoga: Crea unha Xogada
     * Visualizamos unha mensaxe, para que se vexa que 
     * Notese o uso de this para indicar o obxecto Xogador actual.
     */
    public Xogada xoga() throws MovementoIlegal {
        System.out.println("Turno de "+this);
        return new Xogada(this);       // De momento esta xogada non ten información asociada
    }
    
    public Ficha getFicha() {
        return ficha;
    }
    
    /**
     * Representación dun obxecto Xogador
     */
    @Override
    public String toString() {
        return ficha.toString();
    }
    
    public static void main(String[] args) {
        Object x=new Xogador(new Ficha("X"));
        System.out.println(x);
    }
}