package practica.clientelistArrays;

import banco.model.Cliente;

//- Para la práctica, si quiero usar la lista iterable la importaría y dejaría de usar la "no iterable".

import practica.clienteIterable.ListaConArraysIterable; 
import practica.clienteIterable.ListaIterable;

/**
 * DAO de clientes para práctica usando ListaConArrays.
 * Adaptado ao modelo Cliente do exercicio do banco.
 */
public class ClientesDAO {

    private ListaIterable<Cliente> clientes; //Al importar la lista iterable tengo que crear la lista Iterable y dejar de usar la otra
    
    //private ListaConArrays<Cliente> clientes; 

    /**
     * Crea o DAO cunha capacidade inicial.
     *
     * @param capacity capacidade inicial da lista
     */
    public ClientesDAO(int capacity) {
        clientes = new ListaConArraysIterable<>(capacity);
    }

    /**
     * Garda un novo cliente.
     *
     * @param cliente cliente a gardar
     * @throws IllegalArgumentException se é null ou xa existe
     */
    public void add(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente é null.");
        }

        if (getByDni(cliente.getDni()) != null) {
            throw new IllegalArgumentException("Este cliente xa existe.");
        }

        clientes.engadir(cliente);
    }

    /**
     * Busca un cliente polo seu DNI.
     *
     * @param dni DNI do cliente
     * @return cliente atopado ou null se non existe
     */
    public Cliente getByDni(String dni) {
        for (int i = 0; i < clientes.tamano(); i++) {
            Cliente cliente = clientes.obter(i);

            if (cliente.getDni().equalsIgnoreCase(dni)) {
                return cliente;
            }
        }

        return null;
    }

    /**
     * ListaIterable todos os clientes gardados.
     *
     * @return array cos clientes almacenados
     */
    public Cliente[] list() {
        Cliente[] listaClientes = new Cliente[clientes.tamano()];

        for (int i = 0; i < clientes.tamano(); i++) {
            listaClientes[i] = clientes.obter(i);
        }

        return listaClientes;
    }

    /**
     * Modifica os datos permitidos dun cliente existente.
     * No exercicio só ten sentido modificar mail, teléfono
     * e o límite de descuberto se o ten.
     *
     * @param dni DNI do cliente
     * @param novoMail novo mail
     * @param novoTelefono novo teléfono
     * @param novoLimite novo límite de descuberto
     * @throws IllegalArgumentException se o cliente non existe
     */
    public void modificarDatosCliente(String dni, String novoMail, String novoTelefono, double novoLimite) {
        Cliente cliente = getByDni(dni);

        if (cliente == null) {
            throw new IllegalArgumentException("O cliente non existe.");
        }

        cliente.setEmail(novoMail);
        cliente.setTelefono(novoTelefono);
        cliente.setLimiteDescuberto(novoLimite);
    }

    /**
     * Borra un cliente polo seu DNI.
     *
     * @param dni DNI do cliente a borrar
     * @throws IllegalArgumentException se o cliente non existe
     */
    public void remove(String dni) {
        for (int i = 0; i < clientes.tamano(); i++) {
            Cliente cliente = clientes.obter(i);

            if (cliente.getDni().equalsIgnoreCase(dni)) {
                clientes.eliminar(i);
                return;
            }
        }

        throw new IllegalArgumentException("O cliente non existe.");
    }

    /**
     * Devolve o número de clientes almacenados.
     *
     * @return tamaño do DAO
     */
    public int size() {
        return clientes.tamano();
    }

    /**
     * Indica se non hai clientes gardados.
     *
     * @return true se está baleiro
     */
    public boolean estaBaleiro() {
        return clientes.estaBaleira();
    }
}