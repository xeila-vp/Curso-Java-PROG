package axenda.model;

import static com.iesrodeira.utils.Input.testEmail;
import static com.iesrodeira.utils.Input.testPhone;
import java.util.Objects;

/**
 * Clase que representa un contacto na axenda, asociado a unha persoa con información de contacto.
 * 
 * <p><strong>Propósito:</strong> Modela un contacto completo que inclúe datos de comunicación (teléfono, email),
 * unha descrición opcional e unha referencia a unha persoa. Actúa coma unha capa de enriquecemento sobre a clase
 * {@link Persoa}, engadindo información específica de contacto.</p>
 * 
 * <p><strong>Características principais:</strong></p>
 * <ul>
 *   <li><strong>Validación robusta:</strong> Valida automáticamente o formato de teléfono e email no construtor</li>
 *   <li><strong>Identificador único:</strong> Ten un campo {@code numero} para identificación interna na axenda</li>
 *   <li><strong>Flexibilidade:</strong> Ofrece múltiples construtores para diferentes niveis de información</li>
 *   <li><strong>Integridade de datos:</strong> Inmutabilidade parcial con campos {@code final} para datos críticos</li>
 *   <li><strong>Comparación correcta:</strong> Implementa {@code equals()} e {@code hashCode()} para coleccións</li>
 * </ul>
 * 
 * <p><strong>Patrón de deseño:</strong> Esta clase segue o patrón <em>Value Object</em> para a parte de contactos específicos,
 * e <em>Composition</em> ao conter unha referencia a {@link Persoa}. A validación no construtor segue o patrón
 * <em>Fail-Fast</em>, detectando erros o antes posible.</p>
 * 
 * <p><strong>Relacións:</strong></p>
 * <ul>
 *   <li><strong>Composición forte con {@link Persoa}:</strong> Un contacto non pode existir sen unha persoa asociada</li>
 *   <li><strong>Dependencia de validación:</strong> Depende de {@link com.iesrodeira.utils.Input} para validación de formatos</li>
 * </ul>
 * 
 * <p><strong>Notas de seguridade:</strong> A validación no construtor prevén a creación de contactos con datos incorrectos,
 * o que protexe a integridade de toda a axenda. Os campos {@code final} garanten que a persoa asociada non cambie.</p>
 * 
 * @author Desenvolvemento IES Rodeira
 * @version 1.2
 * @since 2023-10-15
 * @see Persoa
 * @see com.iesrodeira.utils.Input
 */
public class Contacto {
    /**
     * Identificador único asignado pola axenda. Valor -1 indica que non foi asignado aínda.
     * 
     * <p><strong>Deseño:</strong> Este campo é mutable porque o seu valor depende da posición no array de almacenamento
     * ({@link axenda.model.dao.ContactosDAO}). Non forma parte da identidade lóxica do contacto para comparacións.</p>
     */    
    private int numero;
    /**
     * Descrición opcional do contacto (ex: "Colega de traballo", "Familia", etc.).
     * 
     * <p><strong>Inmutabilidade:</strong> Este campo é {@code final} porque se considera parte esencial da identidade
     * do contacto unha vez creado. Non debería cambiar durante a vida útil do obxecto.</p>
     */
    private final String descripcion;
    /**
     * Enderezo físico adicional (opcional). Non se valida no construtor actual.
     * 
     * <p><strong>Nota:</strong> Actualmente este campo non se usa nos construtores, pero está preparado para futuras
     * versións da aplicación. Pode ser {@code null} se non se especifica.</p>
     */
    private String direccion;
    /**
     * Número de teléfono do contacto. Debe ter formato válido (9 díxitos en España).
     * 
     * <p><strong>Validación:</strong> Valídase no construtor usando {@link com.iesrodeira.utils.Input#testPhone(String)}.
     * Pode modificarse posteriormente, pero novos valores non se validan automáticamente - isto requeriría
     * validación externa antes de chamar a {@link #setTelefono(String)}.</p>
     */
    private String telefono;
    /**
     * Enderezo de correo electrónico do contacto. Debe ter formato válido.
     * 
     * <p><strong>Validación:</strong> Valídase no construtor usando {@link com.iesrodeira.utils.Input#testEmail(String)}.
     * Como {@code telefono}, as modificacións posteriores non se validan automáticamente.</p>
     */
    private String email;
    /**
     * Persoa asociada a este contacto. É o núcleo identificador do contacto.
     * 
     * <p><strong>Inmutabilidade:</strong> Este campo é {@code final} e obrigatorio. Un contacto non pode existir
     * sen unha persoa asociada, e esta relación non debería cambiar unha vez establecida.</p>
     * 
     * <p><strong>Integridade referencial:</strong> A persistencia desta asociación depende da persistencia da
     * persoa asociada. Se a persoa se elimina, este contacto perdería o seu significado principal.</p>
     */
    private final Persoa persoa;
    
    
    /**
     * Crea un novo contacto con todos os campos obrigatórios e validación automática.
     * 
     * <p><strong>Validacións realizadas:</strong></p>
     * <ul>
     *   <li>Formato de email mediante {@link com.iesrodeira.utils.Input#testEmail(String)}</li>
     *   <li>Formato de teléfono mediante {@link com.iesrodeira.utils.Input#testPhone(String)}</li>
     * </ul>
     * 
     * @param persoa A persoa asociada ao contacto (non pode ser null)
     * @param telefono Número de teléfono válido
     * @param email Enderezo de correo electrónico válido
     * @param descripcion Descrición opcional do contacto
     * @throws IllegalArgumentException Se o teléfono ou email non teñen o formato correcto
     * @since 1.0
     * 
     * <p><strong>Nota de rendemento:</strong> A validación no construtor garantiza a integridade dos datos pero
     * pode ter un pequeno impacto no rendemento para volumes moi grandes de contactos. Para aplicacións críticas,
     * considerar validar antes de crear o obxecto.</p>
     */
    public Contacto(Persoa persoa,String telefono,String email,String descripcion) throws IllegalArgumentException {
        testEmail(email);
        testPhone(telefono);
        this.persoa=persoa;
        this.telefono=telefono;
        this.email=email;
        this.descripcion=descripcion;
        this.numero=-1; // Valor inicial que indica "non asignado"
        
    }
    
    /**
     * Crea un novo contacto sen descrición específica.
     * 
     * <p><strong>Convención:</strong> Usa unha cadea baleira como descrición por defecto, o que mantén consistencia
     * coa interface de usuario onde os campos baleiros son tratados como "sen información".</p>
     * 
     * @param persoa A persoa asociada ao contacto
     * @param telefono Número de teléfono válido
     * @param email Enderezo de correo electrónico válido
     * @see #Contacto(Persoa, String, String, String)
     * @since 1.0
     */    
    public Contacto(Persoa persoa,String telefono,String email) {
        this(persoa,telefono,email,"");
    }
    
    /**
     * Crea un novo contacto sen email nin descrición específica.
     * 
     * <p><strong>Caso de uso:</strong> Útil para contactos que só teñen número de teléfono (ex: contactos antigos,
     * números de servizo, etc.). O email queda como cadea baleira.</p>
     * 
     * @param persoa A persoa asociada ao contacto
     * @param telefono Número de teléfono válido
     * @see #Contacto(Persoa, String, String, String)
     * @since 1.0
     */    
    public Contacto(Persoa persoa,String telefono) {
        this(persoa,telefono,"","");
    }

    /**
     * Establece o número de identificación único para este contacto.
     * 
     * <p><strong>Patrón de uso:</strong> Este método é chamado internamente polo DAO ({@link axenda.model.dao.ContactosDAO})
     * ao gardar o contacto na axenda. Normalmente non debería ser chamado directamente polo código de negocio.</p>
     * 
     * @param numero O identificador único asignado polo sistema de almacenamento
     * @since 1.0
     * 
     * <p><strong>Nota de deseño:</strong> O feito de que este campo sexa mutable mentres que outros son inmutables
     * reflicte que o número é unha propiedade de persistencia, non parte da identidade de negocio do contacto.</p>
     */    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Establece o enderezo físico adicional para este contacto.
     * 
     * <p><strong>Flexibilidade:</strong> Este campo é opcional e pode ser null. Non ten validación específica neste momento,
     * pero poderíase engadir no futuro dependendo dos requisitos da aplicación.</p>
     * 
     * @param direccion O enderezo físico (pode ser null ou baleiro)
     * @since 1.1
     */    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Modifica o número de teléfono do contacto.
     * 
     * <p><strong>Advertencia:</strong> Este método non realiza validación automática do novo número. É responsabilidade
     * do código que chama a este método garantir que o novo valor teña formato correcto antes da modificación.</p>
     * 
     * @param telefono Novo número de teléfono
     * @since 1.0
    */    
    public void setTelefono(String telefono) throws IllegalArgumentException {
        testPhone(telefono);
        this.telefono = telefono;
    }


    /**
     * Modifica o enderezo de correo electrónico do contacto.
     * 
     * @param email Novo enderezo de correo electrónico
     * @since 1.0
     */
    public void setEmail(String email) throws IllegalArgumentException {
        testEmail(email);
        this.email = email;
    }

    /**
     * Obtén a persoa asociada a este contacto.
     * 
     * <p><strong>Seguridade:</strong> Devolve unha referencia á persoa orixinal, non unha copia. Calquera cambio
     * na persoa devolta afectará a este contacto. Para maior encapsulamento, considerar devolver unha copia en
     * versións futuras se a mutabilidade é preocupante.</p>
     * 
     * @return A persoa asociada (nunca null)
     * @since 1.0
     */    
    public Persoa getPersoa() {
        return persoa;
    }

    /**
     * Obtén o número de teléfono do contacto.
     * 
     * @return O número de teléfono (nunca null, pero pode estar baleiro en construtores específicos)
     * @since 1.0
     */
    public String getPhone() {
        return telefono;
    }

    /**
     * Obtén a descrición do contacto.
     * 
     * @return A descrición (pode ser cadea baleira se non se especificou)
     * @since 1.0
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtén o número de identificación único deste contacto.
     * 
     * <p><strong>Nota:</strong> Un valor de -1 indica que o contacto aínda non foi asignado a unha posición
     * na axenda (non foi gardado).</p>
     * 
     * @return O identificador único, ou -1 se non foi gardado
     * @since 1.0
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtén o enderezo de correo electrónico do contacto.
     * 
     * @return O email (nunca null, pero pode estar baleiro en construtores específicos)
     * @since 1.0
     */
    public String getEmail() {
        return email;
    }

    /**
     * Representación en cadea do contacto para presentación ao usuario.
     * 
     * <p><strong>Formato:</strong> {@code numero)Nome Apelidos: telefono (email) - descrición}</p>
     * <p><strong>Exemplo:</strong> {@code 5)Ana García: 612345678 (ana@email.com) - Colega de traballo}</p>
     * 
     * <p><strong>Propósito:</strong> Útil para interfaces de usuario, logs e depuración. O formato é legible e
     * contén información suficiente para identificar o contacto rapidamente.</p>
     * 
     * @return Representación en cadea do contacto
     * @since 1.0
     */    
    @Override
    public String toString() {
                
        return numero+")"+persoa.toString()+": "+telefono+" ("+email+") - "+this.descripcion;
    }

    /**
     * Calcula o código hash para este contacto baseado nos seus campos identificadores.
     * 
     * <p><strong>Campos incluídos:</strong> Só se usan {@code descripcion} e {@code persoa} porque:</p>
     * <ul>
     *   <li>{@code persoa} define a identidade principal do contacto</li>
     *   <li>{@code descripcion} permite distinguir contactos da mesma persoa con diferentes roles</li>
     *   <li>{@code numero} non se inclúe porque é un identificador de persistencia, non de negocio</li>
     *   <li>{@code telefono} e {@code email} non se inclúen porque poden cambiar sen cambiar a identidade lóxica</li>
     * </ul>
     * 
     * <p><strong>Importancia:</strong> Unha implementación correcta de {@code hashCode()} é crucial para o
     * funcionamento en coleccións como {@code HashMap} e {@code HashSet}.</p>
     * 
     * @return O código hash calculado
     * @since 1.0
     */    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.descripcion);
        hash = 47 * hash + Objects.hashCode(this.persoa);
        return hash;
    }

    /**
     * Compara este contacto con outro obxecto para determinar se son iguais.
     * 
     * <p><strong>Criterios de igualdade:</strong> Dous contactos son iguais se:</p>
     * <ul>
     *   <li>Teñen a mesma persoa asociada (comparación mediante {@code equals()})</li>
     *   <li>Teñen a mesma descrición (comparación mediante {@code equals()})</li>
     * </ul>
     * 
     * <p><strong>Exclusións:</strong> Non se consideran para a igualdade:</p>
     * <ul>
     *   <li>O número de identificación ({@code numero})</li>
     *   <li>O teléfono e email (poden actualizarse sen cambiar a identidade do contacto)</li>
     *   <li>A dirección (opcional e non crítico)</li>
     * </ul>
     * 
     * <p><strong>Coherencia:</strong> Esta implementación mantén a coherencia co método {@link #hashCode()},
     * o que é esencial para o correcto funcionamento en coleccións baseadas en hash.</p>
     * 
     * @param obj O obxecto co que comparar
     * @return true se os obxectos son iguais segundo os criterios definidos
     * @since 1.0
     */    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contacto other = (Contacto) obj;
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.persoa, other.persoa)) {
            return false;
        }
        return true;
    }
}