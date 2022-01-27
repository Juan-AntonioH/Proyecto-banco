package gestionbancaria;

public class Persona {

    private final String nif;
    private String nombre;

    public Persona(String nif, String nombre) {
        this.nif = nif;
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean igual(Persona person) {
        if (nif.equals(person)) {
            return true;
        }
        return false;

    }

    public boolean igual(String nif) {
        if (nif.equals(nif)) {
            return true;
        }

        return false;

    }

    @Override
    public String toString() {
        return nombre + " (" + nif + ")";
    }
}
