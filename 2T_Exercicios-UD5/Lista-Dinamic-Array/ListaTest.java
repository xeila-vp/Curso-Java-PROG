package memstore;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public class ListaTest {
    public static void main(String[] args) {
        // Creamos a lista de Strings
        
        IList<String> lista = new ListArray<>();
        System.out.println("ListArray Test\n==========");
        long start = System.currentTimeMillis();
        test(lista);
        System.out.println("TOOKS: "+(System.currentTimeMillis()-start));
        

        System.out.println("ListArray100000 Test\n==========");
        lista=new ListArray<>();
        start = System.currentTimeMillis();
        for(int idx=0;idx<100000;idx++) lista.add(Integer.toString(idx));
        lista.remove("99980");
        lista.remove("1500");
        lista.remove("2");
        lista.remove("0");
        lista.remove("99994");
        System.out.println("TOOKS: "+(System.currentTimeMillis()-start));
    }

    public static void test(IList<String> lista) {
        System.out.println("Lista baleira? " + lista.isEmpty());

        // Engadir elementos
        lista.add("un");
        lista.add("dous");
        lista.add("tres");
        System.out.println("Despois de engadir 3 elementos: ");
        printLista(lista);

        // Engadir en posición concreta
        lista.add(1, "intermedio");
        System.out.println("Despois de engadir 'intermedio' na posición 1:");
        printLista(lista);

        // Obter elementos por índice
        System.out.println("Elemento en índice 2: " + lista.get(2));

        // Substituír elemento
        String antigo = lista.set(0, "cero");
        System.out.println("Substituíndo 'un' por 'cero', antigo valor: " + antigo);
        printLista(lista);

        // Buscar índices
        System.out.println("indexOf 'tres': " + lista.indexOf("tres"));
        System.out.println("lastIndexOf 'dous': " + lista.lastIndexOf("dous"));
        System.out.println("Contén 'intermedio'? " + lista.contains("intermedio"));

        // Eliminar por índice
        
        String eliminado = lista.remove(1);
        System.out.println("Eliminado elemento na posición 1: " + eliminado);
        printLista(lista);

        // Eliminar por valor
        boolean removed = lista.remove("tres");
        System.out.println("Eliminado 'tres'? " + removed);
        printLista(lista);

        // Procesamento funcional: maiúsculas
        IList<String> newlista=lista.process(new UnaryOperator<String>() {
            @Override
            public String apply(String value) {
                return value.toUpperCase();
            }
        });
        System.out.println("Despois de procesar a lista (maiúsculas):");
        System.out.print("Orixinal: ");
        printLista(lista);
        System.out.print("Nova Lista: ");
        printLista(newlista);
        
        System.out.println("Despois de actualizar a lista (maiúsculas):");
        //lista.update(s->s.toUpperCase());
        System.out.print("Orixinal: ");
        printLista(lista);
        
        // Converter a array
        String[] array = lista.toArray(new String[0]);
        System.out.println("Lista convertida a array: " + Arrays.toString(array));
        
        System.out.println("Coa letra E");
        IList<String> filtrada=lista.filter(s->s.contains("E"));
        printLista(filtrada);

        // Limpar lista
        lista.clear();
        System.out.println("Despois de clear(): lista baleira? " + lista.isEmpty());
    }

    // Método auxiliar para imprimir a lista
    private static <T> void printLista(IList<T> lista) {
        System.out.print("[");
        int length=lista.size();
        for (int i = 0; i < length; i++) {
            System.out.print(lista.get(i));
            if (i < lista.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
