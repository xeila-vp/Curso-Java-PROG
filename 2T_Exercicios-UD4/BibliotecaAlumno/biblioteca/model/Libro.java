/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.model;

/**
 *
 * @author Xeila
 * 
 * ISBN, Título, Autor
 * 
 */
public class Libro {
    //atributos
    
    private String ISBN;
    private String titulo;
    private String autor;
    
    //constructor
    
    public Libro(String ISBN, String titulo, String autor){
        this.ISBN=ISBN;
        this.titulo=titulo;
        this.autor=autor;
    }
    
    //getters-setters

    public String getISBN() {
        return ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    @Override
    public String toString() {
        return titulo + " (" + ISBN + ") " + ". " + autor;
    }
    
    @Override
    public boolean equals(Object obj) { // sobreescribe un método existente
        if(this == obj){return true;} // si son el mismo objeto = son iguales
        if(obj == null || getClass() != obj.getClass()){return false;} //si es null o de otra clase = no son iguales
        Libro outro = (Libro) obj; // conversion(cast). tratas el libro como un objeto.
        return ISBN.equals(outro.ISBN); // Son iguales si tienen el mismo id(isbn).
    }
    
    @Override
    public int hashCode() {
        return ISBN.hashCode();
    }

}
