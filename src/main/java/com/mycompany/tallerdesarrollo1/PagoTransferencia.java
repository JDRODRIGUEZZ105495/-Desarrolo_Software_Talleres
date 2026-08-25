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
public class PagoTransferencia implements Pago{
    private final String cbu;
    public PagoTransferencia(String cbu) { this.cbu = cbu; }
    @Override
    public void procesar(BigDecimal monto) {
        System.out.println("Transfiriendo $" + monto + " al CBU " + cbu);
        // lógica de transferencia interbancaria    
    }
    @Override
    public String getDescripcion() { return "Transferencia a CBU " + cbu; }
}
