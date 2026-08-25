/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tallerdesarrollo1;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author juand
 */
public class TallerDesarrollo1 {

static ArrayList<Cuenta> cuentas = new ArrayList<>();
    static final String ARCHIVO = "cuentas.txt";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cargarCuentas();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    crearCuentaNormal();
                    break;
                case 2:
                    crearCuentaCorriente();
                    break;
                case 3:
                    listarCuentas();
                    break;
                case 4:
                    consultarSaldo();
                    break;
                case 5:
                    depositar();
                    break;
                case 6:
                    debitar();
                    break;
                case 7:
                    realizarPago();
                    break;
                case 8:
                    guardarCuentas();
                    System.out.println("\nDatos guardados. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nOpción no válida.");
            }
        } while (opcion != 8);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n=== MENÚ BANCO ===");
        System.out.println("1. Crear cuenta ahorro");
        System.out.println("2. Crear cuenta corriente");
        System.out.println("3. Listar cuentas");
        System.out.println("4. Consultar saldo");
        System.out.println("5. Depositar");
        System.out.println("6. Debitar");
        System.out.println("7. Realizar pago");
        System.out.println("8. Guardar y salir");
        System.out.println("==================");
    }

    public static void crearCuentaNormal() {
        System.out.println("\n--- NUEVA CUENTA AHORROS ---");
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        System.out.print("Titular: ");
        String titular = scanner.nextLine();
        BigDecimal saldoInicial = leerDecimal("Saldo inicial: ");

        try {
            Cuenta cuenta = new Cuenta(numero, titular, saldoInicial);
            cuentas.add(cuenta);
            guardarCuentas();
            System.out.println("Cuenta creada con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void crearCuentaCorriente() {
        System.out.println("\n--- NUEVA CUENTA CORRIENTE ---");
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        BigDecimal saldoInicial = leerDecimal("Saldo inicial: ");
        BigDecimal limite = leerDecimal("Límite descubierto: ");

        try {
            CuentaCorriente cuenta = new CuentaCorriente(numero, saldoInicial, limite);
            cuentas.add(cuenta);
            guardarCuentas();
            System.out.println("Cuenta corriente creada con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarCuentas() {
        System.out.println("\n--- LISTA DE CUENTAS ---");
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
            return;
        }

        for (int i = 0; i < cuentas.size(); i++) {
            Cuenta cuenta = cuentas.get(i);
            System.out.println("\nCuenta #" + (i + 1));
            System.out.println("Número: " + cuenta.getNumero());
            System.out.println("Titular: " + cuenta.getTitular());
            System.out.println("Saldo: $" + cuenta.getSaldo());

            if (cuenta instanceof CuentaCorriente) {
                CuentaCorriente corriente = (CuentaCorriente) cuenta;
                System.out.println("Tipo: Corriente | Límite: $" + corriente.getLimiteDescubierto());
            } else {
                System.out.println("Tipo: Ahorro");
            }
        }
    }

    public static void consultarSaldo() {
        Cuenta cuenta = seleccionarCuenta();
        if (cuenta != null) {
            System.out.println("Cuenta " + cuenta.getNumero() + " tiene un saldo de: $" + cuenta.getSaldo());
        }
    }

    public static void depositar() {
        Cuenta cuenta = seleccionarCuenta();
        if (cuenta == null) return;

        BigDecimal monto = leerDecimal("Monto a depositar: ");
        try {
            cuenta.depositar(monto);
            guardarCuentas();
            System.out.println("Depósito realizado. Nuevo saldo: $" + cuenta.getSaldo());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void debitar() {
        Cuenta cuenta = seleccionarCuenta();
        if (cuenta == null) return;

        BigDecimal monto = leerDecimal("Monto a debitar: ");
        try {
            cuenta.debitar(monto);
            guardarCuentas();
            System.out.println("Débito realizado. Nuevo saldo: $" + cuenta.getSaldo());
        } catch (Cuenta.SaldoInsuficienteException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void realizarPago() {
        System.out.println("\n--- PAGOS ---");
        BigDecimal total = leerDecimal("Valor del pedido: ");

        if (total.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("El valor no puede ser negativo.");
            return;
        }

        CheckoutService.Pedido pedido = new CheckoutService.Pedido(total);
        System.out.println("Método de pago: 1. Tarjeta | 2. Transferencia | 3. Efectivo");
        int opcion = leerEntero("Opción: ");

        Pago metodoPago = null;

        switch (opcion) {
            case 1:
                System.out.print("Número de tarjeta: ");
                String tarjeta = scanner.nextLine();
                metodoPago = new PagoTarjeta(tarjeta);
                break;
            case 2:
                System.out.print("CBU: ");
                String cbu = scanner.nextLine();
                metodoPago = new PagoTransferencia(cbu);
                break;
            case 3:
                metodoPago = new PagoEfectivo();
                break;
            default:
                System.out.println("Opción de pago no válida.");
                return;
        }

        CheckoutService checkout = new CheckoutService();
        checkout.finalizarCompra(pedido, metodoPago);
    }

    public static Cuenta seleccionarCuenta() {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas disponibles.");
            return null;
        }

        System.out.println("\n--- SELECCIONAR CUENTA ---");
        for (int i = 0; i < cuentas.size(); i++) {
            Cuenta cuenta = cuentas.get(i);
            System.out.println((i + 1) + ". " + cuenta.getNumero() + " (" + cuenta.getTitular() + ")");
        }

        int opcion = leerEntero("Seleccione una opción: ");
        if (opcion < 1 || opcion > cuentas.size()) {
            System.out.println("Opción inválida.");
            return null;
        }

        return cuentas.get(opcion - 1);
    }

    public static void guardarCuentas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Cuenta cuenta : cuentas) {
                if (cuenta instanceof CuentaCorriente) {
                    CuentaCorriente corriente = (CuentaCorriente) cuenta;
                    writer.write("CORRIENTE;" + cuenta.getNumero() + ";" + cuenta.getTitular() + ";" + cuenta.getSaldo() + ";" + ((CuentaCorriente) cuenta).getLimiteDescubierto());
                } else {
                    writer.write("NORMAL;" + cuenta.getNumero() + ";" + cuenta.getTitular() + ";" + cuenta.getSaldo());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static void cargarCuentas() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                
                if (datos[0].equals("NORMAL")) {
                    String numero = datos[1];
                    String titular = datos[2];
                    BigDecimal saldo = new BigDecimal(datos[3]);
                    
                    cuentas.add(new Cuenta(numero, titular, saldo));
                } else if (datos[0].equals("CORRIENTE")) {
                    String numero = datos[1];
                    BigDecimal saldo = new BigDecimal(datos[3]);
                    BigDecimal limite = new BigDecimal(datos[4]);
                    
                    cuentas.add(new CuentaCorriente(numero, saldo, limite));
                }
            }
            System.out.println("Cuentas cargadas correctamente.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número entero.");
            }
        }
    }

    public static BigDecimal leerDecimal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número decimal válido.");
            }
        }
    }
}
