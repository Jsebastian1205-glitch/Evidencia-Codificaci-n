# BuildZone - Módulo JDBC

Proyecto académico para la implementación del módulo de productos de BuildZone
utilizando Java, JDBC, Maven y MySQL.

## Descripción

BuildZone es una plataforma orientada a la consulta y comparación de componentes
para PC. Este módulo implementa operaciones CRUD sobre la entidad `producto`.

## Tecnologías

- Java 17
- JDBC
- MySQL
- Maven
- Git / GitHub

## Estructura

- `config`: conexión con la base de datos.
- `model`: clases que representan las entidades.
- `dao`: acceso a datos y operaciones CRUD.
- `main`: ejecución de pruebas.
- `database`: script SQL.

## Instalación

1. Instalar Java 17.
2. Instalar MySQL.
3. Abrir MySQL Workbench.
4. Ejecutar `database/buildzone.sql`.
5. Verificar usuario y contraseña en:
   `src/main/resources/database.properties`.
6. Abrir el proyecto como proyecto Maven.
7. Ejecutar `com.buildzone.main.Main`.

## Operaciones CRUD

El `ProductoDAO` implementa:

- `insertar()`
- `consultarTodos()`
- `consultarPorId()`
- `actualizar()`
- `eliminar()`

## Git

```bash
git init
git add .
git commit -m "Inicialización del proyecto BuildZone JDBC"
git branch -M main
git remote add origin URL_DEL_REPOSITORIO
git push -u origin main
```

Posteriormente:

```bash
git add .
git commit -m "Implementación CRUD de productos"
git push
```

## Nota

El proyecto no implementa compras, pagos, pedidos ni carrito, porque BuildZone
funciona como plataforma de comparación y consulta.
