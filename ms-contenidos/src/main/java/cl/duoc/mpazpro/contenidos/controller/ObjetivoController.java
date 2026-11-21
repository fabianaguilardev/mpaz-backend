package cl.duoc.mpazpro.contenidos.controller;

import cl.duoc.mpazpro.contenidos.dto.ObjetivoRequest;
import cl.duoc.mpazpro.contenidos.model.Objetivo;
import cl.duoc.mpazpro.contenidos.service.ObjetivoService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/objetivos")
public class ObjetivoController {

    private final ObjetivoService service;

    public ObjetivoController(ObjetivoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Objetivo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Objetivo obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Objetivo crear(@Valid @RequestBody ObjetivoRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public Objetivo actualizar(@PathVariable Integer id, @Valid @RequestBody ObjetivoRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
