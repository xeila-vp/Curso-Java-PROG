/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tgames.xogador;

import java.util.Scanner;
import tgames.Ficha;
import tgames.MovementoIlegal;
import tgames.Xogada;
import tgames.Xogador;

/**
 *
 * @author xavi
 */
public class XogadorFilaColumna extends Xogador {
  /**
     Construtor: Crea un obxecto Xogador4enRaia. Para iso debe crearse antes a parte correspondente a clase base, Xogador*
    * @param ficha  - Ficha asociada ao xogador
    */
    public XogadorFilaColumna(Ficha ficha) {
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
        try {   // Intentamos a entrada do número de columna.
            System.out.println("Turno de "+this);
            System.out.println("Escribe * para cancelar o xogo");
            System.out.print("\t > Fila ?: ");  // Visualizamos unha mensaxe informativa
            text=scn.nextLine();
            if (text.equals("*")) System.exit(0);
            // Si o usuario introduce calquera cousa que non sexa un número saltará NumberFormatException ao facer a conversión
            int fila=Integer.parseInt(text);  //  Solicitamos a entrada e a convertimos a número
            System.out.print("\t > Columna?: ");  // Visualizamos unha mensaxe informativa
            text=scn.nextLine();
            if (text.equals("*")) System.exit(0);
            // Si o usuario introduce calquera cousa que non sexa un número saltará NumberFormatException ao facer a conversión
            int columna=Integer.parseInt(text);  //  Solicitamos a entrada e a convertimos a número
            xogada=new XogadaFilaColumna(this,this.ficha,fila,columna);  // Creamos o obxecto Xogada4enRaia que é un obxecto Xogada específico para o xogo do 4 en Raia
        } catch(NumberFormatException e) {
            throw new MovementoIlegal("Debes introducir un número"); // Notificamos a aplicación que a xogada realizada era ilegal
        }
        return xogada;
    }    
}
