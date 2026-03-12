# 🛒 Proyecto Spring Boot – Gestión de Productos y Categorías

## 📌 Descripción

Este proyecto es una aplicación web desarrollada con **Spring Boot** que permite gestionar **productos y categorías** mediante una interfaz web.

La aplicación incluye **autenticación y control de acceso por roles**, permitiendo diferentes permisos según el tipo de usuario.

El sistema utiliza:

- Spring Boot
- Spring Security
- Spring Data JPA
- Thymeleaf
- Hibernate
- Maven

El objetivo del proyecto es practicar el desarrollo de aplicaciones **backend con Spring**, aplicando arquitectura por capas, control de seguridad y persistencia de datos.

---

# ⚙️ Funcionalidades principales

La aplicación permite:

- Gestionar **categorías**
- Gestionar **productos**
- Navegar por las categorías y ver sus productos
- Autenticación de usuarios
- Control de permisos según rol
- Manejo de excepciones personalizadas
- Inicialización automática de datos al arrancar la aplicación

---

# 👥 Sistema de roles

La aplicación tiene dos tipos de usuarios:

| Rol | Descripción |
|----|----|
| ADMIN | Administrador con acceso completo |
| USER | Usuario normal con acceso limitado |

---

# 🔐 Usuarios creados automáticamente

Al iniciar la aplicación por primera vez, el sistema crea automáticamente dos usuarios para poder probar la aplicación.

| Usuario | Contraseña | Rol |
|-------|-------|-------|
| admin | 1234 | ADMIN |
| usuario | 1234 | USER |

Las contraseñas se almacenan cifradas mediante **Spring Security y BCrypt**.

---

# 👨‍💼 Permisos del ADMIN

El usuario **ADMIN** puede:

- Ver todas las categorías
- Crear nuevas categorías
- Editar categorías
- Borrar categorías (excepto la categoría General)
- Ver productos
- Crear productos
- Editar productos
- Borrar productos

Es el usuario con control total del sistema.

---

# 👤 Permisos del USER

El usuario **USER** tiene permisos más limitados.

Puede:

- Ver la lista de categorías
- Acceder a una categoría concreta
- Ver los productos de cada categoría
- Ver los detalles de un producto

No puede:

- Crear categorías
- Editar categorías
- Borrar categorías
- Crear productos
- Editar productos
- Borrar productos

Esto se controla mediante **Spring Security y autorización por roles**.

---

# 🗂 Inicialización automática de datos

El proyecto incluye una clase llamada **DataInitializer** que se ejecuta automáticamente al iniciar la aplicación.

Esta clase crea datos iniciales necesarios para el funcionamiento del sistema.

### Categoría inicial

Se crea automáticamente la categoría:

- **General**

Esta categoría tiene reglas especiales:

- ❌ No se puede borrar
- ❌ No se puede modificar
- ✅ Los productos sin categoría se asignan automáticamente a esta categoría

Esto evita que los productos se queden sin categoría.

---



# 🧠 Reglas de negocio

El sistema incluye varias validaciones implementadas en los servicios.

### Categorías

- No se pueden crear categorías con el mismo nombre
- No se puede borrar la categoría **General**
- No se puede modificar la categoría **General**

### Productos

- Los productos pertenecen a una categoría
- Si una categoría se elimina, sus productos pasan automáticamente a la categoría **General**

---

# 🧱 Arquitectura del proyecto

El proyecto sigue la arquitectura típica de aplicaciones Spring:

### Controller

Gestiona las peticiones HTTP y las vistas.

### Service

Contiene la lógica de negocio y validaciones.

### Repository

Gestiona el acceso a la base de datos mediante **Spring Data JPA**.

### Model

Define las entidades del sistema:

- Producto
- Categoría
- Usuario

---

# ▶️ Cómo ejecutar el proyecto

1. Clonar el repositorio
   git clone https://github.com/SashaBidardel/proyecto_spring_productos.git
2. Crear la base de datos en MySQL: Abrir **MySQL Workbench** o la consola de MySQL y ejecutar: CREATE DATABASE productos;
3. Configurar las credenciales de la base de datos en el archivo: **src/main/resources/application.properties**
4. Configurar el usuario y contraseña de MySQL:
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
5. Abrir el proyecto en un IDE (IntelliJ, Eclipse, etc.)
6.  Ejecutar la aplicación Spring Boot
7. Abrir en el navegador: http://localhost:8080




---

# 🎯 Objetivo del proyecto

Este proyecto ha sido desarrollado como práctica para aprender:

- Desarrollo de aplicaciones web con **Spring Boot**
- Control de acceso con **Spring Security**
- Persistencia con **JPA / Hibernate**
- Uso de **Thymeleaf** para vistas
- Arquitectura por capas
- Manejo de excepciones
- Inicialización automática de datos

---

# 👨‍💻 Autor

**Sasha Bidardel Fattahi**