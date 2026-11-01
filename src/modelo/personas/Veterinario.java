package modelo.personas;

/**
 * Veterinario que puede trabajar en distintas clínicas.
 *
 * Regla 2: no puede tener dos citas programadas a la misma hora.
 * Regla 6: sus cobros deben poder consultarse.
 */
public class Veterinario extends Persona {
    private String cedulaProfesional;
    private String especialidad;

    /**
     * Crea un veterinario con su cédula y especialidad.
     * @param nombre nombre del veterinario
     * @param apellido apellido del veterinario
     * @param telefono teléfono de contacto
     * @param correo correo electrónico
     * @param cedulaProfesional cédula profesional
     * @param especialidad área de especialidad
     */
    public Veterinario(String nombre, String apellido, String telefono, String correo,
            String cedulaProfesional, String especialidad) {
        super(nombre, apellido, telefono, correo);
        this.cedulaProfesional = cedulaProfesional;
        this.especialidad = especialidad;
    }

    /**
     * Devuelve la cédula profesional.
     * @return cédula del veterinario
     */
    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    /**
     * Cambia la cédula profesional.
     * @param cedulaProfesional nueva cédula
     */
    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    /**
     * Devuelve la especialidad.
     * @return especialidad del veterinario
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Cambia la especialidad.
     * @param especialidad nueva especialidad
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Describe el rol del veterinario.
     * @return descripción de su rol
     */
    @Override
    public String describirRol() {
        return null;
    }

    /**
     * Resume los datos del veterinario.
     * @return datos del veterinario en texto
     */
    @Override
    public String toString() {
        return null;
    }
}
