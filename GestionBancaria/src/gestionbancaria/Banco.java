package gestionbancaria;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Banco {

    // Atributos
    private String nombreBanco = "ING Mislata";
    private Map<Long, CuentaBancaria> cuentas = new HashMap<>();

    // Constructor
    public Banco() {
        cargaDatosIniciales();
    }

    //Getters y Setters
    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    // Metodos
    private void cargaDatosIniciales() {
        // personas y titulares
        Persona persona1 = new Persona("19222333Y", "Ana Sanchez");
        Persona persona2 = new Persona("22888444D", "Laura Sanchez");
        Persona persona3 = new Persona("23885447D", "Pamelo Joe");
        Persona titular1 = new Persona("33444666S", "Juan Sánchez");
        Persona titular2 = new Persona("52352432L", "Pepe Luis");
        Persona titular3 = new Persona("89752304P", "Otro Mas");
        // cuentas
        CuentaBancaria cuenta1 = new CuentaBancaria(123456789, titular1);
        CuentaBancaria cuenta2 = new CuentaBancaria(987654321, titular2);
        CuentaBancaria cuenta3 = new CuentaBancaria(112233445, titular3);
        // autorizados
        cuenta1.autorizar(persona1);
        cuenta1.autorizar(persona2);
        cuenta1.autorizar(persona3);
        cuenta2.autorizar(titular1);
        //saldo
        cuenta1.ingresar(300);
        cuenta2.ingresar(3500);
        cuenta3.sacar(30);
        // añadir cuentas
        cuentas.put(cuenta1.getNumCuenta(), cuenta1);
        cuentas.put(cuenta2.getNumCuenta(), cuenta2);
        cuentas.put(cuenta3.getNumCuenta(), cuenta3);
        // domiciliar
        cuenta1.domiciliar("123-ABC", "Ladrones", 350, "Todo lo tuyo me pertenece", "mensual");
        cuenta1.domiciliar("123-ABCD", "Ladrones 2.0", 450, "Todo lo tuyo me pertenece", "mensual");
        cuenta1.domiciliar("123-DDA", "Ladrones 2.0", 1500, "Todo lo tuyo me pertenece en mayor medida", "trimestral");
        cuenta2.domiciliar("456-XPI", "Hª Navarro", 120, "Coste de la obra", "mensual");
        cuenta3.domiciliar("789-QWE", "COFIDIS", 3520, "Comisiones", "anual");
    }

    public CuentaBancaria localicarCC(long nCuenta) {
        if (cuentas.containsKey(nCuenta)) {
            return cuentas.get(nCuenta);
        } else {
            return null;
        }
    }
}
