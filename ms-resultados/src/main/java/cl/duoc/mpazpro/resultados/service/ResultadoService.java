package cl.duoc.mpazpro.resultados.service;

import cl.duoc.mpazpro.resultados.dto.ResultadoRequest;
import cl.duoc.mpazpro.resultados.exception.RecursoNoEncontradoException;
import cl.duoc.mpazpro.resultados.model.Resultado;
import cl.duoc.mpazpro.resultados.repository.ResultadoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/** Capa de negocio: coordina las operaciones y delega el acceso a datos en el repositorio. */
@Service
public class ResultadoService {

    private final ResultadoRepository repository;

    public ResultadoService(ResultadoRepository repository) {
        this.repository = repository;
    }

    public List<Resultado> listar(Integer estudianteId, Integer oaId) {
        if (estudianteId != null) {
            return repository.findByEstudianteFicticioId(estudianteId);
        }
        if (oaId != null) {
            return repository.findByOaId(oaId);
        }
        return repository.findAll();
    }

    public Resultado obtener(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El resultado " + id + " no existe"));
    }

    public Resultado crear(ResultadoRequest request) {
        return repository.save(copiarDatos(new Resultado(), request));
    }

    public Resultado actualizar(Integer id, ResultadoRequest request) {
        Resultado existente = obtener(id);
        return repository.save(copiarDatos(existente, request));
    }

    public void eliminar(Integer id) {
        repository.delete(obtener(id));
    }

    private Resultado copiarDatos(Resultado destino, ResultadoRequest request) {
        destino.setEstudianteFicticioId(request.estudianteFicticioId());
        destino.setOaId(request.oaId());
        destino.setPuntaje(request.puntaje());
        destino.setIntentosUsados(request.intentosUsados());
        destino.setFechaRegistro(request.fechaRegistro());
        return destino;
    }
}
