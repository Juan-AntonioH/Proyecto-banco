package gestionbancaria;

import java.text.DecimalFormat;
import java.util.HashSet;
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

    public boolean autorizar(Persona autorizado) {
        return autorizados.add(autorizado);
    }

    public String verAutorizados() {
        return "Personas autorizadas: " + autorizados;
    }
}
