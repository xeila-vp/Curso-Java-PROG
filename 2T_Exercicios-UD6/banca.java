import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class ContadorPalabras {
    
    public static void main(String[] args) {
         Crear o Scanner para ler a entrada do usuario
        Scanner scanner = new Scanner(System.in);
        
         Solicitar o texto ao usuario
        System.out.println(=== CONTADOR DE PALABRAS ===);
        System.out.print(Introduce un texto );
        String texto = scanner.nextLine();
        
         Limpiar o texto eliminar signos de puntuación e converter a minúsculas
        texto = texto.toLowerCase();
        texto = texto.replaceAll([^a-zA-Záéíóúüñs], );   
        
         Dividir o texto en palabras (separadas por espazos)
        String[] palabras = texto.trim().split(s+);
        
         Crear un TreeMap para gardar as palabras e as súas aparicións
         TreeMap ordena as chaves (palabras) automaticamente en orde alfabético
        MapString, Integer contador = new TreeMap();
        
        int counter=0;   Non queremos contar o caso de palabras baleiras
         Contar as aparicións de cada palabra
        for (String palabra  palabras) {
            if (!palabra.isEmpty()) {   Evitar palabras baleiras  (split si non ten coincidencias pode retornar a cadea baleira, por exemplo para  )
                 Se a palabra xa existe, incrementamos o contador
                 Se non existe, poñemos 1
                 contador.put(palabra, contador.getOrDefault(palabra, 0) + 1);

                 Desglosamos a liña anterior para que se entenda mellor
                Integer c=contador.get(palabra); 

                if (c==null) contador.put(palabra,1);
                else         contador.put(palabra,c+1);
                counter++;
            }
        }
        
         Mostrar resultados
        System.out.println(n=== RESULTADOS ===);
        System.out.println(Número total de palabras  + counter);
        System.out.println(Número de palabras únicas  + contador.size());
        System.out.println(n=== APARICIÓNS POR PALABRA (ORDE ALFABÉTICO) ===);
        
         Recorrer o TreeMap (xa está ordenado) e mostrar cada palabra co seu contador
        for (Map.EntryString, Integer entrada  contador.entrySet()) {
            System.out.printf(%-20s → %d aparición(s)%n, entrada.getKey(), entrada.getValue());
        }
    }
}