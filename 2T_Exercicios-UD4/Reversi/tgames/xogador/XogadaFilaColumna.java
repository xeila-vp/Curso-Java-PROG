package tgames.xogador;

import tgames.Ficha;
import tgames.Xogador;

/**
 * No Reversi Unha Xogada consiste nunha posición do tableiro: fila, columna
 * @author xavi
 */
public class XogadaFilaColumna extends XogadaColumna {
    private int fila;
    
    /**
    * Construtor: Recibe o Xogador, a Ficha, a fila e a columna
    * Recibe o xogador que realiza a xogada, a ficha a xogar, e a fila e columna na que quere xogar
    * O xogador é un atributo que se hereda da clase base Xogador definida no package tgames.
    */
    public XogadaFilaColumna(Xogador xogador,Ficha ficha,int fila, int columna) {
        super(xogador,ficha,columna);  // Chamamos ao construtor da clase pai Xogador indicando o xogador que realiza a xogada.
        this.fila=fila;
    }

    
    /**
    * Permite obter o valor da fila da xogada
    */
    public int getFila() {
        return fila;
    }
    
    @Override
    public String toString() {
        return "> "+xogador+" xogou en ("+fila+","+getColumna()+")";
    }
}
