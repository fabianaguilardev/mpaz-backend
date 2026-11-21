package cl.duoc.mpazpro.contenidos.repository;

import cl.duoc.mpazpro.contenidos.model.Actividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {

    List<Actividad> findByOaId(Integer oaId);

    boolean existsByOaId(Integer oaId);
}
