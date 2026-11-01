package modelo.personas;

import java.util.ArrayList;
import java.util.List;

import modelo.mascotas.Mascota;

/**
 * Dueño de una o varias mascotas.
 */
public class Dueno extends Persona {
    private String direccion;
    private List<Mascota> mascotas;

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
        this.direccion = direccion;
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
        this.direccion = direccion;
    }

    /**
     * Devuelve las mascotas.
     * @return mascotas del dueño
     */
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    /**
     * Agrega una mascota al dueño.
     * @param mascota mascota a agregar
     */
    public void agregarMascota(Mascota mascota) {
    }

    /**
     * Lista las mascotas del dueño.
     * @return mascotas del dueño
     */
    public List<Mascota> listarMascotas() {
        return null;
    }

    /**
     * Describe el rol del dueño.
     * @return descripción de su rol
     */
    @Override
    public String describirRol() {
        return null;
    }

    /**
     * Resume los datos del dueño.
     * @return datos del dueño en texto
     */
    @Override
    public String toString() {
        return null;
    }
}
