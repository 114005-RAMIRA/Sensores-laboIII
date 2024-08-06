package ar.edu.utn.frc.tup.lc.iii.entities;
import ar.edu.utn.frc.tup.lc.iii.models.Unidad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "sensores")
public class SensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nombre;
    private String lugar;
    @Enumerated(EnumType.STRING)
    private Unidad unidad;

}
