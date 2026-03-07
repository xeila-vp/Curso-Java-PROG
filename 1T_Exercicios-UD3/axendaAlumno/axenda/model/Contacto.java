/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package axenda.model;

import static com.iesrodeira.utils.Input.testEmail;
import static com.iesrodeira.utils.Input.testPhone;
import java.util.Objects;

/**
 *
 * @author Xeila
 */
public class Contacto {
    private int numero=-1;
    private String descricion;
    private String direccion;
    private String telefono;
    private String email;
    private Persoa persoa;
    
    //metodo con persoa, telefono, email e descricion
    public Contacto(Persoa persoa, String telefono, String email, String descricion){
        this.descricion=descricion;
        testPhone(telefono);
        this.telefono=telefono;
        testEmail(email);
        this.email=email;
        this.persoa=persoa;
        this.numero=-1;
    }
    //metodo con persoa, telefono, email
    public Contacto(Persoa persoa, String telefono, String email){
        this.descricion="";
        this.telefono=telefono;
        this.persoa=persoa;
        this.email=email;
    }
    //metodo con persoa, telefono
    public Contacto(Persoa persoa, String telefono){
        this.descricion="";
        this.persoa=persoa;
        this.email="";
    }
    //getters-setters(número, teléfono, email ou descrición )
        //validar mails e telefonos
    
    public int getNumero(){
        return numero;
    }
    public void setNumero(int numero){
        this.numero=numero;
    }
    public String getDescricion(){
        return descricion;
    }
    public void setDescricion(String descricion){
        this.descricion=descricion;
    }
    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono)throws IllegalArgumentException{
        testPhone(telefono);
        this.telefono=telefono;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email) throws IllegalArgumentException{
        testEmail(email);
        this.email=email;
    }
    public Persoa getPersoa(){
        return persoa;
    }

    //comparar descripcións

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contacto other = (Contacto) obj;
        if (!Objects.equals(this.descricion, other.descricion)) {
            return false;
        }
        return Objects.equals(this.persoa, other.persoa);
    }
   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.descricion);
        hash = 41 * hash + Objects.hashCode(this.persoa);
        return hash;
    }

    @Override
    public String toString(){
        return numero + ") " + persoa + ": " + telefono + " (" + email + ") " + " - " + descricion; 
    }
    //override toString
    
}


