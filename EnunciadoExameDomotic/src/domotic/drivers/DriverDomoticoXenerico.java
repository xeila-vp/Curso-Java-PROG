package domotic.drivers;

import domotic.DispositivoDomotico;
import domotic.DomoticException;
import java.util.Arrays;
import java.util.List;
import domotic.interfaces.DriverDomotico;


public class DriverDomoticoXenerico implements DriverDomotico {
    protected DispositivoDomotico device;
    private boolean connected=false;
    private boolean devicestatus=false;

    
    @Override
    public void setDevice(DispositivoDomotico device) throws DomoticException {
        this.device=device;
    }
    
    @Override
    public boolean getStatus() {
        return devicestatus;
    }

    @Override
    public List<String> cmdList() {
        return Arrays.asList("ON", "OFF");
    }
    
    @Override
    public void connect() throws DomoticException {
        if (device == null) throw new DomoticException("Can't connect null devices");
        System.out.println("Connecting "+device.getId());
        this.connected=true;
    }

    @Override
    public void command(String cmd,List<String> params) throws DomoticException {
        List<String> listacmd=cmdList();
        if (!listacmd.contains(cmd)) throw new DomoticException("O dispositivo non ten a capacidade "+cmd);
        if (!connected) throw new DomoticException("O dispositivo non está conectado");
        switch(cmd) {
            case "ON": on(); break;
            case "OFF": off(); break;
        }
    }

    @Override
    public void on() throws DomoticException {
        System.out.println("Switching On "+device);
        devicestatus=true;
    }

    @Override
    public void off() throws DomoticException {
        System.out.println("Switching Off "+device);
        devicestatus=false;
    }
    
    @Override
    public void close() throws DomoticException {
        System.out.println("Closing connection to "+device);
    }
    
}
