package gestionbancaria;

public class Persona {
// Atributos

    private final String nif;
    private String nombre;
// Constructor

    public Persona(String nif, String nombre) {
        this.nif = nif;
        this.nombre = nombre;
    }
// Getters y Setters

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
// Metodos

    public boolean igual(Persona person) {
        if (nif.equalsIgnoreCase(person.getNif())) {
            return true;
        }
        return false;
    }

    public boolean igual(String person) {
        if (nif.equalsIgnoreCase(person)) {
            return true;
        }
        return false;
    }

    @Override  //REDEFINE EL MÉTODO
    public String toString() {
        return nombre + " (" + nif + ")";
    }
}
