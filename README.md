# 🏛️ CASTANHERO - Sistema Integral de Gestión Institucional

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.2-brightgreen.svg)
![Java](https://img.shields.io/badge/Java-11-orange.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13%2B-blue.svg)
![License](https://img.shields.io/badge/License-Proprietary-red.svg)

**Sistema empresarial de gestión académica, administrativa y operacional para instituciones educativas**

[Características](#-características-principales) •
[Arquitectura](#-arquitectura) •
[Tecnologías](#-stack-tecnológico) •
[Instalación](#-instalación) •
[Módulos](#-módulos-del-sistema)

</div>

---

## 📋 Descripción del Proyecto

**Castanhero** es un sistema integral de gestión institucional desarrollado para la **Universidad Autónoma del Pluralismo (UAP/USIC)**, diseñado para administrar de manera centralizada todos los procesos académicos, administrativos, financieros y operacionales de una institución educativa superior.

El sistema está construido con una arquitectura empresarial robusta basada en **Spring Boot** y sigue las mejores prácticas de diseño de software, ofreciendo una solución escalable, modular y segura para la gestión institucional completa.

### 🎯 Objetivo

Centralizar y automatizar los procesos de gestión institucional, desde la matrícula de estudiantes hasta la administración de recursos humanos, pasando por la gestión documental, financiera y de proyectos, proporcionando una plataforma unificada con control de acceso basado en roles.

---

## 🚀 Stack Tecnológico

### Backend Framework

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Spring Boot** | 2.5.2 | Framework principal de aplicación |
| **Spring MVC** | 5.3.x | Arquitectura web MVC |
| **Spring Data JPA** | 2.5.x | Capa de persistencia y acceso a datos |
| **Spring Security** | 5.5.x | Autenticación y autorización |
| **Hibernate ORM** | 5.4.x | Mapeo objeto-relacional |
| **Spring Boot Actuator** | 2.5.2 | Monitoreo y métricas de aplicación |
| **Spring Boot DevTools** | 2.5.2 | Herramientas de desarrollo |
| **Spring Boot Mail** | 2.5.2 | Servicio de correo electrónico |

### Base de Datos

- **PostgreSQL** - Base de datos relacional principal
- **Hibernate Dialect** - PostgreSQLDialect
- **DDL Strategy** - Update (auto-actualización de esquemas)

### Capa de Presentación

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Thymeleaf** | 3.0.x | Motor de plantillas server-side |
| **Thymeleaf Spring Security** | 3.0.4 | Integración de seguridad en vistas |
| **Bootstrap** | 4.x | Framework CSS responsive |
| **jQuery** | 3.x | Biblioteca JavaScript |
| **AJAX** | - | Comunicación asíncrona |

### Seguridad y Validación

- **BCrypt Password Encoder** - Cifrado de contraseñas
- **ZXCVBN** (1.2.3) - Validación de fortaleza de contraseñas
- **Spring Security** - Control de acceso basado en roles (RBAC)
- **Bean Validation API** (2.0.1) - Validación de datos

### Generación de Documentos

| Librería | Versión | Propósito |
|----------|---------|-----------|
| **iText PDF** | 5.5.9 | Generación de documentos PDF |
| **Flying Saucer PDF** | 9.1.22 | Conversión HTML a PDF |
| **Apache POI** | 4.1.2 | Generación/lectura de Excel (XLSX) |
| **Google ZXing** | 3.4.0 | Generación de códigos QR/Barras |

### Utilidades y Herramientas

- **Lombok** (1.18.20) - Reducción de código boilerplate
- **ModelMapper** (3.1.0) - Mapeo de objetos DTO
- **Gson** (2.8.5) - Serialización JSON
- **NekoHTML** (1.9.22) - Parsing HTML5 legacy

### Build y Deploy

- **Maven** 3.6+ - Gestión de dependencias y construcción
- **Apache Tomcat Embedded** - Servidor de aplicaciones embebido
- **Java** 11+ - Plataforma de ejecución

---

## 🏗️ Arquitectura

### Arquitectura en Capas

El sistema sigue una **arquitectura en capas de 3 niveles** (Three-Tier Architecture) que separa claramente las responsabilidades:

```
┌─────────────────────────────────────────────────────────────────┐
│                     CAPA DE PRESENTACIÓN                        │
│              (Controllers + Thymeleaf Views)                    │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐         │
│  │ Controllers  │  │ REST APIs    │  │  Thymeleaf   │         │
│  │   Web MVC    │  │   JSON/AJAX  │  │  Templates   │         │
│  └──────────────┘  └──────────────┘  └──────────────┘         │
├─────────────────────────────────────────────────────────────────┤
│                    CAPA DE LÓGICA DE NEGOCIO                    │
│                    (Services + DTOs)                            │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐         │
│  │  Services    │  │   Security   │  │ PDF/Email    │         │
│  │ Implementa.  │  │  UserDetails │  │   Services   │         │
│  └──────────────┘  └──────────────┘  └──────────────┘         │
├─────────────────────────────────────────────────────────────────┤
│                   CAPA DE ACCESO A DATOS                        │
│                (Repositories + DAOs)                            │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐         │
│  │     JPA      │  │   Custom     │  │  Pagination  │         │
│  │ Repositories │  │     DAOs     │  │   Support    │         │
│  └──────────────┘  └──────────────┘  └──────────────┘         │
├─────────────────────────────────────────────────────────────────┤
│                    CAPA DE PERSISTENCIA                         │
│                   (Entities + Database)                         │
│  ┌──────────────────────────────────────────────────┐          │
│  │  150+ Entity Classes (JPA/Hibernate)             │          │
│  │  PostgreSQL Database (100+ tablas)               │          │
│  └──────────────────────────────────────────────────┘          │
└─────────────────────────────────────────────────────────────────┘
```

### Estructura de Paquetes

```
uap.usic.siga/
│
├── 📁 controladores/              # Capa de Presentación
│   ├── LoginController            # Autenticación
│   ├── RegisterController         # Registro de usuarios
│   ├── IndexController            # Dashboard principal
│   ├── pg/                        # Módulo de Postgrado (8 controladores)
│   ├── gdoc/                      # Gestión Documental (5 controladores)
│   ├── olimpiadas/                # Competencias Olímpicas
│   ├── sicoes/                    # Gestión de Proyectos (4 controladores)
│   ├── adminControladores/        # Panel de administración
│   └── restApiControllers/        # APIs REST
│
├── 📁 servicios/                  # Capa de Lógica de Negocio
│   ├── impl/                      # Implementaciones (24+ servicios)
│   ├── userDetails/               # Spring Security UserDetails
│   ├── usuariosBusquedas/         # Búsquedas de usuarios
│   └── utilities/                 # Servicios utilitarios (Email, PDF)
│
├── 📁 modelos/                    # Capa de Acceso a Datos
│   ├── CrudRepository             # Interfaz base CRUD
│   ├── UserRepository             # Repositorio JPA de usuarios
│   ├── RoleRepository             # Repositorio de roles
│   ├── 20+ DAOs personalizados    # Acceso a datos custom
│   └── paginaciones/              # Soporte de paginación
│
├── 📁 entidades/                  # Capa de Persistencia
│   ├── Usuarios, Roles            # Autenticación y autorización
│   ├── Personas                   # Registro de personas
│   ├── InsSedes, InsFacultades    # Estructura institucional
│   └── 100+ entidades de dominio  # Entidades de negocio
│
├── 📁 entidadesPg/                # Entidades del Módulo Postgrado
│   ├── PgEstudiantes              # Estudiantes
│   ├── PgProgramas                # Programas académicos
│   ├── PgModulos, PgMaterias      # Estructura curricular
│   └── 50+ entidades adicionales  # Dominio académico
│
├── 📁 configuracion/              # Configuraciones Spring
│   ├── SecurityConfig             # Configuración de seguridad
│   ├── SetupDataLoader            # Carga de datos iniciales
│   └── ModelMapperConfig          # Configuración de mapeo DTO
│
└── 📁 dto/                        # Data Transfer Objects
    ├── UserDto, UserUpdateDto     # DTOs de usuarios
    ├── PostulantesResponse        # Respuestas API
    └── pgDto/                     # DTOs específicos de postgrado
```

### Patrones de Diseño Implementados

| Patrón | Ubicación | Propósito |
|--------|-----------|-----------|
| **MVC** | Controladores/Vistas | Separación de presentación y lógica |
| **Service Layer** | `servicios/` | Encapsulación de lógica de negocio |
| **Repository/DAO** | `modelos/` | Abstracción de acceso a datos |
| **DTO** | `dto/` | Transferencia de datos entre capas |
| **Dependency Injection** | `@Autowired` | Inversión de control |
| **Template Method** | Thymeleaf | Reutilización de plantillas |
| **Singleton** | Spring Beans | Instancias únicas de servicios |

---

## 🔐 Seguridad

### Características de Seguridad

- **Autenticación basada en correo electrónico** - Login con email en lugar de username
- **Cifrado BCrypt** - Contraseñas hasheadas con 60 caracteres
- **Control de acceso basado en roles (RBAC)** - Separación por perfiles de usuario
- **Validación de fortaleza de contraseñas** - Implementación de zxcvbn
- **Gestión de sesiones** - Estado de sesión seguro server-side
- **CSRF Protection** - Protección contra ataques cross-site
- **SQL Injection Prevention** - Uso de JPA/PreparedStatements

### Roles del Sistema

- **ADMIN** - Acceso total al sistema y panel administrativo
- **USER** - Acceso a funciones estándar según permisos
- **Roles personalizados** - Asignables por módulo y función específica

### URLs Públicas (Sin autenticación)

```
/login, /register, /index
/css/**, /js/**, /images/**, /assets/**
/olimpiadas/** (visualización pública de competencias)
/vendor/**, /scss/**
```

### URLs Protegidas

```
/adminPage/**          → Requiere rol ADMIN
/postLogin             → Página post-autenticación
Todos los módulos      → Requiere autenticación + permisos
```

---

## 📦 Módulos del Sistema

### 🎓 Módulo de Postgrado (PG)

**Entidades principales:** `PgEstudiantes`, `PgProgramas`, `PgModulos`, `PgMaterias`, `PgDocentes`

**Funcionalidades:**
- ✅ Gestión completa de estudiantes de postgrado
- ✅ Administración de programas (Diplomados, Especializaciones, Maestrías)
- ✅ Gestión de módulos y materias por programa
- ✅ Sistema de matrícula y seguimiento de inscripciones
- ✅ Gestión de postulantes y proceso de admisión
- ✅ Control de notas y clasificación académica (Grade)
- ✅ Vinculación de docentes a módulos
- ✅ Reportes académicos y estadísticos
- ✅ Importación/exportación masiva de datos (Excel)
- ✅ Gestión de aranceles y pagos de matrícula

**Controladores:** 8 controladores especializados
- `AdministrarProgramas`, `AdministrarEstudiantes`, `AdministrarMaterias`
- `AdministrarModulos`, `AdministrarMatricula`, `AdministrarPostulantes`
- `ReportesProgramas`, `EnlaceDocentes`

---

### 📄 Módulo de Gestión Documental (GDOC)

**Entidades principales:** `GdocTitulos`, `GdocTitulados`, `GdocConsejos`, `GdocResoluciones`

**Funcionalidades:**
- ✅ Gestión de títulos académicos y diplomas
- ✅ Registro de titulados por programa
- ✅ Administración de consejos directivos
- ✅ Control de resoluciones institucionales
- ✅ Gestión de autoridades y firmas
- ✅ Soporte de resoluciones digitales
- ✅ Administración de convenios
- ✅ Reportes y certificaciones

**Controladores:** 5 controladores especializados

---

### 🏅 Módulo de Olimpiadas (OO)

**Entidades principales:** `OoCompeticiones`, `OoEnfrentamientos`, `OoEquiposParticipantes`, `OoPuntuaciones`

**Funcionalidades:**
- ✅ Organización de competencias académicas
- ✅ Gestión de equipos y participantes
- ✅ Administración de etapas y fases
- ✅ Control de enfrentamientos/encuentros
- ✅ Sistema de puntuación y ranking
- ✅ Registro de unidades educativas participantes
- ✅ Soporte de competencias multi-pregunta
- ✅ Reportes de desempeño y resultados

---

### 🏗️ Módulo de Gestión de Proyectos (SICOES)

**Entidades principales:** `SicoesProyectos`, `SicoesContratados`, `SicoesContrataciones`

**Funcionalidades:**
- ✅ Administración de proyectos institucionales
- ✅ Gestión de contratistas y proveedores
- ✅ Control de contrataciones y contratos
- ✅ Seguimiento de ejecución de proyectos
- ✅ Reportes detallados por:
  - Proyectos
  - Contratistas
  - Directivas funcionales
  - Modalidad de contratación
  - Unidades organizacionales

**Controladores:** 4 controladores + 5 módulos de reportes

---

### 💰 Módulo de Caja y Finanzas (CJA)

**Entidades principales:** `CjaProveedores`, `CjaGastosEjecutados`, `CjaPedidosGastos`, `CjaIngresos`

**Funcionalidades:**
- ✅ Control de presupuesto institucional
- ✅ Gestión de proveedores
- ✅ Registro de ingresos y clasificación
- ✅ Control de gastos ejecutados
- ✅ Gestión de pedidos de compra
- ✅ Clasificación de tipos de gasto
- ✅ Control de facturas y recibos
- ✅ Reportes financieros

---

### 👥 Módulo de Personal Administrativo (PNL)

**Entidades principales:** `PnlPersonalAdministrativos`, `PnlCargos`, `PnlItems`

**Funcionalidades:**
- ✅ Gestión de personal administrativo
- ✅ Administración de cargos y posiciones
- ✅ Clasificación de personal
- ✅ Asignación de roles institucionales
- ✅ Registro de historiales laborales

---

### 📊 Módulo de Planificación (POAIS)

**Entidades principales:** `Poais`, `PoaisActividades`, `PoaisObjetivos`, `PoaisRequisitos`

**Funcionalidades:**
- ✅ Planificación anual de actividades
- ✅ Gestión de objetivos institucionales
- ✅ Programación de actividades e hitos
- ✅ Control de requisitos y cumplimiento
- ✅ Asignación de supervisores
- ✅ Seguimiento de cualificaciones profesionales

---

### 🗳️ Módulo Electoral (ESC)

**Entidades principales:** `EscElecciones`, `EscMesas`, `EscResultados`

**Funcionalidades:**
- ✅ Administración de procesos electorales
- ✅ Gestión de mesas de votación
- ✅ Tabulación de resultados
- ✅ Generación de gráficos estadísticos
- ✅ Monitoreo de urnas

---

### 🏛️ Módulo Congresional (CNG)

**Entidades principales:** `CngCongresistas`, `CngComisiones`, `CngMesasDirectivas`

**Funcionalidades:**
- ✅ Registro de congresistas
- ✅ Gestión de comisiones parlamentarias
- ✅ Administración de mesas directivas

---

### ⚙️ Módulos Core

#### Registro Personal (PRS)
- Gestión de personas con datos demográficos completos
- Control de países, géneros, grados académicos
- Información de estado civil y documentos de identidad

#### Institucional (INS)
- Gestión de sedes institucionales
- Unidades organizacionales (funcionales y orgánicas)
- Administración de facultades y carreras

#### Menú/Navegación (MNU)
- Sistema de menús dinámicos
- Asignación de funciones por rol
- Gestión de enlaces y permisos
- Asociación de funciones a usuarios

---

## 🔧 Instalación

### Prerrequisitos

- **Java Development Kit (JDK)** 11 o superior
- **Apache Maven** 3.6+
- **PostgreSQL** 10+ instalado y ejecutándose
- **Git** para control de versiones

### 1. Clonar el repositorio

```bash
git clone https://github.com/LuchoNoPrograma/castanhero-legacy.git
cd castanhero-legacy
```

### 2. Configurar la base de datos

Crear la base de datos en PostgreSQL:

```sql
CREATE DATABASE castanhero;
CREATE USER postgres WITH PASSWORD 'admin123';
GRANT ALL PRIVILEGES ON DATABASE castanhero TO postgres;
```

### 3. Configurar application.properties

Editar `src/main/resources/application.properties`:

```properties
# Base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/castanhero
spring.datasource.username=postgres
spring.datasource.password=admin123

# Correo electrónico (opcional)
spring.mail.host=smtp.tuservidor.com
spring.mail.username=tu_email@dominio.com
spring.mail.password=tu_password
spring.mail.port=587
```

### 4. Compilar el proyecto

```bash
# Usando Maven wrapper (recomendado)
./mvnw clean install

# O usando Maven instalado
mvn clean install
```

### 5. Ejecutar la aplicación

```bash
# Opción 1: Usando Maven
mvn spring-boot:run

# Opción 2: Ejecutar JAR directamente
java -jar target/Castanhero20-0.0.1-SNAPSHOT.jar
```

### 6. Acceder a la aplicación

Abrir navegador en: **http://localhost:8080**

---

## 👤 Credenciales por Defecto

**Administrador:**
- Email: `admin@gmail.com`
- Password: `admin`

**Usuario estándar:**
- Email: `user1@gmail.com`
- Password: `user1`

> ⚠️ **IMPORTANTE:** Cambiar estas credenciales en producción

---

## 📊 Estadísticas del Proyecto

| Métrica | Valor |
|---------|-------|
| **Total de archivos Java** | 200+ |
| **Clases de entidad** | 150+ |
| **Servicios implementados** | 24+ |
| **Controladores** | 40+ |
| **Tablas de BD (estimadas)** | 100+ |
| **Líneas de código (entidades)** | 11,400+ |
| **Módulos funcionales** | 10+ |
| **Dependencias Maven** | 30+ |

---

## 🛠️ Configuración Avanzada

### Variables de entorno

```bash
# Puerto del servidor
SERVER_PORT=8080

# Base de datos
DB_HOST=localhost
DB_PORT=5432
DB_NAME=castanhero
DB_USER=postgres
DB_PASSWORD=admin123

# Perfil de Spring
SPRING_PROFILES_ACTIVE=production
```

### Perfiles de Spring

#### Desarrollo (dev)
```properties
spring.jpa.show-sql=true
spring.thymeleaf.cache=false
logging.level.org.springframework.web=DEBUG
```

#### Producción (prod)
```properties
spring.jpa.show-sql=false
spring.thymeleaf.cache=true
logging.level.org.springframework.web=ERROR
spring.jpa.hibernate.ddl-auto=validate
```

---

## 📈 Monitoreo y Métricas

El sistema incluye **Spring Boot Actuator** para monitoreo:

**Endpoints disponibles:**
- `/actuator/health` - Estado de salud de la aplicación
- `/actuator/metrics` - Métricas de rendimiento
- `/actuator/info` - Información de la aplicación

---

## 🧪 Testing

```bash
# Ejecutar tests unitarios
mvn test

# Ejecutar tests de integración
mvn verify

# Ejecutar tests con cobertura
mvn clean test jacoco:report
```

---

## 📝 Licencia

Este proyecto es propiedad de **Universidad Autónoma del Pluralismo (UAP/USIC)** y es de uso interno institucional.

---

## 👨‍💻 Desarrollo y Mantenimiento

**Organización:** UAP - Unidad de Sistemas de Información y Comunicación (USIC)
**Proyecto:** Castanhero 2.0
**Framework Base:** Spring Boot 2.5.2
**Lenguaje:** Java 11

---

## 📞 Soporte

Para soporte técnico o consultas sobre el sistema, contactar al departamento de USIC de la UAP.

---

<div align="center">

**Desarrollado con ☕ y Spring Boot**

`uap.usic.siga` | Castanhero 2.0 | 2021-2025

</div>
