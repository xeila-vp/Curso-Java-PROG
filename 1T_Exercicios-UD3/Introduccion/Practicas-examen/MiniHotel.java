/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

/**
 *
 * @author Xeila
 */
public class MiniHotel {

    // ==== HABITACIÓN ====
    static class Room {
        private int number;
        private int capacity;

        public Room(int number, int capacity) {
            this.number = number;
            this.capacity = capacity;
        }

        public int getNumber() { return number; }
    }

    // ==== CLIENTE ====
    static class Client {
        private String dni;
        private String nome;

        public Client(String dni, String nome) {
            this.dni = dni;
            this.nome = nome;
        }

        public String getDni() { return dni; }
        public String getNome() { return nome; }
    }

    // ==== HOTEL ====
    static class Hotel {

        private String nome;
        private Room[] rooms; // array fixo de habitacións
        private MiniDynamicArray clients; // lista dinámica de clientes

        public Hotel(String nome, Room[] rooms) {
            this.nome = nome;
            this.rooms = rooms;

            // Creamos unha lista baleira
            this.clients = new MiniDynamicArray(50);
        }

        public void addClient(Client c) {
            clients.add(c);
        }

        public Client getClient(String dni) {
            for (int i = 0; i < clients.size(); i++) {
                Client c = (Client) clients.get(i);
                if (c.getDni().equals(dni)) {
                    return c;
                }
            }
            return null;
        }
    }

    public static void demo() {

        // Crear array de habitacións
        Room[] rooms = new Room[2];
        rooms[0] = new Room(101, 1);
        rooms[1] = new Room(201, 2);

        // Crear hotel
        Hotel h = new Hotel("Hotel Mini", rooms);

        // Engadir clientes
        h.addClient(new Client("111A", "Ana"));
        h.addClient(new Client("222B", "Brais"));

        // Buscar cliente
        Client atopado = h.getClient("222B");

        if (atopado != null) {
            System.out.println("Atopado cliente: " + atopado.getNome());
        } else {
            System.out.println("Cliente non atopado");
        }

        System.out.println();
    }
}
