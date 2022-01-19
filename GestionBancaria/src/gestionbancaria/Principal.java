package gestionbancaria;

public class Principal {

    public static void main(String[] args) {
        Persona titular = new Persona("33444666S", "Juan Sánchez");
        CuentaBancaria cuenta1 = new CuentaBancaria(12235443, titular);

        Persona persona1 = new Persona("19222333Y", "Ana Sanchez");
        Persona persona2 = new Persona("22888444D", "Laura Sanchez");
        Persona persona3 = new Persona("22888444D", "laura sanchez");
        System.out.println(persona1.toString());
        System.out.println(persona2.toString());
        System.out.println(persona3.toString());

        if (cuenta1.autorizar(persona1)) {
            System.out.println(persona1.getNombre() + " ha sido autorizada");
        }
        if (cuenta1.autorizar(persona2)) {
            System.out.println(persona2.getNombre() + " ha sido autorizada");
        }
        if (cuenta1.autorizar(persona3)) {
            System.out.println(persona3.getNombre() + " ha sido autorizada");
        }
        if (persona2.igual(persona3.getNif())) {
            System.out.println("persona2 y persona3 tienen el mismo nif");
        }
        System.out.println("Personas autorizadas en la cuenta:"
                + cuenta1.verAutorizados());
    }

}
