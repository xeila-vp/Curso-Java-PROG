
package references;

class TestGeometry {
    public static void main(String[] args) {
        Punto p1=new Punto(5,5);
        Punto p2=new Punto(10,10);

        Recta recta=new Recta(p1,p2);
        System.out.println(recta);

        // *Xa non será posible modificar dende aquí os atributos da Recta*
        System.out.println();

        p1.setX(12);
        System.out.println(recta); // Como vemos, recta *NON modifica* o seu punto de inicio

        // Os getter getA e getB *xa non permiten romper a encapsulación*
        System.out.println(recta);
        Punto pinicio=recta.getA();
        pinicio.setY(500);
        System.out.println(recta); // Como vemos, recta *NON ten modificado*  o seu punto de inicio
    }
}

