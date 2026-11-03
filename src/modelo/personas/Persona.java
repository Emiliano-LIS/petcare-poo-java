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
        this.nombre = validarTexto(nombre, "El nombre");
        this.apellido = validarTexto(apellido, "El apellido");
        this.telefono = validarTexto(telefono, "El teléfono");
        this.correo = validarTexto(correo, "El correo");
    }

    protected static String validarTexto(String texto, String campo) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(campo + " es obligatorio.");
        }
        return texto.trim();
    }

    /**
     * Actualiza el contacto.
     * @param telefono nuevo teléfono
     * @param correo nuevo correo
     */
    public void actualizarContacto(String telefono, String correo) {
        if ((telefono == null && correo == null)
                || (telefono != null && telefono.isBlank())
                || (correo != null && correo.isBlank())) {
            System.out.println("Indica al menos un dato de contacto y evita textos vacíos.");
            return;
        }
        if (correo != null) {
            this.correo = correo.trim();
            System.out.println("Correo cambiado exitosamente");
        }
        if (telefono != null) {
            this.telefono = telefono.trim();
            System.out.println("Telefono cambiado exitosamente");
        }
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
        this.nombre = validarTexto(nombre, "El nombre");
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
        this.apellido = validarTexto(apellido, "El apellido");
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
        this.telefono = validarTexto(telefono, "El teléfono");
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
        this.correo = validarTexto(correo, "El correo");
    }

    /**
     * Describe su rol en la clínica.
     * @return rol de la persona
     */
    public abstract String describirRol();

    /**
     * Resume los datos de contacto de la persona.
     * @return datos de la persona en texto
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + apellido
                + " | Teléfono: " + telefono + " | Correo: " + correo;
    }
}
