package banco;

import java.util.Scanner;

/**
 * Clase principal da aplicación de xestión bancaria.
 */
public class XestorBanca {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===== XESTOR BANCA =====");
            System.out.println("1. Crear cliente");
            System.out.println("2. Abrir conta");
            System.out.println("3. Realizar ingreso");
            System.out.println("4. Realizar retirada");
            System.out.println("5. Domiciliar recibo");
            System.out.println("6. Realizar cargo");
            System.out.println("7. Consultar conta");
            System.out.println("8. Consultar cliente");
            System.out.println("0. Saír");
            System.out.print("Escolla unha opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    System.out.println("-> Opción crear cliente (pendente de completar)");
                    break;
                case 2:
                    System.out.println("-> Opción abrir conta (pendente de completar)");
                    break;
                case 3:
                    System.out.println("-> Opción realizar ingreso (pendente de completar)");
                    break;
                case 4:
                    System.out.println("-> Opción realizar retirada (pendente de completar)");
                    break;
                case 5:
                    System.out.println("-> Opción domiciliar recibo (pendente de completar)");
                    break;
                case 6:
                    System.out.println("-> Opción realizar cargo (pendente de completar)");
                    break;
                case 7:
                    System.out.println("-> Opción consultar conta (pendente de completar)");
                    break;
                case 8:
                    System.out.println("-> Opción consultar cliente (pendente de completar)");
                    break;
                case 0:
                    System.out.println("Saíndo da aplicación...");
                    break;
                default:
                    System.out.println("Opción non válida.");
                    break;
            }

        } while (opcion != 0);

        sc.close();
    }
}