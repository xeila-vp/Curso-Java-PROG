package reversi;

import tgames.xogador.XogadorFilaColumna;
import tgames.Ficha;
import tgames.Resultado;
import tgames.Xogador;
import tgames.Xogo;

/**
 *
 * @author xavi
 */
public class Reversi {
       public static void main(String[] args) {
        Xogador[] xogadores=new Xogador[2];
        MotorReversi motor=new MotorReversi(xogadores);
        xogadores[0]=new XogadorFilaColumna(new Ficha(Ficha.YELLOW));
        xogadores[1]=new XogadorFilaColumna(new Ficha(Ficha.BROWN));
        //xogadores[0]=new XogadorReversiBot(new Ficha(Ficha.YELLOW),motor);
        //xogadores[1]=new XogadorReversiBot(new Ficha(Ficha.BROWN),motor);
        Xogo game=new Xogo(motor);
        Resultado result=game.play();
        
        System.out.println();
        System.out.println("===============================");
        System.out.println(result);
        System.out.println("===============================");
        
    } 
}
