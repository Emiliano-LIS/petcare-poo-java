package modelo.clinica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.comprobante.Comprobante;
import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;

/**
 * Organiza los consultorios, veterinarios y citas.
 *
 * Regla 2: un veterinario no puede tener dos citas programadas a la misma hora.
 * Regla 4: un consultorio no puede usarse en dos citas al mismo tiempo.
 * Regla 6: los cobros deben consultarse por mascota y veterinario.
 * Los consultorios pertenecen a la clínica; los veterinarios son independientes.
 */
public class Clinica {
    private String nombre;
    private final List<Consultorio> consultorios;
    private final List<Veterinario> veterinarios;
    private final List<Cita> citas;
    private final List<Mascota> mascotas;

    /**
     * Crea una clínica con listas vacías.
     * @param nombre nombre de la clínica
     */
    public Clinica(String nombre) {
        this.nombre = validarNombre(nombre);
        this.consultorios = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.mascotas = new ArrayList<>();
    }

    /**
     * Devuelve el nombre.
     * @return nombre de la clínica
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = validarNombre(nombre);
    }

    private static String validarNombre(String nombre) {
        // Hay que validar si es nulo o si es ""
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la clínica es obligatorio.");
        }
        return nombre.trim();
    }

    /**
     * Devuelve los consultorios.
     * @return lista de consultorios
     */
    public List<Consultorio> getConsultorios() {
        // Para evitar problemas de edición (y generar inconsistencias) solo pasamos una copia de la lista
        return List.copyOf(consultorios);
    }

    /**
     * Devuelve los veterinarios.
     * @return equipo de la clínica
     */
    public List<Veterinario> getVeterinarios() {
        // Para evitar problemas de edición (y generar inconsistencias) solo pasamos una copia de la lista
        return List.copyOf(veterinarios);
    }

    /**
     * Devuelve las citas.
     * @return copia no modificable de las citas registradas
     */
    public List<Cita> getCitas() {
        // Esto es para evitar que se modifiquen las citas.
        return List.copyOf(citas);
    }

    /**
     * Devuelve las mascotas registradas actualmente.
     * @return copia no modificable del registro activo
     */
    public List<Mascota> getMascotas() {
        // Esto es para evitar que se modifiquen las mascotas.
        return List.copyOf(mascotas);
    }

    /**
     * Registra una mascota y la asocia a la lista de su dueño.
     * Si vuelve después de una baja, comienza con un historial nuevo.
     * @param mascota mascota con dueño a registrar
     * @throws IllegalArgumentException si falta la mascota, su dueño o ya está registrada
     */

    // Al final se opto por agregar esto por el "dar de baja mascotas"; antes las citas hacian el vinculo, ahora se hace directo.
    public void registrarMascota(Mascota mascota) {
        if (mascota == null || mascota.getDueno() == null) {
            System.out.println("La mascota debe tener un dueño.");
            return;
        }
        if (mascotas.contains(mascota)) {
            System.out.println("La mascota ya está registrada.");
            return;
        }
        mascota.crearHistorial();
        mascota.getDueno().agregarMascota(mascota);
        mascotas.add(mascota);
    }

    /**
     * Da de baja una mascota, cancela sus reservas y elimina su historial.
     * Conserva las citas atendidas y sus comprobantes para consultar los cobros.
     * @param mascota mascota registrada a retirar
     * @throws IllegalArgumentException si la mascota es nula o no está registrada
     */
    public void darBajaMascota(Mascota mascota) {
        if (mascota == null || !mascotas.contains(mascota)) {
            System.out.println("La mascota no esta registrada en esta clinica.");
            return;
        }
        for (Cita cita : citas) {
            // Si encontramos la mascota, cancelamos todas las citas activas.
            if (cita.getMascota() == mascota && cita.getEstado() == EstadoCita.PROGRAMADA) {
                cita.cancelar();
            }
        }
        // La quitamos de la lista de mascotas de esta clinica por lo que esta dada de baja.
        mascotas.remove(mascota);
        // Para el sistema el dueño ya no tiene la mascota porqué esta dada de baja (Como si estuviera eliminada).
        if (mascota.getDueno() != null) {
            mascota.getDueno().eliminarMascota(mascota);
        }
        mascota.eliminarHistorial();
        // Las citas conservadas aún referencian a la mascota para las consultas de cobros.
    }

    /**
     * Crea un consultorio de la clínica.
     * @param numero número del consultorio
     * @return consultorio creado
     */
    public Consultorio crearConsultorio(int numero) {
        if (numero <= 0) {
            System.out.println("El número del consultorio debe ser mayor que cero.");
            return null;
        }
        for (Consultorio consultorio : consultorios) {
            if (consultorio.getNumero() == numero) {
                System.out.println("Ya existe un consultorio con ese número.");
                return null;
            }
        }
        Consultorio consultorioNuevo = new Consultorio(numero);
        consultorios.add(consultorioNuevo);
        return consultorioNuevo;
    }

    /**
     * Agrega un veterinario al equipo.
     * @param veterinario veterinario ya creado
     */
    public void agregarVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            System.out.println("El veterinario es obligatorio.");
            return;
        }
        for (Veterinario vet : veterinarios) {
            if (vet.getCedulaProfesional().equalsIgnoreCase(veterinario.getCedulaProfesional())) {
                System.out.println("Ya existe un veterinario con esa cédula profesional.");
                return;
            }
        }
        System.out.println("Registro agregado correctamente");
        veterinarios.add(veterinario);
    }

    /**
     * Agenda una cita si hay disponibilidad.
     * @param citaAgendar cita a agendar
     */
    public void agendarCita(Cita citaAgendar) {
        if (!validarDatosCita(citaAgendar)) {
            return;
        }
        if (!mascotas.contains(citaAgendar.getMascota())) {
            System.out.println("La mascota no está registrada en la clínica.");
            return;
        }
        if (citaAgendar.getEstado() != EstadoCita.PROGRAMADA || citas.contains(citaAgendar)) {
            System.out.println("La cita debe ser nueva y estar programada.");
            return;
        }
        if (!validarDisponibilidad(citaAgendar.getVeterinario(), citaAgendar.getFechaHora())) {
            System.out.println("El veterinario no esta disponible en este horario");
            return;
        }

        if (!veterinarios.contains(citaAgendar.getVeterinario())
                || !consultorios.contains(citaAgendar.getConsultorio())) {
            System.out.println("El veterinario o consultorio debe pertenecer a la clinica");
            return;
        }

        if (!consultorioDisponible(citaAgendar)) {
            System.out.println("El consultorio está ocupado en ese horario");
            return;
        }
        citas.add(citaAgendar);
    }

    private boolean validarDatosCita(Cita cita) {
        if (cita == null) {
            System.out.println("La cita es obligatoria.");
            return false;
        }
        if (cita.getMascota() == null || cita.getVeterinario() == null
                || cita.getFechaHora() == null || cita.getServicio() == null
                || cita.getConsultorio() == null) {
            System.out.println("La cita debe tener todos sus datos de atención.");
            return false;
        }
        return true;
    }

    private boolean consultorioDisponible(Cita nuevaCita) {
        for (Cita cita : citas) {
            if (cita.getEstado() != EstadoCita.CANCELADA
                    && cita.getConsultorio() == nuevaCita.getConsultorio()
                    && cita.getFechaHora().equals(nuevaCita.getFechaHora())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Busca una cita por folio.
     * @param folio folio a buscar
     * @return cita encontrada o {@code null} si no existe
     */
    public Cita buscarCita(String folio) {
        if (folio == null || folio.isBlank()) {
            System.out.println("El folio es obligatorio.");
            return null;
        }
        for (Cita cita : citas) {
            if (cita.getFolio().equals(folio.trim())) {
                System.out.println(cita);
                return cita;
            }
        }
        System.out.println("No se encontraron coincidencias.");
        return null;
    }

    /**
     * Sobrecarga de metodos:
     * Busca las citas de un día.
     * @param fecha día a consultar
     * @return citas de la fecha indicada
     */
    public List<Cita> buscarCita(LocalDate fecha) {
        List<Cita> coincidencias = new ArrayList<>();
        if (fecha == null) {
            System.out.println("La fecha es obligatoria.");
            return coincidencias;
        }
        for (Cita cita : citas) {
            if (cita.getFechaHora().toLocalDate().equals(fecha)) {
                coincidencias.add(cita);
            }
        }
        if (coincidencias.isEmpty()) {
            System.out.println("No se encontraron coincidencias.");
        }
        return coincidencias;
    }

    /**
     * Comprueba si el veterinario tiene citas programadas en el horario solicitado.
     * @param veterinario veterinario a consultar
     * @param fechaHora horario propuesto
     * @return {@code true} si el horario está disponible
     */
    public boolean validarDisponibilidad(Veterinario veterinario, LocalDateTime fechaHora) {
        if (veterinario == null || fechaHora == null) {
            System.out.println("El veterinario y el horario son obligatorios.");
            return false;
        }
        for (Cita cita : citas) {
            if (cita.getEstado() == EstadoCita.PROGRAMADA
                    && veterinario.equals(cita.getVeterinario())
                    && fechaHora.equals(cita.getFechaHora())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Consulta el total cobrado por veterinario.
     * @param veterinario veterinario a consultar
     * @return total cobrado por el veterinario
     */
    public BigDecimal consultarTotalCobradoPorVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            System.out.println("El veterinario es obligatorio");
            return BigDecimal.ZERO;
        }
        BigDecimal totalCobrado = BigDecimal.ZERO;

        for (Cita cita : citas) {
            Comprobante comprobante = cita.getComprobante();

            if (cita.getVeterinario() == veterinario && comprobante != null) {
                totalCobrado = totalCobrado.add(comprobante.getTotal());
            }
        }
        return totalCobrado;
    }

    /**
     * Consulta los cobros de una mascota.
     * @param mascota mascota a consultar
     * @return comprobantes de la mascota
     */
    public List<Comprobante> consultarCobrosPorMascota(Mascota mascota) {
        List<Comprobante> cobros = new ArrayList<>();
        if (mascota == null) {
            System.out.println("La mascota es obligatoria.");
            return cobros;
        }

        for (Cita cita : citas) {
            Comprobante comprobante = cita.getComprobante();

            if (cita.getMascota() == mascota && comprobante != null) {
                cobros.add(comprobante);
            }
        }

        return cobros;
    }

    /**
     * Resume los datos y la cantidad de registros de la clínica.
     * @return datos de la clínica en texto
     */
    @Override
    public String toString() {
        return "Clínica: " + nombre + " | Consultorios: " + consultorios.size()
                + " | Veterinarios: " + veterinarios.size()
                + " | Citas: " + citas.size()
                + " | Mascotas activas: " + mascotas.size();
    }
}
