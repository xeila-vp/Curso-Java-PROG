/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app.biblioteca;

import java.util.InputMismatchException;
import java.util.Scanner;
import biblioteca.storage.PrestamosDAO;
import biblioteca.storage.LibrosDAO;
import biblioteca.storage.SociosDAO;
import biblioteca.storage.AlmacenException;
import biblioteca.model.Libro;
import biblioteca.model.Prestamo;
import biblioteca.model.Socio;
import utils.DNIValidator;
import utils.EmailValidator;

/**
 *
 * @author Xeila
 */
public class AppBiblioteca {

    /**
     * @param args the command line arguments
     * Engadir libros
     * Rexistrar novos socios
     * Rexistrar préstamos dos socios
     * Rexistrar devolucións dos socios
     * 
     */
    //crear os obxetos DAO - private + Tipo(obxeto) + nome da variable  = novo obxeto
    
    private LibrosDAO xestionLibros = new LibrosDAO();
    private SociosDAO xestionSocios = new SociosDAO();
    private PrestamosDAO xestionPrestamos = new PrestamosDAO();
    
    
    public static void main(String[] args){
        
        AppBiblioteca biblioteca = new AppBiblioteca(); //creamos o obxeto biblioteca
        
        Scanner scn=new Scanner(System.in);
        int opcion = 0;
        
        do {
            
        System.out.println("----MENÚ BIBLIOTECA----");
        
        System.out.println("1.- Rexistrar libro: ");  
        System.out.println("2.- Borrar libro: "); 
        System.out.println("3.- Buscar libro: "); 
        System.out.println("4.- Rexistrar socio: ");
        System.out.println("5.- Borrar socio: "); 
        System.out.println("6.- Buscar socio: "); 
        System.out.println("7.- Rexistrar préstamo: ");
        System.out.println("8.- Rexistrar devolución: "); 
        System.out.println("9.- Ver rexistro libros: ");
        System.out.println("10.- Ver rexistro socios: ");
        System.out.println("11.- Ver rexistro préstamos: "); 
        
        System.out.println("12.- Sair: ");        
        
        try{
            
        opcion = scn.nextInt();
            
                while(opcion>12 || opcion<1){
                    System.out.println("Non atopado. Introduce un número do menú.");
                    opcion = scn.nextInt();
                }
                switch(opcion){
                    case 1:
                        scn.nextLine();
                        System.out.println("---Engadir Libro---\n");
                        
                        System.out.println("ISBN: ");
                        String ISBN = scn.nextLine();
                        
                        System.out.println("Título");
                        String titulo = scn.nextLine();
                        
                        System.out.println("Autor");
                        String autor = scn.nextLine();
                        
                        Libro novoLibro = new Libro(ISBN, titulo, autor); //crear o novo obxecto
                        
                        try{                        
                            biblioteca.xestionLibros.add(novoLibro); // metelo en DAO
                            System.out.println("Libro rexistrado: " + novoLibro);
                        }catch (AlmacenException e){
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                        
                    case 2:
                        scn.nextLine();
                        System.out.println("---Borrar Libro---\n");
                        
                        System.out.println("ISBN: ");
                        ISBN = scn.nextLine();
                        
                        try{
                            biblioteca.xestionLibros.remove(ISBN);
                            System.out.println("Libro borrado.");
                        }catch (AlmacenException e){
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                        
                    case 3:
                        scn.nextLine();
                        System.out.println("---Buscar Libro---\n");
                        
                        System.out.println("ISBN: ");
                        ISBN = scn.nextLine();
                        
                        Libro l = biblioteca.xestionLibros.getById(ISBN);
                        
                        if(l == null){
                            System.out.println("O libro non existe");
                        }else{
                            System.out.println("Datos do libro " + l);
                        }
                        break;
                        
                    case 4:
                        scn.nextLine();
                        System.out.println("---Engadir Socio---\n");
                        
                        System.out.println("Nome completo: ");
                        String nome = scn.nextLine();
                        
                        System.out.println("Dirección: ");
                        String direccion = scn.nextLine();
                        
                        System.out.println("Email: ");
                        String email = scn.nextLine();
                        
                        System.out.println("DNI: ");
                        String DNI = scn.nextLine();
                        
                        try{
                            //validacións
                            EmailValidator.TestEmail(email);
                            DNIValidator.TestDNI(DNI);
                            
                            //convertimos dni a idSocio como int e sin letra
                            int idSocio = Integer.parseInt(DNI.substring(0,8));
                            
                            Socio novoSocio= new Socio(nome, direccion, email, idSocio); //creamos o novo socio
                                
                            try{
                                biblioteca.xestionSocios.add(novoSocio); // pasámolo  a DAO
                                System.out.println("Socio rexistrado: " + novoSocio);
                            }catch (AlmacenException e){
                                System.out.println("Error: " + e.getMessage());
                            }
                        }catch (IllegalArgumentException e){
                            System.out.println("Error: " + e.getMessage());
                        }

                        break;
                    case 5:
                        scn.nextLine();
                        System.out.println("---Borrar Socio---\n");
                        
                        System.out.println("DNI: ");
                        DNI = scn.nextLine();
                        
                        try{
                            //validación dni
                            DNIValidator.TestDNI(DNI);
                            
                            //convertir a idSocio
                            int idSocio = Integer.parseInt(DNI.substring(0,8));
                                try{
                                    biblioteca.xestionSocios.remove(idSocio);
                                    System.out.println("Socio borrado.");
                                }catch (AlmacenException e){
                                    System.out.println("Error: " + e.getMessage());
                            }
                        }catch (IllegalArgumentException e){
                            System.out.println("Error: " + e.getMessage());
                        }
                        
                        break;
                    case 6:
                        scn.nextLine();
                        System.out.println("---Buscar Socio---\n");
                        
                        System.out.println("DNI: ");
                        DNI = scn.nextLine();
                        
                        try{
                            DNIValidator.TestDNI(DNI);
                            int idSocio = Integer.parseInt(DNI.substring(0,8));
                            Socio buscado = biblioteca.xestionSocios.getById(idSocio);
                                if(buscado == null){
                                System.out.println("Este socio non existe.");
                            }else{
                                System.out.println("Datos do socio: " + buscado);
                            }
                        }catch (IllegalArgumentException e){
                            System.out.println("Error: " + e.getMessage());
                        }
                        
                        break;
                    case 7:
                        scn.nextLine();
                        System.out.println("---Rexistrar Préstamo---\n");
                        
                        System.out.println("ISBN Libro: ");
                        ISBN = scn.nextLine();
                        
                        System.out.println("DNI(sin letra) Socio: ");
                        int idSocio = scn.nextInt();
                        scn.nextLine();
                        
                        Libro libroPrestamo = biblioteca.xestionLibros.getById(ISBN);
                        Socio socioPrestamo = biblioteca.xestionSocios.getById(idSocio);
                        
                        if(libroPrestamo == null){
                            System.out.println("O libro non existe.");
                        }else if(socioPrestamo == null){
                            System.out.println("O socio non existe.");
                        }else{
                            Prestamo novoPrestamo = new Prestamo(libroPrestamo, socioPrestamo);
                            try{
                                biblioteca.xestionPrestamos.add(novoPrestamo);
                                System.out.println("Novo préstamo rexistrado. \n" + novoPrestamo);
                            }catch (AlmacenException e){
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;
                        
                    case 8:
                        scn.nextLine();
                        System.out.println("---Rexistrar Devolución---\n");
                        
                        System.out.println("ISBN Libro: ");
                        ISBN = scn.nextLine();
                        
                        libroPrestamo = biblioteca.xestionLibros.getById(ISBN);
                        
                        if(libroPrestamo == null){
                            System.out.println("O libro non está rexistrado.");
                        }else{
                            try{
                                Prestamo devolucion = biblioteca.xestionPrestamos.getByISBN(ISBN);
                                biblioteca.xestionPrestamos.devolverByISBN(ISBN);
                                System.out.println("Devolución rexistrada. " + devolucion);
                            }catch (AlmacenException e){
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;
                        
                    case 9:
                        scn.nextLine();
                        System.out.println("---Rexistro de Libros---\n");
                        
                        Libro[] listaLibros = biblioteca.xestionLibros.list();
                        
                        if(listaLibros.length == 0){
                            System.out.println("Non hay libros rexistrados.");
                        }else{
                            for(Libro libro : listaLibros){
                                System.out.println(libro);
                            }
                        }
                        break;
                        
                    case 10:
                        scn.nextLine();
                        System.out.println("---Rexistro de Socios---\n");
                        
                        Socio[] listaSocios = biblioteca.xestionSocios.list();
                        
                        if(listaSocios.length == 0){
                            System.out.println("Non hay socios rexistrados.");
                        }else{
                            for(Socio socio : listaSocios){
                                System.out.println(socio);
                            }
                        }
                        break;
                    case 11:
                        scn.nextLine();
                        System.out.println("---Rexistro de Prestamos---\n");
                        
                        Prestamo[] listaPrestamos = biblioteca.xestionPrestamos.list();
                        
                        if(listaPrestamos.length == 0){
                            System.out.println("Non hay préstamos rexistrados.");
                        }else{
                            for(Prestamo prestamo : listaPrestamos){
                                System.out.println(prestamo);
                            }
                        }
                        break;
                    case 12:
                        System.out.println("Saindo...");
                        break;
                }
                
        }catch (InputMismatchException e) {
            System.out.println("Error. Formato no válido.");}
        
        } while (opcion != 12);
        
    }    
}
