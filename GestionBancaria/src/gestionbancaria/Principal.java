package gestionbancaria;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static Scanner datos = new Scanner(System.in);
    public static Scanner sc2 = new Scanner(System.in);
    public static Scanner sc = new Scanner(System.in);
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public static final String MORADO = "\u001B[35m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean bucle = true;
        Scanner longo = new Scanner(System.in);
        Banco b1 = new Banco("Banco1");
        System.out.println("Introduce un numero de cuenta");
        long localizarCuenta = longo.nextLong();
        CuentaBancaria cuenta = b1.localicarCC(localizarCuenta);
        if (b1.localicarCC(localizarCuenta) == null) {
            System.out.println("Mete un numero correcto");
        } else {
            do {
                String respuesta = menu();
                switch (respuesta) {
                    case "1" -> //INGRESAR DINERO
                        System.out.println(menuIngresar(cuenta));
                    case "2" ->
                        menuSacar(cuenta);
                    case "3" ->
                        verInformacion(cuenta);
                    case "4" ->
                        domiciliarRecibo(cuenta);
                    case "5" ->
                        recibosPorPeriodicidad(cuenta);

                    case "0" -> {
                        System.out.println("Gracias por usar nuestra aplicación");
                        bucle = false;  //SALIMOS DEL BUCLE
                    }
                }
            } while (bucle);
        }
    }

    public static String menu() {
        String respuesta;

        System.out.println("__________________________________");
        System.out.println("GESTION DE CUENTA BANCARIA        ");
        System.out.println("1-Ingresar dinero.                ");
        System.out.println("2-Sacar dinero.                   ");
        System.out.println("3-Informacion de cuenta.          ");
        System.out.println("4-Domiciliar recibo.              ");
        System.out.println("5-Mostrar recibos por periodicidad");
        System.out.println("0-Salir.                          ");
        System.out.println("__________________________________");
        respuesta = datos.nextLine();
        return respuesta;
    }

    public static String menuIngresar(CuentaBancaria cuenta) {
        String rojo = "\u001B[31m";
        String negro = "\u001B[30m";
        System.out.println("¿Cuánto dinero desea ingresar?");
        double cantidad = sc2.nextDouble();
        int count = cuenta.ingresar(cantidad);
        String mensaje = "";
        if (count == 0) {
            mensaje = "Se ha ingresado " + cantidad + " €" + "\n Saldo: " + cuenta.getSaldoFormateado();
        }
        if (count == 1) {
            mensaje = "Se ha ingresado " + cantidad + " €" + "\n Saldo: " + cuenta.getSaldoFormateado() + rojo + "\n AVISO: NOTIFICAR A HACIENDA" + negro;
        }
        if (count == -1) {
            mensaje = rojo + "No se ha podido hacer el ingreso, vuelve a intentarlo o introduce un valor válido." + negro;
        }
        return mensaje;
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
                System.out.println("\n" + "Saldo: " + cuenta.getSaldoFormateado());
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

    private static void domiciliarRecibo(CuentaBancaria cuenta) {
        double importe = 0;
        System.out.println("Indica el cif del recibo");
        String cif = sc.nextLine();
        System.out.println("Indica el nombre de la empresa");
        String nombreEmpresa = sc.nextLine();
        for (int i = 0; i < 1;) {
            i = 1;
            try {
                System.out.println("¿Cual es el importe del recibo?");
                importe = Double.parseDouble(sc.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un caracter");
                sc = new Scanner(System.in);
                i = 0;
            } catch (Exception e) {
                System.out.println("Ocurrio un error inesperado");
                sc = new Scanner(System.in);
                i = 0;
            }
        }
        System.out.println("¿Cual es el motivo de pagar tanto?");
        String concepto = sc.nextLine();
        System.out.println("¿Cual es la periocidad del recibo? 'mensual, trimestral o anual' ");
        String periocidad = sc.nextLine();
        System.out.println(cuenta.domiciliar(cif, nombreEmpresa, importe, concepto, periocidad));
    }

    public static void verInformacion(CuentaBancaria cuenta) {
        System.out.println("Información cuenta: ");
        System.out.println(cuenta.informacionCuenta());
    }

    private static void recibosPorPeriodicidad(CuentaBancaria cuenta) {
        String periodicidad;
        System.out.println("¿Indica la periocidad de los recibos que quieres recibir información"
                + " 'mensual, trimestral, anual.");
        periodicidad = sc.nextLine();
        System.out.println("Los recibos " + periodicidad + "es son:\n" + cuenta.listadoRecibosDomiciliados(periodicidad));

    }

}
