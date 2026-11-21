package cl.duoc.mpazpro.contenidos.service;

import cl.duoc.mpazpro.contenidos.dto.ObjetivoRequest;
import cl.duoc.mpazpro.contenidos.exception.RecursoNoEncontradoException;
import cl.duoc.mpazpro.contenidos.exception.ReglaNegocioException;
import cl.duoc.mpazpro.contenidos.model.Objetivo;
import cl.duoc.mpazpro.contenidos.repository.ActividadRepository;
import cl.duoc.mpazpro.contenidos.repository.ObjetivoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoService {

    private final ObjetivoRepository objetivoRepository;
    private final ActividadRepository actividadRepository;

    public ObjetivoService(ObjetivoRepository objetivoRepository, ActividadRepository actividadRepository) {
        this.objetivoRepository = objetivoRepository;
        this.actividadRepository = actividadRepository;
    }

    public List<Objetivo> listar() {
        return objetivoRepository.findAll();
    }

    public Objetivo obtener(Integer id) {
        return objetivoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El objetivo " + id + " no existe"));
    }

    public Objetivo crear(ObjetivoRequest request) {
        return objetivoRepository.save(copiarDatos(new Objetivo(), request));
    }

    public Objetivo actualizar(Integer id, ObjetivoRequest request) {
        Objetivo existente = obtener(id);
        return objetivoRepository.save(copiarDatos(existente, request));
    }

    /** Regla de negocio: no se puede borrar un objetivo que todavía tiene actividades. */
    public void eliminar(Integer id) {
        Objetivo existente = obtener(id);
        if (actividadRepository.existsByOaId(id)) {
            throw new ReglaNegocioException("No se puede eliminar el objetivo " + id + " porque tiene actividades asociadas");
        }
        objetivoRepository.delete(existente);
    }

    private Objetivo copiarDatos(Objetivo destino, ObjetivoRequest request) {
        destino.setAsignaturaId(request.asignaturaId());
        destino.setCodigoMineduc(request.codigoMineduc());
        destino.setDescripcion(request.descripcion());
        destino.setAutorizado(Boolean.TRUE.equals(request.autorizado()));
        return destino;
    }
}
