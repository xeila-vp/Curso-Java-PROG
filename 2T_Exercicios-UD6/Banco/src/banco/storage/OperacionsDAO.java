package banco.storage;

import banco.model.Operacion;
import com.iesrodeira.datastores.IIndexStorage;
import com.iesrodeira.datastores.IndexStorage;

/**
 * DAO de operacións en memoria.
 * Indexado por un ID interno incremental.
 */
public class OperacionsDAO {

    private IIndexStorage<Integer, Operacion> storage;
    private int nextId;

    public OperacionsDAO() {
        storage = new IndexStorage<>();
        nextId = 1;
    }

    public void add(Operacion operacion) {
        if (operacion == null) {
            throw new IllegalArgumentException("Operación nula");
        }

        storage.add(nextId, operacion);
        nextId++;
    }

    public Operacion getById(int id) {
        return storage.get(id);
    }

    public Operacion[] list() {
        return storage.values();
    }

    public int size() {
        return storage.size();
    }
}
