/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica.dinamicstorage;

import banco.model.Cliente;
import java.util.Arrays;

/**
 * DAO de clientes en memoria usando un array dinámico manual.
 * Versión de práctica para ver a evolución antes de usar IStorage ou IndexStorage.
 */

public class ClientesDAO {

    // Capacidade inicial do array
    private static final int CAPACIDADE_INICIAL = 10;

    // Array onde se gardan os clientes
    private Cliente[] clientes;

    // Número real de clientes gardados
    private int countClientes;

    /**
     * Crea o DAO cun array inicial baleiro.
     */
    public ClientesDAO() {
        this.clientes = new Cliente[CAPACIDADE_INICIAL];
        this.countClientes = 0;
    }

    /**
     * Garda un novo cliente.
     *
     * @param c cliente a gardar
     * @throws IllegalArgumentException se o cliente é null ou xa existe
     */
    public void add(Cliente c) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("O cliente é null.");
        }

        // Comproba se xa existe un cliente co mesmo DNI
        if (getByDni(c.getDni()) != null) {
            throw new IllegalArgumentException("Este cliente xa existe.");
        }

        // Se o array está cheo, amplíase
        if (countClientes == clientes.length) {
            clientes = Arrays.copyOf(clientes, clientes.length + CAPACIDADE_INICIAL);
        }

        clientes[countClientes] = c;
        countClientes++;
    }

    /**
     * Busca un cliente polo seu DNI.
     *
     * @param dni DNI do cliente
     * @return cliente atopado ou null se non existe
     */
    public Cliente getByDni(String dni) {
        for (int i = 0; i < countClientes; i++) {
            if (clientes[i].getDni().equalsIgnoreCase(dni)) {
                return clientes[i];
            }
        }
        return null;
    }

    /**
     * Devolve unha copia cos clientes realmente gardados.
     *
     * @return array de clientes
     */
    public Cliente[] list() {
        Cliente[] listaClientes = new Cliente[countClientes];

        for (int i = 0; i < countClientes; i++) {
            listaClientes[i] = clientes[i];
        }

        return listaClientes;
    }

    /**
     * Modifica os datos permitidos dun cliente existente.
     * Non crea un cliente novo, senón que actualiza o obxecto xa gardado.
     *
     * @param dni DNI do cliente a modificar
     * @param novoEmail novo correo
     * @param novoTelefono novo teléfono
     * @param novoLimite novo límite de descuberto
     * @throws IllegalArgumentException se o cliente non existe
     */
    public void modificarDatosCliente(String dni, String novoEmail, String novoTelefono, double novoLimite)
            throws IllegalArgumentException {

        Cliente cliente = getByDni(dni);

        if (cliente == null) {
            throw new IllegalArgumentException("O cliente non existe.");
        }
        // Actualiza só os datos que permite modificar o exercicio
        cliente.setEmail(novoEmail);
        cliente.setTelefono(novoTelefono);
        cliente.setLimiteDescuberto(novoLimite);
    }

    /**
     * Borra un cliente polo seu DNI.
     *
     * @param dni DNI do cliente a borrar
     * @throws IllegalArgumentException se o cliente non existe
     */
    public void remove(String dni) throws IllegalArgumentException {
        for (int i = 0; i < countClientes; i++) {
            if (clientes[i].getDni().equalsIgnoreCase(dni)) {

                // Despraza os elementos á esquerda para non deixar ocos
                for (int j = i; j < countClientes - 1; j++) {
                    clientes[j] = clientes[j + 1];
                }
                // Borra a última referencia e reduce o contador
                countClientes--;
                clientes[countClientes] = null;
                return;
            }
        }
        throw new IllegalArgumentException("O cliente non existe.");
    }
}