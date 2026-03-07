package cuentapalabras;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CuentaPalabras {
    
    
    public static Map<String,Integer> getWords(String texto) {
        Map<String,Integer> words=new TreeMap<>();
        // Limpiar o texto: eliminar signos de puntuación e converter a minúsculas
        texto = texto.toLowerCase();
        texto = texto.replaceAll("[^a-zA-Záéíóúüñ\\s]", " ");
        
        // Dividir o texto en palabras (separadas por espazos)
        
        String[] palabras = texto.trim().split("\\s+");
        
        // Crear un TreeMap para gardar as palabras e as súas aparicións
        // TreeMap ordena as chaves (palabras) automaticamente en orde alfabético
        
        // Contar as aparicións de cada palabra
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {  // Evitar palabras baleiras
                // Se a palabra xa existe, incrementamos o contador
                // Se non existe, poñemos 1
                words.put(palabra, words.getOrDefault(palabra, 0) + 1);
                /* Mais simple:
                    Integer wordcount=words.get(palabra);
                    if (wordcount==null) words.put(palabra,1);
                    else                 words.put(palabra,wordcount+1);
                */
            }
        }
        return words;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear o Scanner para ler a entrada do usuario
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar o texto ao usuario
        System.out.println("=== CONTADOR DE PALABRAS ===");
        System.out.print("Introduce un texto: ");
        String texto = scanner.nextLine();
        
        Map<String,Integer> palabras=getWords(texto);
       
                
        // Mostrar resultados
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Número de palabras únicas: " + palabras.size());
        System.out.println("\n=== APARICIÓNS POR PALABRA (ORDE ALFABÉTICO) ===");
        int count=0;
        // Recorrer o TreeMap (xa está ordenado) e mostrar cada palabra co seu contador
        for (Map.Entry<String, Integer> entrada : palabras.entrySet()) {
            System.out.printf("%-20s → %d aparición(s)%n", entrada.getKey(), entrada.getValue());
            count+=entrada.getValue();
        }        
        System.out.println("Número total de palabras: " + count);
    }
    
}
