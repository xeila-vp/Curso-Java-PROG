
package strings;

import java.util.regex.Pattern;

public class Validacion {
    public static void main(String[] args) {
        String correo = "usuario@exemplo.com";
        String regexCorreo = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";

        boolean valido = Pattern.matches(regexCorreo, correo);
        System.out.println("Correo válido? " + valido);

        String telefono = "123-456-7890";
        boolean validoTel = telefono.matches("\\d{3}-\\d{3}-\\d{4}");
        System.out.println("Teléfono válido? " + validoTel);
    }
}

