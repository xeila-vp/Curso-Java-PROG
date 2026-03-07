package axenda.model.dao;
import axenda.model.Contacto;

/**
 * Excepción personalizada para xestionar tentativas de inserción de elementos duplicados.
 * 
 * <p><strong>Propósito:</strong> Indica que se intentou engadir un elemento que xa existe nunha colección,
 * estrutura de datos ou sistema de almacenamento onde os elementos deben ser únicos.</p>
 * 
 * <p><strong>Características principais:</strong></p>
 * <ul>
 *   <li><strong>Especificidade:</strong> Proporciona unha mensaxe de erro clara e específica sobre o elemento duplicado</li>
 *   <li><strong>Flexibilidade:</strong> Ofrece dous construtores: un xeral e outro específico que acepta o obxecto duplicado</li>
 *   <li><strong>Integración:</strong> Funciona perfectamente coas clases DAO para validación de datos antes da inserción</li>
 *   <li><strong>Diagnóstico:</strong> Inclúe información detallada do elemento duplicado para facilitar a depuración</li>
 * </ul>
 * 
 * <p><strong>Patrón de uso recomendado:</strong></p>
 * <pre>
 * try {
 *     axenda.guardarContacto(novoContacto);
 * } catch (DuplicateElement e) {
 *     System.err.println("Erro: " + e.getMessage());
 *     // Ofrecer opcións ao usuario: modificar, cancelar, etc.
 * }
 * </pre>
 * 
 * <p><strong>Deseño:</strong> Esta excepción extende {@link Exception} (checked exception) porque:</p>
 * <ul>
 *   <li>A duplicidade é unha situación esperada e recuperable no fluxo normal da aplicación</li>
 *   <li>Forza ao programador a manexar explicitamente este caso, evitando que se pasen por alto</li>
 *   <li>Mellora a legibilidade do código ao indicar claramente que un método pode rexeitar elementos duplicados</li>
 * </ul>
 * 
 * <p><strong>Integración co sistema:</strong> Esta clase está deseñada para traballar en conxunto coas clases DAO
 * ({@link ContactosDAO}) como parte dun sistema robusto de validación de integridade de datos.</p>
 * 
 * @author Desenvolvemento IES Rodeira
 * @version 1.1
 * @since 2023-10-15
 * @see ContactosDAO
 * @see StorageFull
 */
public class DuplicateElement extends Exception {

     /**
     * Construtor específico que acepta o elemento duplicado para crear unha mensaxe detallada.
     * 
     * <p><strong>Propósito:</strong> Proporciona información contextual sobre qué elemento exactamente
     * se está a duplicar, o que é crucial para a depuración e para dar retroalimentación útil ao usuario.</p>
     * 
     * <p><strong>Como funciona:</strong> Utiliza o método {@code toString()} do obxecto Contacto para
     * xerar unha representación de cadea significativa. Isto require que a clase {@link Contacto}
     * teña unha implementación adecuada de {@code toString()} que mostre información relevante.</p>
     * 
     * <p><strong>Exemplo de uso:</strong></p>
     * <pre>
     * throw new DuplicateElement(contactoExistente);
     * </pre>
     * 
     * <p><strong>Mensaxe resultante:</strong> "Contacto[NIF=12345678Z, Nome=Ana, Apelidos=García]"</p>
     * 
     * @param c O obxecto Contacto que causou a duplicidade
     * @since 1.0
     */
    public DuplicateElement(Contacto c) {
        super(c.toString());
    }
    
    /**
     * Construtor por defecto que usa unha mensaxe estándar para duplicidades.
     * 
     * <p><strong>Propósito:</strong> Ofrece unha mensaxe xeral cando non é necesario ou posible especificar
     * que elemento exactamente está duplicado, ou cando o contexto xa é claro polo fluxo da aplicación.</p>
     * 
     * <p><strong>Vantaxes:</strong></p>
     * <ul>
     *   <li><strong>Consistencia:</strong> Todas as duplicidades sen especificar usan a mesma mensaxe</li>
     *   <li><strong>Simplicidade:</strong> Redúce o código necesario para crear a excepción</li>
     *   <li><strong>Internacionalización:</strong> Facilita a tradución posterior xa que hai un só punto de cambio</li>
     * </ul>
     * 
     * <p><strong>Nota de deseño:</strong> A mensaxe por defecto está en galego para manter a coherencia co resto da aplicación.
     * Para aplicacións multilingües, deberíase considerar cargar a mensaxe desde un ficheiro de recursos.</p>
     * 
     * <p><strong>Exemplo de uso:</strong></p>
     * <pre>
     * throw new DuplicateElement();
     * </pre>
     * 
     * <p><strong>Mensaxe resultante:</strong> "Elemento Duplicado"</p>
     * 
     * @since 1.0
     */
    public DuplicateElement() {
        super("Elemento Duplicado"); // constructor por defecto
    }
}