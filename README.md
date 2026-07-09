# 📚 Biblioteca API

API REST para gestión de biblioteca construida con Java y Spring Boot,
desplegada en producción con Docker y CI/CD automatizado.

## 🌐 Demo en vivo
**URL:** `https://biblioteca-api-l26u.onrender.com/api/libros`

## 🛠️ Tecnologías
- **Java 21** + **Spring Boot 3.3**
- **PostgreSQL** — base de datos relacional
- **JPA/Hibernate** — ORM para persistencia
- **Docker** — containerización con multi-stage build
- **GitHub Actions** — CI/CD pipeline automatizado
- **Render** — plataforma de deploy en producción
- **JUnit 5 + Mockito** — tests unitarios e integración

## 📋 Funcionalidades
- ✅ CRUD completo de libros
- ✅ Validaciones con mensajes de error claros
- ✅ Manejo global de excepciones
- ✅ DTOs para separar capas de la API
- ✅ Tests unitarios y de integración
- ✅ Deploy automático en cada push a master

## 🏗️ Arquitectura
```
Controller → Service → Repository → PostgreSQL
```
Arquitectura en capas con separación de responsabilidades.

## 🚀 Endpoints
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/libros | Listar todos los libros |
| GET | /api/libros/{id} | Obtener libro por ID |
| GET | /api/libros/disponibles | Listar disponibles |
| POST | /api/libros | Crear libro |
| PUT | /api/libros/{id} | Actualizar libro |
| DELETE | /api/libros/{id} | Eliminar libro |

## 📦 Ejecutar localmente

### Con Docker (recomendado)
```bash
git clone https://github.com/XimenaPov/biblioteca-api
cd biblioteca-api
docker compose up --build
```
API disponible en: `http://localhost:8080`

### Sin Docker
```bash
# Requiere Java 21 y PostgreSQL instalados
./mvnw spring-boot:run
```

## 🧪 Ejecutar tests
```bash
./mvnw test
```

## 📁 Estructura del proyecto
```
src/
├── main/java/com/ximena/bibliotecaapi/
│   ├── controller/    # Endpoints HTTP
│   ├── service/       # Lógica de negocio
│   ├── repository/    # Acceso a datos
│   ├── model/         # Entidades JPA
│   ├── dto/           # Objetos de transferencia
│   └── exception/     # Manejo de errores
└── test/              # Tests unitarios e integración
```

## 👩‍💻 Desarrollado por
**Ximena Poveda** — Desarrolladora Backend Java
[GitHub](https://github.com/XimenaPov)