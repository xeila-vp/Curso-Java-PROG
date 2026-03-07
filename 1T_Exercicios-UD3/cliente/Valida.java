/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosclases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class Valida {
    public static boolean NifValido(String nif){
    //comprobar si el dni tiene la longitud correcta
    
        if(nif != null || nif.length()==9){
            
            //extraer parte numérica y letra del NIF
            String numStringDNI = nif.substring(0,8);
            String letraDNI = nif.substring(8);
            
            //convertir la parte numérica a int para poder hayar el resto

            int numDNI = Integer.parseInt(numStringDNI);

            //declaro el resto como el resto del numero del dni / 23
            int resto = numDNI%23;
            
            //declaro un array donde la ubicación de cada letrase corresponde con el resto
            String[] letraComparacion={"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
            String letraCalculada = letraComparacion[resto];
            
            if(!letraDNI.equalsIgnoreCase(letraCalculada)){
                return false;
            }
        }return true;
    }
    public static boolean telefonoValido(String telefono){
        if(telefono != null && !telefono.isEmpty()){
            String regex = "^(\\+[0-9]{2})?([0-9]{9})$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(telefono);
            return matcher.matches();
        }
        return true;
        
    }
    public static boolean emailValido(String email){
        if(email !=null && !email.isEmpty()){
            String regex = "^[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*\\@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3}+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return true;
    }
}
