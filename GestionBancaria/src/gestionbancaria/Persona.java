package gestionbancaria;

public class Persona {

    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public static final String MORADO = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
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
        return ROJO + nombre + AZUL + " (" + ROJO + VERDE + nif + AZUL + ")" + RESET;
    }
}
