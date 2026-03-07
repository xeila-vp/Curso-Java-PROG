/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.model;

import com.iesrodeira.utils.CancelException;
import com.iesrodeira.utils.Console;
import com.iesrodeira.utils.Validator;

/**
 *
 * @author xavi
 */
public class SocioForm {
    private Socio socio=null;
    
    public SocioForm() {
        this.socio=new Socio();
    }
        
    public SocioForm(Socio socio) {
        this.socio=socio;
    }
    
    public Socio form(Biblioteca biblioteca) throws CancelException {
        int numeroSocio=socio.getNumeroSocio();
        boolean ok=true;
        String nif="";
        String nome=socio.getNome();
        String direccion=socio.getDireccion();
        String email=socio.getEmail();
        
        if (numeroSocio == 0) { // So pedimos o nif do Socio si é un novo socio
            do {
                System.out.println(Console.banner("Alta de Socio"));
                nif=Console.readNif("Nif do Socio: ");
                Socio exists=biblioteca.buscaSocio(Validator.getNifNumber(nif));
                if (exists!=null) {
                    System.out.println("\tXa existe ese socio: "+exists);
                    ok=false;
                } else ok=true;
            } while(!ok);
            
        } else {
            System.out.println(Console.banner("Edición de Socio"));
            System.out.printf("Nif do Socio: %s\n",nif);
        }
        nome=Console.readTextNoEmpty("Nome do Socio: ", nome);
        direccion=Console.readText("Direccion do Socio: ",direccion);
        email=Console.readEmail("Email do Socio: ",email);

        if (numeroSocio == 0)
            socio.setNumeroSocio(Validator.getNifNumber(nif));
        socio.setNome(nome);
        socio.setEmail(email);
        socio.setDireccion(direccion);
        return socio;
    }
}
