
package references;

public class Comparacion {
    public static void main(String[] args) {
        Punto p1=new Punto(5,5);
        Punto p2=new Punto(10,10);

        Recta r1=new Recta(p1,p2);
        Recta r2=new Recta(p1,p2);

        System.out.println("Son iguais ? "+ (r1.equals(r2))); // *false* A implementación de equals en Object so retorna true si son o mesmo obxecto.

        //  *Si queremos que equals se comporte correctamente debemos sobrepoñelo escribindo unha versión correcta do método na clase Recta*

        // A *clase String* e un tanto especial, porque é inmutable e as constantes se reutilizan.
        String c1="test";
        String c2="test"; // Realmente so se crea un único obxecto String "test". Logo se reutiliza. c1 e c2 teñen a mesma referencia 

        System.out.println(c1 == c2);      // *true*, porque so existe no sistema unha unica constante 'test' 

        // Sin embargo, si creamos o String con *new*, se crean obxectos diferentes.
        String a=new String("test");
        String b=new String("test");
        System.out.println(a == b);      // *false* (referencias diferentes)
        System.out.println(a.equals(b)); // *true*  (mesmo contido e a clase String dispón dunha versión de equals correcta)
    }
}

