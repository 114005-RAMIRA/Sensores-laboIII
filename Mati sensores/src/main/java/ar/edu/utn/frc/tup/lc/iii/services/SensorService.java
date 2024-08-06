package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.dtos.SensorDTO;
import ar.edu.utn.frc.tup.lc.iii.dtos.consultas.SensorPorCantLecturasDTO;
import ar.edu.utn.frc.tup.lc.iii.models.Dummy;
import ar.edu.utn.frc.tup.lc.iii.models.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SensorService {
    Sensor createSensor (SensorDTO sensorDTO);
    List<Sensor> getSensors ();
    Sensor getByName (String name);
    Sensor updateSensor(SensorDTO sensorDTO);
    void deleteSensorById (Long id);
    List<SensorPorCantLecturasDTO> getSensorPerLecturasCount();

}
