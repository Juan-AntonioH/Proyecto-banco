package gestionbancaria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CuentaBancaria {

    public static DecimalFormat formatea = new DecimalFormat("###,###.##");
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public static final String MORADO = "\u001B[35m";
// Atributos

    private final long numCuenta;
    private final Persona titular;
    private double saldo;
    private Set<Persona> autorizados = new HashSet<>();
    private List<Recibo> recibos = new ArrayList<>();
// Constructor

    public CuentaBancaria(long ncuenta, Persona titular) {
        numCuenta = ncuenta;
        this.titular = titular;
        saldo = 0;

    }

// Getters y Setters
    public long getNumCuenta() {
        return numCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Persona getTitular() {
        return titular;
    }

    public Set<Persona> getAutorizados() {
        return autorizados;
    }

    public void setAutorizados(Set<Persona> autorizados) {
        this.autorizados = autorizados;
    }
// Metodos

    public String getSaldoFormateado() {

        String saldoActual = "Saldo: " + VERDE + formatea.format(saldo) + "€" + RESET + "\n";
        return saldoActual;
    }

    public String informacionCuenta() {
        String informacion = "";
        informacion += "Nº cuenta: " + numCuenta + " - " + titular.getNombre();
        if (!autorizados.isEmpty()) {
            informacion += "\nPersonas autorizadas: " + autorizados;
        }
        informacion += "\n" + getSaldoFormateado();
        return informacion;
    }

    public int ingresar(double cantidad) {
        if (cantidad > 0 && cantidad < 3000) {
            saldo += cantidad;
            return 0;

        } else if (cantidad >= 3000) {
            saldo += cantidad;
            return 1;
        }
        return -1;
    }

    public double sacar(double cantidad) {
        if (cantidad > saldo + 50) {
            return saldo;
        } else if (saldo - cantidad > (-50) && saldo - cantidad <= (-1)) {
            saldo -= cantidad;
        } else {
            saldo -= cantidad;
        }
        return saldo;
    }

    public String domiciliar(String cif, String nombreEmpresa, double importe, String concepto, String periocidad) {
        boolean comprobante = true;
        String informacion = "No se pudo crear el recibo por los siguientes motivos:";
        if (cif.isBlank()) {
            informacion += "\nEl campo cif no tiene contenido.";
            comprobante = false;
        }
        if (nombreEmpresa.isBlank()) {
            informacion += "\nEl campo nombreEmpresa no tiene contenido.";
            comprobante = false;
        }
        if (importe == 0) {
            informacion += "\nNo has introducido ningún importe.";
            comprobante = false;
        }
        if (concepto.isBlank()) {
            informacion += "\nEl concepto esta vacio.";
            comprobante = false;
        }
        if (!(periocidad.equals("mensual") || periocidad.equals("trimestral")
                || periocidad.equals("anual"))) {
            informacion += "\nNo has introducido una periocidad correcta";
            comprobante = false;
        }
        if (comprobante) {
            Recibo recibo = new Recibo(cif, nombreEmpresa, importe, concepto, periocidad);
            recibos.add(recibo);
            informacion = "\nEl recibo fue creado correctamente:\n" + recibo.toString();
        }
        return informacion;

    }

    public Set<Recibo> listadoRecibosDomiciliados(String periodicidad) {
        Set<Recibo> lista = new HashSet<>();
        for (Recibo recibo : recibos) {
            if (recibo.getPeriodicidad().equalsIgnoreCase(periodicidad)) {
                lista.add(recibo);
            }
        }
        return lista;
    }

    public boolean autorizar(Persona autorizado) {
        return autorizados.add(autorizado);
    }

    public String verAutorizados() {
        return "Personas autorizadas: " + autorizados;
    }

    @Override
    public String toString() {
        return "{numero de cuenta:" + numCuenta + ", titular:" + titular.getNombre() + '}';
    }

}
