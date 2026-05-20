# MANUAL DE USUARIO

## SGCM - Sistema de Gestión de Clínica Médica

**Para:** Farmacias Parecido / Clínicas Don Chuy

**Versión:** 1.0.0

**Fecha:** Mayo 2026

---

## ÍNDICE

1. [Introducción](#1-introducción)
2. [Pantalla de Inicio de Sesión](#4-pantalla-de-inicio-de-sesión)
3. [Panel del Administrador](#5-panel-del-administrador)
   - 3.1 [Gestión de Pacientes](#51-gestión-de-pacientes)
   - 3.2 [Gestión de Médicos](#52-gestión-de-médicos)
   - 3.3 [Gestión de Citas](#53-gestión-de-citas)
   - 3.4 [Gestión de Enfermeros](#54-gestión-de-enfermeros)
   - 3.5 [Reportes](#55-reportes)
   - 3.6 [Auditoría](#56-auditoría)
4. [Panel del Médico](#6-panel-del-médico)
   - 4.1 [Mi Agenda](#61-mi-agenda)
   - 4.2 [Pacientes](#62-pacientes)
   - 4.3 [Registrar Consulta](#63-registrar-consulta)
5. [Flujo Completo de una Consulta](#7-flujo-completo-de-una-consulta)
6. [Reglas de Negocio Importantes](#8-reglas-de-negocio-importantes)
7. [Solución de Problemas](#9-solución-de-problemas)
8. [Preguntas Frecuentes](#10-preguntas-frecuentes)

---

## 1. INTRODUCCIÓN

El **SGCM (Sistema de Gestión de Clínica Médica)** es una aplicación de escritorio diseñada para administrar las operaciones diarias de una clínica médica. Permite gestionar pacientes, médicos, citas, consultas, pagos y generar reportes, todo desde una interfaz gráfica intuitiva.

### Roles del Sistema

El sistema maneja dos roles principales:

| Rol | Descripción | Acceso |
|-----|-------------|--------|
| **ADMINISTRADOR** | Personal administrativo de la clínica | Gestión completa del sistema (pacientes, médicos, citas, enfermeros, reportes, auditoría) |
| **MÉDICO** | Personal médico que atiende consultas | Agenda, pacientes, registro de consultas, expedientes clínicos |

> **Nota:** El enfermero existe en el sistema pero **no tiene acceso directo** al sistema. Su información es gestionada por el administrador.

---

## 2. PANTALLA DE INICIO DE SESIÓN

Al iniciar el sistema, aparece la pantalla de login con el título **"SGCM - Farmacias Parecido"**.

### Campos de la Pantalla

| Campo | Descripción | Obligatorio |
|-------|-------------|-------------|
| **Usuario** | Nombre de usuario registrado | Sí |
| **Contraseña** | Contraseña asociada al usuario | Sí |

### Credenciales de Prueba

| Usuario | Contraseña | Rol |
|---------|-----------|-----|
| `admin` | `admin` | Administrador |
| `medico` | `medico` | Médico |

### Acciones Disponibles

- **Iniciar Sesión:** Valida las credenciales y abre el panel correspondiente al rol.
- **Tecla Enter:** También activa el botón de inicio de sesión desde cualquier campo.

### Mensajes de Error

| Mensaje | Causa | Solución |
|---------|-------|----------|
| "Usuario o contraseña incorrectos" | Credenciales inválidas | Verificar mayúsculas/minúsculas |
| "Usuario y contraseña son obligatorios" | Campos vacíos | Llenar ambos campos |
| "Rol no autorizado" | Rol no válido | Contactar al administrador |

---

## 3. PANEL DEL ADMINISTRADOR

Al iniciar sesión como **administrador**, se abre el panel con el título **"SGCM - Panel Administrador"** en pantalla completa.

La interfaz está organizada en **pestañas (tabs)** en la parte superior:

```
[Pacientes] [Médicos] [Citas] [Enfermeros] [Reportes] [Auditoría]
```

En la barra de menú superior (Archivo → Cerrar Sesión) puede cerrar la sesión en cualquier momento.

> **Seguridad:** La sesión expira automáticamente después de **15 minutos de inactividad**.

---

### 3.1 GESTIÓN DE PACIENTES

**Pestaña:** Pacientes

#### Panel de Búsqueda

| Campo | Descripción |
|-------|-------------|
| **Expediente** | Buscar por número de expediente exacto |
| **Nombre** | Buscar por nombre del paciente |
| **Apellido** | Buscar por apellido |
| **Edad** | Filtrar por edad |
| **Botón Buscar** | Ejecuta la búsqueda |

> **Tip:** Si deja el campo de expediente vacío y presiona Buscar, se muestran **todos los pacientes activos**.

#### Tabla de Pacientes

| Columna | Descripción |
|---------|-------------|
| ID | Identificador interno (oculto al usuario) |
| Expediente | Número de expediente único |
| Nombre | Nombre completo del paciente |
| CURP | Clave Única de Registro de Población |
| Edad | Edad en años |
| Estado | Activo o Inactivo |
| Editar | Botón para editar datos |
| Deshabilitar | Botón para desactivar paciente |

#### Acciones Disponibles

**Registrar Paciente:**
1. Hacer clic en el botón **"Registrar Paciente"** (parte inferior).
2. Completar el formulario:
   - **Nombre completo** *(obligatorio)*
   - **CURP** *(obligatorio)*
   - **Dirección**
   - **Estado civil** (Soltero, Casado, Divorciado, Viudo, Unión libre)
   - **Edad** *(número)*
3. Clic en **Guardar**.
4. El sistema genera automáticamente un **número de expediente único** (formato: EXP-YYYYMMDD-XXXX).

> **Importante:** El número de expediente **nunca se puede modificar** después de crearlo.

**Editar Paciente:**
1. Localizar al paciente en la tabla.
2. Clic en el botón **"Editar"** de la fila correspondiente.
3. Modificar los datos permitidos.
4. Guardar cambios.

**Deshabilitar Paciente:**
1. Localizar al paciente en la tabla.
2. Clic en **"Deshabilitar"**.
3. Confirmar la acción en el diálogo.
4. El paciente pasa a estado **Inactivo** (no se elimina físicamente).

---

### 3.2 GESTIÓN DE MÉDICOS

**Pestaña:** Médicos

#### Tabla de Médicos

| Columna | Descripción |
|---------|-------------|
| ID | Identificador interno |
| Nombre | Nombre completo del médico |
| Especialidad | PEDIATRIA, CARDIOLOGIA, DERMATOLOGIA, NEUROLOGIA, GENERAL |
| Teléfono | Teléfono fijo |
| Estado | Activo o Inactivo |
| Deshabilitar | Botón para desactivar médico |

#### Acciones Disponibles

**Registrar Médico:**
1. Clic en **"Registrar Médico"**.
2. Completar el formulario:
   - **Nombre completo** *(obligatorio)*
   - **Especialidad** *(obligatorio, seleccionar del combo)*
   - **Teléfono fijo**
   - **Teléfono celular**
   - **Correo electrónico**
3. Clic en **Guardar**.

**Deshabilitar Médico:**
1. Clic en **"Deshabilitar"** del médico correspondiente.
2. Confirmar la acción.
3. El sistema **cancela automáticamente todas las citas futuras** del médico.
4. El médico pasa a estado Inactivo.

> **Advertencia:** Al deshabilitar un médico, todas sus citas pendientes y confirmadas futuras se cancelan. Esta acción **no se puede deshacer**.

---

### 3.3 GESTIÓN DE CITAS

**Pestaña:** Citas

#### Tabla de Citas

| Columna | Descripción |
|---------|-------------|
| ID | Identificador de la cita |
| Paciente | Nombre del paciente |
| Médico | Nombre del médico |
| Fecha | Fecha programada |
| Hora | Hora de inicio |
| Estado | Pendiente, Confirmada, Completada, Cancelada, No presentada, Expirada |
| Cambiar Estado | Botón para modificar estado |
| Cancelar | Botón para cancelar cita |

#### Estados de Cita

| Estado | Color/Indicador | Descripción |
|--------|-----------------|-------------|
| **Pendiente** | Gris | Cita agendada, esperando confirmación |
| **Confirmada** | Azul | Paciente confirmó asistencia |
| **Completada** | Verde | Consulta realizada exitosamente |
| **Cancelada** | Rojo | Cita cancelada (con o sin motivo) |
| **No presentada** | Naranja | Paciente no llegó (automático a los 15 min) |
| **Expirada** | Gris oscuro | Fecha/hora de la cita ya pasó |

#### Acciones Disponibles

**Agendar Cita:**
1. Clic en **"Agendar Cita"** (parte inferior).
2. Seleccionar:
   - **Paciente** (del combo)
   - **Médico** (del combo)
   - **Fecha** (formato YYYY-MM-DD)
   - **Hora** (del combo: 08:00 a 17:00)
   - **Consultorio** (opcional)
3. Clic en **Guardar**.

> **Reglas:**
> - No puede haber dos citas del mismo médico a la misma hora.
> - La duración de toda cita es **fija de 1 hora** (hora fin = hora + 60 min).
> - No se pueden agendar citas en horarios ocupados.

**Cambiar Estado:**
1. Clic en **"Cambiar Estado"** de la cita.
2. Seleccionar el nuevo estado del combo.
3. El sistema valida que la transición sea permitida:
   - Pendiente → Confirmada, Cancelada, No presentada, Expirada
   - Confirmada → Completada, Cancelada, No presentada

**Cancelar Cita:**
1. Clic en **"Cancelar"** de la cita.
2. Ingresar el **motivo de cancelación** *(obligatorio)* en el diálogo.
3. Confirmar.

> **Restricción:** La cancelación requiere **mínimo 72 horas de anticipación**. Si faltan menos horas, el sistema rechazará la cancelación.

---

### 3.4 GESTIÓN DE ENFERMEROS

**Pestaña:** Enfermeros

#### Tabla de Enfermeros

| Columna | Descripción |
|---------|-------------|
| ID | Identificador |
| Nombre | Nombre completo |
| CURP | CURP del enfermero |
| Médico Asignado | Médico al que está asignado |
| Estado | Activo o Inactivo |
| Deshabilitar | Botón para desactivar |

#### Acciones Disponibles

- **Registrar Enfermero:** Abre formulario (por implementar en esta versión).
- **Deshabilitar:** Desactiva al enfermero (eliminación lógica).

> **Nota:** Los enfermeros no tienen credenciales de acceso al sistema.

---

### 3.5 REPORTES

**Pestaña:** Reportes

#### Filtros Disponibles

| Filtro | Descripción |
|--------|-------------|
| **Fecha inicio** | Fecha inicial del rango (YYYY-MM-DD) |
| **Fecha fin** | Fecha final del rango (YYYY-MM-DD) |
| **Médico** | Nombre o parte del nombre del médico |
| **Especialidad** | Seleccionar del combo |
| **Paciente** | Nombre o parte del nombre del paciente |

#### Acciones

- **Generar Reporte:** Aplica los filtros y muestra resultados en la tabla.
- **Limpiar Filtros:** Borra todos los filtros y vacía la tabla.

#### Tabla de Resultados

| Columna | Descripción |
|---------|-------------|
| Fecha | Fecha del reporte |
| Consultorio | Número o nombre del consultorio |
| Especialidad | Especialidad médica |
| Médico | Nombre del médico |
| Paciente | Nombre del paciente |
| Motivo Consulta | Motivo registrado |

> **Tip:** Deje los filtros vacíos y presione "Generar Reporte" para ver todos los registros.

---

### 3.6 AUDITORÍA

**Pestaña:** Auditoría

Muestra el registro completo de todas las operaciones realizadas en el sistema.

#### Tabla de Auditoría

| Columna | Descripción |
|---------|-------------|
| ID | Identificador del registro |
| Usuario | Usuario que realizó la acción |
| Entidad | Tipo de entidad afectada (Paciente, Médico, Cita, etc.) |
| ID Entidad | Identificador del registro afectado |
| Acción | Tipo de acción (CREAR, ACTUALIZAR, DESHABILITAR, etc.) |
| Fecha y Hora | Momento exacto de la operación |

#### Acciones

- **Refrescar:** Actualiza la tabla con los registros más recientes.

> **Importante:** Los registros de auditoría son **inalterables**. Solo se pueden consultar, nunca modificar ni eliminar.

---

## 4. PANEL DEL MÉDICO

Al iniciar sesión como **médico**, se abre el panel con el título **"SGCM - Panel del Médico"** en pantalla completa.

Pestañas disponibles:

```
[Mi Agenda] [Pacientes] [Consulta]
```

> **Seguridad:** La sesión expira automáticamente después de **15 minutos de inactividad**.

---

### 4.1 MI AGENDA

**Pestaña:** Mi Agenda

Muestra todas las citas asignadas al médico que ha iniciado sesión.

#### Tabla de Agenda

| Columna | Descripción |
|---------|-------------|
| ID | Identificador de la cita |
| Paciente | Nombre del paciente |
| Fecha | Fecha programada |
| Hora | Hora de inicio |
| Estado | Estado actual de la cita |
| Ver Detalle | Botón para ver información completa |
| Iniciar Consulta | Botón para comenzar la consulta médica |

#### Acciones Disponibles

**Ver Detalle:**
1. Clic en **"Ver Detalle"** de la cita.
2. Se muestra una ventana con:
   - Nombre del paciente
   - Fecha y hora
   - Estado actual
   - Consultorio asignado

**Iniciar Consulta:**
1. Clic en **"Iniciar Consulta"**.
2. Solo disponible para citas en estado **CONFIRMADA**.
3. Se abre el diálogo de **Registrar Consulta**.

> **Restricción:** No se puede iniciar consulta en citas Pendientes, Canceladas, Expiradas o No presentadas.

> **Automatismo:** Si una cita confirmada pasa **15 minutos** de su hora de inicio sin ser atendida, el sistema la marca automáticamente como **"No presentada"**.

---

### 4.2 PACIENTES

**Pestaña:** Pacientes

Permite buscar pacientes y ver sus expedientes clínicos completos.

#### Panel de Búsqueda

| Campo | Descripción |
|-------|-------------|
| **Nombre / Expediente** | Texto a buscar |
| **Botón Buscar** | Ejecuta la búsqueda |

#### Tabla de Resultados

| Columna | Descripción |
|---------|-------------|
| Expediente | Número de expediente |
| Nombre | Nombre completo |
| CURP | CURP |
| Edad | Edad en años |
| Estado | Activo o Inactivo |
| Ver Expediente | Botón para abrir expediente completo |

#### Acciones Disponibles

**Ver Expediente:**
1. Buscar el paciente por nombre o expediente.
2. Clic en **"Ver Expediente"**.
3. Se abre el **Expediente Clínico Completo** con:
   - **Datos Personales:** Expediente, nombre, CURP, edad, estado civil, dirección
   - **Historial de Citas:** Fecha, hora, médico, estado de cada cita
   - **Historial Clínico:** Fecha, diagnóstico, tratamiento, medicamentos de cada consulta

> **Seguridad:** Solo los **médicos** pueden ver información clínica. Los administradores **nunca** tienen acceso a diagnósticos, tratamientos, observaciones ni medicamentos.

---

### 4.3 REGISTRAR CONSULTA

**Pestaña:** Consulta (o accesible desde "Iniciar Consulta" en la agenda)

Formulario médico completo para registrar una consulta.

#### Sección: Datos Vitales

| Campo | Descripción | Unidad |
|-------|-------------|--------|
| **Estatura** | Altura del paciente | metros (m) |
| **Peso** | Peso del paciente | kilogramos (kg) |
| **Temperatura** | Temperatura corporal | grados Celsius (°C) |

#### Sección: Datos Clínicos

| Campo | Descripción |
|-------|-------------|
| **Observaciones** | Notas generales de la consulta |
| **Diagnóstico** | Diagnóstico médico |
| **Tratamiento** | Tratamiento prescrito |
| **Estudios solicitados** | Análisis o estudios a realizar |
| **Medicamentos** | Nombre y duración del medicamento |

#### Acciones

- **Guardar Consulta:** Valida los datos, guarda la consulta y limpia el formulario.
- Después de guardar, el sistema pregunta si desea **registrar el pago**.

**Flujo de Pago (opcional):**
1. Si acepta registrar pago, se abre el diálogo de **Pago**.
2. Ingresar **monto** *(obligatorio)*.
3. Seleccionar **método de pago**:
   - **Efectivo:** Pago en efectivo
   - **Tarjeta:** Pago con tarjeta
   - **Libre:** Sin costo (cortesía)
4. Clic en **Procesar Pago**.
5. El sistema genera automáticamente el **comprobante**.
6. Se muestra el comprobante en pantalla (diseño tipo ticket).

> **Regla:** Todo pago **siempre** genera un comprobante automático. Nunca puede quedar un pago sin su comprobante.

---

## 5. FLUJO COMPLETO DE UNA CONSULTA

Paso a paso, desde la llegada del paciente hasta la generación del comprobante:

```
┌─────────────────────────────────────────────────────────────┐
│  PASO 1: AGENDAR CITA                                       │
│  → Administrador agenda cita (Paciente + Médico + Fecha)   │
│  → Estado inicial: PENDIENTE                                │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│  PASO 2: CONFIRMAR CITA                                     │
│  → Administrador cambia estado a CONFIRMADA                │
│  → O el médico la confirma desde su agenda                  │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│  PASO 3: INICIAR CONSULTA                                   │
│  → Médico abre "Mi Agenda"                                  │
│  → Clic en "Iniciar Consulta" de la cita confirmada        │
│  → Se abre el formulario de consulta                        │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│  PASO 4: REGISTRAR CONSULTA                                 │
│  → Médico llena datos vitales y clínicos                    │
│  → Clic en "Guardar Consulta"                               │
│  → Estado de cita cambia a COMPLETADA                       │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│  PASO 5: REGISTRAR PAGO (Opcional)                          │
│  → Si el médico acepta, se abre diálogo de pago            │
│  → Ingresar monto y método de pago                          │
│  → Clic en "Procesar Pago"                                  │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│  PASO 6: GENERAR COMPROBANTE                                │
│  → El sistema genera comprobante automáticamente            │
│  → Se muestra en pantalla (diseño tipo ticket)              │
│  → El paciente puede solicitar impresión                    │
└─────────────────────────────────────────────────────────────┘
```

---

## 6. REGLAS DE NEGOCIO IMPORTANTES

### Para Administradores

| Regla | Descripción |
|-------|-------------|
| **No ver información clínica** | Jamás podrá ver diagnósticos, tratamientos, observaciones ni medicamentos |
| **Expediente inmutable** | El número de expediente de un paciente nunca se modifica |
| **Eliminación lógica** | Los registros nunca se eliminan físicamente; solo se marcan como Inactivos |
| **Cancelación con anticipación** | Para cancelar una cita deben faltar mínimo 72 horas |
| **Sin conflictos de horario** | No pueden existir dos citas del mismo médico a la misma hora |
| **Duración fija** | Toda cita dura exactamente 1 hora |

### Para Médicos

| Regla | Descripción |
|-------|-------------|
| **Solo médicos registran consultas** | Ningún otro rol puede crear registros clínicos |
| **Acceso a expedientes** | Solo médicos pueden ver el historial clínico completo |
| **Citas confirmadas** | Solo se puede iniciar consulta en citas con estado CONFIRMADA |

### Generales

| Regla | Descripción |
|-------|-------------|
| **Pago → Comprobante** | Todo pago genera comprobante automáticamente. Nunca sin comprobante. |
| **Auditoría inalterable** | Los registros de auditoría solo se pueden crear, nunca modificar ni eliminar |
| **Sesión expira** | La sesión se cierra automáticamente después de 15 minutos de inactividad |
| **No presentada automática** | Una cita confirmada sin atención a los 15 min de su inicio se marca automáticamente |
| **Expirada automática** | Las citas pendientes que superan su fecha/hora se marcan como Expiradas |

---

## 7. SOLUCIÓN DE PROBLEMAS

### Problema: "No se puede iniciar sesión"

| Causa | Solución |
|-------|----------|
| Credenciales incorrectas | Verificar mayúsculas/minúsculas. Usar: admin/admin o medico/medico |
| Campos vacíos | Llenar ambos campos (usuario y contraseña) |
| Rol no válido | Contactar al administrador del sistema |

### Problema: "No se puede agendar cita"

| Causa | Solución |
|-------|----------|
| Horario ocupado | Seleccionar otra hora u otro médico |
| Campos obligatorios vacíos | Llenar paciente, médico, fecha y hora |
| Formato de fecha incorrecto | Usar formato YYYY-MM-DD (ejemplo: 2026-05-20) |

### Problema: "No se puede cancelar cita"

| Causa | Solución |
|-------|----------|
| Faltan menos de 72 horas | La cancelación requiere mínimo 72 horas de anticipación |
| Motivo vacío | Ingresar obligatoriamente el motivo de cancelación |
| Cita ya cancelada/completada | No se pueden cancelar citas en estado final |

### Problema: "No se puede iniciar consulta"

| Causa | Solución |
|-------|----------|
| Cita no está confirmada | Solo citas CONFIRMADAS permiten iniciar consulta |
| Cita expirada o cancelada | Verificar el estado de la cita en la agenda |

### Problema: "No se ve información clínica"

| Causa | Solución |
|-------|----------|
| Rol de administrador | Los administradores nunca ven información clínica por seguridad |
| Iniciar sesión como médico | Solo los médicos tienen acceso a expedientes clínicos |

### Problema: "La sesión se cerró sola"

| Causa | Solución |
|-------|----------|
| Inactividad de 15 minutos | El sistema cierra sesión automáticamente por seguridad |
| Volver a iniciar sesión | Ingresar credenciales nuevamente |


