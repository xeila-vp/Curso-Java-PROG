
package composicion;

public class Coche {
    private Motor motor;  // Un Coche *ten un* Motor

    public Coche(Motor motor) {
        this.motor = motor;
    }

    public void acelerar(int km) {
        motor.acelera_hasta(km);
        System.out.println("Acelerando con motor de " + motor.getPotencia() + " CV a "+motor.getVelocidade()+"km/h");
    }
}

