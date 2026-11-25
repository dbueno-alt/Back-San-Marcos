# Back San Marcos

API Spring Boot para registrar, consultar, editar y archivar citas de la Veterinaria San Marcos. Auth administra las cuentas; este servicio valida los JWT y gestiona las citas.

## Requisitos

- Java 17 o superior.
- MySQL.
- El servicio Auth disponible para emitir los JWT.

## Configuración local

El servicio usa el puerto `8082`. Configura `DB_URL`, `DB_USER`, `DB_PASSWORD` y `JWT_SECRET` en el entorno local. `JWT_SECRET` debe coincidir con el valor configurado en Auth. CORS usa `APP_CORS_ALLOWED_ORIGIN` y, por defecto, permite `http://localhost:5173`.

No guardar contraseñas ni el secreto JWT en archivos versionados.

## Ejecución en Windows

Desde esta carpeta, en PowerShell:

```text
.\mvnw.cmd spring-boot:run
```

## Endpoints principales

- `/api/v1/citas`: CRUD de las citas propias del usuario autenticado.
- `/api/v1/citas/historial`: historial archivado del usuario.
- `/api/v1/admin/citas`: listado administrativo y cambio de estado; requiere rol `ADMIN`.

Swagger UI está disponible en `http://localhost:8082/swagger-ui/index.html`.

El perfil `prod` usa `ACT_DB_URL`, `ACT_DB_USER`, `ACT_DB_PASSWORD`, `JWT_SECRET` y `APP_CORS_ALLOWED_ORIGIN`. También limita el servicio a `127.0.0.1` para publicarlo mediante Nginx.
