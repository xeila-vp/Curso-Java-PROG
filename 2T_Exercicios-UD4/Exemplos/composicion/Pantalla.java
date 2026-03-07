
package composicion;

public class Pantalla {
    private String cor; 

    public Pantalla(String cor) {
        this.cor=cor;
    }

    // *Construtor de copia*
    public Pantalla(Pantalla p) {
        this.cor=p.cor;
    }

    public void setCor(String cor) {
        this.cor=cor;
    }

    public void debuxar() {
        System.out.println("Debuxando en cor "+cor+"...");
    }
}

