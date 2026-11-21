package cl.duoc.mpazpro.contenidos.controller;

import cl.duoc.mpazpro.contenidos.dto.ActividadRequest;
import cl.duoc.mpazpro.contenidos.model.Actividad;
import cl.duoc.mpazpro.contenidos.service.ActividadService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actividades")
public class ActividadController {

    private final ActividadService service;

    public ActividadController(ActividadService service) {
        this.service = service;
    }

    @GetMapping
    public List<Actividad> listar(@RequestParam(required = false) Integer oaId) {
        return service.listar(oaId);
    }

    @GetMapping("/{id}")
    public Actividad obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actividad crear(@Valid @RequestBody ActividadRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public Actividad actualizar(@PathVariable Integer id, @Valid @RequestBody ActividadRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
