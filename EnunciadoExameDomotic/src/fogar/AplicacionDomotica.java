package fogar;

import domotic.DispositivoDomotico;
import java.util.List;
import java.util.Scanner;
import domotic.interfaces.AdaptadorDomotico;
import domotic.DomoticException;
import domotic.interfaces.DriverDomotico;
import utils.CommandParser;

public class AplicacionDomotica {
    private static Scanner scn=new Scanner(System.in);

       
    private static DispositivoDomotico DeviceFactory(String devicetype,String id,String nome,String descricion) throws DomoticException {
        switch(devicetype) {
            case "LUZ": return new DispositivoDomotico(id,nome,descricion);
        }
        throw new DomoticException("Os dispositivos de tipo "+devicetype+" non están soportados");
    }
    
    public static void main(String[] args) throws InterruptedException {
        int option=0;

        Fogar fogar=new Fogar();
        do {
            System.out.println("\n");
            try {
                System.out.println("Control Domótico");
                System.out.println("================");                
                System.out.println("1.- Engadir Dispositivo");
                System.out.println("2.- Control Domotico");
                System.out.println("3.- Saír");
                System.out.print("\n Elixe opcion: ");
                option=Integer.parseInt(scn.nextLine());
                switch(option) {
                    case 1: DispositivoDomotico d=deviceForm(fogar);
                            if (d!=null) fogar.addDevice(d);
                            break;
                    case 2: homeControlPanel(fogar);
                            break;
                }
            } catch(Exception e) {
                System.out.println("ERROR: "+e.getMessage());
            }
        } while(option!=3);
    }

    // --> Opción de exercicio:  Refactorizar para evitar duplicación de código extraíndo un método T <T> choose(String prompt,T[] optionlist);
    private static DriverDomotico chooseDriver(Fogar fogar) {
        int choose=-1;
        String[] driverlist=fogar.getDriverNames();
        do {
            for(int idx=0;idx<driverlist.length;idx++) System.out.println((idx+1)+".- "+driverlist[idx]);
            System.out.println((driverlist.length+1)+".- Cancelar");
            System.out.print("\nElixe un driver: ");
            choose=Integer.parseInt(scn.nextLine());
        } while(choose < 1 || choose > driverlist.length+1);
        if (choose > driverlist.length) return null;
        return fogar.getDriver(driverlist[choose-1]);
    }
        
    private static String chooseDeviceType(Fogar fogar) {
        int choose=-1;
        String[] typeslist=fogar.getDeviceTypes();
        do {
            for(int idx=0;idx<typeslist.length;idx++) System.out.println((idx+1)+".- "+typeslist[idx]);
            System.out.println((typeslist.length+1)+".- Cancelar");
            System.out.print("\nElixe un tipo de dispositivo: ");
            choose=Integer.parseInt(scn.nextLine());
        } while(choose < 1 || choose > typeslist.length+1);
        if (choose > typeslist.length) return null;
        return typeslist[choose-1];
    }
    
    private static DispositivoDomotico deviceForm(Fogar fogar) throws DomoticException {
        DispositivoDomotico device=null;
        System.out.println("\n\nEngadindo Novo Dispositivo ao Fogar");
        System.out.println("===================================");
        System.out.print("DEVICE_ID: ");
        String device_id=scn.nextLine();
        System.out.print("NAME: ");
        String name=scn.nextLine();
        System.out.print("DESCRIPTION: ");
        String description=scn.nextLine();
        DriverDomotico driver=chooseDriver(fogar);
        if (driver!=null) {
            String devicetype=chooseDeviceType(fogar);
            System.out.println("Creando dispositivo "+devicetype);
            if (devicetype!=null) {
                device=DeviceFactory(devicetype,device_id,name,description);
                device.setDriver(driver);
                fogar.addDevice(device);
            }
        }
        return device;
    }
    
    private static void homeControlPanel(Fogar fogar) throws DomoticException {
        DispositivoDomotico[] devices=fogar.getDevices();
        if (devices.length == 0) System.out.println("Non temos dispositivos");
        else {
            System.out.println("Control de Dispositivos do Fogar");
            System.out.println("================================");
            for(DispositivoDomotico d: devices) System.out.println(d);
            System.out.print("\nCOMANDO: ");
            String cmd=scn.nextLine();
            List<String> params=CommandParser.parse(cmd);
            if (params.size()<2) throw new DomoticException("Syntax Error: <dispositivo> <comando> <parametro> ...");
            String devicename=params.remove(0);
            String action=params.remove(0);
            DispositivoDomotico device=fogar.getDevice(devicename);
            if (device==null) throw new DomoticException("O dispositivo "+devicename+" non existe");
            if (action.equals("HELP")) {
                List<String> cmds=device.cmdList();
                for(String c:cmds) System.out.println(c);
            } else device.command(action,params);
        }
    }
}
