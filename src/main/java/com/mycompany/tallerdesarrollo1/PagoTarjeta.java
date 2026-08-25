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
// Implementación 1: pago con tarjeta de crédito
public class PagoTarjeta implements Pago {
    private final String numeroTarjeta;

    public PagoTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }

    @Override
    public void procesar(BigDecimal monto) {
        System.out.println("Cargando $" + monto + " a la tarjeta " + numeroTarjeta);
        // lógica de autorización con la red de tarjetas    
    }

    @Override
    public String getDescripcion() { return "Tarjeta " + numeroTarjeta; };
}