package banco.storage;

import banco.model.Domiciliacion;
import com.iesrodeira.datastores.IIndexStorage;
import com.iesrodeira.datastores.IndexStorage;

/**
 * DAO de domiciliacións en memoria.
 * Indexado por código de domiciliación.
 */
public class DomiciliacionsDAO {

    private IIndexStorage<String, Domiciliacion> storage;

    public DomiciliacionsDAO() {
        storage = new IndexStorage<>();
    }

    public void add(Domiciliacion domiciliacion) {
        if (domiciliacion == null) {
            throw new IllegalArgumentException("Domiciliación nula");
        }

        if (storage.get(domiciliacion.getCodDomiciliacion()) != null) {
            throw new IllegalArgumentException("Xa existe unha domiciliación con ese código");
        }

        storage.add(domiciliacion.getCodDomiciliacion(), domiciliacion);
    }

    public Domiciliacion getByCodigo(String codigo) {
        return storage.get(codigo);
    }

    public void update(Domiciliacion domiciliacion) {
        if (domiciliacion == null) {
            throw new IllegalArgumentException("Domiciliación nula");
        }

        if (storage.get(domiciliacion.getCodDomiciliacion()) == null) {
            throw new IllegalArgumentException("A domiciliación non existe");
        }

        storage.set(domiciliacion.getCodDomiciliacion(), domiciliacion);
    }

    public void remove(String codigo) {
        storage.remove(codigo);
    }

    public Domiciliacion[] list() {
        return storage.values();
    }

    public int size() {
        return storage.size();
    }
}