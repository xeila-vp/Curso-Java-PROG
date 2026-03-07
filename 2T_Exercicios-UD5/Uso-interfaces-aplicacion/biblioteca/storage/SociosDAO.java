package biblioteca.storage;

import biblioteca.model.Socio;
import com.iesrodeira.collection.ArrayIList;
import com.iesrodeira.storage.IStorage;
import com.iesrodeira.storage.StorageList;
import java.util.List;

public class SociosDAO {
    private IStorage<Socio> socios;

    
    public SociosDAO() {
        List<Socio> backstore=new ArrayIList<>(s->new Socio(s));
        socios=new StorageList<>(backstore);
    }
    
    /**
    * Engade o socio ao almacén. Lanza unha DAOException si o socio xa existe.
    */
    public void engadir(Socio socio) throws DAOException {
       if (!socios.add(socio)) throw new DAOException("Erro engadindo. Probablemente o socio xa existe");
    }


    /**
    * Busca un socio por numero de socio
    */
    public Socio buscarPorNumero(int numero) {
        for(Socio s:socios) if (s.getNumeroSocio()==numero) return s;
        return null;
    }

    /**
    * Busca un Socio
    */
    public Socio buscarPorNumero(Socio socio) throws DAOException {
        return socios.retrieve(socio);
    }
}
