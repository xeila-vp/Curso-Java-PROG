
package axenda.model;

import java.util.Objects;
import com.iesrodeira.utils.Input;

/**
 * Clase que representa unha persoa con identificación fiscal e datos persoais básicos.
 * 
 * <p><strong>Propósito:</strong> Modela unha entidade de persoa con identidade única a través do NIF (Número de
 * Identificación Fiscal), garantindo a integridade dos datos mediante validación rigorosa no construtor.</p>
 * 
 * <p><strong>Características principais:</strong></p>
 * <ul>
 *   <li><strong>Validación robusta:</strong> Valida automáticamente o formato do NIF no construtor usando as regras oficiais españolas</li>
 *   <li><strong>Inmutabilidade:</strong> Todos os campos son {@code final}, garantindo que unha persoa non pode cambiar de identidade unha vez creada</li>
 *   <li><strong>Identidade única:</strong> O NIF actúa como identificador único e chave primaria para a persoa</li>
 *   <li><strong>Representación estándar:</strong> Implementa {@code toString()} para unha presentación legible e consistente</li>
 *   <li><strong>Comparación correcta:</strong> Implementa {@code equals()} e {@code hashCode()} baseados no NIF para coleccións</li>
 * </ul>
 * 
 * <p><strong>Patrón de deseño:</strong> Esta clase segue o patrón <em>Value Object</em> inmutable, onde a identidade
 * está definida polo NIF e non pode cambiar. A validación no construtor segue o patrón <em>Fail-Fast</em>,
 * detectando erros de formato antes de crear o obxecto.</p>
 * 
 * <p><strong>Relacións:</strong></p>
 * <ul>
 *   <li><strong>Dependencia de validación:</strong> Depende de {@link com.iesrodeira.utils.Input} para validar o formato do NIF</li>
 *   <li><strong>Composición con {@link Contacto}:</strong> Unha persoa pode estar asociada a múltiples contactos na axenda</li>
 * </ul>
 * 
 * <p><strong>Notas de seguridade:</strong> A validación do NIF no construtor prevén a creación de persoas con identidade
 * non válida, o que é crítico para a integridade de toda a aplicación. A inmutabilidade dos campos evita cambios
 * accidentais ou maliciosos na identidade da persoa.</p>
 * 
 * @author Desenvolvemento IES Rodeira
 * @version 1.1
 * @since 2023-10-15
 * @see com.iesrodeira.utils.Input
 * @see Contacto
 */
public class Persoa {
    /**
     * Número de Identificación Fiscal (NIF) da persoa. Actúa como identificador único.
     * 
     * <p><strong>Validación:</strong> Debe cumprir o formato español oficial: 8 díxitos seguidos dunha letra de control
     * calculada segundo o algoritmo oficial. A validación realízase no construtor mediante
     * {@link com.iesrodeira.utils.Input#testNif(String)}.</p>
     * 
     * <p><strong>Inmutabilidade:</strong> Este campo é {@code final} porque o NIF define a identidade única da persoa
     * e non pode cambiar durante a súa vida útil. É a base para a implementación de {@code equals()} e {@code hashCode()}.</p>
     */
    private final  String nif;
    /**
     * Nome propio da persoa.
     * 
     * <p><strong>Inmutabilidade:</strong> Este campo é {@code final} porque forma parte esencial da identidade da persoa.
     * Aínda que nunha aplicación real o nome podería cambiar (por exemplo, por matrimonio), nesta implementación
     * considérase parte fundamental da identidade inicial.</p>
     * 
     * <p><strong>Nota:</strong> Actualmente non hai validación específica para o nome (como lonxitude mínima ou
     * caracteres permitidos), pero poderíase engadir no futuro segundo os requisitos da aplicación.</p>
     */
    private final String nome;
    /**
     * Apelidos da persoa.
     * 
     * <p><strong>Inmutabilidade:</strong> Este campo é {@code final} polo mesmo motivo que {@link #nome} - forma parte
     * da identidade da persoa na súa creación.</p>
     * 
     * <p><strong>Presentación:</strong> Nos métodos de presentación como {@link #toString()}, os apelidos móstranse
     * antes do nome para seguir convencións de ordenación alfabética estándar.</p>
     */
    private final String apelidos;
    
    /**
     * Crea un novo obxecto Persoa con validación automática do NIF.
     * 
     * <p><strong>Validación:</strong> Chama a {@link com.iesrodeira.utils.Input#testNif(String)} para verificar que
     * o NIF ten formato correcto segundo as regras españolas. Se o NIF non é válido, lanza unha excepción e non se
     * crea o obxecto, garantindo que só existan persoas con identidade válida.</p>
     * 
     * @param nif O NIF da persoa no formato 12345678Z
     * @param nome O nome propio da persoa
     * @param apelidos Os apelidos da persoa
     * @throws IllegalArgumentException Se o NIF non ten formato válido
     * @since 1.0
     * 
     * <p><strong>Nota de deseño:</strong> A validación no construtor é unha boas práctica que segue o principio
     * "fail-fast" - detecta erros o antes posible. Isto evita ter que comprobar a validez do NIF en cada método
     * que use este obxecto.</p>
     * 
     * <p><strong>Estrutura de datos:</strong> Esta clase é ideal para usar como chave en mapas ou en conxuntos
     * debido á súa inmutabilidade e implementación correcta de {@code equals()} e {@code hashCode()}.</p>
     */    
    public Persoa(String nif,String nome,String apelidos) throws IllegalArgumentException {
        Input.testNif(nif);
        this.nif=nif;
        this.nome=nome;
        this.apelidos=apelidos;
    }

    /**
     * Obtén o NIF da persoa.
     * 
     * <p><strong>Seguridade:</strong> Devolve unha copia da cadea interna (as cadeas son inmutables en Java), polo
     * que non hai risco de modificación accidental do valor interno.</p>
     * 
     * @return O NIF no formato 12345678Z
     * @since 1.0
     */    
    public String getNif() {
        return nif;
    }

    /**
     * Obtén o nome propio da persoa.
     * 
     * @return O nome da persoa
     * @since 1.0
     */    
    public String getNome() {
        return nome;
    }

    /**
     * Obtén os apelidos da persoa.
     * 
     * @return Os apelidos da persoa
     * @since 1.0
     */
    public String getApelidos() {
        return apelidos;
    }
    
    /**
     * Representación en cadea da persoa para presentación ao usuario.
     * 
     * <p><strong>Formato:</strong> {@code [NIF] Apelidos, Nome}</p>
     * <p><strong>Exemplo:</strong> {@code [12345678Z] García López, Ana}</p>
     * 
     * <strong>Propósito:</strong> Este formato é ideal para interfaces de usuario porque:
     * <ul>
     *   <li>O NIF entre corchetes destaca visualmente como identificador único</li>
     *   <li>Os apelidos antes do nome facilitan a ordenación alfabética</li>
     *   <li>A coma separa claramente apelidos e nome</li>
     * </ul>
     * 
     * @return Representación legible da persoa
     * @since 1.0
     */    
    @Override
    public String toString() {
        return "["+ nif +"] "+apelidos+", "+ nome;
    }

    /**
     * Calcula o código hash para esta persoa baseado no seu NIF.
     * 
     * <p><strong>Importancia:</strong> Unha implementación correcta de {@code hashCode()} é fundamental para o
     * funcionamento eficiente en coleccións baseadas en hash como {@code HashMap}, {@code HashSet}, ou
     * {@code Hashtable}.</p>
     * 
     * <p><strong>Algoritmo:</strong> Usa un algoritmo estándar con números primos (7 e 59) para distribuír os hashes
     * de forma uniforme e minimizar colisións. O NIF é o único campo usado porque define a identidade única da persoa.</p>
     * 
     * 
     * <p><strong>Importancia:</strong> Esta implementación é crítica para coleccións como {@code HashSet} ou
     * como chaves en {@code HashMap}, onde a igualdade determina a unicidade dos elementos.</p>
     * 
     * @return O código hash calculado
     * @since 1.0
     */    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nif);
        return hash;
    }

    /**
     * Compara esta persoa con outro obxecto para determinar se son iguais.
     * 
     * <p><strong>Criterio de igualdade:</strong> Dúas persoas son iguais se teñen o mesmo NIF. Os nomes e apelidos
     * non se consideran para a igualdade porque o NIF é o identificador único oficial.</p>
     * 
     * <p><strong>Optimizacións:</strong></p>
     * <ul>
     *   <li>Primeiro comproba se son a mesma referencia ({@code this == obj})</li>
     *   <li>Despois comproba se o obxecto é nulo</li>
     *   <li>Verifica que sexan da mesma clase ({@code getClass() != obj.getClass()})</li>
     *   <li>Finalmente compara os NIFs directamente sen usar {@code equals()} para maior eficiencia</li>
     * </ul>
     * 
     * <p><strong>Coherencia:</strong> Esta implementación mantén a coherencia co método {@link #equals(Object)},
     * o que é un requisito fundamental segundo o contrato de {@code Object} en Java.</p>
     * 
     * @param obj O obxecto co que comparar
     * @return {@code true} se os obxectos son iguais, {@code false} en caso contrario
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
        final Persoa other = (Persoa) obj;
        if (!other.nif.equals(nif)) return false;
        return true;
    }
}