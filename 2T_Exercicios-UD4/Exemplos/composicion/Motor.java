
package composicion;

public class Motor {
    private int potencia;
    private int velocidade;

    public Motor(int potencia) {
        this.potencia = potencia;
        this.velocidade = 0;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getVelocidade() {
        return this.velocidade;
    }

    public void acelera_hasta(int kmh) {
        this.velocidade=kmh;
    }
}

