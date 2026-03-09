package domotic.interfaces;

import domotic.DomoticException;
import domotic.DispositivoDomotico;


public interface DriverDomotico extends AdaptadorDomotico {
    public void setDevice(DispositivoDomotico device) throws DomoticException;
    public boolean getStatus();
}
