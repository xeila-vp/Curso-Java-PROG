package catroenraia;

import tgames.xogador.XogadorColumna;
import tgames.Ficha;
import tgames.Resultado;
import tgames.Xogo;

public class Xogo4enRaia {
    public static void main(String[] args) {
        XogadorColumna[] xogadores=new XogadorColumna[2];
        Motor4enRaia motor=new Motor4enRaia(xogadores);
        xogadores[0]=new Xogador4enRaiaBotSimple(new Ficha(Ficha.RED),motor);
        xogadores[1]=new Xogador4enRaiaBotSmart(new Ficha(Ficha.YELLOW),motor);
        Xogo game=new Xogo(motor);
        Resultado result=game.play();
        
        System.out.println();
        System.out.println("===============================");
        System.out.println(result);
        System.out.println("===============================");
        
    }
}