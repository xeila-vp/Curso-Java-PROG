package catroenraia;

import tgames.xogador.XogadaColumna;
import tgames.Ficha;
import tgames.MovementoIlegal;
import tgames.Xogada;
import tgames.Xogador;

/**
* Xogador4enRaiaBot: Xogador automático moi intelixente para o xogo 4 en raia.
* Usa estratexias avanzadas: gañar → bloquear → centro → mellor columna dispoñible.
* ☢️  Herdamos todos os atributos e métodos de Xogador4RaiaBotSimple
*/
public class Xogador4enRaiaBotSmart extends Xogador4enRaiaBotSimple {

    /**
    *  Necesitamos acceder ao Motor do xogo para examinar o estado do xogo, de modo que o facilitamos no construtor
    */
    public Xogador4enRaiaBotSmart(Ficha ficha,Motor4enRaia m) {
        super(ficha,m);
    }

    /**
    * Simula se o xogador con 'ficha' gañaría xogando en columna.
    * ⚠️  NON coloca a ficha, solo comproba si xogando nunha columna se fai 4 en raia
    */
    private boolean xogadaGana(Ficha ficha, int columna) {
        try {
                // atopar onde caería a ficha
                int fila = 0;// Comezamos na fila 0 da columna 
                // Mentras non cheguemos a última fila e a fila esté baleira, imos baixando. O final, en fila está a fila libre máis baixa.
                while (fila + 1 < estado.nFilas() && estado.colleFicha(fila + 1, columna) == null) fila++;
                // Miramos a ver si xogando nesa posición se fai 4 en raia. Usamos o atributo estado, herdado da clase pai Xogador4enRaiaBotSimple
                return estado.catroEnRaia(ficha, fila, columna);
            } catch (MovementoIlegal e) {
                return false;
            }
        }

    @Override
    public Xogada xoga() throws MovementoIlegal {
        Xogada xogada=null;
        int[] columnas = columnasXogables(); // Lista de columnas onde se pode xogar. E un método herdado de Xogador4enRaiaBotSimple 
        int centro = estado.nColumnas()/2; // 2; Columna central, para táctica de xogo* 


        // 1. PRIORIDADE MÁXIMA: ¿Podo gañar nesta xogada?
        // Probamos en cada columna si xogando ahí se fai catro en raia...
        for (int c = 0; c < columnas.length; c++) {
            int col=columnas[c];
            if (xogadaGana(this.ficha, col)) {
                xogada=new XogadaColumna(this, this.ficha, col);
            }
        }

        // Non temos aínda a xogada, probamos outro modo
        if (xogada==null) {
            // 2. SEGUNDA PRIORIDADE: ¿Vai gañar o rival? Bloqueamos!
            Xogador rival=estado.seguinteXogador();
            for (int c = 0; c < columnas.length; c++) {
                int col=columnas[c];
                if (xogadaGana(rival.getFicha(), col)) {
                    xogada=new XogadaColumna(this, this.ficha, col);
                }
            }
        }

        // Non temos aínda a xogada, probamos outro modo
        if (xogada==null) {
            // 3. Priorizar columnas centrais: xogan mellor a longo prazo.
            if (estado.colleFicha(0, centro) == null)
                xogada=new XogadaColumna(this, this.ficha, centro);
        }

        // Non temos aínda a xogada, probamos outro modo
        if (xogada==null) {
            // 4. Escollemos columna dispoñible a menor distancia ao centro.
            int mellor = -1;
            int mellorDist = 999;
            for (int c = 0; c < columnas.length; c++) {
                int col=columnas[c];
                if (estado.colleFicha(0, col) == null) {
                    int dist = Math.abs(col - centro);
                    if (dist < mellorDist) {
                        mellorDist = dist;
                        mellor = col;
                    }
                }
            }
            if (mellor != -1) {
                xogada=new XogadaColumna(this, this.ficha, mellor);
            }
        }

        // Non temos a xogada, isto non é posible. Non debería pasar porque o motor detectaría antes o empate.
        if (xogada==null)
            throw new MovementoIlegal("Non quedan columnas xogables");
        System.out.println(this+" xoga en "+xogada); // Visualizamos a xogada elexida
        return xogada; // Retornamos a xogada elexida
    }
}