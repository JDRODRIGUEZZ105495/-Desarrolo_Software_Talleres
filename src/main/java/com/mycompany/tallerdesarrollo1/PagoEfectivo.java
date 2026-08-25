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
// Implementación 3: pago en efectivo
public class PagoEfectivo implements Pago {
    @Override
    public void procesar(BigDecimal monto) {
        System.out.println("Registrando pago en efectivo de $" + monto);
    }
    @Override
    public String getDescripcion() { return "Efectivo"; }
}
