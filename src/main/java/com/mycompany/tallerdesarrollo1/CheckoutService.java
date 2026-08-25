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
public class CheckoutService {
    public void finalizarCompra(
        Pedido pedido,
        Pago metodoDePago) {
        // El mismo código funciona para tarjeta,
        // transferencia o efectivo.
        metodoDePago.procesar(pedido.getTotal());

            System.out.println(
                "Compra finalizada. Método: " +
                metodoDePago.getDescripcion()
            );
    }
    
    public static class Pedido {
        private final BigDecimal total;
        public Pedido(BigDecimal total) {
            if (total.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(
                        "El total no debe ser negativo"
                );
            }
            this.total = total;
        }
        public BigDecimal getTotal() {
            return total;
        }
    };
}
