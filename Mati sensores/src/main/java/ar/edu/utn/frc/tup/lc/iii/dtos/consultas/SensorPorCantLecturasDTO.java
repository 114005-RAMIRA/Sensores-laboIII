package ar.edu.utn.frc.tup.lc.iii.dtos.consultas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorPorCantLecturasDTO {
    private Long id;
    private String nombre;
    private String unidad;
    private Long cantidadLecturas;
}
