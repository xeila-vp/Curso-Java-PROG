package memstore;

public class Copiers {
    public static <T> Copier<T> identity() {
        return v->v;
    }
}
