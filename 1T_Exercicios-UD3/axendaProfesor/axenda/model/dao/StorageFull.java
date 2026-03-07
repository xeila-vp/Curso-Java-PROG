package axenda.model.dao;

/**
 * Excepción personalizada para xestionar situacións de almacenamento cheo.
 * 
 * <p><strong>Propósito:</strong> Indica que non hai espazo dispoñible para almacenar novos elementos
 * nunha estrutura de datos ou sistema de almacenamento con capacidade limitada.</p>
 * 
 * <p><strong>Características principais:</strong></p>
 * <ul>
 *   <li><strong>Especificidade:</strong> Separa claramente os erros de capacidade dos outros tipos de erros</li>
 *   <li><strong>Recuperabilidade:</strong> Representa unha situación esperada e recuperable no fluxo normal da aplicación</li>
 *   <li><strong>Diagnóstico:</strong> Permite tomar accións específicas cando se esgota o espazo de almacenamento</li>
 *   <li><strong>Mensaxes automáticas:</strong> Proporciona unha mensaxe estándar clara sen necesidade de configuración adicional</li>
 * </ul>
 * 
 * <p><strong>Patrón de uso recomendado:</strong></p>
 * <pre>
 * try {
 *     axenda.guardarContacto(novoContacto);
 * } catch (StorageFull e) {
 *     System.err.println("Erro: A axenda está chea - " + e.getMessage());
 *     // Ofrecer opcións ao usuario: ampliar capacidade, eliminar contactos antigos, etc.
 * }
 * </pre>
 * 
 * <p><strong>Deseño:</strong> Esta excepción extende {@link Exception} (checked exception) porque:</p>
 * <ul>
 *   <li>A saturación de almacenamento é unha situación esperada en sistemas con recursos limitados</li>
 *   <li>Forza ao programador a manexar explicitamente este caso, evitando que se pasen por alto</li>
 *   <li>Mellora a robustez da aplicación ao considerar límites de capacidade dende o principio</li>
 *   <li>Permite un manexo de erros máis granular que usar RuntimeException xeral</li>
 * </ul>
 * 
 * <p><strong>Integración co sistema:</strong> Esta clase está deseñada para traballar en conxunto coas clases DAO
 * ({@link ContactosDAO}) como parte dun sistema robusto de xestión de recursos e validación de capacidade.</p>
 * 
 * <p><strong>Casos de uso típicos:</strong></p>
 * <ul>
 *   <li>Arrays con tamaño fixo que chegan ao seu límite</li>
 *   <li>Estructuras de datos estáticas con capacidade máxima definida</li>
 *   <li>Sistemas con restricións de memoria ou almacenamento</li>
 *   <li>Validacións de capacidade antes de operacións críticas</li>
 * </ul>
 * 
 * @author Desenvolvemento IES Rodeira
 * @version 1.1
 * @since 2023-10-15
 * @see ContactosDAO
 * @see DuplicateElement
 */

public class StorageFull extends Exception {
    /**
     * Construtor por defecto que crea unha excepción con mensaxe automática.
     * 
     * <p><strong>Propósito:</strong> Proporciona unha mensaxe estándar clara e concisa cando non se require
     * información adicional específica do contexto. A mensaxe xerada automáticamente polo sistema
     * (normalmente "axenda.model.dao.StorageFull") pode ser suficiente para moitos casos de uso.</p>
     * 
     * <p><strong>Vantaxes:</strong></p>
     * <ul>
     *   <li><strong>Consistencia:</strong> Todas as excepcións de almacenamento cheo usan o mesmo formato de mensaxe</li>
     *   <li><strong>Simplicidade:</strong> Non require parámetros adicionais para casos xerais</li>
     *   <li><strong>Eficiencia:</strong> Crea a excepción co mínimo custo computacional</li>
     * </ul>
     * 
     * <p><strong>Nota de deseño:</strong> Este constructor é adecuado para aplicacións onde o contexto da excepción
     * xa é claro polo fluxo do programa, ou onde a mensaxe por defecto do sistema é suficiente para o diagnóstico.</p>
     * 
     * <p><strong>Exemplo de uso:</strong></p>
     * <pre>
     * if (non hai espazo dispoñible) {
     *     throw new StorageFull();
     * }
     * </pre>
     * 
     * @since 1.0
     */
    public StorageFull() {
        super();
    }
    
    /**
     * <strong>Boa práctica de extensibilidade:</strong> Aínda que esta clase só ten un construtor por defecto,
     * considérase engadir futuramente construtores adicionais para casos de uso máis específicos:
     * <pre>
     * // Constructor con mensaxe personalizada
     * public StorageFull(String mensagem) {
     *     super(mensagem);
     * }
     * 
     * // Constructor con causa para anidar excepcións
     * public StorageFull(Throwable causa) {
     *     super(causa);
     * }
     * 
     * // Constructor completo
     * public StorageFull(String mensaxe, Throwable causa) {
     *     super(mensaxe, causa);
     * }
     * </pre>
     * 
     * <p><strong>Razón actual:</strong> Manteñer a simplicidade na API inicial xa que a mensaxe por defecto
     * é suficiente para a maioría dos casos de uso no contexto actual da aplicación de xestión de axenda.</p>
     * 
     * <p><strong>Recomendación:</strong> Se a aplicación crece en complexidade e require mensaxes máis específicas
     * ou diagnóstico detallado, ampliar a clase con construtores adicionais mantendo a compatibilidade retrógrada.</p>
     */
}