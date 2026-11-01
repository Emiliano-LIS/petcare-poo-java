package modelo.personas;

/**
 * Datos de contacto compartidos por dueños y personal.
 */
public abstract class Persona {
    /** Nombre de la persona. */
    protected String nombre;
    /** Apellido de la persona. */
    protected String apellido;
    /** Teléfono de contacto. */
    protected String telefono;
    /** Correo electrónico. */
    protected String correo;

    /**
     * Guarda los datos de la persona.
     * @param nombre nombre de la persona
     * @param apellido apellido de la persona
     * @param telefono teléfono de contacto
     * @param correo correo electrónico
     */
    public Persona(String nombre, String apellido, String telefono, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    /**
     * Actualiza el contacto.
     * @param telefono nuevo teléfono
     * @param correo nuevo correo
     */
    public void actualizarContacto(String telefono, String correo) {
    }

    /**
     * Devuelve el nombre.
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el apellido.
     * @return apellido de la persona
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Cambia el apellido.
     * @param apellido nuevo apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Devuelve el teléfono.
     * @return teléfono de contacto
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Cambia el teléfono.
     * @param telefono nuevo teléfono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuelve el correo.
     * @return correo electrónico
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Cambia el correo.
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Describe su rol en la clínica.
     * @return rol de la persona
     */
    public abstract String describirRol();
}
