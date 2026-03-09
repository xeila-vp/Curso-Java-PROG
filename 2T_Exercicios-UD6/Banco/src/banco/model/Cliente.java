/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.model;

/**
 *
 * @author Usuario
 * 
/**
 * Clase Cliente para a aplicación de xestión bancaria.
 * Garda os datos comúns e o tipo de cliente (ordinario ou empresa).
 */

public class Cliente {
    
    //declarar parámetros accesibles so dende a clase Cliente
    private String dni;
    private String nome;
    private String apelidos;
    private String email;
    private String telefono;
    private TipoCliente tipoCliente;
    private double limiteDescuberto=2000; 

/**
     * Crea un cliente indicando os parámetros mínimos para crear o obxeto.
     * Si es ordinario, el límite de descubierto queda a 0.
     * Si es empresa, se le asigna el límite por defecto de 2000.
     * @param dni
     * @param nome
     * @param apelidos
     * @param email
     * @param telefono
*/

//Ordinario    
public Cliente(String dni, String nome, String apelidos, String email, String telefono){
    this.dni=dni;
    this.nome=nome;
    this.apelidos=apelidos;
    this.email=email;
    this.telefono=telefono;
    this.tipoCliente=TipoCliente.ORDINARIO;
    this.limiteDescuberto=0.0;
}

//Empresa    
public Cliente(String dni, String nome, String apelidos, String email, String telefono, double limiteDescuberto){
    this.dni=dni;
    this.nome=nome;
    this.apelidos=apelidos;
    this.email=email;
    this.telefono=telefono;
    this.tipoCliente=TipoCliente.EMPRESA;
    this.limiteDescuberto=limiteDescuberto;
}

    public String getDni() {
        return dni;
    }

    public String getNome() {
        return nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public double getLimiteDescuberto() {
        return limiteDescuberto;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    // Pódense modificar mail 
    public void setEmail(String email) {
        this.email = email;
    }
    //Pódense modificar  teléfono
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    /**
            *  Se permite cambiar el límite que por defecto es 2000 solo si el cliente es de empresa.
            * @param limiteDescuberto
            */
    
    public void setLimiteDescuberto(double limiteDescuberto){
        if (this.tipoCliente == TipoCliente.EMPRESA){
            this.limiteDescuberto = limiteDescuberto;
        }
    }
}