# MPAZ PRO - Backend (microservicios)

Backend de la app Android **MPAZ PRO 2.0** (DSY1105 - Desarrollo de Aplicaciones Móviles).
Dos microservicios independientes en Spring Boot 3 (Java 17), cada uno con su propia base de datos H2 en archivo.

**Integrantes:** Miguel Pasten Medina · Fabián Aguilar

## Microservicios

| Microservicio | Puerto | Entidades | Base de datos |
|---|---|---|---|
| `ms-resultados` | 9081 | `Resultado` (tabla `resultados_prueba`) | `ms-resultados/data/resultadosdb` |
| `ms-contenidos` | 9082 | `Objetivo` (`objetivos_aprendizaje`) y `Actividad` (`actividades`) | `ms-contenidos/data/contenidosdb` |

Capas de cada microservicio: `controller` (HTTP) → `service` (reglas de negocio) → `repository` (Spring Data JPA) → `model` (entidades).
`dto` contiene los datos de entrada validados; `exception` traduce errores a respuestas 404 y 400.
Los microservicios no se llaman entre sí: `Resultado.oaId` y `Actividad.oaId` son referencias que valida la app.

## Endpoints

### ms-resultados (`http://localhost:9081`)
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/resultados` | Lista todos. Filtros opcionales: `?estudianteId=` o `?oaId=` |
| GET | `/api/resultados/{id}` | Obtiene uno (404 si no existe) |
| POST | `/api/resultados` | Crea (201) |
| PUT | `/api/resultados/{id}` | Actualiza |
| DELETE | `/api/resultados/{id}` | Elimina (204) |

Cuerpo: `{"estudianteFicticioId":1,"oaId":1,"puntaje":80,"intentosUsados":1,"fechaRegistro":1700000000000}`
Validaciones: todos obligatorios, ids positivos, `puntaje` ≥ 0, `intentosUsados` entre 1 y 5.

### ms-contenidos (`http://localhost:9082`)
| Método | Ruta | Descripción |
|---|---|---|
| GET / POST | `/api/objetivos` | Lista / crea objetivos |
| GET / PUT / DELETE | `/api/objetivos/{id}` | Obtiene / actualiza / elimina (400 si aún tiene actividades) |
| GET | `/api/actividades` | Lista actividades. Filtro opcional: `?oaId=` |
| POST | `/api/actividades` | Crea (400 si el objetivo `oaId` no existe) |
| GET / PUT / DELETE | `/api/actividades/{id}` | Obtiene / actualiza / elimina |

Objetivo: `{"asignaturaId":1,"codigoMineduc":"LE02 OA 01","descripcion":"...","autorizado":true}`
Actividad: `{"oaId":1,"titulo":"Sumas","tipoDesafio":"opcion","pregunta":"¿2+2?","respuestaCorrecta":"4","alternativaIncorrecta1":"3","alternativaIncorrecta2":"5","alternativaIncorrecta3":"6"}`

## Cómo ejecutar

Requisitos: JDK 17 y Maven. Abrir **dos terminales**:

```bash
cd ms-resultados && mvn spring-boot:run
cd ms-contenidos && mvn spring-boot:run
```

(Cada servicio también se puede abrir como proyecto Maven en el IDE y ejecutar su clase `*Application`.)
Ejecutar siempre desde la carpeta del microservicio: la base de datos se crea en su carpeta `data/`.

Pruebas unitarias del backend: `mvn test` dentro de cada carpeta.

### Ver la base de datos
Consola H2 en `http://localhost:9081/h2-console` y `http://localhost:9082/h2-console`
JDBC URL: `jdbc:h2:file:./data/resultadosdb` (o `contenidosdb`) · usuario `sa` · sin contraseña.

### Probar con curl
```bash
curl -X POST localhost:9082/api/objetivos -H "Content-Type: application/json" \
  -d '{"asignaturaId":1,"codigoMineduc":"LE02 OA 01","descripcion":"Leer palabras","autorizado":true}'
curl localhost:9082/api/objetivos
curl -X POST localhost:9081/api/resultados -H "Content-Type: application/json" \
  -d '{"estudianteFicticioId":1,"oaId":1,"puntaje":80,"intentosUsados":1,"fechaRegistro":1700000000000}'
curl localhost:9081/api/resultados
```

### Conexión desde la app
- Emulador Android: `http://10.0.2.2:9081/` y `http://10.0.2.2:9082/`
- Teléfono físico: la IP del computador en la red local (por ejemplo `http://192.168.1.10:9081/`)
- La app debe declarar el permiso `INTERNET` y permitir tráfico HTTP (`android:usesCleartextTraffic="true"`).
