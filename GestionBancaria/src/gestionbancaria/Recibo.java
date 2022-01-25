package gestionbancaria;

public class Recibo {

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
        return "Recibo{" + "cif=" + cif + ", nombreEmpresa=" + nombreEmpresa + ", importe=" + importe + ", concepto=" + concepto + ", periodicidad=" + periodicidad + '}';
    }

}
