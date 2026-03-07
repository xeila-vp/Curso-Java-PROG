/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.model;

/**
 *
 * @author Xeila
 * 
 * Nº Usuario, Nome, Dirección, Email
 * 
 */
public class Socio {
    private int idSocio;
    private String nome;
    private String direccion;
    private String email;
    
    public Socio(String nome, String direccion, String email, int idSocio){
        this.idSocio=idSocio;
        this.nome=nome;
        this.direccion=direccion;
        this.email=email;
    }
    
    public int getIdSocio(){return idSocio;}
    public String getNome(){return nome;}
    public String getDireccion(){return direccion;}
    public String getEmail(){return email;}
    
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setDireccion(String direccion){
        this.direccion=direccion;
    }
    public void setEmail(String email){
        this.email=email;
    }
    
    @Override
    public String toString(){
        return nome + " (" + idSocio + ") " + ": " + email + ", " + direccion;
    }
    @Override
    public boolean equals (Object obj){
        if(this == obj){return true;}
        if(obj == null || getClass() != obj.getClass()){return false;}
        Socio s = (Socio) obj;
        return this.idSocio == (s.idSocio);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idSocio;
        return hash;
    }
    
}
