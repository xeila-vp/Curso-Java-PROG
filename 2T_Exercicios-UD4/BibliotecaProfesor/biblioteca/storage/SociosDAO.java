package biblioteca.storage;

import biblioteca.model.Socio;

/**
* Almacén simple de socios usando arrays.
*/
public class SociosDAO {
    // Numero máximo de Socios que se poden xestionar
    private static final int MAX_SOCIOS = 100;  
    // Array para almacenar os Socios actuais -- É volátil, cando o programa remata se perden os datos --
    private Socio[] socios = new Socio[MAX_SOCIOS];

    /**
    * Engade o socio ao almacén. Lanza unha DAOException si o socio xa existe.
    */
    public void engadir(Socio socio) throws DAOException {
        // Buscamos o primeiro elemento que vale null e comprobamos que non exista o socio
        int pos=get_pos_and_check(socio);  
        if (pos < MAX_SOCIOS) {
            socios[pos] = socio;
        } else throw new DAOException("O almacen de socios está cheo");
    }


    /**
    * Busca un socio por numero de socio
    */
    public Socio buscarPorNumero(int numero) {
        int pos=get_pos(numero);
        if (pos<0) return null;
        return socios[pos];
    }

    /**
    * Borra un Socio
    */
    public Socio buscarPorNumero(Socio socio) throws DAOException {
        int pos=get_pos(socio.getNumeroSocio());
        if (pos<0) throw new DAOException("O socio non existe");
        socios[pos]=null;
        return socio;
    }
    
    /**
    * Busca un socio por numero de socio
    */
    private int get_pos(int numero) {
        for (int i = 0; i < MAX_SOCIOS; i++) {
            if (socios[i]!=null && socios[i].getNumeroSocio() == numero) return i;
        }
        return -1;
    }
    
   /**
    * Busca un oco e lanza unha DAOException si o socio xa existe.
    *  Fago as dúas cousas por eficiencia. O mellor dende o punto de vista do deseño/claridade e separar responsabilidades:
    *  Un método que indique si existe e en qué posición
    *  Un método que nos retorne o primeiro oco libre.
    */
    private int get_pos_and_check(Socio socio) throws DAOException {
        int pos=MAX_SOCIOS;

        if (socio==null) throw new DAOException("Non se pode engadir null");
        for(int x=0;x<MAX_SOCIOS;x++) {
            if (socios[x]==null && pos==MAX_SOCIOS) pos=x;
            if (socio.equals(socios[x])) throw new DAOException("O socio xa existe");
        }
        return pos;
    }

}
