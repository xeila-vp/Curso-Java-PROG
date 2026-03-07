package tgames;

public class Xogo {
    private Motor regras;

    /**
     * Construtor: Crea un Xogo
     */
    public Xogo(Motor engine) {
        this.regras=engine;
    }

    /**
     * Leva a cabo o xogo
     */
    public Resultado play() {
        Resultado estado=null;         // O estado null indica "xogo en curso"
        Xogador turno=regras.inicializa();         // Indica ao Motor que prepare un novo xogo e retorne o xogador que inicia o xogo
        do {
            try {
                regras.amosaEstado();
                Xogada xogada=turno.xoga();                
                estado=regras.procesaXogada(xogada);
                if (estado == null) turno=regras.seguinteTurno();
            } catch(MovementoIlegal e) {
                System.out.println(e.getMessage());
            }
        } while(estado==null);
        regras.amosaEstado();
        return estado;
    }
}