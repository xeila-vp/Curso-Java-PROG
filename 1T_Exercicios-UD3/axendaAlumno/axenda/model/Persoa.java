/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package axenda.model;

import static com.iesrodeira.utils.Input.testNif;
import java.util.Objects;

/**
 *
 * @author Xeila
 */
public class Persoa {
    private String nif;
    private String nome;
    private String apelidos;
    
    public Persoa(String nif, String nome, String apelidos)throws IllegalArgumentException{
        testNif(nif);
        this.nif=nif;
        this.nome=nome;
        this.apelidos=apelidos;
    }
    public String getNif(){
        return nif;
    }
    public String getNome(){
        return nome;
    }
    public String getApelidos(){
        return apelidos;
    }
    
    //comprobar nif iguais boolean equals
    @Override
    public boolean equals(Object obj){
        if(this == obj)return true; //si es un objeto
        if(!(obj instanceof Persoa))return false; //si no es un cliente
        Persoa outro = (Persoa) obj;
        return this.nif.equals(outro.nif);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.nif);
        return hash;
    }
    
    @Override
    public String toString(){
        return "[" + nif + "] " + apelidos + ", " + nome;
    }
}
