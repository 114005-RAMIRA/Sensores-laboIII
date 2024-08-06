package ar.edu.utn.frc.tup.lc.iii.models;
import ar.edu.utn.frc.tup.lc.iii.entities.SensorEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

// Anotaciones de Lombok para generar automáticamente el constructor con todos los argumentos,
// el constructor sin argumentos, y los métodos getters y setters.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lectura {
    private Long id;
    private Sensor sensor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime fecha;
    private Double temperatura;
}
