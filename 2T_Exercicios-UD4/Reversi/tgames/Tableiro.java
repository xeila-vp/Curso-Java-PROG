package tgames;

public class Tableiro {
    private final String NULL="🔲"; // Representación no terminal para unha posición baleira 
    private Ficha[][] tableiro; // Estrutura para almacenar as fichas dos xogadores 

    /**
    * Construtor
    * @param nfilas - Numero de filas do taboleiro
    * @param ncols  - Número de columnas do taboleiro
    */
    public Tableiro(int nfilas,int ncols) {
        tableiro=new Ficha[nfilas][ncols];
    }
    
    public int nFilas() {
        return tableiro.length;
    }
    
    public int nColumnas() {
        return tableiro[0].length;
    }

    /**
    * Establecemos o estado inicial do tableiro
    */
    public void init() {
        for(int f=0;f<tableiro.length;f++) { // Percorremos as filas
            for(int c=0;c<tableiro[0].length;c++) { // Paa cada fila, percorremos as columnas
                tableiro[f][c]=null;
            }
        }
    }

    /**
    * Amosamos o taboleiro na pantalla
    */
    public void show() {
        for(int f=0;f<tableiro.length;f++) { // Percorremos as filas
            for(int c=0;c<tableiro[0].length;c++) { // Paa cada fila, percorremos as columnas
                if (tableiro[f][c]==null) System.out.print(NULL);
                else System.out.print(tableiro[f][c]);
            }
            System.out.println();   // Salto de liña 
        }
    }
    
    /**
    * Poñemos unha ficha no tabloleiro
    */
    public Ficha colleFicha(int fila,int columna) throws MovementoIlegal {
        // Comprobamos os límites.
        // tableiro.length nos da a lonxitude da primeira dimensión (número de filas)
        // tableiro[0].length nos da a lonxitude da segunda dimensión (número de columnas)
        if ((fila<0) || (fila>=tableiro.length) || (columna<0) || (columna >=tableiro[0].length))
            throw new MovementoIlegal("A posición ("+fila+","+columna+") está fora do tableiro"); // Lanzamos o sinal notificando o erro 
        return tableiro[fila][columna]; // Retornamos a ficha da posición (fila,columna). 
    }

    /**
    * Obtemos unha ficha dunha posición do taboleiro
    */
    public Ficha ponFicha(int fila,int columna,Ficha ficha) throws MovementoIlegal {
        // Comprobamos os límites.
        // tableiro.length nos da a lonxitude da primeira dimensión (número de filas)
        // tableiro[0].length nos da a lonxitude da segunda dimensión (número de columnas)
        // Comprobamos que (fila,columna) e unha posición dentro do taboleiro sinalando MovementoIlegal en caso contrario
        if ((fila<0) || (fila>=tableiro.length) || (columna<0) || (columna >=tableiro[0].length))
            throw new MovementoIlegal("A posición ("+fila+","+columna+") está fora do tableiro");         // Lanzamos o sinal notificando o erro 
        Ficha oldficha=tableiro[fila][columna];  // Recuperamos a ficha da posición (fila,columna). (pode ser null) 
        tableiro[fila][columna]=ficha;  // Gardamos a ficha na súa posición 
        return oldficha; // Retornamos a ficha que estaba anteriormente en (fila,columna)* 
    }
    
    public boolean isIn(int fila, int columna) {
        return ((fila>=0) && (fila<tableiro.length) && (columna>=0) && (columna<tableiro[0].length));
    }

}