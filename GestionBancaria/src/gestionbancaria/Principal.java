package gestionbancaria;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static DecimalFormat formatea = new DecimalFormat("###,###.##");
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
                case "2": //SACAR DINERO
                    menuSacar(cuenta);
                    break;
                case "3": //INFORMACIÓN CUENTA
                    verInformacion(cuenta);
                    break;
                case "4": // DOMILICIAR RECIBO
                    domiciliarRecibo(cuenta);
                    break;
                case "5": // MOSTRAR RECIBOS POR PERIOCIDAD
                    recibosPorPeriocidad(cuenta);
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
        System.out.println("4-Domiliciar recibo.");
        System.out.println("5-Listar recibos según periodicidad");
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
                        System.out.println("Se ha ingresado: " + formatea.format(cantidad) + "€");
                        System.out.println(MORADO + "AVISO: NOTIFICAR A HACIENDA" + RESET);
                        break;
                    case 0:
                        System.out.println("Se ha ingresado: " + formatea.format(cantidad) + "€");
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

    private static void domiciliarRecibo(CuentaBancaria cuenta) {
        System.out.println("Indica el cif del recibo");
        String cif = sc.nextLine();
        System.out.println("Indica el nombre de la empresa");
        String nombreEmpresa = sc.nextLine();
        System.out.println("¿Cual es el importe del recibo?");
        double importe = Double.parseDouble(sc.nextLine());
        System.out.println("¿Cual es el motivo de pagar tanto?");
        String concepto = sc.nextLine();
        System.out.println("¿Cual es la periocidad del recibo? 'mensual, trimestral o anual' ");
        String periocidad = sc.nextLine();
        System.out.println("El recibo fue creado\n"
                + cuenta.domiciliar(cif, nombreEmpresa, importe, concepto, periocidad));
    }

    private static void recibosPorPeriocidad(CuentaBancaria cuenta) {
//        boolean correcto = true;
        String periodicidad;
//        do {
            System.out.println("¿Indica la periocidad de los recibos que quieres recibir información"
                    + " 'mensual, trimestral, anual.");
            periodicidad = sc.nextLine();
//            if ((periodicidad == "mensual" || periodicidad== "trimestral"
//                    || periodicidad=="anual")) {
                System.out.println(cuenta.listadoRecibosDomiciliados(periodicidad));
//                correcto = false;
//            }
//        } while (correcto);

    }
}
