package axenda.model.dao;
import axenda.model.Contacto;
import java.util.Arrays;

/**
 * Clase DAO (Data Access Object) para xestionar o almacenamento e recuperación de contactos.
 * 
 * <p><strong>Propósito:</strong> Proporciona unha capa de abstracción para o acceso aos datos,
 * encapsulando a lóxica de almacenamento nun array e ofrecendo operacións CRUD (Create, Read, Update, Delete)
 * para os obxectos Contacto.</p>
 * 
 * <p><strong>Características principais:</strong></p>
 * <ul>
 *   <li><strong>Almacenamento simple:</strong> Utiliza un array estático como estrutura de datos subxacente</li>
 *   <li><strong>Busca flexible:</strong> Permite buscar contactos por múltiples criterios (NIF, teléfono, email, descrición)</li>
 *   <li><strong>Validación robusta:</strong> Comproba duplicados e espazo de almacenamento antes de gardar</li>
 *   <li><strong>Xestión de identificadores:</strong> Asigna números de posición únicos a cada contacto</li>
 *   <li><strong>Manexo de excepcións:</strong> Utiliza excepcións personalizadas para fluxos de erro claros</li>
 * </ul>
 * 
 * <p><strong>Patrón de deseño:</strong> Esta clase segue o patrón <em>Data Access Object</em>, que separa a lóxica
 * de negocio da lóxica de acceso aos datos. Isto permite cambiar a implementación de almacenamento (de array a base
 * de datos, por exemplo) sen afectar ao resto da aplicación.</p>
 * 
 * <p><strong>Limitacións coñecidas:</strong></p>
 * <ul>
 *   <li>O tamaño do array é fixo na creación, o que limita o número máximo de contactos</li>
 *   <li>Non hai persistencia entre execucións (os datos pérdense ao pechar a aplicación)</li>
 *   <li>A busca por descrición usa <code>contains()</code> o que pode ser sensible a maiúsculas/minúsculas</li>
 * </ul>
 * 
 * @author Desenvolvemento IES Rodeira
 * @version 1.2
 * @since 2023-10-15
 * @see axenda.model.Contacto
 * @see axenda.model.Persoa
 */
public class ContactosDAO {
    /**
     * Array que almacena os contactos. Cada posición pode conter un obxecto Contacto ou null.
     * 
     * <p><strong>Deseño:</strong> O array ten tamaño fixo definido no construtor. As posicións non utilizadas
     * permanecen como null. Esta implementación simple é adecuada para pequenas aplicacións con número limitado
     * de contactos.</p>
     */
    private Contacto axenda[];
    
    /**
     * Crea un novo obxecto ContactosDAO con capacidade específica.
     * 
     * @param num Capacidade máxima da axenda (número máximo de contactos que se poden almacenar)
     * @since 1.0
     * 
     * <p><strong>Nota:</strong> O tamaño do array é fixo e non se pode cambiar despois da creación.
     * Escoller un tamaño axeitado é importante para evitar desperdicio de memoria ou limitacións prematuras.</p>
     */
    public ContactosDAO(int num){
        this.axenda=new Contacto[num];
    }

    /**
     * Comproba se un contacto xa existe na axenda.
     * 
     * <p><strong>Lóxica:</strong> Recorre todo o array comparando cada contacto non nulo co contacto proporcionado
     * usando o método <code>equals()</code>. Isto require que a clase Contacto teña unha implementación correcta
     * de <code>equals()</code> e <code>hashCode()</code>.</p>
     * 
     * @param contacto O contacto que se quere comprobar
     * @return true se o contacto xa existe na axenda, false en caso contrario
     * @since 1.0
     * 
     * <p><strong>Nota de deseño:</strong> Este método é private porque só se utiliza internamente para
     * validar duplicados antes de gardar un novo contacto. A súa complexidade é O(n) onde n é o número
     * de contactos almacenados.</p>
     */    
    private boolean exists(Contacto contacto) {
        for (Contacto c : axenda) {
            if ((c != null) && (c.equals(contacto))) return true;
        }
        return false;
    }
    
    /**
     * Atopa a primeira posición dispoñible no array para almacenar un novo contacto.
     * 
     * <p><strong>Lóxica:</strong> Busca secuencialmente a primeira posición que contén null. Se non atopa
     * ningunha posición dispoñible, lanza unha excepción StorageFull.</p>
     * 
     * @return A posición do array onde se pode almacenar o novo contacto
     * @throws StorageFull Se non hai espazo dispoñible no array (está cheo)
     * @since 1.0
     * 
     * <p><strong>Optimización:</strong> Para arrays moi grandes, considerar manter un contador de posicións
     * dispoñibles ou usar unha estrutura de datos máis eficiente como unha cola de posicións libres.</p>
     */
    private int getPosicion() throws StorageFull {
        int i=0;
        while ((axenda[i] != null) && (i < axenda.length)) i++;
        if (i==axenda.length) throw new StorageFull();
        return i;
    }
       
    /**
     * Garda un novo contacto na axenda.
     * 
     * <p><strong>Validacións:</strong></p>
     * <ul>
     *   <li>Comproba se o contacto xa existe (lanza DuplicateElement se é duplicado)</li>
     *   <li>Comproba se hai espazo dispoñible (lanza StorageFull se non hai)</li>
     *   <li>Asigna un número de posición único ao contacto</li>
     * </ul>
     * 
     * @param contacto O contacto que se quere gardar
     * @throws StorageFull Se non hai espazo dispoñible para almacenar o novo contacto
     * @throws DuplicateElement Se o contacto xa existe na axenda
     * @since 1.0
     * 
     * <p><strong>Patrón de transacción:</strong> Este método realiza varias operacións atómicas:
     * validación, asignación de identificador e almacenamento. Se calquera paso falla, non se realiza
     * ningunha modificación no estado da axenda.</p>
     */
    public void guardarContacto(Contacto contacto) throws StorageFull,DuplicateElement {
        if (exists(contacto)) throw new DuplicateElement(contacto);
        int posicion=getPosicion();
        contacto.setNumero(posicion);
        axenda[posicion] = contacto;
    }

    /**
     * Busca contactos por NIF da persoa asociada.
     * 
     * @param nif O NIF polo que se quere buscar
     * @return Un array con todos os contactos que teñen esa persoa asociada, ou null se non se atopa ningún
     * @since 1.0
     * 
     * <p><strong>Nota:</strong> Devolve un novo array con só os contactos coincidentes,
     * Debería crear unha copia dos obxectos en lugar de retornar a referencia os obxectos orixinais
     * para evitar que se modifiquen os datos internos accidentalmente.</p>
     */    
    public Contacto[] getContactoNif(String nif){
        Contacto[] buffer=new Contacto[axenda.length];
        int pos=0;
        
        for(Contacto c: axenda) {
            if ((c != null) && (c.getPersoa().getNif().equals(nif))) {
                buffer[pos]=c;
                pos++;
            }
        }
        if (pos==0) return null;
        return Arrays.copyOfRange(buffer,0,pos);
    }
    
    /**
     * Busca contactos por número de teléfono.
     * 
     * @param phone O número de teléfono polo que se quere buscar
     * @return Un array con todos os contactos que teñen ese teléfono, ou null se non se atopa ningún
     * @since 1.0
     * 
     * <p><strong>Notas de deseño:</strong> Este método asume que o teléfono está ben formatado e validado.
     * Para aplicacións reais, considerar normalizar o teléfono (eliminar espazos, guións) antes da comparación.</p>
     */
    public Contacto[] getContactoPhone(String phone){
         Contacto[] buffer=new Contacto[axenda.length];
        int pos=0;
        
        for(Contacto c: axenda) {
            if ((c != null) && (c.getPhone().equals(phone))) {
                buffer[pos]=c;
                pos++;
            }
        }
        if (pos==0) return null;
        return Arrays.copyOfRange(buffer,0,pos);
    }
    
        
    /**
     * Busca contactos por enderezo de correo electrónico.
     * 
     * @param email O enderezo de correo electrónico polo que se quere buscar
     * @return Un array con todos os contactos que teñen ese email, ou null se non se atopa ningún
     * @since 1.0
     * 
     * <p><strong>Notas de internacionalización:</strong> As comparacións de email deberían ser case-insensitive
     * segundo os estándares RFC. Considerar usar <code>equalsIgnoreCase()</code> no canto de <code>equals()</code>.</p>
     */
    public Contacto[] getContactoEmail(String email){
        Contacto[] buffer=new Contacto[axenda.length];
        int pos=0;
        
        for(Contacto c: axenda) {
            if ((c != null) && (c.getEmail().equals(email))) {
                buffer[pos]=c;
                pos++;
            }
        }
        if (pos==0) return null;
        return Arrays.copyOfRange(buffer,0,pos);
    }
     
    /**
     * Busca contactos por palabra chave na descrición.
     * 
     * @param descripcion A palabra ou frase que se busca na descrición
     * @return Un array con todos os contactos que conteñen esa descrición, ou null se non se atopa ningún
     * @since 1.0
     * 
     */    
    public Contacto[] getContactoDescripcion(String descripcion){
     Contacto[] buffer=new Contacto[axenda.length];
        int pos=0;
        
        for(Contacto c: axenda) {
            if ((c != null) && (c.getDescripcion().toUpperCase().contains(descripcion.toUpperCase()))) {
                buffer[pos]=c;
                pos++;
            }
        }
        if (pos==0) return null;
        return Arrays.copyOfRange(buffer,0,pos);    
    }

    /**
     * Recupera un contacto polo seu número de posición.
     * 
     * @param numero O número de posición do contacto no array
     * @return O contacto na posición especificada, ou null se non hai contacto nesa posición
     * @since 1.0
     */    
    public Contacto getContactoNumero(int numero){
        if (numero>=axenda.length) return null; // Comprobamos que o número corresponde cunha entrada da axenda
        return axenda[numero];
    }
     
    /**
     * Elimina un contacto da axenda.
     * 
     * @param numero O número de posición do contacto que se quere eliminar
     * @return "Contacto Eliminado" se a eliminación foi exitosa, null se non existía contacto nesa posición
     * @since 1.0
     */
    public String eliminarContacto(int numero){
        if (numero>=axenda.length) return null; // Comprobamos que o número corresponde cunha entrada da axenda
        if (axenda[numero]!=null) {
            axenda[numero]=null;
            return "Contacto Eliminado";
        }
        return null;
    }

    /**
     * Obtén unha copia de todos os contactos existentes na axenda.
     * 
     * @return Un array con todos os contactos non nulos, ou null se non hai ningún contacto
     * @since 1.0
     * 
     * <p><strong>Encapsulamento:</strong> Debería devolver unha copia dos obxectos do array interno, non a referencia directa,
     * para prever modificacións accidentais dos datos internos desde fóra da clase.</p>
     */    
    public Contacto[] getAxenda() {
        Contacto[] buffer=new Contacto[axenda.length];
        int pos=0;
        
        for(Contacto c: axenda) {
            if (c != null) {
                buffer[pos]=c;
                pos++;
            }
        }
        if (pos==0) return null;
        return Arrays.copyOfRange(buffer,0,pos);    
    }
    
    /**
     * Elimina todos os contactos da axenda.
     * 
     * <p><strong>Efecto:</strong> Coloca null en todas as posicións do array, liberando todas as referencias
     * aos obxectos Contacto. Isto permite que o recolector de lixo elimine os obxectos da memoria.</p>
     * 
     * @since 1.0
     * 
     * <p><strong>Notas de rendemento:</strong> Este método ten complexidade O(n) onde n é o tamaño do array.
     * Para arrays moi grandes, considerar crear un novo array en lugar de recorrer o existente.</p>
     */    
    public void eliminarContactos() {
        for (int i = 0; i < axenda.length; i++) {
            axenda[i] = null;
        }
        // Sería máis eficiente facer:  axenda=new Contacto[axenda.length];
       
    }
}
