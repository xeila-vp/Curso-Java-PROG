/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.storage;

import biblioteca.model.Libro;
import biblioteca.storage.AlmacenException;

/**
 *
 * @author Usuario
 */
public class LibrosDAO {
    
    //atributos
    public static final int MAX_LIBROS = 100; //constantes para indicar o número máximo de elementos
    private final Libro[] libros; //declarar array de objetos libro
    private int countLibros=0;

    //constructor
    public LibrosDAO(){
        libros = new Libro[MAX_LIBROS];
    }
    
    //metodo engadir libro
    public void add(Libro l) throws AlmacenException{
        
        //evita gardar libros nulos
        if(l == null){
            throw new AlmacenException("Libro nulo");
        }
        //comproba si hay espacio no array.
        if(countLibros >= MAX_LIBROS){
            throw new AlmacenException("Almacén de libros completo");
        }
        //comproba que os id non estén xa rexistrados.
        for(int i=0;i<countLibros;i++){
            if(libros[i].equals(l)){
            throw new AlmacenException("O libro xa está rexistrado.");
            }
        }
        libros[countLibros] = l;
        countLibros++;
    }
    //metodo buscar libro
    
    public Libro getById(String ISBN){
        for(int i=0;i<countLibros;i++){
            if(libros[i].getISBN().equals(ISBN)){
                return libros[i];
            }
        }return null;
    }
    
    //metodo buscar por titulo
    public Libro getByTitulo(String titulo){
        for(int i=0;i<countLibros;i++){
            if(libros[i].getTitulo().equalsIgnoreCase(titulo)){
                return libros[i];
            }
        }return null;
    }
    
    //metodo listar libros
    
    public Libro[] list(){
        Libro[] listaLibro = new Libro[countLibros];
        for(int i=0;i<countLibros;i++){
            listaLibro[i] = libros[i];
        }
        return listaLibro;
    }
    
    //metodo borrar  libro
    public void remove(String ISBN)throws AlmacenException {
        
        //busca no array e cando atope o libro borrao.
        for(int i=0;i<countLibros;i++){
            if(libros[i].getISBN().equals(ISBN)){
                for(int atopado = i; atopado < countLibros -1; atopado++){
                    libros[atopado]=libros[atopado + 1]; //copiar o da dereita no atopado
                }
                countLibros--;
                libros[countLibros] = null; //eliminamos a última posición que está duplicado;
                return;
            }
        }
        throw new AlmacenException("O libro non está rexistrado.");
    }
}
