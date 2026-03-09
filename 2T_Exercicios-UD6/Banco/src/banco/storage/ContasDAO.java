package banco.storage;

import banco.model.Conta;
import com.iesrodeira.datastores.IndexStorage;

/**
 * DAO de contas en memoria.
 * Indexado polo número de conta.
 */
public class ContasDAO {

    private IndexStorage<Integer, Conta> storage;

    public ContasDAO() {
        storage = new IndexStorage<>();
    }

    public void add(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta nula");
        }

        if (storage.get(conta.getNumConta()) != null) {
            throw new IllegalArgumentException("Xa existe unha conta con ese número");
        }

        storage.add(conta.getNumConta(), conta);
    }

    public Conta getByNumConta(int numConta) {
        return storage.get(numConta);
    }

    public void update(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta nula");
        }

        if (storage.get(conta.getNumConta()) == null) {
            throw new IllegalArgumentException("A conta non existe");
        }

        storage.set(conta.getNumConta(), conta);
    }

    public void remove(int numConta) {
        storage.remove(numConta);
    }

    public Conta[] list() {
        return storage.values();
    }

    public int size() {
        return storage.size();
    }
}