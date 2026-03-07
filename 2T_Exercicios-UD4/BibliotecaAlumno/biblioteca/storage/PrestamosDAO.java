/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.storage;

import biblioteca.model.Prestamo;

/**
 *
 * @author Usuario
 */
public class PrestamosDAO {
    
    //atributos

    private static final int MAX_PRESTAMOS=100;
    private final Prestamo[] prestamos;
    private int countPrestamos = 0;
    
    //constructor
    public PrestamosDAO(){
        prestamos=new Prestamo[MAX_PRESTAMOS];//creamos array con el máximo de prestamos.
    }
    
    //metodo listar préstamos
    public Prestamo[] list(){ 
        Prestamo[] listaPrestamo = new Prestamo[countPrestamos]; //creamos unha copia do array solo cos datos non nulls.
        for(int i=0;i<countPrestamos;i++){
            listaPrestamo[i] = prestamos[i]; 
        }
        return listaPrestamo;
    }
    
    //metodo añadir prestamo
    
    public void add(Prestamo p) throws AlmacenException{
        
        // comprobamos que no es nulo
        if(p == null){
            throw new AlmacenException("O préstamo é nulo");
        }
        // comprobamos que no está completo el registro
        if(countPrestamos >= MAX_PRESTAMOS){
            throw new AlmacenException("O rexistro de préstamos está cheo");
        }
        // comprobamos que no existe el préstamo
        for(int i=0;i<countPrestamos;i++){
            if(prestamos[i].getLibro().getISBN().equals(p.getLibro().getISBN()) && prestamos[i].getDataDevolucion()== null){
                throw new AlmacenException("Hay un préstamo activo deste libro.");
            }
        }
        prestamos[countPrestamos]=p;
        countPrestamos++;
    }
    
    //metodo buscar prestamo por ISBN
    public Prestamo getByISBN(String ISBN){
        for(int i=0;i<countPrestamos;i++){
            if(prestamos[i].getLibro().getISBN().equals(ISBN) && prestamos[i].getDataDevolucion() == null){
                return prestamos[i];
            }
        }return null;
    }
    //metodo buscar prestamo por Nome do libro
    public Prestamo getByTitulo(String titulo){
        for(int i=0;i<countPrestamos;i++){
            if(prestamos[i].getLibro().getTitulo().equals(titulo) && prestamos[i].getDataDevolucion() == null){
                return prestamos[i];
            }
        }return null;
    }
    
    //metodo devolver  libro
    public void devolverByISBN(String ISBN) throws AlmacenException{
        Prestamo libroPrestado = getByISBN(ISBN); //buscámolo co método de buscar anterior
        if(libroPrestado == null){
            throw new AlmacenException("O préstamo non existe");
        }
        libroPrestado.devolver();
    }
}
