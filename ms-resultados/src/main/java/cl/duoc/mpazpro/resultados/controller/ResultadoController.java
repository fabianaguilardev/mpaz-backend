package cl.duoc.mpazpro.resultados.controller;

import cl.duoc.mpazpro.resultados.dto.ResultadoRequest;
import cl.duoc.mpazpro.resultados.model.Resultado;
import cl.duoc.mpazpro.resultados.service.ResultadoService;
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

/** Recibe las solicitudes HTTP y construye las respuestas. La lógica vive en el Service. */
@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    private final ResultadoService service;

    public ResultadoController(ResultadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Resultado> listar(@RequestParam(required = false) Integer estudianteId,
                                  @RequestParam(required = false) Integer oaId) {
        return service.listar(estudianteId, oaId);
    }

    @GetMapping("/{id}")
    public Resultado obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resultado crear(@Valid @RequestBody ResultadoRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public Resultado actualizar(@PathVariable Integer id, @Valid @RequestBody ResultadoRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
