
package references;

class Enderezo {
    String rua;
    int num;

    Enderezo(String rua, int num) {
        this.rua = rua;
        this.num = num;
    }
}

public class Cliente {
    String nome;
    Enderezo enderezo;

    public Cliente(String nome, Enderezo enderezo) {
        this.nome = nome;
        this.enderezo = enderezo;
    }

    // copia superficial
    public Cliente copiaSuperficial() {
        return new Cliente(this.nome, this.enderezo); 
    }

    // copia profunda
    public Cliente copiaProfunda() {
        Enderezo copia_enderezo=new Enderezo(this.enderezo.rua, this.enderezo.num);
        return new Cliente(this.nome,copia_enderezo);
    }
}

