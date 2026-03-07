package biblioteca;

import biblioteca.model.Biblioteca;
import biblioteca.model.Prestamo;
import biblioteca.model.Publicacion;
import biblioteca.model.PublicacionForm;
import biblioteca.model.Socio;
import biblioteca.model.SocioForm;
import biblioteca.storage.DAOException;
import com.iesrodeira.utils.CancelException;
import com.iesrodeira.utils.Console;

import java.util.Scanner;

/**
 * Clase principal da aplicación.
 * 
 * Contén o menú por consola e permite xestionar:
 *  - socios
 *  - publicacións
 *  - préstamos
 *  - devolucións
 *
 * Esta clase NON contén lóxica de negocio,
 * só interacción co usuario.
 */
public class XestorBiblioteca {
    /** Obxecto principal que xestiona a biblioteca */
    private static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        String[] menu={"Rexistrar Socio","Engadir Publicacion","Realizar Préstamo","Realizar Devolución","Listar Préstamos Activos"};
        int opcion=0;
        do {
            try {
                opcion = Console.menu("BIBLIOTECA",menu);
                System.out.println();
                switch (opcion) {
                    case 1: rexistrarSocio();     break;
                    case 2: engadirPublicacion(); break;
                    case 3: realizarPrestamo();   break;
                    case 4: realizarDevolucion(); break;
                    case 5: listarPrestamos();    break;
                }
            } catch(Exception e) {
                System.out.println("\n"+Console.banner(e.getMessage()));
            }
        } while (opcion != 6);
    }

    /**
    * Mostra o menú principal por pantalla e retorna a opción elixida
    */
    private static int menuPrincipal() {
        Scanner sc=new Scanner(System.in);
        try {
            System.out.println();
            System.out.println("===== BIBLIOTECA =====");
            System.out.println("1. Rexistrar socio");
            System.out.println("2. Engadir publicación");
            System.out.println("3. Realizar préstamo");
            System.out.println("4. Realizar devolución");
            System.out.println("5. Listar préstamos activos");
            System.out.println("6. Saír");
            System.out.print("Escolla unha opción: ");
            return Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {};    // Si non introducimos un número retornamos 0
        return 0;
    }

    /**
    * Rexistra un novo socio na biblioteca
    */
    private static void rexistrarSocio() throws DAOException, CancelException {
        SocioForm newsocio=new SocioForm();
        Socio socio = newsocio.form(biblioteca);
        biblioteca.rexistrarSocio(socio);
        Console.waitEnter(Console.banner("Socio rexistrado correctamente."));
    }

    /**
    * Engade unha nova publicación á biblioteca
    */
    private static void engadirPublicacion() throws DAOException, CancelException {
        PublicacionForm newpublicacion=new PublicacionForm();
        Publicacion p = newpublicacion.form(biblioteca);
        biblioteca.engadirPublicacion(p);
        Console.waitEnter(Console.banner("Publicación engadida correctamente."));
    }

    
    
    /**
    * Realiza un préstamo dun libro a un socio
    */
    private static void realizarPrestamo() throws Exception {
        int numero =Console.readInt("Número de Socio: ");
        Socio socio=biblioteca.buscaSocio(numero);
        if (socio==null) throw new Exception("Non existe o socio");
        System.out.println("\tSOCIO: "+socio);
        
        String isbn = Console.readISBN("ISBN da publicación: ");
        Publicacion publicacion=biblioteca.buscaPublicacion(isbn);
        if (publicacion==null) throw new Exception("Non existe a publicación");
        System.out.println("\tPUBLICACION: "+publicacion);
        
        if (Console.areYouSure()) {
            biblioteca.prestar(socio, publicacion);
            Console.waitEnter(Console.banner("Prestamo rexistrado correctamente."));
        }
    }

    /**
    * Realiza a devolución dun préstamo activo
    */
    private static void realizarDevolucion() throws Exception {
        int numero =Console.readInt("Número de Socio: ");
        Socio socio=biblioteca.buscaSocio(numero);
        if (socio==null) throw new Exception("Non existe o socio");
        System.out.println("\tSOCIO: "+socio);
        
        String isbn = Console.readISBN("ISBN da publicación: ");

        Prestamo prestamo=biblioteca.buscaPrestamo(socio,isbn);
        if (prestamo==null) throw new Exception("O socio "+socio+" non ten esa publicación en préstamo");
        System.out.println("\tPRESTADO O DIA: "+prestamo.getDataPrestamo());
        
        if (Console.areYouSure()) {
            biblioteca.devolver(prestamo);
            Console.waitEnter(Console.banner("Devolución rexistrada correctamente."));
        }
    }


    /**
    * Amosa a lista de todas as publicacións en préstamo
    */
    private static void listarPrestamos() {
        Prestamo[] lista=biblioteca.prestamosActivos();
        if (lista.length == 0) Console.waitEnter("Non existen préstamos pendentes");
        else {
            for(Prestamo p: lista) {
                System.out.println(p);
            }
            Console.waitEnter();
        }
    }
}
