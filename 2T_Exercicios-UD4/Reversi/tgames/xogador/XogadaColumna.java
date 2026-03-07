package tgames.xogador;
import tgames.Ficha;
import tgames.Xogada;
import tgames.Xogador;

/**
Xogada4EnRaia: Representa unha xogada do xogo 4 en raia, transporta o xogador que realiza a xogada, a ficha xogada,  e a columna elixida
*/
public class XogadaColumna extends  Xogada {
    private final int columna;  // Columna onde o xogador quere realizar a xogada. E final: Unha vez establecido no construtor este atributo non pode variar o seu valor
    private final Ficha ficha;  // Ficha que usa o xogador. No xogo do 4 en raia non sería necesario, xa que todas as fichas dun xogador son iguais. E final: Unha vez establecido no construtor este atributo non pode variar o seu valor

    /**
    * Construtor: Recibe o Xogador, a Ficha, e a columna
    * Recibe o xogador que realiza a xogada, a ficha a xogar, e a columna na que quere xogar
    * O xogador é un atributo que se hereda da clase base Xogador definida no package tgames.
    */
    public XogadaColumna(Xogador xogador,Ficha ficha,int columna) {
        super(xogador);  // Chamamos ao construtor da clase pai Xogador indicando o xogador que realiza a xogada.
        this.ficha=ficha;
        this.columna=columna;
    }

    /**
    * Permite obter a Ficha xogada (que é un atributo privado)
    */
    public Ficha getFicha() {
        return ficha;
    }

    /**
    * Permite obter o valor da columna da xogada (que é un atributo privado)
    */
    public int getColumna() {
        return columna;
    }
    
    @Override
    public String toString() {
        return "> "+xogador+" xogou na columna "+columna;
    }
}
