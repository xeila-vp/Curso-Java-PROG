package biblioteca.storage;

import biblioteca.model.Prestamo;
import java.util.Arrays;

/**
* Almacén de préstamos.
*/
public class PrestamosDAO {
    // Número máximo de Préstamos que imos poder xestionar
    private static final int MAX_PRESTAMOS = 300;
    // Array para almacenar os Socios actuais -- É volátil, cando o programa remata se perden os datos --
    private Prestamo[] prestamos = new Prestamo[MAX_PRESTAMOS];

    /**
    * Engade o Préstamo ao almacén. Lanza unha DAOException si o Publicacion xa existe.
    */
    public void engadir(Prestamo prestamo) throws DAOException {
        // Buscamos o primeiro elemento que vale null e comprobamos que non exista un Prestamo activo da Publicación
        int pos=get_pos_and_check(prestamo);  
        if (pos < MAX_PRESTAMOS) {
            prestamos[pos] = prestamo;
        } else throw new DAOException("O almacen de prestamos está cheo");
    }

    /**
    * Retorna un préstamo sin retornar por ISBN
    */
    public Prestamo buscarPrestamoActivo(String isbn) {
        for (int i = 0; i < MAX_PRESTAMOS; i++) {
            // Si o préstamo non está devolto e a publicación ten o *isbn* axeitado...
            if (prestamos[i]!=null && !prestamos[i].estaDevolto() && prestamos[i].getPublicacion().getIsbn().equals(isbn)) 
                return prestamos[i];
        }
        return null;
    }

    /**
    * Lista de publicacións sen devolver
    */
    public Prestamo[] listarPrestamosActivos() {
        Prestamo[] lista=new Prestamo[MAX_PRESTAMOS];
        int count=0;
        // Almacenamos nun array todas as publicacións pendentes de devolución e retornamos os elementos atopados
        for (int i = 0; i < MAX_PRESTAMOS; i++) {
            if (prestamos[i]!=null && !prestamos[i].estaDevolto()) {
                lista[count]=prestamos[i];
                count++;
            }
        }
        Prestamo[] total=Arrays.copyOf(lista,count);
        return total;
    }

    /**
    * Busca un oco e lanza unha DAOException si a Publicación xa está prestada.
    *  Fago as dúas cousas por eficiencia. O mellor dende o punto de vista do deseño/claridade e separar responsabilidades:
    *  Un método que indique si existe e en qué posición
    *  Un método que nos retorne o primeiro oco libre.
    */
    private int get_pos_and_check(Prestamo prestamo) throws DAOException {
        int pos=MAX_PRESTAMOS;

        if (prestamo==null) throw new DAOException("Non se pode engadir null");
        for(int x=0;x<MAX_PRESTAMOS;x++) {
            if (prestamos[x]==null && pos==MAX_PRESTAMOS) pos=x;
            if (prestamos[x]!=null && !prestamos[x].estaDevolto()) {
                if (prestamos[x].getPublicacion().equals(prestamo.getPublicacion())) throw new DAOException("A publicación xa está en Préstamo");
            }
        }
        return pos;
    }
}
