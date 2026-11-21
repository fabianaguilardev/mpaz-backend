package cl.duoc.mpazpro.contenidos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/** Datos de entrada de un objetivo. No incluye id: lo genera el servidor. */
public record ObjetivoRequest(
        @NotNull @Positive Integer asignaturaId,
        @NotBlank String codigoMineduc,
        @NotBlank String descripcion,
        Boolean autorizado
) {
}
