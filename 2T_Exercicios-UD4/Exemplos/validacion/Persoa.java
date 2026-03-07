
package validacion;

/// Clase con validación e uso de excepcións estándar
public class Persoa {
    private String nome;
    private int idade;

    public Persoa(String nome, int idade) {
        if (nome == null || nome.isBlank() || idade < 0 || idade > 120 ) throw new IllegalArgumentException("O nome non pode ser baleiro");
        this.nome=nome;
        this.idade=idade;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("O nome non pode ser baleiro");
        this.nome = nome;
    }

    public void setIdade(int idade) {
        if (idade < 0 || idade > 120)
            throw new IllegalArgumentException("Idade fóra de rango permitido");
        this.idade = idade;
    }

    @Override
    public String toString() {
        return nome + " (" + idade + " anos)";
    }
}

