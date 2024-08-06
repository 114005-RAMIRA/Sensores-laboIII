package ar.edu.utn.frc.tup.lc.iii.services;
import ar.edu.utn.frc.tup.lc.iii.dtos.LecturaDTO;
import ar.edu.utn.frc.tup.lc.iii.dtos.SensorDTO;
import ar.edu.utn.frc.tup.lc.iii.entities.LecturaEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Dummy;
import ar.edu.utn.frc.tup.lc.iii.models.Lectura;
import ar.edu.utn.frc.tup.lc.iii.models.Sensor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public interface LecturaService {
    Lectura createLectura (LecturaDTO lecturaDTO);
    List<Lectura> getLecturas();
    Lectura getByDate(LocalDateTime localDateTime);
    void deleteLecturaById(Long id);
    Lectura updateLectura (LecturaDTO lecturaDTO);
}
