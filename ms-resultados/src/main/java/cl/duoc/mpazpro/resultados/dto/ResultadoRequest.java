package cl.duoc.mpazpro.resultados.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/** Datos de entrada. No incluye id: lo genera el servidor. */
public record ResultadoRequest(
        @NotNull @Positive Integer estudianteFicticioId,
        @NotNull @Positive Integer oaId,
        @NotNull @Min(0) Integer puntaje,
        @NotNull @Min(1) @Max(5) Integer intentosUsados,
        @NotNull @Positive Long fechaRegistro
) {
}
