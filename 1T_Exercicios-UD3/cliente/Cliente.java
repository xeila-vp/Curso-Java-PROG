/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosclases;

/**
 *
 * @author Usuario
 */

public class Cliente {
    //declarar parámetros accesibles so dende a clase Cliente
    private String nome;
    private String apelido;
    private String nif;
    private String email;
    private String telefono;
    
    //Constructor que indica os parámetros mínimos para crear o obxeto
    public Cliente(String nif, String apelido, String nome){
        this.nif = nif; //asignamos o valor recibido ó atributo interno da clase
        if(!Valida.NifValido(nif)){
            throw new IllegalArgumentException("Nif inválido");
        }
        this.apelido = apelido;
        this.nome = nome;
    }
    
    // métodos Getter

    public String getNif() {
        return nif;
    }

    public String getApelido() {
        return apelido;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }
    
    // Setters email e teléfono para podelos cambiar
    public void setEmail(String email) {
        if(!Valida.emailValido(email)){
            throw new IllegalArgumentException("email inválido");
        }
        this.email = email;
    }

    public void setTelefono(String telefono) {
        if(!Valida.telefonoValido(telefono)){
            throw new IllegalArgumentException("teléfono inválido");
        }
        this.telefono = telefono;
    }
    
    @Override // Sobreescribimos o método equals da clase object
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Cliente))return false; // Comprobar si é un cliente.
        Cliente outro = (Cliente) obj; // Convertir (Cast) para poder acceder ós atributos.
        return this.nif.equals(outro.nif);
    }

    @Override
    public String toString(){ // definimos como ver o obxeto
        return "["+ nif +"] " + apelido + ", " + nome;
    }



public static void main(String[] args) {
    
    Cliente c01=new Cliente("35488478F", "Gómez", "Pedro"); //Instanciar a clase para crear un novo cliente
    c01.setEmail("ana.dk@correos.gob.es");
    c01.setTelefono("");
    
    Cliente c02=new Cliente("35488478F", "Pérez", "Juana"); 
    c02.setEmail("");
    c02.setTelefono("600005050");
    
    System.out.println("Cliente 01 Email: " + c01.getEmail());
    System.out.println("Cliente 02 Telefono: " + c02.getTelefono());
    System.out.println("Cliente 01: " + c01);
    
    if (c01.equals(c02)) {
        System.out.println("Cliente 02: O DNI " + c01.getNif() + " pertence a outro cliente.");
    } else {
        System.out.println("Cliente 02: " + c02);
    }
    }

}
