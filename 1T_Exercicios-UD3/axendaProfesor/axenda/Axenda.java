package axenda;

import axenda.model.Contacto;
import axenda.model.dao.ContactosDAO;
import axenda.model.dao.DuplicateElement;
import axenda.model.dao.StorageFull;
import axenda.model.forms.Forms;
import com.iesrodeira.utils.CancelException;
import com.iesrodeira.utils.Input;


/**
 * Clase principal que coordina a aplicación de xestión de axenda de contactos.
 * 
 * <p><strong>Propósito:</strong> Proporciona a interface de usuario e o fluxo de control principal para unha aplicación
 * de xestión de contactos, integrando todas as capas do sistema (presentación, negocio e datos).</p>
 * 
 * <p><strong>Características principais:</strong></p>
 * <ul>
 *   <li><strong>Interface de usuario interactiva:</strong> Amosa menús e xestiona a interacción co usuario mediante a clase {@link Input}</li>
 *   <li><strong>Xestión completa de contactos:</strong> Permite listar, crear, buscar, modificar e eliminar contactos</li>
 *   <li><strong>Validación robusta:</strong> Utiliza o sistema de validación das clases DAO e de utilidades</li>
 *   <li><strong>Manexo de erros explícito:</strong> Xestiona excepcións de cancelación, almacenamento cheo e elementos duplicados</li>
 *   <li><strong>Arquitectura en capas:</strong> Separa claramente a presentación (Forms), negocio (Contacto, Persoa) e datos (ContactosDAO)</li>
 * </ul>
 * 
 * <p><strong>Patrón de deseño:</strong> Esta clase segue o patrón <em>Application Controller</em>, actuando como coordinador
 * central que delega tarefas específicas a outras clases especializadas. Tamén utiliza o patrón <em>Singleton</em> para
 * a instancia única da axenda de contactos.</p>
 * 
 * <p><strong>Arquitectura:</strong></p>
 * <ul>
 *   <li><strong>Capa de presentación:</strong> {@link Input}, {@link Forms} - xestión da interface de usuario</li>
 *   <li><strong>Capa de negocio:</strong> {@link axenda.model.Contacto}, {@link axenda.model.Persoa} - modelos de dominio</li>
 *   <li><strong>Capa de datos:</strong> {@link ContactosDAO} - persistencia e recuperación de datos</li>
 *   <li><strong>Capa de control:</strong> Esta clase - coordinación do fluxo da aplicación</li>
 * </ul>
 * 
 * <p><strong>Notas de rendemento:</strong> A implementación actual usa un array estático con capacidade fixa (25 contactos).
 * Para aplicacións con maiores volumes de datos, considerar unha implementación máis escalable como base de datos ou
 * estruturas de datos dinámicas.</p>
 * 
 * @author Desenvolvemento IES Rodeira
 * @version 1.2
 * @since 2023-10-15
 * @see ContactosDAO
 * @see Forms
 * @see Input
 */
public class Axenda {
    // Engadir este construtor para impedir a instanciación
    private Axenda() {
        // Evita que a clase poida ser instanciada
    }
    
    /**
     * Array de opcións para o menú principal da aplicación.
     * 
     * <p><strong>Deseño:</strong> Este array estático define as opcións dispoñibles no menú principal, facilitando
     * a modificación futura das opcións sen ter que cambiar o código de navegación do menú. A última opción ("Saír")
     * engádese automaticamente polo método {@link Input#menu(String, Object[])}.</p>
     */    
    private static String[] menu={"Listado de Contactos","Alta de Contactos","Busca de Contactos"};
    
    /**
     * Instancia única da axenda de contactos con capacidade para 25 contactos.
     * 
     * <p><strong>Patrón Singleton:</strong> Esta instancia estática é compartida por toda a aplicación, garantindo
     * que todos os contactos se manteñan nun único lugar. A capacidade fixa de 25 contactos é adecuada para uso
     * educativo ou pequenas aplicacións, pero podería ser configurábel ou dinámica en versións futuras.</p>
     * 
     * <p><strong>Escalabilidade:</strong> Para aplicacións máis grandes, considerar unha implementación que permita
     * cambiar o tamaño dinamicamente ou usar persistencia externa.</p>
     */
    private static ContactosDAO axenda=new ContactosDAO(25);
    
  /**
     * Punto de entrada principal da aplicación.
     * 
     * <p><strong>Fluxo de execución:</strong></p>
     * <ol>
     *   <li>Amosa o menú principal repetidamente ata que o usuario escolla saír</li>
     *   <li>Para cada opción do menú, chama ao método correspondente</li>
     *   <li>Xestiona as excepcións de cancelación do usuario</li>
     *   <li>O bucle termina cando o usuario escolla a opción 4 (Saír)</li>
     * </ol>
     * 
     * <p><strong>Manexo de erros:</strong> Captura {@link CancelException} para permitir que o usuario cancele
     * operacións en calquera momento premendo '*' e volva ao menú principal sen que a aplicación se peche.</p>
     * 
     * @param args Argumentos da liña de comandos (non se usan nesta implementación)
     * @since 1.0
     */
    public static void main(String[] args) {
        int op=0;
        do {
            try {
                op=Input.menu("Axenda de Contactos",menu);
                switch(op) {
                    case 1: listaContacto(); break;
                    case 2: altaContacto(); break;
                    case 3: buscaContacto(); break;
                }
            } catch (CancelException ex) {
                System.out.println(ex.getMessage());
            }
        } while(op!=4);
    }
    
    /**
     * Amosa o listado completo de contactos existentes na axenda.
     * 
     * <p><strong>Fluxo:</strong></p>
     * <ol>
     *   <li>Obtén todos os contactos da axenda mediante {@link ContactosDAO#getAxenda()}</li>
     *   <li>Se non hai contactos, amosa unha mensaxe informativa</li>
     *   <li>Se hai contactos, amosa un título e cada contacto nunha nova liña</li>
     *   <li>Agarda a que o usuario prema ENTER para continuar</li>
     * </ol>
     * 
     * <p><strong>Presentación:</strong> Utiliza {@link Input#printTitle(String)} para un formato visual consistente
     * e {@link Contacto#toString()} para unha representación legible de cada contacto.</p>
     * 
     * @since 1.0
     */
    public static void listaContacto() {
        Contacto[] lista=axenda.getAxenda();
        if (lista==null) System.out.println("Non existen contactos.");
        else {
            Input.printTitle("Listado de Contactos");
            for(Contacto c:lista) System.out.println(c);
        }
        Input.waitEnter();
    }

    /**
     * Xestiona o proceso de alta (creación) dun novo contacto.
     * 
     * <p><strong>Fluxo:</strong></p>
     * <ol>
     *   <li>Amosa o formulario de contacto mediante {@link Forms#contactoForm()}</li>
     *   <li>Pide confirmación ao usuario antes de gardar</li>
     *   <li>Tenta gardar o contacto na axenda</li>
     *   <li>Xestiona erros de almacenamento cheo ou elementos duplicados</li>
     *   <li>Agarda a que o usuario prema ENTER para continuar</li>
     * </ol>
     * 
     * <p><strong>Validacións:</strong> Delega todas as validacións (NIF, teléfono, email) á clase {@link Forms}
     * e á clase {@link ContactosDAO}, garantindo que só se gardan contactos válidos.</p>
     * 
     * <p><strong>Confirmación:</strong> Utiliza {@link Input#areYouSure()} para evitar insercións accidentais e
     * dar ao usuario a oportunidade de revisar os datos antes de gardalos.</p>
     * 
     * @throws CancelException Se o usuario cancela calquera paso do proceso premendo '*'
     * @since 1.0
     */    
    public static void altaContacto() throws CancelException{
        Contacto c=Forms.contactoForm();
        if (Input.areYouSure()) {
            try {
                axenda.guardarContacto(c);
            } catch (StorageFull | DuplicateElement ex) {
                System.out.println(ex.getMessage());
            }
        }
        Input.waitEnter("Contacto gardado. Para seguir pulsa Enter.");
    }
    
    /**
     * Xestiona o proceso de busca, visualización e modificación de contactos.
     * 
     * <p><strong>Fluxo:</strong></p>
     * <ol>
     *   <li>Pide ao usuario que escolla o criterio de busca (NIF, email, teléfono ou descrición)</li>
     *   <li>Pide o valor a buscar segundo o criterio escollido</li>
     *   <li>Realiza a busca na axenda</li>
     *   <li>Se non hai resultados, amosa unha mensaxe</li>
     *   <li>Se hai un único resultado, amósao directamente</li>
     *   <li>Se hai múltiples resultados, amosa un menú para que o usuario escolla un</li>
     *   <li>Para o contacto escollido, permite modificalo ou borralo</li>
     * </ol>
     * 
     * <p><strong>Flexibilidade:</strong> Este método integra múltiples funcionalidades nun só proceso, permitindo
     * ao usuario non só buscar contactos senón tamén modificalos ou eliminanos directamente dende os resultados.</p>
     * 
     * <strong>Experiencia de usuario:</strong> O proceso está deseñado para ser intuitivo:
     * <ul>
     *   <li>Uso de opcións en maiúsculas/minúsculas para facilitar a selección</li>
     *   <li>Confirmación antes de borrar contactos importantes</li>
     *   <li>Edición directa dos contactos desde os resultados da busca</li>
     * </ul>
     * 
     * 
     * @throws CancelException Se o usuario cancela calquera paso do proceso premendo '*'
     * @since 1.0
     */    
    public static void buscaContacto() throws CancelException {
        String dato;
        Contacto[] result=null;
        char op=Input.option("Por (N)if, (E)mail, (T)eléfono ou (D)escrición? ","NETD");
        
        Input.printTitle("Listado de Contactos");
        switch(op) {
            case 'N': 
                dato=Input.readNif("Nif:");
                result=axenda.getContactoNif(dato);
                break;
            case 'E':
                dato=Input.readEmail("Email:");
                result=axenda.getContactoEmail(dato);
                break;
            case 'T':
                dato=Input.readPhone("Teléfono:");
                result=axenda.getContactoPhone(dato);
                break;
            case 'D':
                dato=Input.readText("Descrición:");
                result=axenda.getContactoDescripcion(dato);
                break;    
        }
        if (result==null) System.out.println("Non se atoparon resultados");
        else {
            int elexido;
            if (result.length==1) elexido=0;
            else  {
                elexido=Input.menu("Resultados (Elixe un para modificar ou borrar)",result);
            }
            if (elexido!=result.length) {
                Contacto econtacto=result[elexido];
                Input.printTitle("Edición de Contacto");
                System.out.println(econtacto);
                op=Input.option("(M)odificar, (B)orrar","MB");
                if ((op=='B') && Input.areYouSure()) axenda.eliminarContacto(econtacto.getNumero());
                else {
                    Forms.contactoForm(econtacto);
                }
            }
        }
        Input.waitEnter();
     }
    
}
