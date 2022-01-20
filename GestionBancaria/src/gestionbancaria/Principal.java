package gestionbancaria;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    static Scanner sc = new Scanner(System.in);
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public static final String MORADO = "\u001B[35m";

    public static void main(String[] args) {

        Persona titular = new Persona("12345678S", "Pepe Luis");
        CuentaBancaria cuenta = new CuentaBancaria(12235443, titular);
        do {
            String respuesta = menu(); //Llamar al menu
            switch (respuesta) {
                case "1": //INGRESAR DINERO
                    menuIngresar(cuenta);
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
}
