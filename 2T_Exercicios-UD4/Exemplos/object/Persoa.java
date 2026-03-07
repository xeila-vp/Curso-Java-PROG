
package object;

/// Exemplo de clase que sobrescribe os métodos clave de Object
public class Persoa {
    private String nome;
    private int idade;

    public Persoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Persoa[nome=" + nome + ", idade=" + idade + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;             /// mesma referencia
        if (!(o instanceof Persoa)) return false;
        Persoa p = (Persoa) o;
        return idade == p.idade && java.util.Objects.equals(nome, p.nome);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nome, idade);
    }
}

