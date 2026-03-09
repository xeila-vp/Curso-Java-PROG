package banco.storage;

import banco.model.Cliente;
import com.iesrodeira.datastores.IIndexStorage;
import com.iesrodeira.datastores.IndexStorage;

public class ClientesDAO {

    private IIndexStorage<String, Cliente> storage;

    public ClientesDAO() {
        storage = new IndexStorage<>();
    }

    public void add(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente nulo");
        }
        if (storage.get(cliente.getDni()) != null) {
            throw new IllegalArgumentException("Xa existe un cliente con ese DNI");
        }
        storage.add(cliente.getDni(), cliente);
    }

    public Cliente getByDni(String dni) {
        return storage.get(dni);
    }

    public void update(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente nulo");
        }
        if (storage.get(cliente.getDni()) == null) {
            throw new IllegalArgumentException("O cliente non existe");
        }
        storage.set(cliente.getDni(), cliente);
    }

    public void remove(String dni) {
        storage.remove(dni);
    }

    public Cliente[] list() {
        return storage.values();
    }

    public int size() {
        return storage.size();
    }
}