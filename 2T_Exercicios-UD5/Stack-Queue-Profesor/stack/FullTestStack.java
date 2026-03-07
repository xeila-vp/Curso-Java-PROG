package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class FullTestStack {
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
        System.out.println("DEMOSTRACIÓN DIDÁCTICA DA PILA (IStack<T>)");
        System.out.println("=".repeat(60));
        System.out.println();

        // ============================================================
        // 1. PILA DE NÚMEROS ENTEROS
        // ============================================================
        System.out.println("🔹 CASO 1: PILA DE ENTEROS (comportamento LIFO)");
        IStack<Integer> pilaEnteiros = new Stack<>();
        System.out.println("Estado inicial: pila baleira");

        pilaEnteiros.push(10);
        System.out.println("→ push(10)");
        printArray("   Contido (toArray):", pilaEnteiros.toArray(Integer.class));

        pilaEnteiros.push(20);
        System.out.println("→ push(20)");
        printArray("   Contido (toArray):", pilaEnteiros.toArray(Integer.class));

        pilaEnteiros.push(30);
        System.out.println("→ push(30)");
        printArray("   Contido (toArray):", pilaEnteiros.toArray(Integer.class));

        System.out.println("→ pop() = " + pilaEnteiros.pop());
        printArray("   Contido tras pop:", pilaEnteiros.toArray(Integer.class));

        System.out.println("→ pop() = " + pilaEnteiros.pop());
        printArray("   Contido tras pop:", pilaEnteiros.toArray(Integer.class));

        System.out.println("→ pop() = " + pilaEnteiros.pop());
        printArray("   Contido tras pop:", pilaEnteiros.toArray(Integer.class));
        System.out.println();

        // ============================================================
        // 2. ERRO: POP NUNHA PILA BALEIRA
        // ============================================================
        System.out.println("🔹 CASO 2: Tentativa de pop() nunha pila baleira");
        try {
            pilaEnteiros.pop();
            System.out.println("❌ ERRO: Non se lanzou a excepción agardada!");
        } catch (NoSuchElementException e) {
            System.out.println("✅ CORRECTO ("+e.getMessage()+"): Lanzouse NoSuchElementException ao facer pop() nunha pila baleira");
        }
        System.out.println();

        // ============================================================
        // 3. VALORES NULOS
        // ============================================================
        System.out.println("🔹 CASO 3: Inserción de valores null");
        IStack<String> pilaStrings = new Stack<>();
        pilaStrings.push("Primeiro");
        pilaStrings.push(null);
        pilaStrings.push("Último");

        printArray("   Contido da pila:", pilaStrings.toArray(String.class));
        System.out.println("→ pop() = " + pilaStrings.pop());
        System.out.println("→ pop() = " + pilaStrings.pop() + " (é null)");
        System.out.println("→ pop() = " + pilaStrings.pop());
        System.out.println();

        // ============================================================
        // 4. REDIMENSIONAMENTO AUTOMÁTICO
        // ============================================================
        System.out.println("🔹 CASO 4: Redimensionamento automático ao encher a pila");
        IStack<Integer> pilaGrande = new Stack<>();
        for (int i = 1; i <= 30; i++) {
            pilaGrande.push(i);
            System.out.println("   push(" + i + ") → tamaño actual: " + ((Stack<?>) pilaGrande).size());
        }
        printArray("   Contido final:", pilaGrande.toArray(Integer.class));
        System.out.println();

        // ============================================================
        // 5. toArray() NON MODIFICA A PILA
        // ============================================================
        System.out.println("🔹 CASO 5: toArray() non altera o estado da pila");
        IStack<String> pilaProba = new Stack<>();
        pilaProba.push("A");
        pilaProba.push("B");
        pilaProba.push("C");

        System.out.println("Antes de toArray():");
        printArray("   Contido:", pilaProba.toArray(String.class));

        String[] copia = pilaProba.toArray(String.class); // Non debería baleirar a pila
        System.out.println("Despois de toArray():");
        printArray("   Contido:", pilaProba.toArray(String.class));

        System.out.println("→ pop() = " + pilaProba.pop() + " (debería ser 'C')");
        System.out.println();

        // ============================================================
        // 6. PILAS INDEPENDENTES
        // ============================================================
        System.out.println("🔹 CASO 6: Dúas pilas independentes non se interferen");
        IStack<Integer> pilaX = new Stack<>();
        IStack<Integer> pilaY = new Stack<>();

        pilaX.push(100);
        pilaY.push(200);
        pilaX.push(101);

        System.out.println("Pila X: " + Arrays.toString(pilaX.toArray(Integer.class)));
        System.out.println("Pila Y: " + Arrays.toString(pilaY.toArray(Integer.class)));

        System.out.println("→ pop() en X = " + pilaX.pop());
        System.out.println("→ pop() en Y = " + pilaY.pop());
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
        
        IStack<ObxectoProba> o_pila = new Stack<>();
        o_pila.push(new ObxectoProba("Un"));
        o_pila.push(new ObxectoProba("Dous"));
        o_pila.push(new ObxectoProba("Tres"));
        o_pila.push(new ObxectoProba("Catro"));
        o_pila.push(new ObxectoProba("Cinco"));
        printArray("   Contido:", o_pila.toArray(ObxectoProba.class));

        ObxectoProba[] lista=o_pila.toArray(ObxectoProba.class);
        lista[2].texto="Acabo de modificar a información da pila";
        printArray("   Novo Contido:", o_pila.toArray(ObxectoProba.class));
        
        System.out.println("\nPara evitar isto, o método toArray debería retornar unha copia de cada un dos datos\n");
        
        // ============================================================
        // 7. RESUMO FINAL
        // ============================================================
        System.out.println("=".repeat(60));
        System.out.println("✅ DEMOSTRACIÓN FINALIZADA");
        System.out.println("=".repeat(60));
        System.out.println("OBSERVACIÓNS DIDÁCTICAS:");
        System.out.println(" • A pila segue a regra LIFO (Last In, First Out)");
        System.out.println(" • pop() nunha pila baleira lanza NoSuchElementException");
        System.out.println(" • Os valores null son válidos e consérvanse");
        System.out.println(" • toArray() devolve un novo array sen modificar a pila");
        System.out.println(" • A implementación redimensiona automaticamente o array");
        System.out.println(" • Si retornamos un Array cos elementos será posible alterar calquera elemento da pila"
                + " (xa que retornamos unha referencia dentro do array)");
        System.out.println("=".repeat(60));
    }
}