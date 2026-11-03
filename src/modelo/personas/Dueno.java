package modelo.personas;

import java.util.ArrayList;
import java.util.List;

import modelo.mascotas.Mascota;

/**
 * Dueño de una o varias mascotas.
 */
public class Dueno extends Persona {
    private String direccion;
    private final List<Mascota> mascotas;

    /**
     * Crea un dueño sin mascotas registradas.
     * @param nombre nombre del dueño
     * @param apellido apellido del dueño
     * @param telefono teléfono de contacto
     * @param correo correo electrónico
     * @param direccion domicilio del dueño
     */
    public Dueno(String nombre, String apellido, String telefono, String correo,
            String direccion) {
        super(nombre, apellido, telefono, correo);
        this.direccion = validarTexto(direccion, "La dirección");
        this.mascotas = new ArrayList<>();
    }

    /**
     * Devuelve la dirección.
     * @return dirección del dueño
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Cambia la dirección.
     * @param direccion nueva dirección
     */
    public void setDireccion(String direccion) {
        this.direccion = validarTexto(direccion, "La dirección");
    }

    /**
     * Devuelve las mascotas.
     * @return copia no modificable de las mascotas del dueño
     */
    public List<Mascota> getMascotas() {
        return List.copyOf(mascotas);
    }

    /**
     * Agrega una mascota al dueño.
     * @param mascota mascota a agregar
     */
    public void agregarMascota(Mascota mascota) {
        if (mascota == null || mascota.getDueno() != this) {
            System.out.println("La mascota debe pertenecer a este dueño.");
            return;
        }
        if (mascotas.contains(mascota)) {
            return;
        }
        System.out.println("Se ha agregado la mascota " + mascota.getNombre()
                + " a la lista de mascota");
        this.mascotas.add(mascota);

    }

    /**
     * Retira una mascota de la lista del dueño durante su baja.
     * @param mascota mascota a retirar
     */
    public void eliminarMascota(Mascota mascota) {
        if (mascota == null) {
            System.out.println("La mascota es obligatoria.");
            return;
        }
        mascotas.removeIf(registrada -> registrada == mascota);
    }

    /**
     * Lista las mascotas del dueño.
     * en consola.
     */
    public void listarMascotas() {
        for (Mascota mascota : mascotas) {
            System.out.println("Nombre de la Mascota: " + mascota.getNombre());
            System.out.println("Edad de la Mascota: " + mascota.getEdad());
            System.out.println("Peso de la Mascota: " + mascota.getPeso());
            System.out.println(" ");
        }
    }

    /**
     * Describe el rol del dueño.
     * @return descripción de su rol
     */
    @Override
    public String describirRol() {
        return "El dueño es responsable de una o más mascotas.";
    }

    /**
     * Resume los datos del dueño.
     * @return datos del dueño en texto
     */
    @Override
    public String toString() {
        return "Dueño | " + super.toString() + " | Dirección: " + direccion
                + " | Mascotas: " + mascotas.size();
    }
}
