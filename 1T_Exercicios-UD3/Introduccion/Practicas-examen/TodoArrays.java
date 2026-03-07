/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

/**
 *
 * @author Xeila
 */
public class ChuletaArrays {

    public static void main(String[] args) {

        // ============================================================
        // 1) ARRAY SIMPLE — ENTEROS
        // ============================================================

        // Declaración dun array de 5 enteros
        int[] numeros = new int[5];

        // Introducimos valores (posicións 0 a 4)
        numeros[0] = 10;
        numeros[1] = 20;
        numeros[2] = 30;
        // As posicións que non asignamos quedan a 0 por defecto

        // Contador de elementos reais usados (importante para exames)
        int contadorNumeros = 3;

        // Recorrer array ata o contador (non ata length!)
        for (int i = 0; i < contadorNumeros; i++) {
            System.out.println("Numero[" + i + "] = " + numeros[i]);
        }

        // Modificar un valor
        numeros[1] = 99;

        // Borrar (poñer valor por defecto)
        numeros[2] = 0;
        contadorNumeros--;  // reducimos elementos reais

        // Buscar un número
        int buscado = 99;
        int posNumero = -1;
        for (int i = 0; i < contadorNumeros; i++) {
            if (numeros[i] == buscado) {
                posNumero = i;
                break;
            }
        }
        System.out.println("Posicion do 99: " + posNumero);



        // ============================================================
        // 2) ARRAY SIMPLE — TEXTO (String)
        // ============================================================

        // Declaración en liña
        String[] nomes = {"Ana", "Brais", "Carla"};
        int contadorNomes = nomes.length; // aquí xa van 3 elementos reais

        // Recorrer array de texto
        for (int i = 0; i < contadorNomes; i++) {
            System.out.println("Nome[" + i + "] = " + nomes[i]);
        }

        // Buscar texto (igual que en Axenda)
        String nomeBuscado = "Carla";
        int posNome = -1;
        for (int i = 0; i < contadorNomes; i++) {
            if (nomes[i].equalsIgnoreCase(nomeBuscado)) {
                posNome = i;
                break;
            }
        }
        System.out.println("Carla está en: " + posNome);



        // ============================================================
        // 3) ARRAY SIMPLE DE OBXECTOS (como Rooms, Clientes, Contactos)
        // ============================================================

        // Creamos array de clientes
        Cliente[] clientes = new Cliente[4];
        int contadorClientes = 0;

        // Engadir obxectos
        clientes[contadorClientes++] = new Cliente("Ana");
        clientes[contadorClientes++] = new Cliente("Brais");
        clientes[contadorClientes++] = new Cliente("Carla");

        // Recorrer
        for (int i = 0; i < contadorClientes; i++) {
            System.out.println("Cliente[" + i + "] = " + clientes[i]);
        }

        // Buscar cliente por nome
        String clienteBuscado = "Brais";
        int posCliente = -1;
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i].getNome().equalsIgnoreCase(clienteBuscado)) {
                posCliente = i;
                break;
            }
        }
        System.out.println("Brais está en: " + posCliente);

        // Borrar cliente (desprazar á esquerda)
        for (int i = posCliente; i < contadorClientes - 1; i++) {
            clientes[i] = clientes[i + 1];
        }
        contadorClientes--;
        clientes[contadorClientes] = null; // limpar última posición



        // ============================================================
        // 4) ARRAY DINÁMICO MANUAL (como DinamicArray do Hotel)
        // ============================================================

        ClienteArray dinamico = new ClienteArray();

        dinamico.add(new Cliente("David"));
        dinamico.add(new Cliente("Elena"));
        dinamico.add(new Cliente("Fran"));
        dinamico.add(new Cliente("Gabi"));  // aquí amplía

        dinamico.imprimir();  // función propia



        // ============================================================
        // 5) ARRAYLIST (colección dinámica profesional)
        // ============================================================

        ArrayList<Cliente> listaClientes = new ArrayList<>();

        // Engadir
        listaClientes.add(new Cliente("Helena"));
        listaClientes.add(new Cliente("Iván"));
        listaClientes.add(new Cliente("Julia"));

        // Inserir nunha posición
        listaClientes.add(1, new Cliente("Inserido"));

        // Ler
        System.out.println("Cliente posición 0: " + listaClientes.get(0));

        // Actualizar
        listaClientes.set(0, new Cliente("Modificado"));

        // Buscar (usa equals → podes redefinilo en Cliente)
        System.out.println("Pos de Iván: " + listaClientes.indexOf(new Cliente("Iván")));

        // Borrar
        listaClientes.remove(1);

        // Recorrer
        for (Cliente c : listaClientes) {
            System.out.println("-> " + c);
        }

        // Tamaño
        System.out.println("Total clientes: " + listaClientes.size());
    }
}



// ==================================================================
// CLASE CLIENTE (do estilo do Hotel/Axenda)
// ==================================================================
class Cliente {

    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome='" + nome + '\'' + '}';
    }

    // Igualdade por nome → útil para indexOf en ArrayList
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cliente)) return false;
        Cliente outro = (Cliente) obj;
        return this.nome.equalsIgnoreCase(outro.nome);
    }
}



// ==================================================================
// ARRAY DINÁMICO MANUAL — IGUAL AO QUE FIXECHES NO HOTEL
// ==================================================================
class ClienteArray {

    private Cliente[] datos;
    private int size;

    public ClienteArray() {
        datos = new Cliente[3]; // capacidade inicial
        size = 0;
    }

    public void add(Cliente c) {
        if (size == datos.length) ampliar();
        datos[size++] = c;
    }

    private void ampliar() {
        Cliente[] novo = new Cliente[datos.length * 2];
        for (int i = 0; i < datos.length; i++) {
            novo[i] = datos[i];
        }
        datos = novo;
    }

    public Cliente get(int i) {
        if (i < 0 || i >= size) return null;
        return datos[i];
    }

    public void remove(int i) {
        if (i < 0 || i >= size) return;
        for (int x = i; x < size - 1; x++) {
            datos[x] = datos[x + 1];
        }
        datos[--size] = null;
    }

    public void imprimir() {
        System.out.println("=== CLIENTES DINÁMICOS ===");
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + datos[i]);
        }
    }

    public int size() { return size; }
}

