/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reversi;

import tgames.xogador.XogadaFilaColumna;
import tgames.Ficha;
import tgames.MovementoIlegal;
import tgames.Xogada;
import tgames.Xogador;

/**
 *
 * @author xavi
 */
public class XogadorReversiBot  extends Xogador {
    private MotorReversi motor;  // Para acceder o estado do xogo e decidir a xogada
    

    /**
     Construtor: Crea un obxecto Xogador4enRaia. Para iso debe crearse antes a parte correspondente a clase base, Xogador*
    * @param ficha  - Ficha asociada ao xogador
    */
    public XogadorReversiBot(Ficha ficha,MotorReversi motor) {
        super(ficha); // Chamamos ao construtor da clase base...
        this.motor=motor;
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
        XogadaFilaColumna xogada=null;   // Almacenará a xogada creada polo xogador
        
        
        System.out.println("Turno de "+this);
        xogada=motor.bestXogada(this);  // Eliximos a xogada onde máis fichas capturamos
        System.out.println("\t > Fila?: "+xogada.getFila());  // Visualizamos unha mensaxe informativa
        System.out.println("\t > Columna?: "+xogada.getColumna());  // Visualizamos unha mensaxe informativa
        return xogada;
    }    
    
}
