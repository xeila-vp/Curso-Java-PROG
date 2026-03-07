package catroenraia;

import tgames.xogador.XogadaColumna;
import tgames.xogador.XogadorColumna;
import java.util.Random;

import tgames.Ficha;
import tgames.MovementoIlegal;
import tgames.Xogada;

/**
 * *Xogador4enRaiaBotSimple*: Xogador do 4 en Raia automático
 * Sortea unha das columnas posibles.
 * {WARNING}{BLUE} Neste caso poderíamos herdar de Xogador directamente, non habería maior diferencia
 */
public class Xogador4enRaiaBotSimple extends XogadorColumna {
    Motor4enRaia estado; // O estado do xogo (que obtemos usando o Motor) o necesitamos para planear a xogada{CYAN}

    /**
    *  Necesitamos acceder ao Motor do xogo para examinar o estado do xogo, de modo que o facilitamos no construtor
     * @param ficha
     * @param m
    */
    public Xogador4enRaiaBotSimple(Ficha ficha,Motor4enRaia m) {
        super(ficha);
        this.estado=m;
    }

    /**
    * Sobrepoñemos o método xoga() de XogadorColumna 
 Poderíamos herdar directamente do xenérico Xogador xa que non aproveitamos nada de XogadorColumna.
    */
    @Override
    public Xogada xoga() throws MovementoIlegal {
        Xogada xogada=null;
        int[] xogadas_posibles=columnasXogables();
        int elexida=new Random().nextInt(xogadas_posibles.length); // Retorna un número ao azar entre 0 e xogadas_posibles.length-1
        xogada=new XogadaColumna(this, this.ficha, elexida);
        System.out.println(this+" xoga en "+xogada); // Visualizamos a xogada elexida
        return xogada;
    }

    /**
    * Retorna un Array con todos os números de columna onde é posible xogar
    * @return
    * @throws MovementoIlegal 
    */
    int[] columnasXogables() throws MovementoIlegal {
        String columnas_str="";
        int columnas=estado.nColumnas();
        for (int c = 0; c < columnas; c++) {
            if ( estado.colleFicha(0, c) == null) {
                if (!columnas_str.isEmpty()) columnas_str=columnas_str+",";
                columnas_str=columnas_str+c;
            }
        }
        if (columnas_str.isEmpty())
            throw new MovementoIlegal("Non quedan columnas xogables");
           
        // Dividimos o String para obter o array de columnas
        String[] strcols=columnas_str.split(",");
        
        // Creamos o Array de int do tamaño axeitado
        int[] cols=new int[strcols.length];
        
        //  Convertimos a int[]
        for(int c=0;c < strcols.length; c++) cols[c]=Integer.parseInt(strcols[c]);
        return cols;
    }
}

