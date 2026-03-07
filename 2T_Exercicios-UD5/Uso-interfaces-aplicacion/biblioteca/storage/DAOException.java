package biblioteca.storage;

/**
* Notificacións de erros nos procedementos do storage
*/
public class DAOException extends Exception {

/**
* Construimos a excepción cunha mensaxe obrigatoria
*/
public DAOException(String msg) {
super(msg);
}
}
