package biblioteca.storage;

import biblioteca.model.Prestamo;
import com.iesrodeira.collection.ListArray;
import com.iesrodeira.storage.IStorage;
import com.iesrodeira.storage.StorageList;
import java.util.List;

/**
* Almacén de préstamos.
*/
public class PrestamosDAO {
    private IStorage<Prestamo> prestamos;

    public PrestamosDAO() {
        List<Prestamo> backstore=new ListArray<>(p->new Prestamo(p));
        prestamos=new StorageList<>(backstore);
    }
    
    
    /**
    * Engade o Préstamo ao almacén. Lanza unha DAOException si o Publicacion xa existe.
    */
    public void engadir(Prestamo prestamo) throws DAOException {
        if (!prestamos.add(prestamo)) throw new DAOException("Erro engadindo. Probablemente o prestamo xa existe");
    }

    /**
    * Retorna un préstamo sin retornar por ISBN
    */
    public Prestamo buscarPrestamoActivo(String isbn) {
        for(Prestamo p:prestamos) if (!p.estaDevolto() && p.getPublicacion().getIsbn().equals(isbn)) return p;
        return null;
    }

    /**
    * Lista de publicacións sen devolver
     * @return 
    */
    public Prestamo[] listarPrestamosActivos() {
        return prestamos.filter(p-> !p.estaDevolto(),Prestamo.class);
    }
}
