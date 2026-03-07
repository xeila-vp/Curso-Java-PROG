/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reversi;

import tgames.xogador.XogadaFilaColumna;
import tgames.Ficha;
import tgames.Motor;
import tgames.MovementoIlegal;
import tgames.Resultado;
import tgames.Tableiro;
import tgames.Xogada;
import tgames.Xogador;

/**
 *
 * @author xavi
 */
public class MotorReversi extends Motor {
    // Este enum representa o movemento no taboleiro hacia todas as posicións posibles
    // indicando canto se debe sumar aos valores de fila e columna para avanzar nesa direción
    enum Dir {
        LEFT(-1,0),
        RIGHT(1,0),
        TOP(0,-1),
        BOTTOM(0,1),
        LEFTTOP(-1,-1),
        LEFTBOTTOM(-1,1),
        RIGHTTOP(1,-1),
        RIGHTBOTTON(1,1);
        
        int incx;
        int incy;
        
        Dir(int incx,int incy) {
            this.incx=incx;
            this.incy=incy;
        }
    }
    
    
    public MotorReversi(Xogador[] xogadores) {
        super(new Tableiro(8,8), xogadores);
    }
    
    /**
      Procesa a xogada retornando o Resultado ou null si o xogo continúa. SOBREPOÑEMOS o método procesa_xogada(Xogada xogada)*
     * Si a xogada non é legal, lanza unha Exception MovementoIlegal
     * @param xogada - Xogada a procesar (e unha XogadaFilaColumna)
     */
    @Override
    protected Resultado procesaXogada(Xogada xogada) throws MovementoIlegal {
        boolean ok=false;
        XogadaFilaColumna xr=(XogadaFilaColumna) xogada;    // Casting para poder acceder a metodos que so ten XogadaFilaColumna
        //      1.- Debemos comprobar si a xogada e correcta
        //          A xogada é correcta si: 
        //              - A posición está dentro do taboleiro
        //              - A posición está libre
        //              - Si cando se xoga na posición se reviran fichas contrarias
        
        Ficha ficha=estado.colleFicha(xr.getFila(),xr.getColumna());    // Si está fora do tableiro lanza MovementoIlegal
        if (ficha!=null) throw new MovementoIlegal("A posición xa está ocupada");
        Dir[] directions=Dir.values();
        for(Dir dir:directions) {     // Imos revirando en cada direción si procede
            int numreviradas=test_reviradas(xr,dir);
            if (numreviradas > 0) {  // Se reviran fichas. As reviramos e indicamos que a xogada é legal
                revira(xr,dir,numreviradas);
                ok=true;
            }
        }
        // Si non se revirou ningunha ficha, a xogada é ilegal
        if (!ok) throw new MovementoIlegal("Movemento erróneo. Nesta posición non capturas pezas");
        // Poñemos a ficha
        estado.ponFicha(xr.getFila(),xr.getColumna(),xr.getFicha());
        
        //      3.- Si ningún xogador pode xogar, a partida remata.
        //          - Contamos as fichas de cada xogador e xeramos o Resultado
        if (bestXogada(xogadores[0])==null && bestXogada(xogadores[1])==null) {
            int count_0=count(xogadores[0].getFicha());
            int count_1=count(xogadores[1].getFicha());
            if (count_0==count_1) return new Resultado("Fin do Xogo. Un honroso empate a "+count_1+" capturas");
            else if (count_0 > count_1) return new Resultado("Fin do Xogo. O gañador foi "+xogadores[0]+" con "+count_0+" capturas");
            return new Resultado("Fin do Xogo. O gañador foi "+xogadores[1]+" con "+count_1+" capturas");
        }
        return null;
    }
    
    // Retorna a xogada onde o Xogador xog captura máis fichas contrarias ou null si non pode capturar ningunha
    // O fago así porque pode ser de utilidade para a creación de Bots de xogo.
    public XogadaFilaColumna bestXogada(Xogador xog) {
        XogadaFilaColumna xr=null;
        int tot=0;
        for (int fila=0;fila<estado.nFilas(); fila++) {
            for(int columna=0;columna<estado.nColumnas();columna++) {
                try {
                    if (estado.colleFicha(fila, columna)==null) {  // A casiña está baleira, podo xogar
                        int num=0;
                        XogadaFilaColumna xogada=new XogadaFilaColumna(xog,xog.getFicha(),fila,columna);
                        Dir[] directions=Dir.values();  // Non queremos xerar o array para cada elemento Dir no seguinte for
                        for(Dir dir:directions) { // Sumo as capturas desta xogada en todas as direccións
                            num+=test_reviradas(xogada,dir);
                        }
                        if (num > tot) { // Esta xogada captura máis fichas que a anterior, quedo con ela
                            xr=xogada;
                            tot=num;
                        }
                    }
                } catch (MovementoIlegal ex) {
                    // Non debe ocurrir nunca, capturo e rompo o programa
                    System.out.println("ERRO FATAL en bestXogada"); // Mensaxe de depuracion
                    System.exit(0);
                }
            }
        }
        return xr;
    }
    
    /**
     * Cambia o turno ao seguinte xogador
     * Retorna o xogador en turno
     * SOBREPOÑEMOS o método de Motor, xa que so debemos cambiar de turno si o xogador
     * que colle o turno pode xogar
     */
    @Override
    protected Xogador seguinteTurno() {
        int newturno=(turno+1)%xogadores.length;
        if (bestXogada(xogadores[newturno])!=null) {    // Si o seguinte xogador en turno ten algunha xogada, cambiamos o turno
            turno=newturno;
        }
        return xogadores[turno];
    }
    
    /**
     * No reversi sempre comezan brancas (xogador 1)
     * No reversi temos 4 fichas inicialmente no tableiro
     * NOTA: Cambio en Motor xogadores e turno a protected,
     * @return 
     */
    @Override
    protected Xogador inicializa() {
        try {
            estado.init();
            turno=0;
            estado.ponFicha(3, 3, xogadores[0].getFicha());
            estado.ponFicha(4, 4, xogadores[0].getFicha());
            estado.ponFicha(3, 4, xogadores[1].getFicha());
            estado.ponFicha(4, 3, xogadores[1].getFicha());
        } catch (MovementoIlegal ex) {
            // Non debe ocurrir nunca, capturo e rompo o programa
            System.out.println("ERRO FATAL en inicializa"); // Mensaxe de depuracion
            System.exit(0);         }
        return xogadores[turno];
    }

    // Retorna o número de fichas que a xogada xr captura na dirección dir
    // Implementamos equals en Ficha usando o color. NON E IMPRESCINDIBLE, xa que so temos 2 obxectos Ficha, unha para cada xogador
    private int test_reviradas(XogadaFilaColumna xr, Dir dir) {
        int fila=xr.getFila();          // Fila da Xogada
        int columna=xr.getColumna();    // Columna da Xogada
        Ficha ficha=xr.getFicha();      // Ficha que se xoga
        int count=0;
        
        try {
            fila+=dir.incy;     // Avanzamos a columna
            columna+=dir.incx;  // Avanzamos a fila
            while (estado.isIn(fila,columna)) {
                Ficha ficha_no_tableiro=estado.colleFicha(fila, columna);
                if (ficha_no_tableiro == null) return 0;    // Si atopo un null, non capturo ningunha ficha
                if (ficha.equals(ficha_no_tableiro)) return count;  // Fin da captura
                count++;            // Capturamos a ficha
                fila+=dir.incy;     // Avanzamos a columna
                columna+=dir.incx;  // Avanzamos a fila
            }
        } catch(MovementoIlegal e) {
            // Non debe ocurrir nunca, capturo e rompo o programa
            System.out.println("ERRO FATAL en test_reviradas"); // Mensaxe de depuracion
            System.exit(0);        
        }
        return 0;   // Non capturno ningunha
    }

    /**
     * Sei cantas fichas capturo, dende donde e en qué direccion
     * A captura consiste en cambiar as fichas por fichas propias
     * @param xr
     * @param dir
     * @param numreviradas 
     */
    private void revira(XogadaFilaColumna xr, Dir dir, int numreviradas) {
        int fila=xr.getFila();          // Fila da Xogada
        int columna=xr.getColumna();    // Columna da Xogada
        Ficha ficha=xr.getFicha();      // Ficha que se xoga
        try {
            while (numreviradas>0) {
                fila+=dir.incy;     // Avanzamos a columna
                columna+=dir.incx;  // Avanzamos a fila
                estado.ponFicha(fila, columna, ficha);
                numreviradas--;
            }   
        } catch(MovementoIlegal e) {
            // Non debe ocurrir nunca, capturo e rompo o programa
            System.out.println("ERRO FATAL en revira"); // Mensaxe de depuracion
            System.exit(0);
        }
    }
    
    /**
     * Conta o número de ficha que ten o tableiro
     * @param ficha
     * @return 
     */
    private int count(Ficha ficha) {
        int count=0;
        for (int columna=0;columna<estado.nColumnas();columna++) {
            for(int fila=0;fila<estado.nFilas(); fila++) {
                try {
                    if (ficha.equals(estado.colleFicha(fila, columna))) count++;
                } catch (MovementoIlegal ex) {
                    // Non debe ocurrir nunca, capturo e rompo o programa
                    System.out.println("ERRO FATAL en count"); // Mensaxe de depuracion
                    System.exit(0);
                }
            }
        }
        return count;
    }
}
