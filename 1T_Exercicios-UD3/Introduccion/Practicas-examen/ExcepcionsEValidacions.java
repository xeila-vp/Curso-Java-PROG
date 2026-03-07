/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apuntesexamen;

/**
 *
 * @author Xeila
 */
public class ExcepcionsEValidacions {

    public static void demo() {
        System.out.println("Chuleta cargada correctamente.\nRevisa o código desta clase.");
    }

    // ---------------------------
    // 1) ERROS EN COMPILACIÓN
    // ---------------------------
    /*
     * Ocorren ANTES de executar:
     * - faltan chaves
     * - faltan parénteses
     * - tipo incorrecto
     * - chamar métodos incorrectos
     */

    // Exemplo erro común:
    // System.out.println("Hola"  // falta )


    // ---------------------------
    // 2) ERROS EN EXECUCIÓN
    // ---------------------------
    /*
     * O programa compila pero CRASHEA mentres funciona.
     * Os máis comúns:
     */

    // NULLPOINTEREXCEPTION
    /*
        Client c = null;
        c.getDni();  // ERROR
     */

    // ARRAYINDEXOUTOFBOUNDSEXCEPTION
    /*
        int[] nums = new int[3];
        nums[3] = 10; // ERROR (0-2 válidos)
     */

    // NUMBERFORMATEXCEPTION
    /*
        int n = Integer.parseInt("hola");
     */

    // INPUTMISMATCHEXCEPTION
    /*
        int n = sc.nextInt(); // usuario escribe texto
     */


    // ---------------------------
    // 3) TRY / CATCH BÁSICO
    // ---------------------------
    /*
        try {
            int n = Integer.parseInt("hola");
        } catch (NumberFormatException e) {
            System.out.println("Non é un número válido");
        }
     */


    // ---------------------------
    // 4) LANZAR EXCEPCIÓNS (throw)
    // ---------------------------
    /*
     * Útil para VALIDACIÓN:
     *
        if (dni.length() != 9) {
            throw new IllegalArgumentException("DNI incorrecto");
        }
     */


    // ---------------------------
    // 5) PATRÓN DE VALIDACIÓN TÍPICO
    // ---------------------------
    /*
        public void setEmail(String email) {

            if (email == null || email.isBlank())
                throw new IllegalArgumentException("Email baleiro");

            if (!email.contains("@"))
                throw new IllegalArgumentException("Email inválido");

            this.email = email;
        }
     */


    // ---------------------------
    // 6) EXCEPCIÓN PERSONALIZADA
    // ---------------------------
    /*
     * Exemplo de CancelException:
     *
        public class CancelException extends Exception {
            public CancelException(String msg) {
                super(msg);
            }
        }
     */


    // ---------------------------
    // 7) LANZAR EXCEPCIÓN PERSONALIZADA
    // ---------------------------
    /*
        if (input.equals("*"))
            throw new CancelException("Operación cancelada");
     */


    // ---------------------------
    // 8) CAPTURAR EXCEPCIÓN PERSONALIZADA
    // ---------------------------
    /*
        try {
            pedirCliente();
        } catch (CancelException e) {
            System.out.println(e.getMessage());
        }
     */


    // ---------------------------
    // 9) MULTI-CATCH
    // ---------------------------
    /*
        try {
            int n = Integer.parseInt(txt);
            rooms[i].getNumber();
        } catch (NumberFormatException e) {
            System.out.println("Non é un número");
        } catch (NullPointerException e) {
            System.out.println("Habitación non existe");
        }
     */


    // ---------------------------
    // 10) FINALLY
    // ---------------------------
    /*
        try {
            // ...
        } catch (Exception e) {
            // ...
        } finally {
            System.out.println("Sempre se executa");
        }
     */


    // ---------------------------
    // 11) VALIDACIÓN DE FECHAS
    // ---------------------------
    /*
        if (!end.isAfter(start))
            throw new IllegalArgumentException("A saída debe ser posterior á entrada");
     */

    /*
        LocalDateTime d = LocalDate.now().atTime(12,0); // correcto
     */


    // ---------------------------
    // 12) VALIDAR ARRAYS
    // ---------------------------
    /*
        if (index < 0 || index >= array.length)
            throw new IllegalArgumentException("Índice fóra de rango");
     */


    // ---------------------------
    // 13) VALIDAR CLIENTE ANTES DE USAR
    // ---------------------------
    /*
        if (cliente == null) {
            System.out.println("O cliente non existe.");
            return;
        }
     */


    // ---------------------------
    // 14) VALIDAR STRINGs
    // ---------------------------
    /*
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome obrigatorio");
     */


    // ---------------------------
    // 15) VALIDAR CAPACIDADE (Room)
    // ---------------------------
    /*
        if (capacity < 1 || capacity > 3)
            throw new IllegalArgumentException("Capacidade inválida (1-3)");
     */


    // ---------------------------
    // 16) VALIDAR DISPONIBILIDADE EN RESERVAS
    // ---------------------------
    /*
        if (end.isBefore(start))
            throw new IllegalArgumentException("Datas incorrectas");
     */


    // ---------------------------
    // 17) VALIDACIÓN ANTES DE ENGADIR CLIENTE
    // ---------------------------
    /*
        if (c == null)
            throw new IllegalArgumentException("Cliente nulo");

        if (getClient(c.getDni()) != null)
            throw new IllegalArgumentException("Cliente xa existe");

        clients.add(c);
     */


    // ---------------------------
    // 18) VALIDAR NÚMEROS
    // ---------------------------
    /*
        if (n < 0)
            throw new IllegalArgumentException("Non pode ser negativo");
     */


    // ---------------------------
    // 19) VALIDACIÓN DE EMAIL
    // ---------------------------
    /*
        if (!email.matches("^[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*\\.[A-Za-z]{2,3}$"))
            throw new IllegalArgumentException("Email inválido");
     */


    // ---------------------------
    // 20) PATRÓN XERAL PARA VALIDAR DATOS DE ENTRADA
    // ---------------------------
    /*
        public void validarCliente(Client c) {

            if (c == null)
                throw new IllegalArgumentException("Cliente é null");

            if (c.getDni() == null || c.getDni().isBlank())
                throw new IllegalArgumentException("DNI baleiro");

            if (c.getDni().length() != 9)
                throw new IllegalArgumentException("DNI incorrecto");
        }
     */
}
