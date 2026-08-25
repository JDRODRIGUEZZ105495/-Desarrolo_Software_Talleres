/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerdesarrollo1;

import java.math.BigDecimal;

/**
 *
 * @author juand
 */
public class Cuenta {
    private final String numero;
    private final String titular;
    protected BigDecimal saldo;
    public Cuenta(String numero, String titular, BigDecimal saldoInicial) {
        this.numero   = numero;
        this.titular  = titular;
        this.saldo    = saldoInicial;
        
        if (saldoInicial.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        this.saldo = saldoInicial;
        
    }
     public void depositar(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }

    public void debitar(BigDecimal monto) throws SaldoInsuficienteException {
        if (monto.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El monto debe ser positivo");
        if (monto.compareTo(this.saldo) > 0)
            throw new SaldoInsuficienteException ("Saldo: " + saldo + ", solicitado: " + monto);
        this.saldo = this.saldo.subtract(monto);
    }

    public BigDecimal getSaldo() { return this.saldo; }
    
    public String getNumero() {
        return this.numero;
    }

    public String getTitular() {
        return this.titular;
    }


    protected static class SaldoInsuficienteException extends RuntimeException {

        public SaldoInsuficienteException(String mensaje) {
            super (mensaje);
        }
    }
}
