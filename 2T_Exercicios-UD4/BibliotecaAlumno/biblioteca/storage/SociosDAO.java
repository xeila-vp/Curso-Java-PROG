/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.storage;
import biblioteca.model.Socio;

/**
 *
 * @author Usuario
 */
public class SociosDAO {
    
    // atributos
    private static final int MAX_SOCIOS=100;
    private Socio[] socios;
    private int countSocios = 0;
    
    // constructor
    public SociosDAO(){
        socios = new Socio[MAX_SOCIOS]; 
    }
    
    // metodo para engadir socio
    public void add(Socio s) throws AlmacenException{
        
        //comprobar null antes que iguais porque si é nul xa rompe
        if(s == null){
            throw new AlmacenException("O socio é null.");
        }
        //comprobar iguais
        for(int i=0;i<countSocios;i++){
            if(socios[i].getIdSocio() == s.getIdSocio()){ 
                throw new AlmacenException("Este socio xa existe.");
            }
        }
        //comprobar completo
        if(countSocios >= MAX_SOCIOS){
            throw new AlmacenException("O rexistro de socios está cheo.");
        }
        socios[countSocios] = s;
        countSocios++;
    }
    
    // metodo para buscar por id
    public Socio getById(int idSocio){
        for(int i=0; i<countSocios;i++){
            if(socios[i].getIdSocio() == idSocio){
                return socios[i];
            }
        }return null;
    }
    // metodo para buscar nome
    public Socio getByName(String nome){
        for(int i=0;i<countSocios;i++){
            if(socios[i].getNome().equalsIgnoreCase(nome)){
                return socios[i];
            }
        }return null;
    }
    
    //metodo listar Socio
    public Socio[] list(){
        Socio[] litaSocios = new Socio[countSocios];
        for(int i=0;i<countSocios;i++){
            litaSocios[i] = socios[i];
        }
        return litaSocios;
    }
    //metodo borrar libro
    public void remove(int idSocio) throws AlmacenException{
        for (int i = 0; i<countSocios; i++){
            if(socios[i].getIdSocio() == idSocio){
                for(int atopado=i;atopado<countSocios-1;atopado++){
                    socios[atopado]=socios[atopado+1];
                }
                countSocios--;
                socios[countSocios]=null;
                return;
            }
        }throw new AlmacenException("O socio non existe");
    }
}
