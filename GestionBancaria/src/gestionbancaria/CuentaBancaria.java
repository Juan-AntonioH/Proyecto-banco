package gestionbancaria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CuentaBancaria {
    static DecimalFormat formatea = new DecimalFormat("###,###.##");
    private final long numCuenta;
    private final Persona titular;
    private Set<Persona> autorizados = new HashSet<>();
    private double saldo = 0;
    private Recibos domicilio;
     private List<Recibos> recibos = new ArrayList<>();

    public Recibos getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Recibos domicilio) {
        this.domicilio = domicilio;
    }
    

    public CuentaBancaria(long ncuenta, Persona titular) {
        numCuenta = ncuenta;
        this.titular = titular;
    }

    public long getNumCuenta() {
        return numCuenta;
    }

    public Persona getTitular() {
        return titular;
    }

    public Set<Persona> getAutorizados() {
        return autorizados;
    }

    public double getSaldo() {
        return saldo;
    }


    public String getSaldoFormateado() {
        String formatoSaldo;
        formatoSaldo = formatea.format(saldo) + " €";
        return formatoSaldo;
    }

    public int ingresar(double cantidad) {
        int maximo = 3000;
        int mensaje = -1;
        if (cantidad > 0 && cantidad < 3000) {
            saldo += cantidad;
            return mensaje = 0;
        }
        if (cantidad >= maximo) {
            saldo += cantidad;
            return mensaje = 1;
        }
        return mensaje;
    }

    public void setAutorizados(Set<Persona> autorizados) {
        this.autorizados = autorizados;
    }

    public boolean autorizar(Persona autorizado) {
        return autorizados.add(autorizado);
    }

    public String verAutorizados() {
        String personasAutorizadas = "";
        for (Persona autorizado : autorizados) {
            personasAutorizadas += autorizado + " ";
        }
        return personasAutorizadas;
    }
    public double sacar(double cantidad){
        String fraseInc;
        if(cantidad>0){
            saldo-=cantidad;
        }
        return saldo;
    }
    public String informacionCuenta(){
        String informacion="";
        informacion+= "Nº cuenta: "+numCuenta+" - "+titular.getNombre();
        if(!autorizados.isEmpty()){
            informacion+= "\nPersonas autorizadas: "+autorizados;
        }
        informacion+= "\n Saldo: " +getSaldoFormateado();
        return informacion;
    }
    public String domiciliar(String cif, String nombreEmpresa, double importe, String concepto, String periodicidad ){
        String respuestaNegativa = "No se ha domiciliado por: ";
        String respuestaPositiva = "Domiciliado correctamente.";
        if(cif.isEmpty()){
            return respuestaNegativa+"el cif.";
        }
        if(nombreEmpresa.isEmpty()){
            return respuestaNegativa+ "el nombre de la empresa.";
        }
        if(importe==0){
            return respuestaNegativa+ "el importe.";
        }
        if(concepto.isEmpty()){
            return respuestaNegativa+ "el concepto.";
        }
        if(periodicidad.equals("mensual")||periodicidad.equals("trimestral")|| periodicidad.equals("anual")){
            return respuestaPositiva;
        }
        if(periodicidad!=("mensual")||periodicidad!=("trimestral")|| periodicidad!=("anual")){
            return respuestaNegativa+"por la periodicidad.";
        }
        else{
            
            return respuestaPositiva;
        }
    }
    
    
    public Set<Recibos> listadoRecibosDomiciliados(String periodicidad) {
        Set<Recibos> lista = new HashSet<>();
        for (Recibos recibo : recibos) {
            if (recibo.getPeriodicidad().equalsIgnoreCase(periodicidad)) {
                lista.add(recibo);
            }
        }
        return lista;
    }
}
