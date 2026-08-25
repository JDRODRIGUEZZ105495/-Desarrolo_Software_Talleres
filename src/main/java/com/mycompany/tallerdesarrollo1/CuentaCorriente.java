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
public class CuentaCorriente extends Cuenta{
    private final BigDecimal limiteDescubierto; // atributo propio de CuentaCorriente
    
    public CuentaCorriente(String numero, BigDecimal saldoInicial, BigDecimal limiteDescubierto) {
    super(numero,"", saldoInicial); // llama al constructor del padre
    this.limiteDescubierto = limiteDescubierto;
    }

    // Sobreescribe el comportamiento para permitir saldo negativo hasta el límite
    @Override
    public void debitar(BigDecimal monto) {
        
        BigDecimal saldoDisponible = this.saldo.add(limiteDescubierto);
        if (monto.compareTo(saldoDisponible) > 0)
            throw new SaldoInsuficienteException("Supera el límite de descubierto");
        this.saldo = this.saldo.subtract(monto);
    }
    public BigDecimal getLimiteDescubierto() {
        return limiteDescubierto;
    }
}
