
package composicion;

public class Exemplo {
    public static void main(String[] args) {
        Pantalla pv=new Pantalla("verde");
        Pantalla pa=new Pantalla("azul");
        Ordenador computer=new Ordenador(pv);
        computer.iniciar();
        computer.setPantalla(pa);
        computer.iniciar();
        pa.setCor("negro"); // *Xa non podemos cambiarlle a cor dende fora*
        computer.iniciar();
    }
}

