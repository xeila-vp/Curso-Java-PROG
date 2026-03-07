/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

import biblioteca.model.Socio;
import com.iesrodeira.collection.ArrayIList;
import com.iesrodeira.collection.ListArray;
import com.iesrodeira.storage.IStorage;
import com.iesrodeira.storage.StorageList;
import java.util.ArrayList;
import java.util.List;

public class ExemploIStorage {
    public static void main(String[] args) {
        List<Socio> backend_al=new ArrayList<>();
        List<Socio> backend_la_nc=new ListArray<>();
        List<Socio> backend_la=new ListArray<>(s->new Socio(s));
        List<Socio> backend_ail_nc=new ArrayIList<>();
        List<Socio> backend_ail=new ArrayIList<>(s->new Socio(s));
    
        IStorage<Socio> storage_al=new StorageList<>(backend_al);
        IStorage<Socio> storage_la_nc=new StorageList<>(backend_la_nc);
        IStorage<Socio> storage_la=new StorageList<>(backend_la);
        IStorage<Socio> storage_ail_nc=new StorageList<>(backend_ail_nc);
        IStorage<Socio> storage_ail=new StorageList<>(backend_ail);
        
        
        Socio exemplo=new Socio(1,"Exemplo 1","Sukasa","socio_01@su_email.com");
        Socio exemplo2=new Socio(2,"Exemplo 2","Kasa2","socio_02@su_email.com");

        System.out.println("Inforamción orixinal: ");
        System.out.println(exemplo);
        System.out.println(exemplo2);
    
        storage_al.add(exemplo);
        storage_al.add(exemplo2);
        
        storage_la_nc.add(exemplo);
        storage_la_nc.add(exemplo2);
        
        storage_la.add(exemplo);
        storage_la.add(exemplo2);
        
        storage_ail_nc.add(exemplo);
        storage_ail_nc.add(exemplo2);
        
        storage_ail.add(exemplo);
        storage_ail.add(exemplo2);
        
        
        // Modificamos o contido do Storage dende fora rompendo o encapsulamento si non temos copia defensiva
        
        hack(exemplo);
        
        Socio[] s=storage_al.filter(socio->socio.getNumeroSocio()==2,Socio.class);
        
        hack(s[0]);
        
        
        System.out.println();
        System.out.println("Visualizando a información almacenada:");
        System.out.println("===================================");
        System.out.println("Almacenamiento En ArrayList normal:");
        show(storage_al);
        System.out.println("===================================");
        System.out.println("Almacenamiento En ArrayList con posibilidad de copia defensiva (*SIN* función de copia):");
        show(storage_la_nc);
        System.out.println("===================================");
        System.out.println("Almacenamiento En ArrayList con posibilidad de copia defensiva (*CON* función de copia):");
        show(storage_la);
        
        System.out.println("===================================");
        System.out.println("Almacenamiento En StorageList (implementación de List con DinamicArray (*SIN* función de copia):");
        show(storage_ail_nc);
        
        System.out.println("===================================");
        System.out.println("Almacenamiento En StorageList (implementación de List con DinamicArray (*SIN* función de copia):");
        show(storage_ail);
    }
    
    private static void show(IStorage<Socio> store) {
        for(Socio s:store) System.out.println(s);
    }
    
    
    private static void hack(Socio socio) {
        socio.setNome("Hackedd!!");
        socio.setEmail("Hacked!@mail");
        socio.setDireccion("Hacked Address!!!");
    }
}
