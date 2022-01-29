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
    public static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        Banco cuentaPersonal = new Banco();
        boolean comprobante;
        CuentaBancaria cuenta;
        do {
            comprobante = true;
            String peticion = menuBanco(); //Entrar al menu principal
            switch (peticion) {
                case "1": //Solicitar cuenta a la que acceder
                    cuenta = localizaCC(cuentaPersonal);
                    if (cuenta == null) {
                        System.out.println(ROJO + "Has introducido un numero de cuenta erroneo.\n" + RESET);
                        break;
                    }
                    System.out.println("\nHas podido conectar correctamente a tu cuenta: " + cuenta + ".");
                    do {
                        String respuesta = menu(cuenta); //Llamar al menu de la aplicacion
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
                                comprobante = false; //SALIR DEL BUCLE
                                break;
                            default:
                                System.out.println("Debe seleccionar un numero correcto");
                        }
                    } while (comprobante);
                case "0": //Condición para continuar bucle principal en caso de más opciones
                    if (!peticion.equalsIgnoreCase("0")) {
                        comprobante = true;
                        break;
                    } else {
                        System.out.println("Gracias por usar ING Mislata");
                        return;  //SALIR DEL BUCLE 
                    }

                default:
                    System.out.println("Debe seleccionar un numero correcto");
            }
        } while (comprobante);
    }

    public static String menuBanco() { // Muestra las opciones por pantalla
        String respuesta;
        System.out.println(CYAN + "Bienvenido a ING Mislata" + RESET);
        System.out.println("1-Elegir cuenta con la que desees operar");
        System.out.println("0-Salir\n");
        respuesta = sc.nextLine();
        return respuesta;
    }

    public static String menu(CuentaBancaria cuenta) { // Muestra las opciones por pantalla
        String respuesta;
        System.out.println(AZUL + "GESTION DE CUENTA BANCARIA" + RESET);
        System.out.println(VERDE + "-------------------------------------|" + RESET);
        System.out.println(MORADO + "Cuenta actual: " + "Nº " + ROJO + cuenta.getNumCuenta() + AZUL + " - " + MORADO + "Titular: " + ROJO + cuenta.getTitular().getNombre() + RESET);
        System.out.println(VERDE + "-------------------------------------|" + RESET);
        System.out.println("1-Ingresar dinero.                   " + VERDE + "|" + RESET);
        System.out.println("2-Sacar dinero.                      " + VERDE + "|" + RESET);
        System.out.println("3-Informacion cuenta.                " + VERDE + "|" + RESET);
        System.out.println("4-Domiliciar recibo.                 " + VERDE + "|" + RESET);
        System.out.println("5-Listar recibos según periodicidad  " + VERDE + "|" + RESET);
        System.out.println("0-Salir/Cambiar cuenta               " + VERDE + "|" + RESET);
        System.out.println(VERDE + "-------------------------------------|" + RESET);
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
                        System.out.println("Se ha ingresado: " + VERDE + formatea.format(cantidad) + RESET + "€");
                        System.out.println(MORADO + "AVISO: NOTIFICAR A HACIENDA" + RESET);
                        break;
                    case 0:
                        System.out.println("Se ha ingresado: " + VERDE + formatea.format(cantidad) + RESET + "€");
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
                    System.out.println("Se ha retirado: " + ROJO + formatea.format(cantidad) + RESET + "€");
                    System.out.println(ROJO + "“AVISO: SALDO NEGATIVO”" + RESET);
                } else {
                    System.out.println("Se ha retirado: " + ROJO + formatea.format(cantidad) + RESET + "€");
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

    public static void domiciliarRecibo(CuentaBancaria cuenta) {
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

    public static void recibosPorPeriocidad(CuentaBancaria cuenta) {
        String periodicidad;
        System.out.println("¿Indica la periocidad de los recibos que quieres recibir información"
                + " 'mensual, trimestral, anual'.");
        periodicidad = sc.nextLine();
        if (!cuenta.listadoRecibosDomiciliados(periodicidad).isEmpty()) {
            System.out.println("Los recibos " + ROJO + periodicidad + "es" + RESET + " son:\n");
            for (Recibo listadoRecibosDomiciliado : cuenta.listadoRecibosDomiciliados(periodicidad)) {
                System.out.println(listadoRecibosDomiciliado);
            }
        } else if (cuenta.listadoRecibosDomiciliados(periodicidad).isEmpty()) {
            System.out.println("No tienes recibos con esa periodicidad");
        }
    }

    public static CuentaBancaria localizaCC(Banco cuenta) {
        do {
            try {
                System.out.println("Indique el número de cuenta:\n");
                long numero = Long.parseLong(sc.nextLine());
                CuentaBancaria numeroCuenta = cuenta.localicarCC(numero);
                return numeroCuenta;
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un caracter");
                sc = new Scanner(System.in);
            } catch (Exception e) {
                System.out.println("Se ha producido un error inesperado.");
                sc = new Scanner(System.in);
                break;
            }
        } while (true);
        return null;
    }
}
