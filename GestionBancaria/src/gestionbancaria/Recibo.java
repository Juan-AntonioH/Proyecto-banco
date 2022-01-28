package gestionbancaria;

public class Recibo {

    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public static final String MORADO = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    // Atributos
    private String cif;
    private String nombreEmpresa;
    private double importe;
    private String concepto;
    private String periodicidad;

    // Constructor
    public Recibo(String cif, String nombreEmpresa, double importe, String concepto, String periodicidad) {
        this.cif = cif;
        this.nombreEmpresa = nombreEmpresa;
        this.importe = importe;
        this.concepto = concepto;
        this.periodicidad = periodicidad;
    }
    // Getters y Setters

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }
//    public boolean igual(Recibo recibo) {
//        if (periodicidad.equalsIgnoreCase(recibo.getPeriodicidad())) {
//            return true;
//        }
//        return false;
//    }
//    public boolean setPeriodicidad(String periodicidad) {
//        boolean comprobar = false;
//        if (periodicidad.equals("mensual") || periodicidad.equals("trimestral")
//                || periodicidad.equals("anual")) {
//            this.periodicidad = periodicidad;
//            comprobar = true;
//        }
//        return comprobar;
//    }

    @Override
    public String toString() {
        return "Recibo{" + "cif=" + MORADO + cif + RESET + ", nombreEmpresa="
                + MORADO + nombreEmpresa + RESET + ", importe="
                + MORADO + importe + RESET + ", concepto="
                + MORADO + concepto + RESET + ", periodicidad="
                + MORADO + periodicidad + RESET + '}' + "\n";
    }

}
