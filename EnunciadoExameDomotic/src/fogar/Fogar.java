package fogar;

import domotic.DispositivoDomotico;
import domotic.drivers.DriverDomoticoXenerico;
import domotic.interfaces.DriverDomotico;
import java.util.HashMap;
import java.util.function.Supplier;

public class Fogar  {
    private HashMap<String,Supplier<DriverDomotico>> drivers=new HashMap<>();
    private HashMap<String,DispositivoDomotico> devices=new HashMap<>();

    public Fogar() {
        drivers.put("Xenerico",()->new DriverDomoticoXenerico());
    }

    String[] getDriverNames() {
        return drivers.keySet().toArray(new String[0]);
    }
    
    String[] getDeviceTypes() {
        return new String[] {"TV","PERSIANA","CLIMATIZADOR","LUZ","ENCHUFE"};
    }    
    
    // Engade un DispositivoDomótico os dispositivos
    void addDevice(DispositivoDomotico d) {
        // TODO
    }
    
    // Retorna un Array cos dispositivos almacenados
    DispositivoDomotico[] getDevices() {
        // TODO
        return null;
    }

    // Recupera un DispositivoDomótico pola súa id
    DispositivoDomotico getDevice(String deviceid) {
        // TODO
        return null;
    }
    
    // Retorna un novo obxecto DriverDomotico a partir do nome do driver ou null si ese nome non está rexistrado. Ver a interface Supplier na libraría estándar
    DriverDomotico getDriver(String drivername) {
        // TODO
        return null;
    }
    
}
