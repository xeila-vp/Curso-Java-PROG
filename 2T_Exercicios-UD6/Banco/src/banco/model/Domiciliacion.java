/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.model;

/**
 *
 * @author Usuario
 * 
 **
/**
 * Representa unha domiciliación asociada a unha conta corrente.
 * Garda os datos necesarios para poder realizar posteriormente un cargo.
 */

public class Domiciliacion {
    private String codDomiciliacion;
    private String contaBeneficiario;
    private String concepto;
    private Conta conta;
    
    /**
           * Crea unha domiciliación cos seus datos básicos.
           *
          * @param codDomiciliacion código único da domiciliación
           * @param conta conta á que está asociada
          * @param contaBeneficiario número de conta do beneficiario
           * @param concepto concepto da domiciliación
           */
    
    public Domiciliacion(String codDomiciliacion, Conta conta, String contaBeneficiario, String concepto){
        this.codDomiciliacion = codDomiciliacion;
        this.conta=conta;
        this.contaBeneficiario=contaBeneficiario;
        this.concepto=concepto;
    }

    public String getCodDomiciliacion() {
        return codDomiciliacion;
    }

    public String getContaBeneficiario() {
        return contaBeneficiario;
    }

    public String getConcepto() {
        return concepto;
    }

    public Conta getConta() {
        return conta;
    }
    
}
