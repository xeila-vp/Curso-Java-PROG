/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.model;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 * 
/**
 * Representa unha operación realizada sobre unha conta.
 * O importe será positivo para ingresos e negativo para retiradas.
 */

public class Operacion {
    
    private Conta conta;
    private LocalDate data;
    private String concepto;
    private double importe;
    
    /**
             * Crea unha operación cos seus datos básicos.
             *
             * @param conta conta sobre a que se realiza a operación
            * @param data data da operación
            * @param concepto descrición da operación
            * @param importe importe da operación
            */
    
    public Operacion(Conta conta, LocalDate data, String concepto, double importe) {
        this.conta = conta;
        this.data = data;
        this.concepto = concepto;
        this.importe = importe;
    }

    public Conta getConta() {
        return conta;
    }

    public LocalDate getData() {
        return data;
    }

    public String getConcepto() {
        return concepto;
    }

    public double getImporte() {
        return importe;
    }
}
