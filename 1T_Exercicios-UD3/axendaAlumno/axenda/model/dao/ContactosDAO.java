/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package axenda.model.dao;

import axenda.model.Contacto;

/**
 *
 * @author Xeila
 */
public class ContactosDAO {
    
    // Atributo array privado que almacena os contactos
    private Contacto[] axenda;
    
    // Constructor: capacidade máxima
    public ContactosDAO(int capacidade){
        axenda = new Contacto[capacidade];
    }
    
    //MÉTODO engadir contacto
    public void add(Contacto novo) throws DuplicateElement, StorageFull{
        //1. Comprobar duplicado
        for (Contacto c:axenda){
            if(c != null && c.equals(novo)){
                throw new DuplicateElement("Xa existe o contacto: " + c);
            }
        }
        //1. Buscar sitio libre
        for (int i=0; i<axenda.length;i++){
            if(axenda[i] == null){
                axenda[i] = novo;
                // O número do contacto é a posició onde se garda
                novo.setNumero(i);
                return;
            }
        }
        // 3. Se non hai espazo dispoñible
        throw new StorageFull();
    }

    // CLASE con excepción StorageFull
    private static class StorageFull extends Exception {
        public StorageFull() {
            super();
        }
    }
    
    // Clase con excepción DuplicateElement
    private static class DuplicateElement extends Exception {
        public DuplicateElement(String message) {
            super(message);
        }
    }
    //BUSCAR CONTACTOS POR NUMERO
    public Contacto findByNumero(int numero){
        if (numero<0 || numero >= axenda.length){
            return null;
        }
        return axenda[numero];
    }
    //ELIMINAR CONTACTO POR NUMERO
    public Contacto deleteByNumero(int numero){
        //comprobar que esté no rango do array
        if (numero < 0 || numero>=axenda.length){
            return null;
        }
        //Si está dentro, collemos o contacto desa posición
        Contacto eliminado = axenda[numero];
        
        //asignamos null para eliminar
        axenda[numero]=null;
        
        return eliminado;
    }
    //BUSCAR CONTACTOS POR NIF
    public Contacto[] findByNif(String nif){
        
        Contacto[] temp = new Contacto[axenda.length];
        int count=0;
        
        for (int i = 0; i < axenda.length; i++) {
            Contacto c = axenda[i];
            
            if (c !=null && c.getPersoa().getNif().equals(nif)){
            temp[count] = c;
            count++;
        }
        }if (count == 0) {
            return null;
        } else {
            return temp;
        }
    }
    //BUSCAR CONTACTOS POR TELEFONO
    public Contacto[] findByTelefono(String telefono){
        
        //array temporal do tamaño da axenda
        Contacto[] temp = new Contacto[axenda.length];
        
        //contador para saber cantos se atopan
        int count=0;
        
        //recorremos array
        for (int i=0;i<axenda.length;i++){
            //contacto da posición actual
            Contacto c = axenda[i];
            
            //se a posición non está vacía e o tel conincide
            if(c !=null && telefono.equals(c.getTelefono())){
                //gardamos o contacto na seguinte posición libre
                temp[count] = c;
                //seguimos buscando coincidencias
                count++;
            }
        }
        if (count == 0){//se non hay coincidencias devolvemos baleiro
            return null;
        }else {
            return temp;//devolvemos o array cas coincidencias
        }
    }
    //BUSCAR POR EMAIL
    public Contacto[] findByEmail(String email){
        Contacto[] temp = new Contacto[axenda.length];
        int count = 0;
        
        for (int i=0;i<axenda.length;i++){
            Contacto c = axenda[i];
            
            if(c !=null && email.equals(c.getEmail())){
                temp[count] = c;
                count++;
            }
        }
        if(count==0){
            return null;
        }else{
            return temp;
        }
    }
    //BUSCAR POR PALABRA DENTRO DA DESCRIPCION
    public Contacto[] findByDescripcion(String palabra){
        Contacto[] temp = new Contacto[axenda.length];
        int count = 0;
        
        for (int i=0;i<axenda.length;i++){
            Contacto c = axenda[i];
            
            if(c != null && c.getDescricion() != null && c.getDescricion().toLowerCase().contains(palabra.toLowerCase())){
                temp[count]=c;
                count++;
            }
        }
        if(count == 0){
            return null;
        }else{
            return temp;
        }
    }
    //OBTER TODOS OS CONTACTOS GARDADOS
    public Contacto[] obterContactos(){
        return axenda;
    }
    //BORRAR TODOS OS CONTACTOS GARDADOS
    public void borrarContactos(){
        for (int i = 0; i<axenda.length;i++){
            axenda[i]=null;
        }
    }
}
