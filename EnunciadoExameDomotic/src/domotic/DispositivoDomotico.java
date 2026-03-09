package domotic;

import domotic.interfaces.AdaptadorDomotico;
import domotic.interfaces.DriverDomotico;
import java.util.List;

public class DispositivoDomotico implements AdaptadorDomotico {
    protected final String id;
    protected final String nome;
    protected String descricion;
    protected DriverDomotico driver;

    public DispositivoDomotico(String id, String nome, String descricion) {
        this.id = id;
        this.nome = nome;
        this.descricion = descricion;
    }

    public void setDriver(DriverDomotico driver) throws DomoticException {
        this.driver=driver;
        driver.setDevice(this);
        driver.connect();
    }
    
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricion() {
        return descricion;
    }

    public AdaptadorDomotico getDriver() {
        return driver;
    }
    
    @Override
    public void on() throws DomoticException {
        if (driver == null) throw new DomoticException("Not exists driver for this device");
        driver.on();
    }
    
    @Override
    public void off() throws DomoticException {
        if (driver == null) throw new DomoticException("Not exists driver for this device");        
        driver.off();
    };
    
    @Override
    public void command(String cmd,List<String> params) throws DomoticException {
      if (driver == null) throw new DomoticException("Not exists driver for this device");        
       driver.command(cmd, params);
    }

    @Override
    public void close() throws DomoticException {
      if (driver == null) throw new DomoticException("Not exists driver for this device");        
      driver.close();
    }

    @Override
    public List<String> cmdList() throws DomoticException {
        if (driver == null) throw new DomoticException("Not exists driver for this device");
        return driver.cmdList();
    }

    @Override
    public void connect() throws DomoticException {
        if (driver == null) throw new DomoticException("Not exists driver for this device");
        driver.connect();
    }
    
    @Override
    public String toString() {
        return "[ "+id+" ] "+nome+" ("+descricion+") - "+(driver.getStatus()?"ON":"OFF");
    }    
}
