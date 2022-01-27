
package gestionbancaria;

import java.util.HashMap;
import java.util.Map;

public class Banco {
    private  String nombreBanco;
    private Map<Long, CuentaBancaria> cuentas = new HashMap<>();
    
//Constructor
    public Banco (String nombreBanco){
        cargaDatosIniciales();
    }
//Getters y Setters

    public Map<Long,CuentaBancaria> getCuentas() {
        return cuentas;
    }

    public void setCuentas(Map<Long,CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }
    private void cargaDatosIniciales(){
        Persona persona1 = new Persona("47583321J", "Alvaro");
        Persona persona2 = new Persona("46576432H", "Pedro");
        Persona persona3 = new Persona("56785432G", "Luis");
        CuentaBancaria cuenta1 = new CuentaBancaria(1234, persona1);
        CuentaBancaria cuenta2 = new CuentaBancaria(2345, persona2);
        CuentaBancaria cuenta3 = new CuentaBancaria(3456, persona3);
        cuenta1.autorizar(persona3);
        cuenta1.autorizar(persona2);
        cuenta2.autorizar(persona3);
        cuenta3.autorizar(persona1);
        cuentas.put(cuenta1.getNumCuenta(), cuenta1);
        cuentas.put(cuenta2.getNumCuenta(), cuenta2);
        cuentas.put(cuenta3.getNumCuenta(), cuenta3);
        cuenta1.ingresar(200);
        cuenta2.ingresar(200);
        cuenta3.ingresar(200);
    }
    public CuentaBancaria localicarCC (long numCuenta){
        CuentaBancaria cuenta=null;
        for (CuentaBancaria recorrerCuenta : cuentas.values()){
            long numeroCuenta = recorrerCuenta.getNumCuenta();
            if(numeroCuenta==numCuenta){
                cuenta = recorrerCuenta;
            }
        }
        return cuenta;
    }
}
