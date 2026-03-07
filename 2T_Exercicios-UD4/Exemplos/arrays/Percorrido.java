
package arrays;

public class Percorrido {
    public static void main(String[] args) {
        String[] nomes = {"Ana", "Pedro", "Luis"};

        System.out.println("Percorrido con while:");
        int idx=0;
        while(idx < nomes.length) {
            System.out.println(nomes[idx]);
            idx++;
        }

        System.out.println("Percorrido con for tradicional:");
        for (int i = 0; i < nomes.length; i++) {
            System.out.println(nomes[i]);
        }

        System.out.println("Percorrido con for-each:");
        for (String nome : nomes) {
            System.out.println(nome);
        }

        System.out.println("Percorrido con Arrays.stream():");
        java.util.Arrays.stream(nomes).forEach(System.out::println);
    }
}

