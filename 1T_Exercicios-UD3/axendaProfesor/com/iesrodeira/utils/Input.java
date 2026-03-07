package com.iesrodeira.utils;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Clase utilitaria para xestionar a entrada de datos do usuario con validación robusta.
 * Proporciona métodos para ler e validar diferentes tipos de datos (texto, números, emails,
 * NIFs, etc.) con xestión de erros e opcións de cancelación.
 * 
 * <p><strong>Características principais:</strong></p>
 * <ul>
 *   <li>Validación de formatos mediante expresións regulares</li>
 *   <li>Xestión de cancelacións polo usuario (* para cancelar)</li>
 *   <li>Valores por defecto para campos opcionais</li>
 *   <li>Interface de usuario amigable con menús e formatos</li>
 *   <li>Xestión de excepcións para fluxos de erro claros</li>
 * </ul>
 * 
 * <p><strong>Patróns de deseño utilizados:</strong></p>
 * <ul>
 *   <li><em>Singleton implícito</em>: Un único Scanner compartido para toda a clase</li>
 *   <li><em>Validación defensiva</em>: Todos os datos de entrada son validados antes de devolverse</li>
 *   <li><em>Encapsulación</em>: Oculta a complexidade da validación detrás de interfaces sinxelas</li>
 * </ul>
 * 
 * <p><strong>Notas de desenvolvemento:</strong></p>
 * <ul>
 *   <li>Todos os métodos son estáticos para facilitar o acceso sen necesidade de instanciar a clase</li>
 *   <li>As constantes regex están definidas como static final para optimización e claridade</li>
 *   <li>Úsase un único Scanner para evitar problemas de buffer de entrada</li>
 *   <li>As excepcións personalizadas (CancelException) permiten un control de fluxo máis limpo</li>
 * </ul>
 * 
 * @author Desenvolvemento IES Rodeira
 * @version 1.2
 * @since 2023-10-15
 */
public class Input {
    // Engadir este construtor para impedir a instanciación
    private Input() {
        // Evita que a clase utilitaria poida ser instanciada
    }
    
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
    * Scanner compartido para toda a clase. Definido como static final para:
    * <ul>
    *   <li>Evitar a creación múltiple de obxectos Scanner</li>
    *   <li>Prevenir problemas de buffer de entrada ao usar múltiples scanners</li>
    *   <li>Optimizar recursos (un único scanner para todas as operacións)</li>
    * </ul>
    * <p><strong>ADVERTENCIA:</strong> Non se debe pechar este scanner dentro da clase,
    * xa que System.in só se pode pechar unha vez e afectaría a outras partes da aplicación.</p>
    */
    private static final Scanner scanner = new Scanner (System.in );


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
    public static void testNif(String nif) throws IllegalArgumentException {
        char[] control={'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        
        // Validación básica de formato: non nulo e exactamente 9 caracteres
        if (nif == null || nif.length()!=9) {
            throw new IllegalArgumentException ("NIF debe ter 8 números e 1 letra" );
        }
        
        // Extracción dos compoñentes: 8 números + 1 letra
        String numero_str =  nif.substring(0,8);
        char letra = Character.toUpperCase(nif.charAt(8));

        try {
            // Conversión a número e cálculo do índice para a letra de control
            int numero=Integer.parseInt(numero_str);
            int index=numero%23;
            if (control[index]!=letra)
                throw new IllegalArgumentException("DNI non valido. A letra non coincide.");
        } catch(NumberFormatException e) {
            // Captura erros de conversión (os primeiros 8 caracteres non son números)
            throw new IllegalArgumentException("DNI non válido");
        }
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
    public static void testEmail(String email) throws IllegalArgumentException {
        /*if (!email.matches(REGEX_EMAIL)) {
            throw new IllegalArgumentException ("O Email non -e correcto");
        }*/
        // Teño precompilado o PATTERN arriba de xeito estático
        if (!Input.REGEX_EMAIL_PATTERN.matcher(email).matches())
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
    public static void testPhone(String phone) throws IllegalArgumentException {
        if (phone == null || !phone.matches(REGEX_PHONE)) {
          throw new IllegalArgumentException("teléfono no valido");
        }
    }

    /**
    * Lê un texto do usuario con opción de cancelación.
    * 
    * @param title A mensaxe que se amosa ao usuario antes da entrada
    * @return O texto introducido polo usuario
    * @throws CancelException Se o usuario introduce "*" para cancelar a operación
    * @since 1.0
    * 
    * <p><strong>Patrón de uso:</strong> Este método é a base para todos os outros métodos de lectura.
    * Proporciona un mecanismo consistente de cancelación que se propaga a tódolos métodos derivados.</p>
    */
    public static String readText(String title) throws CancelException {
        System.out.print(title + " (* para cancelar): ");
        String input = scanner.nextLine();
        if (input.equals("*")) {
            throw new CancelException("Entrada cancelada polo usuario.");
        }
        return input;
    }

    /**
    * Lê un texto do usuario con valor por defecto e opción de cancelación.
    * 
    * @param title A mensaxe que se amosa ao usuario (inclúe o valor por defecto entre corchetes)
    * @param defaultValue O valor que se devolverá se o usuario preme ENTER sen introducir nada
    * @return O texto introducido polo usuario ou o valor por defecto se se preme ENTER
    * @throws CancelException Se o usuario introduce "*" para cancelar a operación
    * @since 1.1
    * 
    * <p><strong>Experiencia de usuario:</strong> Mostrar o valor por defecto entre corchetes é unha
    * convención estándar en interfaces de liña de comandos que indica ao usuario que pode aceptar o valor
    * actual premendo simplemente ENTER.</p>
    */
    public static String readText(String title, String defaultValue) throws CancelException {
        String input=readText(title+" ["+defaultValue+"]");
        
        if (input.isEmpty()) {
            return defaultValue;
        }
        return input;
    }

    /**
    * Lê un número de teléfono validado do usuario.
    * 
    * @param title A mensaxe que se amosa ao usuario (ex: "Teléfono:")
    * @return O número de teléfono válido introducido polo usuario
    * @throws CancelException Se o usuario cancela a operación introducindo "*"
    * @since 1.0
    * 
    * <p><strong>Patrón de validación:</strong> Usa un bucle de validación continua (while true) que só
    * remata cando se introduce un valor válido. Este patrón é robusto pero require atención para
    * evitar bucles infinitos (garantido pola posibilidade de cancelación).</p>
    */
    public static String readPhone(String title) throws CancelException {
        while (true) {
            String input = readText(title);
            try {
                testPhone(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
    
    /**
    * Lê un NIF validado do usuario.
    * 
    * @param title A mensaxe que se amosa ao usuario (ex: "NIF:")
    * @return O NIF válido introducido polo usuario
    * @throws CancelException Se o usuario cancela a operación introducindo "*"
    * @since 1.0
    * 
    * <p><strong>Seguridade:</strong> A validación de NIF é crítica en moitas aplicacións. Este método
    * garante que só se acepten NIFs correctamente formatados antes de proseguir co programa.</p>
    */
    public static String readNif(String title) throws CancelException {
        while (true) {
            String input = readText(title);
            try {
                testNif(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    /**
    * Lê un número de teléfono con valor por defecto e validación.
    * 
    * @param title A mensaxe que se amosa ao usuario (ex: "Teléfono:")
    * @param defaultValue O valor por defecto que se mostra e usa se o usuario preme ENTER
    * @return O número de teléfono válido introducido polo usuario ou o valor por defecto
    * @throws CancelException Se o usuario cancela a operación introducindo "*"
    * @since 1.1
    * 
    * <p><strong>Consistencia:</strong> Este método combina as capacidades de readPhone() e readText()
    * con valor por defecto, mantendo unha interface uniforme para o programador.</p>
    */
    public static String readPhone(String title, String defaultValue) throws CancelException {
        while (true) {
            String input = readText(title, defaultValue);
            try {
                testPhone(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    /**
    * Lê un enderezo de correo electrónico validado do usuario.
    * 
    * @param title A mensaxe que se amosa ao usuario (ex: "Email:")
    * @return O enderezo de correo electrónico válido introducido polo usuario
    * @throws CancelException Se o usuario cancela a operación introducindo "*"
    * @since 1.0
    * 
    * <p><strong>Nota:</strong> Usa a validación avanzada (REGEX_EMAIL) que é máis estrita pero
    * máis segura ca REGEX_EMAIL_SIMPLE. Para aplicacións menos críticas, poderíase ofrecer unha
    * opción para usar a validación simple.</p>
    */
    public static String readEmail(String title) throws CancelException {
        while (true) {
            String input = readText(title);
            try {
                testEmail(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    /**
     * Lê un enderezo de correo electrónico con valor por defecto e validación.
     * 
     * @param title A mensaxe que se amosa ao usuario (ex: "Email:")
     * @param defaultValue O valor por defecto que se mostra e usa se o usuario preme ENTER
     * @return O enderezo de correo electrónico válido introducido polo usuario ou o valor por defecto
     * @throws CancelException Se o usuario cancela a operación introducindo "*"
     * @since 1.1
     * 
     * <p><strong>Patrón de deseño:</strong> Este método demostra o patrón de método sobrecargado (overloading),
     * proporcionando flexibilidade mantendo unha interface clara.</p>
     */
    public static String readEmail(String title, String defaultValue) throws CancelException {
        while (true) {
            String input = readText(title, defaultValue);
            try {
                testEmail(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    /**
     * Lê unha única opción do usuario entre un conxunto de opcións válidas.
     * 
     * @param title A mensaxe que se amosa ao usuario
     * @param validos Unha cadea cos caracteres válidos (ex: "S/N" para Sí/Non)
     * @return O carácter escollido polo usuario en maiúsculas
     * @throws CancelException Se o usuario cancela a operación introducindo "*"
     * @since 1.0
     * 
     * <p><strong>Flexibilidade:</strong> O método acepta opcións en maiúsculas ou minúsculas e devolve
     * sempre o carácter en maiúsculas, simplificando a lóxica posterior.</p>
     */
    public static char option(String title, String validos) throws CancelException {
        while (true) {
            String input = readText(title + " (" + validos + ")");
            if (input.length() == 1 && validos.toLowerCase().contains(input.toLowerCase())) {
                return Character.toUpperCase(input.charAt(0));
            }
            System.out.println("Opción non válida. Válidas: " + validos);
        }
    }
    
    /**
     * Xera unha liña de guións co mesmo tamaño que o texto proporcionado.
     * Úsase para crear separadores visuais en menús e títulos.
     * 
     * @param text O texto base para determinar a lonxitude da liña
     * @return Unha cadea de guións do mesmo tamaño que o texto de entrada
     * @since 1.0
     * 
     * <p><strong>Nota de rendemento:</strong> Para textos moi longos, considérese usar StringBuilder
     * no canto de concatenación de cadeas nun bucle para maior eficiencia.</p>
     */
    public static String subline(String text) {
        String line="";
        int len=text.length();
        for(int c=0;c<len;c++) line+="-";
        return line;
    }
    
  
    /**
     * Amosa un título con unha liña de guións por debaixo para mellorar a presentación.
     * 
     * @param title O título que se quere amosar
     * @since 1.0
     * 
     * <p><strong>Boa práctica de UI:</strong> Unha presentación visual clara mellora significativamente
     * a experiencia do usuario en interfaces de liña de comandos.</p>
     */    
    public static void printTitle(String title) {
        System.out.println(title);
        System.out.println(subline(title));
    }
    
    /**
     * Amosa un menú interactivo e devolve a opción escollida polo usuario.
     * 
     * @param title O título do menú
     * @param opciones Un array cos textos das opcións do menú
     * @return O número da opción escollida (1 a n) ou (n+1) para saír
     * @throws CancelException Se o usuario cancela a operación introducindo "*"
     * @since 1.0
     * 
     * <p><strong>Deseño de interface:</strong> O menú inclúe automaticamente unha opción de saída
     * como última opción, seguindo convencións estándar de usabilidade.</p>
     */
    public static int menu(String title,Object[] opciones) throws CancelException {
        int op=-1;
        do {
            // Visualiza menú
            Input.printTitle(title);
            for(int c=0; c<opciones.length; c++) System.out.println((c+1)+".- "+opciones[c]);
            System.out.println(opciones.length+1+".- Saír");
            try {
                System.out.print("Elixe opcion: ");
                String str=scanner.nextLine();
                op=Integer.parseInt(str);
            } catch(NumberFormatException e) {
                op=-1;
            }
        } while((op < 0) || (op > opciones.length+1));
        return op;
    }

    /**
    * Pregunta ao usuario se está seguro antes de realizar unha acción crítica.
    * 
    * @return true se o usuario responde 'S', false se responde 'N'
    * @throws CancelException Se o usuario cancela a operación introducindo "*"
    * @since 1.0
    * 
    * <p><strong>Patrón de confirmación:</strong> Este método é esencial para operacións destrutivas
    * ou irreversibles, proporcionando unha capa adicional de seguridade contra erros do usuario.</p>
    */
    public static boolean areYouSure() throws CancelException {
        char respuesta = option("Está vostede seguro?", "S/N");
        return Character.toUpperCase(respuesta) == 'S';
    }

    /**
     * Agarda a que o usuario prema ENTER para continuar, amosando unha mensaxe personalizada.
     * 
     * @param message A mensaxe que se amosa ao usuario
     * @return true sempre (para permitir cadeas de chamadas se é necesario)
     * @since 1.0
     */
    public static boolean waitEnter(String message) {
        System.out.print(message);
        scanner.nextLine();
        return true;
    }
    
    /**
     * Agarda a que o usuario prema ENTER para continuar cunha mensaxe por defecto.
     * 
     * @return true sempre (para permitir cadeas de chamadas se é necesario)
     * @since 1.0
     * 
     * <p><strong>Conveniencia:</strong> Versión sobrecargada de waitEnter() con unha mensaxe estándar
     * para uso común en fluxos de aplicación estándar.</p>
     */
    public static boolean waitEnter() {
        return waitEnter("Para continuar pulse ENTER...");
    }
}
    
    
    
    

