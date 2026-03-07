package com.iesrodeira.utils; 

/**
 * Excepción personalizada para xestionar cancelacións explícitas por parte do usuario nas operacións de entrada de datos.
 * 
 * <p><strong>Propósito:</strong> Proporciona un mecanismo limpo e específico para detectar cando o usuario decide cancelar
 * unha operación de introdución de datos premendo '*' ou outro mecanismo de cancelación definido na aplicación.</p>
 * 
 * <p><strong>Características principais:</strong></p>
 * <ul>
 *   <li><strong>Especificidade:</strong> Separa as cancelacións intencionais dos erros de validación normais</li>
 *   <li><strong>Manexo explícito:</strong> Forza ao programador a considerar o caso de cancelación no fluxo da aplicación</li>
 *   <li><strong>Mensaxes claras:</strong> Proporciona mensaxes predeterminadas ou personalizables para a interface de usuario</li>
 * </ul>
 * 
 * <p><strong>Patrón de uso recomendado:</strong></p>
 * <pre>
 * try {
 *     String nome = Input.readText("Introduza o seu nome");
 *     // procesar nome...
 * } catch (CancelException e) {
 *     System.out.println("Operación cancelada: " + e.getMessage());
 *     // Lóxica para manexar a cancelación (volver a menú, saír, etc.)
 * }
 * </pre>
 * 
 * <p><strong>Deseño:</strong> Esta excepción extende {@link Exception} (checked exception) en lugar de 
 * {@link RuntimeException} (unchecked exception) porque:</p>
 * <ul>
 *   <li>As cancelacións son situacións esperadas e recuperables no fluxo normal da aplicación</li>
 *   <li>Forza ao programador a manexar explicitamente este caso, evitando que se pasen por alto</li>
 *   <li>Mellora a legibilidade do código ao indicar claramente que un método pode ser cancelado</li>
 * </ul>
 * 
 * <p><strong>Integración co sistema:</strong> Esta clase está deseñada para traballar en conxunto coa clase 
 * {@link com.iesrodeira.utils.Input}, formando parte dun sistema robusto de xestión de entrada de datos con validación.</p>
 * 
 * @author Desenvolvemento IES Rodeira
 * @version 1.1
 * @since 2023-10-15
 * @see com.iesrodeira.utils.Input
 */
public class CancelException extends Exception{ 
    
    /**
     * Construtor que acepta unha mensaxe de erro personalizada para a cancelación.
     * 
     * <p><strong>Caso de uso recomendado:</strong> Cando queres proporcionar información específica sobre
     * que operación foi cancelada ou contexto adicional para a interface de usuario ou rexistro de logs.</p>
     * 
     * <p><strong>Exemplo:</strong></p>
     * <pre>
     * throw new CancelException("Cancelación na introdución do NIF para o novo usuario");
     * </pre>
     * 
     * @param mensaje A mensaxe detallada que describe a razón ou contexto da cancelación
     * @since 1.0
     */
    public CancelException(String mensaje){ 
        super(mensaje);
    }

        /**
     * Construtor por defecto que usa unha mensaxe estándar para cancelacións.
     * 
     * <p><strong>Caso de uso recomendado:</strong> Cando a operación cancelada é obvia polo contexto ou
     * non require información adicional. Proporciona consistencia nas mensaxes de cancelación.</p>
     * 
     * <p><strong>Vantaxes:</strong></p>
     * <ul>
     *   <li><strong>Consistencia:</strong> Todas as cancelacións sen especificar usan a mesma mensaxe</li>
     *   <li><strong>Simplicidade:</strong> Redúce o código necesario para crear a excepción</li>
     *   <li><strong>Internacionalización:</strong> Facilita a tradución posterior xa que hai un só punto de cambio</li>
     * </ul>
     * 
     * <p><strong>Nota de deseño:</strong> A mensaxe por defecto está en galego para manter a coherencia co resto da aplicación.
     * Para aplicacións multilingües, deberíase considerar cargar a mensaxe desde un ficheiro de recursos.</p>
     * 
     * @since 1.0
     */
    CancelException() {
        super("Operación Cancelada");
    }
}