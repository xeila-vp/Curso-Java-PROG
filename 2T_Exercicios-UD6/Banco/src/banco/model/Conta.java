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
 * 
 */
public class Conta {
    
    private int numConta;
    private LocalDate dataApertura;
    private Cliente cliente;
    private double saldo;
    private TipoConta tipoConta;
    
    // Propiedades calculadas segundo o tipo
    private final double comisionRetirada;
    private final boolean permiteDomiciliacions;
    private final double limiteDescuberto; 
    
    /**
            * Construtor único: recibe o tipo escollido no alta de conta
            * e calcula as regras segundo ese tipo.
            * @param numConta
            * @param cliente
            * @param tipoConta
            */
    
    public Conta(int numConta, Cliente cliente, TipoConta tipoConta){
        this.cliente=cliente;
        this.numConta=numConta;
        this.saldo=0.0;
        this.dataApertura=LocalDate.now();
        this.tipoConta=tipoConta;
        
        // Segundo o tipo de conta corrente, cambia o límite de descuberto
        if (tipoConta == TipoConta.C_AFORROS) {
            this.comisionRetirada = 0.0;
            this.permiteDomiciliacions = false;
            this.limiteDescuberto = 0.0;
        } else if (tipoConta == TipoConta.CC_PERSOAL) {
            this.comisionRetirada = 0.0001; // 0,01%
            this.permiteDomiciliacions = true;
            this.limiteDescuberto = 0.0;
        } else { // CC_EMPRESA
            this.comisionRetirada = 0.0001; // 0,01%
            this.permiteDomiciliacions = true;
            this.limiteDescuberto = cliente.getLimiteDescuberto();
        }
    }

    public int getNumConta() {
        return numConta;
    }

    public LocalDate getDataApertura() {
        return dataApertura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getComisionRetirada() {
        return comisionRetirada;
    }

    public boolean isPermiteDomiciliacions() {
        return permiteDomiciliacions;
    }

    public double getLimiteDescuberto() {
        return limiteDescuberto;
    }
    
}