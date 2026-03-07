
package arrays;

public class Multidimensional {
    public static void main(String[] args) {
        int[][] matriz = {
                 {10, 20, 30},
                 {40, 50, 60}
        };

        int[][] matriz1 = new int[10][30]; // Neste caso *todos os elementos serán 0*
        Number[][] matriz2 = new Number[10][30]; // Neste caso *todos os elementos terán a referencia null*


        System.out.println("Elemento [0][2]: " + matriz[0][2]);
        System.out.println("Elemento [1][0]: " + matriz[1][0]);

        System.out.println("Percorrido da matriz:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.println("matriz[" + i + "][" + j + "] = " + matriz[i][j]);
            }
        }
    }
}

