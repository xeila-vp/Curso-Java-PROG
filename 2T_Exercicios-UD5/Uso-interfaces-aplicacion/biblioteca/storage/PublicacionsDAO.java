package biblioteca.storage;

import biblioteca.model.Publicacion;
import com.iesrodeira.collection.ListArray;
import com.iesrodeira.storage.IStorage;
import com.iesrodeira.storage.StorageList;
import java.util.List;

/**
* Almacén simple de publicacións.
*/
public class PublicacionsDAO {
    private IStorage<Publicacion> publicacions;
    
    public PublicacionsDAO() {
        List<Publicacion> backstore=new ListArray<>(p->new Publicacion(p));
        publicacions=new StorageList<>(backstore);
    }
    
    
    /**
    * Engade a Publicacion ao almacén. Lanza unha DAOException si o Publicacion xa existe.
    */
    public void engadir(Publicacion publicacion) throws DAOException {
        if (!publicacions.add(publicacion)) throw new DAOException("Erro engadindo: Probablemente a publicación xa existe");
    }

    /**
    * Busca unha Publicación por ISBN
    */
    public Publicacion buscarPorISBN(String isbn) {
        for(Publicacion pub:publicacions) if (pub.getIsbn().equals(isbn)) return pub;
        return null;
    }

}
