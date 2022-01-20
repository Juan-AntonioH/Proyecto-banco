package gestionbancaria;

import java.util.HashSet;
import java.util.Set;

public class CuentaBancaria {
// Atributos

    private final long numCuenta;
    private final Persona titular;
    private Set<Persona> autorizados = new HashSet<>();
// Constructor

    public CuentaBancaria(long ncuenta, Persona titular) {
        numCuenta = ncuenta;
        this.titular = titular;
    }

// Getters y Setters
    public long getNumCuenta() {
        return numCuenta;
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

    public boolean autorizar(Persona autorizado) {
        return autorizados.add(autorizado);
    }

    public String verAutorizados() {
        return "Personas autorizadas: " + autorizados;
    }
}
