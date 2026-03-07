package biblioteca.storage;

import biblioteca.model.Publicacion;

/**
* Almacén simple de publicacións.
*/
public class PublicacionsDAO {
    // Numero máximo de Publicacións que se poden xestionar
    private static final int MAX_PUBLICACIONS = 200;
    // Array para almacenar as Publicacións actuais -- É volátil, cando o programa remata se perden os datos --
    private Publicacion[] publicacions = new Publicacion[MAX_PUBLICACIONS];

    /**
    * Engade a Publicacion ao almacén. Lanza unha DAOException si o Publicacion xa existe.
    */
    public void engadir(Publicacion publicacion) throws DAOException {
        // Buscamos o primeiro elemento que vale null e comprobamos que non exista a Publicacion
        int pos=get_pos_and_check(publicacion);  
        if (pos < MAX_PUBLICACIONS) {
            publicacions[pos] = publicacion;
        } else throw new DAOException("O almacen de publicacions está cheo");
    }

    /**
    * Busca unha Publicación por ISBN
    */
    public Publicacion buscarPorISBN(String isbn) {
        for (int i = 0; i < MAX_PUBLICACIONS; i++) {
            if (publicacions[i]!=null && publicacions[i].getIsbn().equals(isbn)) return publicacions[i];
        }
        return null;
    }

    /**
    * Busca un oco e lanza unha DAOException si a Publicación xa existe.
    *  Fago as dúas cousas por eficiencia. O mellor dende o punto de vista do deseño/claridade e separar responsabilidades:
    *  Un método que indique si existe e en qué posición
    *  Un método que nos retorne o primeiro oco libre.
    */
    private int get_pos_and_check(Publicacion publicacion) throws DAOException {
        int pos=MAX_PUBLICACIONS;

        if (publicacion==null) throw new DAOException("Non se pode engadir null");
        for(int x=0;x<MAX_PUBLICACIONS;x++) {
            if (publicacions[x]==null && pos==MAX_PUBLICACIONS) pos=x;
            if (publicacions.equals(publicacions[x])) throw new DAOException("A publicación xa existe");
        }
        return pos;
    }
}
