# ⚽ Fuentes_Casanova_Aaron_Proyecto_Final_AD

## API REST – Gestión de Torneos de Fútbol  
**Asignatura:** Acceso a Datos (2º DAM)  
**Tecnologías:** Spring Boot 3.x · JPA (Hibernate) · MySQL · Swagger (OpenAPI)  
**Autor:** Aaron Fuentes Casanova  

---

## 📌 Descripción del Proyecto

Este proyecto consiste en el desarrollo de una **API REST completa** para la gestión de competiciones de fútbol, incluyendo torneos, equipos, jugadores, entrenadores y partidos.

El objetivo del proyecto no es únicamente que la API funcione, sino **demostrar dominio del diseño de modelos relacionales, relaciones complejas con JPA, lógica de negocio, paginación y buenas prácticas de arquitectura backend**.

La API está documentada mediante **Swagger/OpenAPI**, permitiendo probar todos los endpoints desde el navegador.

---

## 🎯 Objetivos de Aprendizaje Cubiertos

✔ Diseño de modelos de datos relacionales con JPA  
✔ Relaciones 1:N, 1:1 y N:M correctamente implementadas  
✔ Uso de DTOs para evitar recursividad infinita  
✔ Lógica de negocio en la capa de servicio  
✔ Paginación con `Pageable` y `Page<T>`  
✔ Arquitectura en capas (Controller / Service / Repository)  
✔ Documentación profesional con Swagger  
✔ Datos iniciales mediante `DataInitializer`  

---

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una **arquitectura en capas**, separando responsabilidades:
```

src/main/java
└── es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final
├── config → Configuración (DataInitializer, Swagger)
├── controller → Controladores REST
├── dto
│ ├── request → DTOs de entrada
│ └── response → DTOs de salida
├── exception → Gestión de errores y validaciones
├── mappers → MapStruct (Entity ↔ DTO)
├── model → Entidades JPA
├── repository → Repositorios JPA
└── service
├── interfaz
└── implementacion

```
---

## 🧱 Modelo de Datos (JPA)

### Entidades principales

- **Equipo** (Entidad principal)
- **Partido** (Entidad transaccional)
- **Jugador**
- **Entrenador**
- **Torneo**

### Relaciones implementadas

- **Equipo – Jugador** → 1:N  
- **Equipo – Entrenador** → 1:1  
- **Torneo – Equipo** → N:M  
- **Partido – Equipo** → N:1 (local y visitante)  
- **Partido – Torneo** → N:1  

✔ Uso exclusivo de `LocalDate`  
✔ Uso de DTOs para evitar recursividad infinita en JSON  

---

## ⚙️ Tecnologías Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- MySQL
- MapStruct
- Lombok
- Swagger / OpenAPI
- Maven
- Postman

---

## 🚀 Ejecución del Proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/AaronFuentesC/Fuentes_Casanova_Aaron_Proyecto_Final_AD.git
```
### 2. Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/tu_basedatos

spring.datasource.username=usuario

spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update

### 3. Ejecutar la aplicación
mvn spring-boot:run


---

## 📘 Documentación con Swagger

La API está completamente documentada con Swagger/OpenAPI.

Swagger UI:
👉 http://localhost:8080/swagger-ui.html

OpenAPI JSON:
👉 http://localhost:8080/v3/api-docs

Desde Swagger se pueden:

Ver todos los endpoints

Probar peticiones GET, POST, PUT y DELETE

Visualizar modelos y respuestas

---

## 🔗 Endpoints Principales

###Equipos

GET    /equipos

GET    /equipos/{id}

POST   /equipos

PUT    /equipos/{id}

DELETE /equipos/{id}



### Jugadores

GET    /jugadores

GET    /jugadores/{id}

POST   /jugadores

PUT    /jugadores/{id}

DELETE /jugadores/{id}

GET    /jugadores/paginados

GET    /jugadores/paginados/{nombre}



### Entrenadores

GET    /entrenadores

GET    /entrenadores/{id}

POST   /entrenadores

PUT    /entrenadores/{id}

DELETE /entrenadores/{id}



### Torneos

GET    /torneos

GET    /torneos/{id}

POST   /torneos

PUT    /torneos/{id}

DELETE /torneos/{id}

GET    /torneos/paginados


### Partidos

GET    /partidos

GET    /partidos/{id}

POST   /partidos

PUT    /partidos/{id}

DELETE /partidos/{id}

GET    /partidos/paginados

---

## 🗃️ DataInitializer

El proyecto incluye un DataInitializer que carga datos automáticamente:

Torneos:

LaLiga

Premier League

Bundesliga

Serie A

Ligue 1

Champions League

Equipos reales

Jugadores (incluyendo porteros)

Entrenadores

Partidos reales entre equipos

Esto permite probar la API sin insertar datos manualmente.

Las pruebas pueden realizarse mediante:

Swagger UI

Postman

cURL

---

👨‍💻 Autor

Aaron Fuentes Casanova
2º DAM – Acceso a Datos

