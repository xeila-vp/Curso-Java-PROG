package biblioteca.model;

import com.iesrodeira.utils.CancelException;
import com.iesrodeira.utils.Console;

/**
 *
 * @author xavi
 */
public class PublicacionForm {
    private Publicacion publicacion=null;
    
    public PublicacionForm() {
        this.publicacion=new Publicacion();
    }
        
    public PublicacionForm(Publicacion publicacion) {
        this.publicacion=publicacion;
    }
    
    public Publicacion form(Biblioteca biblioteca) throws CancelException {
        boolean ok=true;
        String isbn = publicacion.getIsbn();
        String titulo = publicacion.getTitulo();
        String autor= publicacion.getAutor();
        
        if (isbn.isBlank()) {
            do {
                System.out.println(Console.banner("Alta de Publicación"));
                isbn=Console.readISBN("ISBN da Publicacion: ");
                Publicacion exists=biblioteca.buscaPublicacion(isbn);
                if (exists!=null) {
                    System.out.println("\tXa existe esa publicación: "+exists);
                    ok=false;
                } else ok=true;
            } while(!ok);
        } else {
            System.out.println(Console.banner("Edición de Publicación"));
            System.out.printf("ISBN da Publicacion: %s\n",isbn);
        }
        titulo=Console.readTextNoEmpty("Titulo da Publicación: ", titulo);
        autor=Console.readTextNoEmpty("Autor da Publicación: ",autor);
        
        if (publicacion.getIsbn().isBlank())
            publicacion.setIsbn(isbn);
        publicacion.setAutor(autor);
        publicacion.setTitulo(titulo);
        return publicacion;
    }
}
