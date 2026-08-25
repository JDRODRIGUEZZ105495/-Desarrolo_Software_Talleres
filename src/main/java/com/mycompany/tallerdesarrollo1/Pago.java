/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tallerdesarrollo1;

import java.math.BigDecimal;

/**
 *
 * @author juand
 */
public interface Pago {
    void procesar(BigDecimal monto);
    String getDescripcion();
}


