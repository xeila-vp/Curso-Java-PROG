package biblioteca.model;

/**
* Representa unha publicación da biblioteca.
* Neste caso simplificamos a libros, pero podería ampliarse.
*/
public class Publicacion {
/** ISBN da publicación */
private String isbn="";
/** Título da publicación */
private String titulo="";
/** Autor da publicación */
private String autor="";

/**
 * Construtor por defecto. So se pode chamar dende o mesmo package
 */
Publicacion() {
}

public Publicacion(Publicacion p) {
    this.isbn=p.isbn;
    this.autor=p.autor;
    this.titulo=p.titulo;
}


/**
* Construtor completo
*/
public Publicacion(String isbn, String titulo, String autor) {
this.isbn = isbn;
this.titulo = titulo;
this.autor = autor;
}

    /**
     * So se pode cambiar o ISBN dende o mesmo package
     * @param isbn 
     */
    void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }



public String getIsbn() {
return isbn;
}

public String getTitulo() {
return titulo;
}

public String getAutor() {
return autor;
}

/**
* Unha Publicación se representa como autor: [isbn] titulo
*/
@Override
public String toString() {
return autor+": ["+isbn+"] "+titulo;
}

/**
* Duas Publicacion son iguais si teñen o mesmo ISBN
*/
@Override
public boolean equals(Object obj) {
if (obj==null) return false;
if (obj.getClass()!=getClass()) return false;
return ((Publicacion)obj).isbn.equals(this.isbn);
}
}
