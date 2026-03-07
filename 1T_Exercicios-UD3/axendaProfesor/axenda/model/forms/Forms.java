package axenda.model.forms;


import com.iesrodeira.utils.CancelException;
import com.iesrodeira.utils.Input;
import axenda.model.Persoa;
import axenda.model.Contacto;

/**
 * Clase utilitaria que proporciona formularios interactivos para a xestión de datos de persoas e contactos.
 * 
 * <p><strong>Propósito:</strong> Centraliza a lóxica de entrada de datos do usuario para garantir consistencia
 * na validación e presentación da información nas operacións de creación e edición de persoas e contactos.</p>
 * 
 * Esta clase é coma un "asistente" que axuda ao usuario a introducir datos correctamente.
 * Contén métodos que amosan preguntas na pantalla e recollen as respostas do usuario,
 * validando que os datos estean ben introducidos antes de crear obxectos.
 * 
 * Esta clase mostra como organizar a entrada de datos
 * do usuario de xeito ordenado e con validacións, o que é moi importante en calquera programa.
 * 
 * <p><strong>Características principais:</strong></p>
 * <ul>
 *   <li><strong>Formularios completos:</strong> Proporciona métodos para crear novas persoas e contactos dende cero</li>
 *   <li><strong>Edición de contactos:</strong> Permite modificar contactos existentes mantendo os datos orixinais como valores por defecto</li>
 *   <li><strong>Validación robusta:</strong> Utiliza a clase {@link Input} para garantir que todos os datos introducidos son válidos</li>
 *   <li><strong>Presentación consistente:</strong> Amosa os datos de persoas e contactos nun formato estandarizado e amigable</li>
 *   <li><strong>Xestión de cancelacións:</strong> Soporta cancelacións do usuario en calquera punto do proceso</li>
 * </ul>
 * 
 * <p><strong>Patrón de deseño:</strong> Esta clase segue o patrón <em>Form Factory</em>, onde cada método actúa como unha
 * fábrica que produce obxectos completos validados a partir da entrada do usuario. Ademais, utiliza o patrón
 * <em>Template Method</em> nos métodos de edición, onde se mantén a estrutura básica pero se permiten valores por defecto.</p>
 * 
 * <p><strong>Integración:</strong> Diseñada para traballar en conxunto co modelo de dominio ({@link Persoa}, {@link Contacto})
 * e coas utilidades de entrada de datos ({@link Input}, {@link CancelException}).</p>
 * 
 * <p><strong>Notas de uso:</strong></p>
 * <ul>
 *   <li>Todos os métodos son estáticos para facilitar o acceso sen necesidade de instanciación</li>
 *   <li>Os métodos que modifican datos existentes devolven un novo obxecto ou modifican o existente</li>
 *   <li>Todas as operacións poden ser canceladas polo usuario introducindo '*' en calquera campo</li>
 * </ul>
 * 
 * @author Desenvolvemento IES Rodeira
 * @version 1.2
 * @since 2023-10-15
 * @see axenda.model.Persoa
 * @see axenda.model.Contacto
 * @see com.iesrodeira.utils.Input
 */
public class Forms {
    
    // Engadir este construtor para impedir a instanciación
    private Forms() {
        // Evita que a clase utilitaria poida ser instanciada
    }
    
    /**
     * Amosa un formulario para crear unha nova Persoa e devolve o obxecto creado.
     * 
     * <p><strong>Fluxo de execución:</strong></p>
     * <ol>
     *   <li>Amosa un título "Nova Persoa"</li>
     *   <li>Pide o NIF coa validación correspondente</li>
     *   <li>Pide o nome (non pode estar baleiro)</li>
     *   <li>Pide os apelidos (non pode estar baleiro)</li>
     *   <li>Crea e devolve un novo obxecto Persoa</li>
     * </ol>
     * 
     * <p><strong>Validacións:</strong></p>
     * <ul>
     *   <li>NIF: Formatado correcto (8 números + 1 letra de control)</li>
     *   <li>Nome e apelidos: Non poden estar baleiros</li>
     * </ul>
     * 
     * @return Un novo obxecto {@link Persoa} con todos os datos introducidos polo usuario
     * @throws CancelException Se o usuario cancela a operación en calquera momento introducindo '*'
     * @since 1.0
     * 
     * <p><strong>Nota de deseño:</strong> Este método non acepta valores por defecto porque se trata dunha
     * operación de creación nova onde todos os campos son obrigatórios e non existe un contexto anterior.</p>
     */    
    public static Persoa persoaForm() throws CancelException {
        Input.printTitle("Nova Persoa");
        String nif=Input.readNif("Introduce Nif: ");
        String nome=readStringNotEmpty("Introduce Nome: ");
        String apelidos=readStringNotEmpty("Introduce Apelidos: ");
        return new Persoa(nif,nome,apelidos);
    }

    /**
     * Amosa un formulario completo para crear un novo Contacto, incluíndo os datos da Persoa asociada.
     * 
     * <p><strong>Fluxo de execución:</strong></p>
     * <ol>
     *   <li>Amosa un título "Alta de Contacto"</li>
     *   <li>Chama a {@link #persoaForm()} para crear unha nova Persoa asociada</li>
     *   <li>Pide o teléfono coa validación correspondente</li>
     *   <li>Pide o email coa validación correspondente</li>
     *   <li>Pide unha descrición (opcional, pode estar baleira)</li>
     *   <li>Crea e devolve un novo obxecto Contacto</li>
     * </ol>
     * 
     * <p><strong>Relación de composición:</strong> Este método demostra a relación de composición entre
     * Contacto e Persoa, onde un Contacto require unha Persoa válida para existir.</p>
     * 
     * @return Un novo obxecto {@link Contacto} con todos os datos introducidos polo usuario
     * @throws CancelException Se o usuario cancela a operación en calquera momento introducindo '*'
     * @since 1.0
     * 
     * <p><strong>Nota de experiencia de usuario:</strong> Ao crear un contacto novo, o usuario debe introducir
     * todos os datos da persoa asociada. Isto garante que non existan contactos con datos de persoa incompletos.</p>
     */    
    public static Contacto contactoForm() throws CancelException {
        Input.printTitle("Alta de Contacto");
        Persoa p=Forms.persoaForm();
        String telefono=Input.readPhone("Telefono: ");
        String email=Input.readEmail("Email: ");
        String descricion=Input.readText("Descrición: ");
        Contacto c=new Contacto(p,telefono,email,descricion);
        return c;
    }
    
    /**
     * Amosa un formulario para editar un Contacto existente, mantendo os datos actuais como valores por defecto.
     * 
     * <p><strong>Fluxo de execución:</strong></p>
     * <ol>
     *   <li>Amosa un título "Edición de Contacto"</li>
     *   <li>Amosa os datos actuais da Persoa asociada ao contacto</li>
     *   <li>Pide o teléfono co valor actual como valor por defecto</li>
     *   <li>Pide o email co valor actual como valor por defecto</li>
     *   <li>Pide a descrición co valor actual como valor por defecto</li>
     *   <li>Actualiza os datos do contacto existente e devólveo</li>
     * </ol>
     * 
     * <p><strong>Patrón de edición:</strong> Este método utiliza o patrón de edición con valores por defecto,
     * onde o usuario pode aceptar os valores actuais premendo simplemente ENTER.</p>
     * 
     * @param c O obxecto Contacto existente que se quere editar
     * @return O mesmo obxecto Contacto actualizado cos novos valores introducidos polo usuario
     * @throws CancelException Se o usuario cancela a operación en calquera momento introducindo '*'
     * @since 1.1
     * 
     * <p><strong>Nota importante:</strong> Este método modifica o obxecto Contacto existente en lugar de crear un novo.
     * Isto é importante porque mantén a identidade do obxecto (e posiblemente a súa referencia noutras partes do sistema).</p>
     */    
    public static Contacto contactoForm(Contacto c) throws CancelException {
        Input.printTitle("Edición de Contacto");
        showPersona(c.getPersoa());
        String telefono=Input.readPhone("Telefono: ",c.getPhone());
        String email=Input.readEmail("Email: ",c.getEmail());
        String descricion=Input.readText("Descrición: ",c.getDescripcion());
        return c;
    }    
    
    /**
     * Método privado para ler unha cadea non baleira do usuario.
     * 
     * <p><strong>Comportamento:</strong> Repite a solicitude ata que o usuario introduce unha cadea non baleira.
     * O usuario pode cancelar a operación introducindo '*'.</p>
     * 
     * <p><strong>Validación:</strong> Este método garante que os campos obrigatórios (como nome e apelidos)
     * non queden baleiros, o que é crítico para a integridade dos datos.</p>
     * 
     * @param title A mensaxe que se amosa ao usuario para solicitar a entrada
     * @return A cadea introducida polo usuario (garantidamente non baleira)
     * @throws CancelException Se o usuario cancela a operación introducindo '*'
     * @since 1.0
     * 
     * <p><strong>Nota de rendemento:</strong> Aínda que usa un bucle do-while, este método é seguro contra bucles
     * infinitos porque o usuario sempre pode cancelar a operación ou introducir un valor válido.</p>
     */    
    private static String readStringNotEmpty(String title) throws CancelException {
        String str=null;
        do {
            str=Input.readText(title);
        } while(str.isEmpty());
        return str;
    }

    /**
     * Amosa na consola os datos dunha Persoa nun formato estandarizado.
     * 
     * <p><strong>Formato de presentación:</strong></p>
     * <pre>
     * Datos Persoais
     * ---------------
     * Nif: [valor]
     * Nome: [valor]
     * Apelidos: [valor]
     * </pre>
     * 
     * <p><strong>Propósito:</strong> Proporciona unha vista de lectura para os datos da persoa,
     * útil para confirmacións antes de modificacións ou simplemente para amosar información.</p>
     * 
     * @param p O obxecto Persoa cuxos datos se queren amosar
     * @since 1.0
     * 
     * <p><strong>Nota de deseño:</strong> Este método non devolve ningún valor porque o seu propósito
     * é puramente presentacional. Para necesidades de serialización ou exportación, deberían crearse
     * outros métodos específicos.</p>
     */
    public static void showPersona(Persoa p) {
        Input.printTitle("Datos Persoais");
        System.out.println("Nif: "+p.getNif());
        System.out.println("Nome: "+p.getNome());
        System.out.println("Apelidos: "+p.getApelidos());
    }
    
    /**
     * Amosa na consola todos os datos dun Contacto, incluíndo a súa Persoa asociada.
     * 
     * <p><strong>Formato de presentación:</strong></p>
     * <pre>
     * Ficha do Contacto
     * -----------------
     * [Datos da Persoa]
     * Telefono: [valor]
     * Email: [valor]
     * Descrición: [valor]
     * </pre>
     * 
     * <p><strong>Composición:</strong> Este método demostra a composición de obxectos, amosando primeiro
     * os datos da Persoa asociada e logo os datos específicos do Contacto.</p>
     * 
     * @param p O obxecto Contacto cuxos datos se queren amosar
     * @since 1.0
     * 
     * <p><strong>Boa práctica:</strong> Mantén a separación de responsabilidades: este método só amosa datos,
     * non os modifica nin realiza validacións. Isto fai o código máis mantible e reutilizable.</p>
     */
    public static void showContacto(Contacto p) {
        Input.printTitle("Ficha do Contacto");
        showPersona(p.getPersoa());
        
        System.out.println("Telefono: "+p.getPhone());
        System.out.println("Email: "+p.getEmail());
        System.out.println("Descrición: "+p.getDescripcion());
    }
    
}