package queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class FullTestQueue {
    // Método auxiliar para imprimir arrays de forma amigable
    private static <T> void printArray(String label, T[] array) {
        StringBuilder builder=new StringBuilder();
        builder.append(label).append(" [");
        //System.out.print(label + " [");
        for (int i = 0; i < array.length; i++) {
            //System.out.print(array[i]);
            if (array[i]==null) builder.append("null");
            else                builder.append(array[i].toString());
            if (i < array.length - 1) builder.append(", "); //System.out.print(", ");
        }
        //System.out.println("]");
        builder.append("]");
        System.out.println(builder);
    }

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("DEMOSTRACIÓN DIDÁCTICA DA COLA (IQueue<T>)");
        System.out.println("=".repeat(60));
        System.out.println();

        // ============================================================
        // 1. COLA DE NÚMEROS ENTEIROS
        // ============================================================
        System.out.println("🔹 CASO 1: COLA DE ENTEROS (comportamento FIFO)");
        IQueue<Integer> colaEnteiros = new Queue<>(3);
        System.out.println("Estado inicial: cola baleira");

        colaEnteiros.add(10);
        System.out.println("→ add(10)");
        printArray("   Contido (toArray):", colaEnteiros.toArray(Integer.class));

        colaEnteiros.add(20);
        System.out.println("→ add(20)");
        printArray("   Contido (toArray):", colaEnteiros.toArray(Integer.class));
                
        colaEnteiros.add(30);
        System.out.println("→ add(30)");
        printArray("   Contido (toArray):", colaEnteiros.toArray(Integer.class));

        System.out.println("→ remove() = " + colaEnteiros.remove());
        printArray("   Contido tras remove:", colaEnteiros.toArray(Integer.class));

        System.out.println("→ remove() = " + colaEnteiros.remove());
        printArray("   Contido tras remove:", colaEnteiros.toArray(Integer.class));

        System.out.println("→ remove() = " + colaEnteiros.remove());
        printArray("   Contido tras remove:", colaEnteiros.toArray(Integer.class));
        System.out.println();

        // ============================================================
        // 2. ERRO: REMOVE NUNHA COLA BALEIRA
        // ============================================================
        System.out.println("🔹 CASO 2: Tentativa de remove() nunha cola baleira");
        try {
            colaEnteiros.remove();
            System.out.println("❌ ERRO: Non se lanzou a excepción agardada!");
        } catch (NoSuchElementException e) {
            System.out.println("✅ CORRECTO ("+e.getMessage()+"): Lanzouse NoSuchElementException ao facer remove() nunha cola baleira");
        }
        System.out.println();

        // ============================================================
        // 3. VALORES NULOS
        // ============================================================
        System.out.println("🔹 CASO 3: Inserción de valores null");
        IQueue<String> colaStrings = new Queue<>(10);
        colaStrings.add("Primeiro");
        colaStrings.add(null);
        colaStrings.add("Último");

        printArray("   Contido da cola:", colaStrings.toArray(String.class));
        System.out.println("→ remove() = " + colaStrings.remove());
        System.out.println("→ remove() = " + colaStrings.remove() + " (é null)");
        System.out.println("→ remove() = " + colaStrings.remove());
        System.out.println();

        // ============================================================
        // 4. ALMACENAMOS MOITOS
        // ============================================================
        System.out.println("🔹 CASO 4: Almacenamento de moitos elementos");
        IQueue<Integer> colaGrande = new Queue<>(29);
        try {
            for (int i = 1; i <= 30; i++) {
                colaGrande.add(i);
                System.out.println("   add(" + i + ") → tamaño actual: " + colaGrande.size());
            }
        } catch(IllegalStateException e) {
            System.out.println("Non caben tantos elementos: "+e.getMessage());
        }
        printArray("   Contido final:", colaGrande.toArray(Integer.class));
        System.out.println();

        // ============================================================
        // 5. toArray() NON MODIFICA A COLA
        // ============================================================
        System.out.println("🔹 CASO 5: toArray() non altera o estado da cola");
        IQueue<String> colaProba = new Queue<>(10);
        colaProba.add("A");
        colaProba.add("B");
        colaProba.add("C");

        System.out.println("Antes de toArray():");
        printArray("   Contido:", colaProba.toArray(String.class));

        String[] copia = colaProba.toArray(String.class); // Non debería baleirar a cola
        System.out.println("Despois de toArray():");
        printArray("   Contido:", colaProba.toArray(String.class));

        System.out.println("→ remove() = " + colaProba.remove() + " (debería ser 'A')");
        System.out.println();

        // ============================================================
        // 6. COLAS INDEPENDENTES
        // ============================================================
        System.out.println("🔹 CASO 6: Dúas colas independentes non se interferen");
        IQueue<Integer> colaX = new Queue<>(5);
        IQueue<Integer> colaY = new Queue<>(5);

        colaX.add(100);
        colaY.add(200);
        colaX.add(101);

        System.out.println("Cola X: " + Arrays.toString(colaX.toArray(Integer.class)));
        System.out.println("Cola Y: " + Arrays.toString(colaY.toArray(Integer.class)));

        System.out.println("→ remove() en X = " + colaX.remove());
        System.out.println("→ remove() en Y = " + colaY.remove());
        System.out.println();
        
        // ============================================================
        // 7. OLLO COAS REFERENCIAS 
        // ============================================================
        System.out.println("🔹 CASO 7: Os os obxectos son referencias, non o olvidemos");
        class ObxectoProba {
            String texto;
            
            ObxectoProba(String texto) {
                this.texto=texto;
            }
           
            @Override
            public String toString() {
                return texto;
            }; 
        }
        
        IQueue<ObxectoProba> o_cola = new Queue<>(5);
        o_cola.add(new ObxectoProba("Un"));
        o_cola.add(new ObxectoProba("Dous"));
        o_cola.add(new ObxectoProba("Tres"));
        o_cola.add(new ObxectoProba("Catro"));
        o_cola.add(new ObxectoProba("Cinco"));
        printArray("   Contido:", o_cola.toArray(ObxectoProba.class));

        ObxectoProba[] lista=o_cola.toArray(ObxectoProba.class);
        lista[2].texto="Acabo de modificar a información da cola";
        printArray("   Novo Contido:", o_cola.toArray(ObxectoProba.class));
        
        System.out.println("Para evitar isto, o método toArray debería retornar unha copia de cada un dos datos\n");
        
        // ============================================================
        // 7. RESUMO FINAL
        // ============================================================
        System.out.println("=".repeat(60));
        System.out.println("✅ DEMOSTRACIÓN FINALIZADA");
        System.out.println("=".repeat(60));
        System.out.println("OBSERVACIÓNS DIDÁCTICAS:");
        System.out.println(" • A cola segue a regra FIFO (First In, First Out)");
        System.out.println(" • remove() nunha cola baleira lanza NoSuchElementException");
        System.out.println(" • Os valores null son válidos e consérvanse");
        System.out.println(" • toArray() devolve un novo array sen modificar a cola");
        System.out.println(" • A implementación redimensiona automaticamente o array");
        System.out.println(" • Si retornamos un Array cos elementos será posible alterar calquera elemento da cola");
        System.out.println("=".repeat(60));
    }
}