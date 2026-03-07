
package composicion;

public class Ordenador {
    private Pantalla pantalla;

    public Ordenador(Pantalla pantalla) {
        this.pantalla = pantalla;
    }

    public void setPantalla(Pantalla pantalla) {
        this.pantalla = new Pantalla(pantalla); // *Copia Defensiva:* Almacenamos unha copia para evitar a manipulación externa 
    }

    public Pantalla getPantalla() {
        return new Pantalla(this.pantalla); // *Copia Defensiva:* Retornamos unha copia da nosa pantalla, para non permitir acceso externo 
    }

    public void iniciar() {
        pantalla.debuxar();
    }
}

