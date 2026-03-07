package tgames;

import java.util.Random;


public class Motor {
    protected Tableiro estado;  // Tableiro que mantén o estado actual do xogo
    protected Xogador[] xogadores;    // Participantes no xogo
    protected int turno;   // Indicie ao xogador en turno na taboa de xogadores  
    
    /**
     * Construtor: Crea un obxecto Motor
     * @param tableiro - Obxecto Tableiro para a partida
     * @param xogadores  - Array de Xogador[] cos participantes
     */
    protected Motor(Tableiro tableiro,Xogador[] xogadores) {
        this.estado=tableiro;
        this.xogadores=xogadores;
    }
  
    /**
     * Inicializa o estado do xogo e retorna o Xogador que comeza a partida
     */
    protected Xogador inicializa() {
        estado.init();
        turno=new Random().nextInt(xogadores.length);
        return xogadores[turno];
    }
    
    /**
     * Amosa na pantalla o estado do xogo
     */
    protected void amosaEstado() {
        estado.show();
    }

    /**
     * Cambia o turno ao seguinte xogador
     * Retorna o xogador en turno
     */
    protected Xogador seguinteTurno() {
        turno=(turno+1)%xogadores.length;
        return xogadores[turno];
    }
    
    /**
     * Procesa a xogada retornando o Resultado ou null si o xogo continúa
     */
    protected Resultado procesaXogada(Xogada xogada) throws MovementoIlegal {
      throw new UnsupportedOperationException("There is not implementation for this class");
    }
    
    public int nFilas() {
        return estado.nFilas();
    }
    
    public int nColumnas() {
        return estado.nColumnas();
    }
    
    public Ficha colleFicha(int fila, int columna) throws MovementoIlegal {
        return estado.colleFicha(fila,columna);
    }
    
    public Xogador seguinteXogador() {
       return xogadores[(turno+1)%xogadores.length];
   }
}
