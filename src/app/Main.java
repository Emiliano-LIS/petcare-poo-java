package app;

import java.util.Scanner;

import modelo.clinica.Clinica;

/**
 * Menú de consola de PetCare.
 */
public class Main {
    private Clinica clinica;
    private final Scanner entrada;

    /**
     * Crea el menú para la clínica.
     * @param clinica clínica a usar
     */
    public Main(Clinica clinica) {
        this.clinica = clinica;
        this.entrada = new Scanner(System.in);
    }

    /**
     * Devuelve la clínica.
     * @return clínica actual
     */
    public Clinica getClinica() {
        return clinica;
    }

    /**
     * Cambia la clínica.
     * @param clinica nueva clínica
     */
    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    /**
     * Repite el menú hasta salir.
     */
    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            switch (opcion) {
                case 1, 2, 3, 4, 5, 6, 7, 8, 9 ->
                    System.out.println("wip");
                case 10 -> System.out.println("Salir.");
                default -> System.out.println("No existe la opción, elige del 1-10");
            }
        } while (opcion != 10);
    }

    /**
     * Inicia PetCare.
     * @param argumentos argumentos de consola
     */
    public static void main(String[] argumentos) {
        Clinica clinica = new Clinica("PetCare");
        Main aplicacion = new Main(clinica);
        aplicacion.iniciar();
    }

    /**
     * Muestra las opciones del menú.
     */
    private void mostrarMenu() {
        System.out.println("\n--- PetCare ---");
        System.out.println("1. Registrar dueño y mascota (perro, gato o ave).");
        System.out.println("2. Registrar veterinario y asignarlo a la clínica.");
        System.out.println("3. Agendar una cita.");
        System.out.println("4. Atender o cancelar una cita.");
        System.out.println("5. Generar el comprobante de una cita atendida.");
        System.out.println("6. Consultar el historial clínico de una mascota.");
        System.out.println("7. Listar todas las mascotas y su sonido (polimorfismo).");
        System.out.println("8. Ver el total cobrado por veterinario.");
        System.out.println("9. Dar de baja una mascota.");
        System.out.println("10. Salir.");
        System.out.print("Elige una opción: ");
    }

    /**
     * Lee la opción sin fallar si se escribe texto.
     * @return opción leída, 0 si no es un entero o 10 si termina la entrada
     */
    private int leerOpcion() {
        if (!entrada.hasNextLine()) {
            return 10;
        }
        String textoOpcion = entrada.nextLine().trim();
        try {
            return Integer.parseInt(textoOpcion);
        } catch (NumberFormatException excepcion) {
            return 0;
        }
    }
}
