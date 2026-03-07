
package examples;

class ProgramaPersoa {
    public static void main(String[] args) {
        // Creamos un obxecto Persoa, gardamos a súa referencia en *p*
        Persoa p=new Persoa();
        // Poñemos "Ana" na característica "nome" do obxecto ao que apunta o valor almacenado en *p* 
        p.nome="Ana";		
        p.idade=23;
        // Facemos que o obxecto ao que apunta o valor almacenado en p "saúde".
        p.saudar();
        // Facemos que o obxecto ao que apunta o valor almacenado en p indique a súa idade.
        p.showIdade();
    }
}

