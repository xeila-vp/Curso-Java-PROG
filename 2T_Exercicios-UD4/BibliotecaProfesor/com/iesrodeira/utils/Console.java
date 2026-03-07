package com.iesrodeira.utils;

import java.util.Scanner;

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
public class Console {
        
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
    
    
    // Engadir este construtor para impedir a instanciación
    private Console() {
        // Evita que a clase utilitaria poida ser instanciada
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
    
    public static int readInt(String title) throws CancelException {
        boolean ok=false;
        int input=0;
        
        do {
            System.out.print(title + " (* para cancelar): ");
            String data = scanner.nextLine();
            if (data.equals("*")) 
                throw new CancelException("Entrada cancelada polo usuario.");
            try {    
                input=Integer.parseInt(data);
                ok=true;
            } catch(NumberFormatException e) {
                System.out.println("\t -> Debes escribir un número enteiro");
            }
        } while(!ok);
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
    
    public static String readTextNoEmpty(String title,String defaultValue) throws CancelException {
        String input="";
        do {
            input=readText(title,defaultValue);
            if (input.isBlank())
                System.out.println("\t -> debes introducir o texto");
        } while(input.isBlank());
        return input;
    }
    

    /**
    * Lê un NIF validado
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
                Validator.nif(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("\t -> Erro: " + e.getMessage());
            }
        }
    }    
    
    /**
    * Lê un NIF validado con valor por defecto
    * 
    * @param title A mensaxe que se amosa ao usuario (ex: "NIF:")
    * @param defaultValue Valor por defecto
    * @return O NIF válido introducido polo usuario
    * @throws CancelException Se o usuario cancela a operación introducindo "*"
    * @since 1.0
    * 
    * <p><strong>Seguridade:</strong> A validación de NIF é crítica en moitas aplicacións. Este método
    * garante que só se acepten NIFs correctamente formatados antes de proseguir co programa.</p>
    */
    public static String readNif(String title,String defaultValue) throws CancelException {
        Validator.nif(defaultValue);
        while (true) {
            String input = readText(title,defaultValue);
            try {
                Validator.nif(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("\t -> Erro: " + e.getMessage());
            }
        }
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
                Validator.phone(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("\t -> Erro: " + e.getMessage());
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
                Validator.phone(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("\t -> Erro: " + e.getMessage());
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
                Validator.email(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("\t -> Erro: " + e.getMessage());
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
                if (input.isBlank()) return defaultValue;
                Validator.email(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("\t -> Erro: " + e.getMessage());
            }
        }
    }

        /**
     * Lê un ISBN
     * 
     * @param title A mensaxe que se amosa ao usuario (ex: "Email:")
     * @return O ISBN
     * @throws CancelException Se o usuario cancela a operación introducindo "*"
     * @since 1.1
     * 
     * <p><strong>Patrón de deseño:</strong> Este método demostra o patrón de método sobrecargado (overloading),
     * proporcionando flexibilidade mantendo unha interface clara.</p>
     */
    public static String readISBN(String title) throws CancelException {
        while (true) {
            String input = readText(title);
            try {
                input=Validator.isbn(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("\t -> Erro: " + e.getMessage());
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
            System.out.println("\t -> Opción non válida. Válidas: " + validos);
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
    public static String subline(int len) {
        String line="";
        for(int c=0;c<len;c++) line+="⎺";
        return line;
    }
    
    /* Retorna un texto suliñado
     * Úsase para crear separadores visuais en menús e títulos.
     * 
     * @param text O texto base para determinar a lonxitude da liña
     * @return O texto suliñado
     * @since 1.0
     * 
     * <p><strong>Nota de rendemento:</strong> Para textos moi longos, considérese usar StringBuilder
     * no canto de concatenación de cadeas nun bucle para maior eficiencia.</p>
     */
    public static String underline(String text) {
        return text+"\n"+subline(text.length());
    }
    
    /**
     * Retorna un texto cunha liña por enriba e outra por debaixo
     * Úsase para crear separadores visuais en menús e títulos.
     * 
     * @param text O texto base para determinar a lonxitude da liña
     * @return O texto cunha liña por enriba e outra por debaixo
     * @since 1.0
     * 
     * <p><strong>Nota de rendemento:</strong> Para textos moi longos, considérese usar StringBuilder
     * no canto de concatenación de cadeas nun bucle para maior eficiencia.</p>
     */
    public static String banner(String text) {
        String upperline="";
        String subline="";
        int len=text.length();
        for(int c=0;c<len;c++) {
            upperline+="⎺";
            subline+="⎽";
        }
        return upperline+"\n"+text+"\n"+subline;
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
            System.out.println(underline(title));
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
        System.out.print(message+"\nPara continuar pulse ENTER...");
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
        System.out.print("Para continuar pulse ENTER...");
        scanner.nextLine();
        return true;
    }
}
    
    
    
    

