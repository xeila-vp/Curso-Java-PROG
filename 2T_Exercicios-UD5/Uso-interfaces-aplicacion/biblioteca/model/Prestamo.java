package biblioteca.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
* Representa un préstamo dunha publicación a un socio.
*/
public class Prestamo {

/** Socio que realiza o préstamo */
private Socio socio;
/** Publicación prestada */
private Publicacion publicacion;
/** Data do préstamo */
private LocalDate dataPrestamo;
/** Data da devolución (null mentres non se devolva) */
private LocalDate dataDevolucion;

/*
* Construtor por defecto, so se pode usar dende o propio package
*/
Prestamo() {}

/**
 * Construtor de Copia
 * @param p 
 */
public Prestamo(Prestamo p) {
    this.socio=new Socio(p.socio);
    this.publicacion=new Publicacion(p.publicacion);
    this.dataPrestamo=p.dataPrestamo;
    this.dataDevolucion=p.dataDevolucion;
}


/**
* Construtor completo
*/
public Prestamo(Socio socio, Publicacion publicacion) {
this.socio = socio;
this.publicacion = publicacion;
this.dataPrestamo = LocalDate.now();
this.dataDevolucion = null;
}

/**
* Marca o préstamo como devolto
*/
public void devolver() {
this.dataDevolucion = LocalDate.now();
}

public boolean estaDevolto() {
return dataDevolucion != null;
}

public Socio getSocio() {
return socio;
}

public Publicacion getPublicacion() {
return publicacion;
}

public LocalDate getDataPrestamo() {
return dataPrestamo;
}

public LocalDate getDataDevolucion() {
return dataDevolucion;
}

/**
* Un Prestamo se representa como <data prestamo> <publicación> - socio
*/
@Override
public String toString() {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String data = dataPrestamo.format(formato);
    return data+" "+publicacion+" - "+socio;
    }
}
