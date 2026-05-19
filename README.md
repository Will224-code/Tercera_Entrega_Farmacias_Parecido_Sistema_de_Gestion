# SGCM — Sistema de Gestión de Clínica Médica

Sistema de escritorio para la administración integral de una clínica médica, desarrollado para **Farmacias Parecido / Clínicas Don Chuy**.  
Permite gestionar pacientes, médicos, citas, consultas clínicas, pagos y reportes.

## Descripción

El sistema sigue una arquitectura por capas (Vista → Controller → Service → Repository) y está implementado en Java con interfaz gráfica Swing. Su diseño incorpora múltiples patrones de diseño para garantizar mantenibilidad, extensibilidad y control de acceso a la información clínica. 

## Tecnologías utilizadas

- Java 11 o superior
- Java Swing (interfaz gráfica de escritorio)
- Patrones de diseño: State, Observer, Strategy, Proxy, Factory

## Módulos del sistema

- Gestión de Pacientes
- Gestión de Médicos
- Gestión de Citas (estados: Pendiente, Confirmada, Completada, Cancelada, No presentada, Expirada)
- Expediente Clínico y Consultas
- Pagos y Comprobantes
- Reportes Diarios
- Control de Sesión y Auditoría

## Roles del sistema

- **Administrador** – gestión general del sistema (usuarios, configuraciones, reportes globales)
- **Médico** – acceso a consultas y expedientes clínicos (restringido por proxy)

## Patrones de diseño implementados

- **State** – gestión de los seis estados de una cita
- **Observer** – auditoría automática de acciones y generación de reportes
- **Strategy** – métodos de pago (Efectivo, Tarjeta, Libre)
- **Proxy** – control de acceso a información clínica según el rol
- **Factory** – creación de estados y comprobantes

## Estructura del proyecto
