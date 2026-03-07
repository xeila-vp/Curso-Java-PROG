
package validacion;

public class PersoaSegura {
    private int idade;

    public void setIdade(int idade) throws IdadeIncorrectaException {
        if (idade < 0 || idade > 120)
            throw new IdadeIncorrectaException("A idade non é válida");
        this.idade = idade;
    }
}

