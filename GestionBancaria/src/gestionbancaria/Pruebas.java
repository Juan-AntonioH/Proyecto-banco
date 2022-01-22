package gestionbancaria;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Pruebas {

    static Scanner sc = new Scanner(System.in);
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public static final String MORADO = "\u001B[35m";

    public static void main(String[] args) {
        //Pruebas
        Persona titular = new Persona("33444666S", "Juan Sánchez");
        CuentaBancaria cuenta = new CuentaBancaria(12235443, titular);

        Persona persona1 = new Persona("19222333Y", "Ana Sanchez");
        Persona persona2 = new Persona("22888444D", "Laura Sanchez");
        Persona persona3 = new Persona("22888444D", "Pamelo Joe");

        if (cuenta.autorizar(persona1)) {
            System.out.println(persona1.getNombre() + " ha sido autorizada");
        }
        if (cuenta.autorizar(persona2)) {
            System.out.println(persona2.getNombre() + " ha sido autorizada");
        }
        if (persona2.igual(persona3)) {
            System.out.println(persona2.getNombre() + " y "
                    + persona3.getNombre() + " tienen el mismo nif");
        } else {
            cuenta.autorizar(persona3);
        }
        System.out.println(cuenta.verAutorizados());
        do {
            String respuesta = menu(); //Llamar al menu
            switch (respuesta) {
                case "1": //INGRESAR DINERO
                    menuIngresar(cuenta);
                    break;
                case "2": //SACAR DINERO
                    menuSacar(cuenta);
                    break;
                case "3": //INFORMACIÓN CUENTA
                    verInformacion(cuenta);
                    break;
                case "0":
                    System.out.println("Gracias por usar nuestra aplicación");
                    return;  //SALIR DEL BUCLE
                default:
                    System.out.println("Debe seleccionar un numero correcto");
            }
        } while (true);
    }

    public static String menu() { // Muestra las opciones por pantalla
        String respuesta;
        System.out.println(AZUL + "GESTION DE CUENTA BANCARIA" + RESET);
        System.out.println("1-Ingresar dinero.");
        System.out.println("2-Sacar dinero.");
        System.out.println("3-Informacion cuenta.");
        System.out.println("0-Salir\n");
        respuesta = sc.nextLine();
        return respuesta;
    }

    public static void menuIngresar(CuentaBancaria cuenta) {
        System.out.println("¿Cuánto dinero desea ingresar?");
        do {
            try {
                double cantidad = Double.parseDouble(sc.nextLine());
                switch (cuenta.ingresar(cantidad)) {
                    case 1:
                        System.out.printf("Se ha ingresado: %.2f€\n", cantidad);
                        System.out.println(MORADO + "AVISO: NOTIFICAR A HACIENDA" + RESET);
                        break;
                    case 0:
                        System.out.printf("Se ha ingresado: %.2f€\n", cantidad);
                        break;
                    default:
                        System.out.println("Debes introducir una cantidad correcta");
                }
                System.out.println("\n" + cuenta.getSaldoFormateado());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un caracter");
                sc = new Scanner(System.in);
            } catch (Exception e) {
                System.out.println("Se ha producido un error inesperado.");
                sc = new Scanner(System.in);
                break;
            }
        } while (true);
    }

    public static void menuSacar(CuentaBancaria cuenta) {
        do {
            try {
                System.out.println("¿Cuánto dinero desea sacar?");
                double cantidad = Double.parseDouble(sc.nextLine());
                double saldo = cuenta.getSaldo();
                double dinero = cuenta.sacar(cantidad);
                System.out.println(dinero);
                if (cantidad <= 0) {
                    System.out.println("Has introducido una cantidad incorrecta");
                } else if (dinero == saldo) {
                    System.out.println(ROJO + "No hay suficiente dinero en la cuenta para sacar " + cantidad + RESET);
                } else if (dinero < 0) {
                    System.out.printf("Se ha retirado: %.2f€\n", cantidad);
                    System.out.println(ROJO + "“AVISO: SALDO NEGATIVO”" + RESET);
                } else {
                    System.out.printf("Se ha retirado: %.2f€\n", cantidad);
                }
                System.out.println("\n" + cuenta.getSaldoFormateado());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un caracter");
                sc = new Scanner(System.in);
            } catch (Exception e) {
                System.out.println("Se ha producido un error inesperado.");
                sc = new Scanner(System.in);
                break;
            }
        } while (true);
    }

    public static void verInformacion(CuentaBancaria cuenta) {
        System.out.println("Aqui tiene la información solicitada");
        System.out.println(cuenta.informacionCuenta());
    }
}
