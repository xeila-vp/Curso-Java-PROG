
package references;

public class TestCopias {
    public static void main(String[] args) {

        Cliente c1 = new Cliente("Ana", new Enderezo("Sol", 7));

        Cliente shallow = c1.copiaSuperficial();
        Cliente deep = c1.copiaProfunda();

        shallow.enderezo.num = 999;
        deep.enderezo.num = 123;

        System.out.println("C1 num = " + c1.enderezo.num); 
        System.out.println("Shallow num = " + shallow.enderezo.num); 
        System.out.println("Deep num = " + deep.enderezo.num); 
    }
}

