package references;

public class CopiaMala {
    public static void main(String[] args) {
        Punto p1 = new Punto(10, 20);
        Punto p2 = p1;  // MAL: só copiamos a referencia

        p2.setX(999);  // Isto modifica TAMÉN p1, porque ambos apuntan ao mesmo obxecto

        System.out.println(p1);  // 999 ← p1 foi modificado indirectamente
    }
}

