package biblioteca.model;

import biblioteca.storage.DAOException;
import biblioteca.storage.PrestamosDAO;
import biblioteca.storage.PublicacionsDAO;
import biblioteca.storage.SociosDAO;

/**
* Xestiona as operacións sobre a biblioteca usando 3 almacens de datos para os Socios, Publicacions e Prestamos
*/
public class Biblioteca {

    /** Almacén de datos para os Socios */
    private SociosDAO sociosDAO = new SociosDAO();
    /** Almacén de datos para as Publicacions */
    private PublicacionsDAO publicacionsDAO = new PublicacionsDAO();
    /** Almacén de datos para os Préstamos */
    private PrestamosDAO prestamosDAO = new PrestamosDAO();

    /**
    * Rexistra un novo Socio notificando a aplicación calquera erro que se produza
    */
    public void rexistrarSocio(Socio socio) throws DAOException {
        sociosDAO.engadir(socio);
    }

    /**
    * Rexistra unha nova Publicacion notificando a aplicación calquera erro que se produza
    */
    public void engadirPublicacion(Publicacion p) throws DAOException {
        publicacionsDAO.engadir(p);
    }

    /**
    * Rexistra unha novo Prestamo a partir do Socio e a Publicacion notificando a aplicación calquera erro que se produza
    */
    public void prestar(Socio socio, Publicacion publicacion) throws Exception {
        if (socio != null && publicacion != null) {
            prestamosDAO.engadir(new Prestamo(socio,publicacion));
        } else throw new Exception("Necesitas un socio e unha publicación para facer un préstamo");
    }

    /**
    * Rexistra unha devolución dun préstamo notificando a aplicación calquera erro que se produza
    */
    public void devolver(Prestamo prestamo) throws Exception {
        if (prestamo != null) {
            prestamo.devolver();
        } else throw new Exception("Debes especificar un préstamo");
    }

    /**
    * Amosa a lista de todas as publicacións prestadas
    */
    public Prestamo[] prestamosActivos() {
        return prestamosDAO.listarPrestamosActivos();
    }

    public Publicacion buscaPublicacion(String isbn) {
        return publicacionsDAO.buscarPorISBN(isbn);
    }

    public Socio buscaSocio(int numero) {
        return sociosDAO.buscarPorNumero(numero);
    }

    public Prestamo buscaPrestamo(Socio socio, String isbn) {
        Prestamo prestamo=prestamosDAO.buscarPrestamoActivo(isbn);
        if (prestamo==null || !prestamo.getSocio().equals(socio)) return null;
        return prestamo;
    }
}