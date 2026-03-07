
package instantiation;  // Que sexa no package instantiation implica que a clase ten que estar dentro dunha carpeta instantiation

class Persoa {
    String nome; // Os obxectos *Persoa*, teñen un *nome*, que é de tipo String
    int idade; // Os obxectos *Persoa*, teñen unha *idade*, que é de tipo int

    // Construtor sen argumentos. O nome da Persoa será null e a idade 0
    Persoa() {
    }

    // Construtor *sobrecargado* que recibe o nome da persoa que queremos crear
    Persoa(String nome) {
        this.nome=nome; // Necesitamos usar *this* para distinguir entre o *atributo nome* e a *variable local nome* na que recibimos o parámetro
        this.idade=0; // Por defecto, unha Persoa ten 0 anos.
    }

    // Construtor *sobrecargado* que recibe o nome da persoa que queremos crear e a súa idade
    Persoa(String nome, int idade) {
        this.nome=nome; // Necesitamos usar *this* para distinguir entre o *atributo nome* e a *variable local nome* na que recibimos o parámetro
        this.idade=idade;
    }

    // Os obxectos Persoa son capaces de *saudar*
    void saudar() {
        System.out.println("Ola, son " + nome);
    }

    public static void main(String[] args) {   // Método principal. Indica o que fai o programa si o invocamos con *java Persoa*
        Persoa p=new Persoa();    // Agora si temos un construtor sin argumentos, e outro que recibe o nome. Podemos crear novos *obxectos Persoa* dos dous modos.
        p.saudar();
        Persoa q=new Persoa("Juan"); // Usamos o construtor para crear unha Persoa de nome Juan, a idade será 0
        q.saudar();
        Persoa r=new Persoa("María", 30);  // Usamos o construtor para crear unha Persoa de nome Maria e 30 anos
        r.saudar();
    }
}

