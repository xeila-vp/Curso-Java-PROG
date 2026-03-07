package tgames.xogador;

import java.util.Scanner;
import tgames.Ficha;
import tgames.MovementoIlegal;
import tgames.Xogada;
import tgames.Xogador;

/**
Xogador4EnRaia: Representa un xogador do xogo 4 en raia, que elabora as xogadas indicando un número de columna
*/
public class XogadorColumna extends Xogador {
    /**
     Construtor: Crea un obxecto Xogador4enRaia. Para iso debe crearse antes a parte correspondente a clase base, Xogador*
    * @param ficha  - Ficha asociada ao xogador
    */
    public XogadorColumna(Ficha ficha) {
        super(ficha); // Chamamos ao construtor da clase base...
    }

    /**
    * xoga: Crea unha Xogada
    * Visualizamos unha mensaxe, para que se vexa que
    * Notese o uso de this para indicar o obxecto Xogador actual.
     * @return 
     * @throws tgames.MovementoIlegal
    */
    @Override
    public Xogada xoga() throws MovementoIlegal {
        Xogada xogada=null;   // Almacenará a xogada creada polo xogador
        Scanner scn=new Scanner(System.in);    // Obxecto Scanner para pedir os datos por teclado
        String text;
        System.out.print("Turno de "+this+". Columna?: ");  // Visualizamos unha mensaxe informativa
        try {   // Intentamos a entrada do número de columna.
            // Si o usuario introduce calquera cousa que non sexa un número saltará NumberFormatException ao facer a conversión
            text=scn.nextLine();
            if (text.equals("*")) System.exit(0);
            int columna=Integer.parseInt(text);  //  Solicitamos a entrada e a convertimos a número
            xogada=new XogadaColumna(this,this.ficha,columna);  // Creamos o obxecto XogadaColumna que é un obxecto Xogada específico para o xogo do 4 en Raia
        } catch(NumberFormatException e) {
            throw new MovementoIlegal("Debes introducir un número"); // Notificamos a aplicación que a xogada realizada era ilegal
        }
        return xogada;
    }
}

