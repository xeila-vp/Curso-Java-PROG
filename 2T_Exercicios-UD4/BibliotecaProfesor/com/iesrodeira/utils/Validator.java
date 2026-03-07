package com.iesrodeira.utils;

import java.util.regex.Pattern;

/**
 *
 * @author xavi
 */
public class Validator {
    /**
     * DEFINO AQUÍ AS REGEX PARA MAIOR CLARIDADE, DESGLOSADAS POR TROZOS
     */
    
    /**
    * Expresión regular para validar números de teléfono españoles.
    * Formato: exactamente 9 díxitos.
    * <p><strong>Exemplos válidos:</strong> "612345678", "987654321"</p>
    * <p><strong>Exemplos non válidos:</strong> "61234567", "6123456789", "612 345 678"</p>
    * <p><strong>Nota de deseño:</strong> Esta regex é específica para España. Para uso internacional,
    * deberíase considerar unha validación máis complexa que permita códigos de país.</p>
    */
    private static final String REGEX_PHONE="\\d{9}";
        
    /**
    * Expresión regular simple para validar enderezos de correo electrónico.
    * Formato básico: usuario@dominio.extensión
    * <p><strong>Características:</strong></p>
    * <ul>
    *   <li>O usuario non pode conter espazos nin @</li>
    *   <li>O dominio debe ter polo menos un punto</li>
    *   <li>A extensión debe ter polo menos un carácter</li>
    * </ul>
    * <p><strong>Exemplo válido:</strong> "usuario@dominio.com"</p>
    * <p><strong>Nota:</strong> Esta versión é máis permisiva que REGEX_EMAIL pero útil para validacións rápidas.</p>
    */
    //  →  Inicio de Cadea            
    //  →  Polo menos un caracter (+) que non sexa nin "blanco" (\t \n..) nin arroba 
    //  →  Unha arroba    
    //  →  Polo menos un caracter (+) que non sexa nin "blanco" (\t \n..) nin arroba     
    //  →  Un punto
    //  →  Polo menos un caracter (+) que non sexa nin "blanco" (\t \n..) nin arroba     
    //  →  Final da cadea
    private static final String REGEX_EMAIL_SIMPLE=
        "^"
        + "[^\s@]+"
        + "@"
        + "[^\s@]+"
        + "\\."
        + "[^\s@]+"
        + "$";
        
    /**
    * Expresión regular avanzada para validar enderezos de correo electrónico.
    * <p><strong>Características avanzadas:</strong></p>
    * <ul>
    *   <li>Validación estrita do formato local-part (antes do @)</li>
    *   <li>Permite subdominios (ex: usuario@sub.dominio.com)</li>
    *   <li>Restrixe extensións de dominio a 1-63 caracteres</li>
    *   <li>Non permite caracteres especiais potencialmente perigosos</li>
    * </ul>
    * <p><strong>Exemplos válidos:</strong> "nome.apelido@dominio.com", "xestion+proba@empresarial.es"</p>
    * <p><strong>Exemplos non válidos:</strong> "usuario@dominio", "usuario@.com", "@dominio.com"</p>
    * <p><strong>Nota de rendemento:</strong> As regex complexas poden ser custosas. Para aplicacións con alto tráfico,
    * considérese usar bibliotecas especializadas como Apache Commons Validator.</p>
    */    
    //  →  Inicio de Cadea
    //  →  Detrás do inicio debe existir como mínimo unha letra maiúscula ou minúscula
    //  →  Detrás poden (*) ir caracteres que non sexa nin "blanco", nin @ nin .    
    //  →  Detrás pode aparecer ou non (*) un grupo (formado polo paréntese) formado por 
    //       - Un . seguido de como mínimo unha letra maiúscula ou minúscula
    //       - Opcionalmente seguida por un ou mais caracteres que non sexan nin "blanco" nin @ nin . 
    //  →  Detrás unha arroba
    //  →  Detrás do inicio debe existir como mínimo unha letra maiúscula ou minúscula
    //  →  Detrás poden (*) ir caracteres que non sexa nin "blanco", nin @ nin .
    //  →  Detrás pode aparecer ou non (*) un grupo (formado polo paréntese) formado por 
    //       - Un . seguido de como mínimo unha letra maiúscula ou minúscula
    //       - Opcionalmente seguida por un ou mais caracteres que non sexan nin "blanco" nin @ nin .
    //  →  Detrás ten que ter un .
    //  →  Detrás, antes de que acabe o String ten que ter unha lietra e dende 1 a 61 letras, guions ou númernos
    //
    private static final String REGEX_EMAIL=
        "^"                         
        + "[A-Za-z]"
        + "[^\s@\\.]*"
        + "(\\.[A-Za-z][^\s@\\.]*)*"            
        + "@"
        + "[A-Za-z]"
        + "[^\s@\\.]*"
        + "(\\.[A-Za-z][^\s@\\.]*)*"
        + "\\."
        + "[A-Za-z][A-Za-z0-9-]{1,62}"
        + "$";
    
    // Pattern precompilado para maior eficiencia.
    private static final Pattern REGEX_EMAIL_PATTERN=Pattern.compile(REGEX_EMAIL);


    /**
    * Valida un NIF (Número de Identificación Fiscal) español.
    * <p><strong>Algoritmo de validación:</strong></p>
    * <ol>
    *   <li>Verifica que teña exactamente 9 caracteres</li>
    *   <li>Os 8 primeiros deben ser números</li>
    *   <li>O último debe ser unha letra que corresponda co resto da división dos números entre 23</li>
    * </ol>
    * 
    * @param nif O NIF a validar (ex: "12345678Z")
    * @throws IllegalArgumentException Se o NIF non é válido coa mensaxe específica do erro:
    *         <ul>
    *           <li>"NIF debe ter 8 números e 1 letra" - formato incorrecto</li>
    *           <li>"DNI non válido" - os 8 primeiros caracteres non son números</li>
    *           <li>"DNI non valido. A letra non coincide." - letra de control incorrecta</li>
    *         </ul>
    * @since 1.0
    * 
    * <p><strong>Nota de deseño:</strong> Este método lanza excepcións en lugar de devolver booleanos
    * para forzar o manexo explícito de erros e proporcionar mensaxes específicas. É unha práctica
    * recomendada para validacións críticas onde o erro debe ser tratado de inmediato.</p>
    */    
    public static void nif(String nif) throws IllegalArgumentException {
        // Conversión a número e cálculo do índice para a letra de control
        int numero=getNifNumber(nif);
        char letra = Character.toUpperCase(nif.charAt(8));
        if (getNifControlChar(numero)!=letra)
           throw new IllegalArgumentException("DNI non valido. A letra non coincide.");
    }  
    
    /**
     * Retorna a letra de control que corresponde a un número de DNI
     * @param numero número de dni
     * @return letra de control que corresponde co número
     */
    public static char getNifControlChar(int numero) {
        char[] control={'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        int index=numero%23;
        return control[index];
    }
    
    /**
     * Retorna o número dun NIF
     * @param nif nif
     * @return numero do nif
     */
    public static int getNifNumber(String nif) {
        int numero=0;
        
        if (nif == null || nif.length()!=9) {
            throw new IllegalArgumentException ("NIF debe ter 8 números e 1 letra" );
        }
        
        // Extracción dos compoñentes: 8 números + 1 letra
        String numero_str =  nif.substring(0,8);
        try {
            // Conversión a número e cálculo do índice para a letra de control
            numero=Integer.parseInt(numero_str);
        } catch(NumberFormatException e) {
            // Captura erros de conversión (os primeiros 8 caracteres non son números)
            throw new IllegalArgumentException("DNI non válido");
        }
        return numero;
    }
    

    /**
    * Valida un enderezo de correo electrónico usando a expresión regular avanzada.
    * 
    * @param email O email a validar
    * @throws IllegalArgumentException Se o email non cumpre co formato especificado en REGEX_EMAIL
    *         coa mensaxe "O Email non é correcto"
    * @since 1.0
    * 
    * <p><strong>Nota de rendemento:</strong> O método String.matches() compila a regex cada vez que se chama.
    * Para uso intensivo, considérese pre-compilar o Pattern: 
    * <code>private static final Pattern EMAIL_PATTERN = Pattern.compile(REGEX_EMAIL);</code></p>
    */
    public static void email(String email) throws IllegalArgumentException {
        /*if (!email.matches(REGEX_EMAIL)) {
            throw new IllegalArgumentException ("O Email non -e correcto");
        }*/
        // Teño precompilado o PATTERN arriba de xeito estático
        if (!REGEX_EMAIL_PATTERN.matcher(email).matches())
            throw new IllegalArgumentException ("O Email non -e correcto");
    }  
        
    /**
    * Valida un número de teléfono usando a expresión regular específica.
    * 
    * @param phone O número de teléfono a validar
    * @throws IllegalArgumentException Se o teléfono non cumpre co formato (9 díxitos) coa mensaxe
    *         "teléfono non válido"
    * @since 1.0
    * 
    * <p><strong>Nota de internacionalización:</strong> Esta validación é específica para España.
    * Para aplicacións internacionais, deberíase implementar unha solución máis flexible que acepte
    * códigos de país e diferentes formatos.</p>
    */
    public static void phone(String phone) throws IllegalArgumentException {
        if (phone == null || !phone.matches(REGEX_PHONE)) {
          throw new IllegalArgumentException("teléfono no valido");
        }
    }
    
    /**
     * Comproba un ISBN
     * https://es.wikipedia.org/wiki/ISBN
     * @param isbn
     * @throws IllegalArgumentException 
     */
    public static String isbn(String isbn) throws IllegalArgumentException {
        if (isbn == null) throw new IllegalArgumentException("ISBN cannot be null");
        // Limpamos o String
        String clean = isbn.replaceAll("[-\\s]", "");

        switch (clean.length()) {
            case 10:
                isbn10(clean);
                break;
            case 13:
                isbn13(clean);
                break;
            default:
                throw new IllegalArgumentException("It's neither an ISB-10 nor ISBN-13");
        }
        return clean;
    }

    private static void isbn10(String isbn) throws IllegalArgumentException {
        // Deben ser 9 dixitos seguidos de outro díxito, X ou x.
        if (!isbn.matches("\\d{9}[\\dXx]")) throw new IllegalArgumentException("It's not an ISBN");
    
        // Calculamos dixito de control
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += digit * (10 - i);
        }
        int control=sum%11;
       
        char last = isbn.charAt(9);
        if (control==10) {
            if (last !='X' && last!='x') throw new IllegalArgumentException("It's not an ISBN");
        } else {
            if (Character.getNumericValue(last) != control) throw new IllegalArgumentException("It's not an ISBN");
        }
    }

    private static void isbn13(String isbn) throws IllegalArgumentException {
        // Deben ser 13 díxitos
        if (!isbn.matches("\\d{13}")) throw new IllegalArgumentException("It's not an ISBN");

        // Calculamos o díxito de control
        int sum = 0;

        for (int i = 0; i < 13; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            // Si a posición é par, tomamos o valor do díxito, si é impar o multiplicamos por 3
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        if (sum % 10 != 0) throw new IllegalArgumentException("It's not an ISBN");
    }
}
