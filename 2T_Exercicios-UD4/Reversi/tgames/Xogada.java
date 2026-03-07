package tgames;

public class Xogada {
    protected Xogador xogador;      // Xogador que creou a Xogada

    /**
    * Construtor: Recibe o Xogador
    */
    public Xogada(Xogador xogador) {
        this.xogador=xogador;
    }
    
    public Xogador getXogador() {
        return xogador;
    }
}
