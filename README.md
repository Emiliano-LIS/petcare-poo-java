# PetCare — Sistema de Gestión Veterinaria

Aplicación de consola desarrollada en Java para gestionar las operaciones básicas de una clínica veterinaria: registro de dueños y mascotas, administración de veterinarios, programación y atención de citas, historial clínico y cálculo de cobros por servicios.

Este proyecto aplica los principios de **Diseño y Programación Orientada a Objetos (POO)** mediante un modelo de clases documentado con UML.

## Tecnologías utilizadas

| Herramienta | Uso |
|---|---|
| Java 21 o superior | Implementación de la aplicación |
| IntelliJ IDEA | Entorno de desarrollo |
| Draw.io | Diseño de diagramas UML |
| Git y GitHub | Control de versiones y publicación |

## Modelo de la solución

El modelo contempla personas (dueños, veterinarios y recepcionistas), mascotas (perros, gatos y aves exóticas), una clínica con consultorios, citas, servicios y comprobantes de cobro.

**Diagrama de clases:** [Abrir diagrama](docs/diagramas/PetCare_UML_Clases.drawio).

## Estructura del proyecto

Estructura prevista, sujeta a ajustes conforme avance la implementación:

```text
petcare-poo-java/
├── README.md
├── .gitignore
├── docs/
│   ├── analisis.md
│   ├── diagramas/
│   └── capturas/
└── src/
    ├── app/
    │   └── Main.java
    └── modelo/
        ├── personas/
        ├── mascotas/
        ├── clinica/
        ├── servicios/
        └── cobros/
```

## Conceptos de POO aplicados

Completar los archivos y números de línea **cuando el código esté implementado**.

| Concepto | Archivo y línea | Explicación |
|---|---|---|
| Herencia | Pendiente | Pendiente |
| Clase abstracta | Pendiente | Pendiente |
| Interfaz | Pendiente | Pendiente |
| Polimorfismo | Pendiente | Pendiente |
| Encapsulamiento | Pendiente | Pendiente |
| Sobrecarga | Pendiente | Pendiente |
| Asociación | Pendiente | Pendiente |
| Agregación | Pendiente | Pendiente |
| Composición | Pendiente | Pendiente |

## Reglas de negocio

1. El peso y la edad de una mascota deben ser mayores que cero.
2. Un veterinario no puede tener dos citas programadas a la misma hora.
3. Una cita cancelada no puede marcarse como atendida.
4. Un consultorio no puede asignarse a dos citas simultáneas.
5. Los costos no pueden ser negativos.
6. Los cobros deben poder consultarse por mascota y por veterinario.

> Pendiente: indicar las clases, métodos y líneas donde se valida cada regla.

## Reflexión personal

**¿Qué aprendí?** Pendiente.

**¿Qué fue lo más difícil?** Pendiente.

**¿Qué mejoraría?** Pendiente.

**Uso de inteligencia artificial (si aplica):** describir de manera concreta las dudas consultadas, el apoyo recibido y lo aprendido, tal como solicita el documento.

## Autor

**Nombre:** Jorge Emiliano Hernandez Martinez.

**Perfil de GitHub:** EmilianoLIS.
