package domotic.interfaces;

import domotic.DomoticException;
import java.util.List;

/**
 * Todas as operacións que permite o driver do fabricante diferentes a connect() on() e off()
 * se realizan mediante o método command.
 * 
 * Por exemplo, un dispositivo programable se pdoería programar co driver do fabricante mediante algo así
 * program("PROGAM",Arrays.asList("15:30 12-12-2026"));
 * 
 */
public interface AdaptadorDomotico {

    void close() throws DomoticException;

    List<String> cmdList() throws DomoticException;

    void connect() throws DomoticException;

    void off() throws DomoticException;

    void on() throws DomoticException;

    void command(String cmd, List<String> params) throws DomoticException;
    
}
