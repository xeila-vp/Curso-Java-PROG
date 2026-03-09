/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.forms;

import banco.model.Cliente;
import java.util.Scanner;

/**
 *
 * @author Usuario
 * 
 * Pide os datos necesarios para activar unha unha conta.
 * numeroConta 
 * Data_apertura
 * Cliente
 * Saldo
 * 
 */
public class ContaForm {
    /**
            * Pide os datos básicos dunha conta  e devolve o obxecto creado.
            *
            * @return cliente creado cos datos introducidos
            */
    public static Cliente altaCliente(Scanner sc){
        
        // Pedimos datos básicos
        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Apelidos: ");
        String apelidos = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        // Pedimos o tipo de cliente
        System.out.println("Tipo de cliente:");
        System.out.println("1. Ordinario");
        System.out.println("2. Empresa");
        System.out.print("Escolle opción: ");
        int opcion = Integer.parseInt(sc.nextLine());

        // Creamos o cliente segundo o tipo
        if (opcion == 2) {
            // Cliente empresa: límite por defecto 2000
            return new Cliente(dni, nome, apelidos, email, telefono, 2000.0);
        } else {
            // Cliente ordinario
            return new Cliente(dni, nome, apelidos, email, telefono);
        }
    }
}
