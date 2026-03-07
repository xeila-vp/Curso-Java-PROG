package catroenraia;

import tgames.xogador.XogadaColumna;
import tgames.Ficha;
import tgames.Motor;
import tgames.MovementoIlegal;
import tgames.Resultado;
import tgames.Tableiro;
import tgames.Xogada;
import tgames.Xogador;

/**
Motor4EnRaia: E o motor do xogo do 4 en Raia, procesa as xogadas dos participantes e indica o resultado
*/
public class Motor4enRaia extends  Motor {

    public Motor4enRaia(Xogador[] xogadores) {
        super(new Tableiro(6,7), xogadores);
    }
    
    /**
      Procesa a xogada retornando o Resultado ou null si o xogo continúa. SOBREPOÑEMOS o método procesa_xogada(Xogada xogada)*
     * Si a xogada non é legal, lanza unha Exception MovementoIlegal
     */
    @Override
    protected Resultado procesaXogada(Xogada xogada) throws MovementoIlegal {
        XogadaColumna x4r=(XogadaColumna) xogada; // Sabemos que a Xogada é unha xogada específica do 4 en raia, facemos un "cast" para poder acceder os seus métodos
        // Recuperamos a información da xogada
        Xogador xogador=x4r.getXogador(); // Xogador
        int columna=x4r.getColumna();     // Columna
        Ficha ficha=x4r.getFicha();       // Ficha
        
        // Si a columna indicada na xogada está ocupada, sinalamos que o movemento é ilegal.
        Ficha f=estado.colleFicha(0,columna);   // Si a columna indicada está fora do tableiro, o tableiro xa lanza o sinal MovementoIlegal sinalando o erro
        if (f != null) throw new MovementoIlegal("A columna "+columna+" está ocupada"); // A columna está ocupada, non se pode xogar ahí. O sinalamos



        // CAIDA DA FICHA: Avanzamos mentres a fila esté baleira....
        int fila=1;
        try {
// Temos en f un null, porque xa sabemos que na posición 0 non existe ficha. O miramos no inicio do método.
            while(f==null) {
                f=estado.colleFicha(fila,columna); // Si devolve null, a ficha pode seguir caendo.
                if (f==null) fila=fila+1; // Non ten ficha, sigue caiendo...
            }
        } catch(MovementoIlegal e) {  // Si salta esta sinal, e que toda a columna está baleira e a ficha cae mais aló da ultima posición
        }

         // Neste momento, temos en fila a posición da primeira fila ocupada, ou a posición seguinte a última fila. Axustamos
        fila=fila-1;
        // Poñemos a ficha
        estado.ponFicha(fila,columna,ficha);

        if (catroEnRaia(ficha,fila,columna)) return new Resultado("O gañador foi "+xogador+"!! "); // Si a ficha en (fila,columna) do xogador fixo 4 en raia, gañou
        if (empate()) return new Resultado("Empate !!"); // Si o taboleiro está cheo, empate
        return null;  // Seguimos xogando
    }

    /**
     * Indica si a colocación da ficha en fila, columna fixo 4 en raia
      De momento non imos implementar isto, retornamos sempre false indicando que nunca se fai catro en raia.
     Iso fará que o xogo sempre continúe ata que o taboleiro se encha se sexa un empate.
     */
    boolean catroEnRaia(Ficha ficha,int fila,int columna) {
        return
            contaEnLiña(ficha, fila, columna, 0, 1)  ||  // horizontal →
            contaEnLiña(ficha, fila, columna, 1, 0)  ||  // vertical ↓
            contaEnLiña(ficha, fila, columna, 1, 1)  ||  // diagonal ↘
            contaEnLiña(ficha, fila, columna, 1,-1);     // diagonal ↙
    }
    
        /**
     * Conta fichas iguais na dirección dada e retorna true se hai 4
     * dx, dy indican o desprazamento na fila e columna.
     */
    private boolean contaEnLiña(Ficha ficha, int fila, int columna, int df, int dc) {
        int conta = 1; // empezamos pola ficha recén colocada

        // Sentido positivo
        conta += contaSentido(ficha, fila, columna, df, dc);

        // Sentido negativo
        conta += contaSentido(ficha, fila, columna, -df, -dc);

        return conta >= 4;
    }
    
    private int contaSentido(Ficha ficha, int fila, int columna, int df, int dc) {
        int count = 0;
        int f = fila + df;
        int c = columna + dc;

        try {
            while (estado.colleFicha(f, c) == ficha) {
                count++;
                f += df;
                c += dc;
            }
        } catch (MovementoIlegal e) {
            // chegouse aos límites do taboleiro
        }

        return count;
    }
    /**
     * Si a fila 0 está chea, será un empate.
     * @throws tgames.MovementoIlegal
     */
    private boolean empate() throws MovementoIlegal {
        int ncols=estado.nColumnas();
        for(int c=0;c<ncols;c++)
            if (estado.colleFicha(0,c)==null) return false;
        return true;
    }
}

