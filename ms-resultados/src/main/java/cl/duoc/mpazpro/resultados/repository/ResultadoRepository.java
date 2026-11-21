package cl.duoc.mpazpro.resultados.repository;

import cl.duoc.mpazpro.resultados.model.Resultado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultadoRepository extends JpaRepository<Resultado, Integer> {

    List<Resultado> findByEstudianteFicticioId(Integer estudianteFicticioId);

    List<Resultado> findByOaId(Integer oaId);
}
