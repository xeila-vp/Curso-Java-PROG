/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package axenda.model.forms;

import axenda.model.Contacto;
import axenda.model.Persoa;
import com.iesrodeira.utils.CancelException;
import com.iesrodeira.utils.Input;

/**
 *
 * @author Xeila
 */
public class Forms {
    
    // Solicita os datos e retorna un obxecto Persoa. Pódese cancelar
    public static Persoa persoaForm()throws CancelException{
        System.out.println("CREAR PERSOA: ");
        
        //pedimos un string para nif, apelidos e nome
        String nif = Input.readText("NIF: ");
        String apelidos = Input.readText("Apelidos: ");
        String nome = Input.readText("Nome: ");
        
        //devolvemos os datos introducidos coas comprobacións
        return new Persoa(nif,apelidos,nome);
        
    } 
    // Visualiza os datos do contacto recibido como parámetro e pregunta si se desexan modificar. 
    public static Contacto contactoForm(Contacto c) throws CancelException{
        
        System.out.println("MODIFICAR CONTACTO: ");
        //visualizamos os datos do contacto
        System.out.println("Telefono: " + c.getTelefono());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Descripción: " + c.getDescricion());
        
        //preguntar si se quere modificar metodo option en input (title,valido)
        String opcion = Input.option("Desexas modificar este contacto? ", "S/N");
        if(opcion.equalsIgnoreCase("N")){
            return c; //si pulsan N, mantense o contacto c intacto
        }
        //si é S, lemos os novos datos
        String novoTelefono = Input.readPhone("Telefono novo: ", c.getTelefono());
        String novoEmail = Input.readMail("Telefono novo: ", c.getEmail());
        String novaDescricion = Input.readText("Telefono novo: ", c.getDescricion());
        
        //asignamos os novos datos ó contacto C
        c.setTelefono(novoTelefono);
        c.setEmail(novoEmail);
        c.setDescricion(novaDescricion);
        
        return c;
    }
    public static Contacto contactoForm(Persoa p) throws CancelException{
        System.out.println("FORMULARIO DE CONTACTO: ");
        
        //mostramos os datos da persoa a pedir os datos de contacto
        System.out.println("Persoa: " + p.getNif()+ " " + p.getNome() + " " + p.getApelidos());
        
        //pedimos os datos de contacto
        String telefono = Input.readPhone("Telefono: ");
        String email = Input.readMail("Email: ");
        String descricion = Input.readText("Descricion: ");
        
        //creamos novo contacto e devolvemos o contacto
        Contacto c = new Contacto(p, telefono, email, descricion);
        return c;
    }
    public static Contacto contactoForm() throws CancelException{
        System.out.println("FORMULARIO PARA CREAR CONTACTO");
        
        //nova persoa cos datos da persoa recollidos en persoaForm
        Persoa p = persoaForm();
        //Novo contacto cos datos de contacto da persoa p recollidos en contactoForm
        Contacto c = contactoForm(p);
        //Devolvemos o contacto
        return c;
    }
}
