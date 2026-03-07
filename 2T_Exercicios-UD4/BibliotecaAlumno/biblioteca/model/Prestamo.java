/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Xeila
 */
public class Prestamo {
    
    private Libro libro;
    private Socio socio;
    private LocalDate dataPrestamo;
    private LocalDate dataDevolucion;
    
    public Prestamo(Libro libro, Socio socio){
        this.libro=libro;
        this.socio=socio;
        this.dataPrestamo = LocalDate.now();
        this.dataDevolucion = null;
    }
    public Socio getSocio(){
        return socio;
    }
    public Libro getLibro(){
        return libro;
    }
    public LocalDate getDataPrestamo(){
        return dataPrestamo;
    }
    public LocalDate getDataDevolucion(){
        return dataDevolucion;
    }
    
    //metodo devolver
    public void devolver(){
        this.dataDevolucion = LocalDate.now(); // asignar data cando se devolva un libro
    }
    
    @Override
    public String toString(){
        return "Libro: (" + libro + "). Socio: " + socio + " - Data prestamo: " + dataPrestamo + " - Data devolución: " + dataDevolucion;
    }
    @Override 
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass())return false;
        Prestamo p = (Prestamo) obj;
        return Objects.equals(socio, p.socio) && Objects.equals(libro, p.libro);
    }
    @Override
    public int hashCode(){
        return Objects.hash(socio, libro);
    }
}
