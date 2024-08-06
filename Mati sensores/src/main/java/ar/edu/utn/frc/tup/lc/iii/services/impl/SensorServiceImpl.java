package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.dtos.SensorDTO;
import ar.edu.utn.frc.tup.lc.iii.dtos.consultas.SensorPorCantLecturasDTO;
import ar.edu.utn.frc.tup.lc.iii.entities.SensorEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Sensor;
import ar.edu.utn.frc.tup.lc.iii.models.Unidad;
import ar.edu.utn.frc.tup.lc.iii.repositories.SensorRepository;
import ar.edu.utn.frc.tup.lc.iii.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SensorRepository sensorRepository;

    @Override
    public Sensor createSensor(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        sensor.setLugar(sensorDTO.getLugar());
        sensor.setNombre(sensorDTO.getNombre());
        sensor.setUnidad(sensorDTO.getUnidad());

        SensorEntity sensorEntity =
                modelMapper.map(sensor,SensorEntity.class);

        SensorEntity sensorEntitySaved =
                sensorRepository.save(sensorEntity);

        return modelMapper.map(sensorEntitySaved,Sensor.class);
    }

    @Override
    public List<Sensor> getSensors() {
        List<Sensor> sensors = new ArrayList<>();
        List<SensorEntity> sensorEntities = sensorRepository.findAll();
        for (SensorEntity sensor:sensorEntities)
        {
            sensors.add(modelMapper.map(sensor,Sensor.class));
        }
        return sensors;
    }

    @Override
    public Sensor getByName(String name) {
        Optional<SensorEntity> optionalSensor =
                sensorRepository.findByNombre(name);

        if (optionalSensor.isPresent())
        {
            return modelMapper.map(optionalSensor.get(),Sensor.class);
        }
        return null;
    }

    @Override
    public Sensor updateSensor(SensorDTO sensorDTO) {

        Sensor sensor = new Sensor();
        if (sensorDTO.getId() != null)
        {
            sensor.setId(sensorDTO.getId());
            sensor.setLugar(sensorDTO.getLugar());
            sensor.setNombre(sensorDTO.getNombre());
            sensor.setUnidad(sensorDTO.getUnidad());

            SensorEntity sensorEntity =
                    modelMapper.map(sensor,SensorEntity.class);

            SensorEntity sensorEntitySaved =
                    sensorRepository.save(sensorEntity);

            return modelMapper.map(sensorEntitySaved,Sensor.class);
        }
        return sensor;
    }

    @Override
    public void deleteSensorById(Long id) {

        if (sensorRepository.existsById(id)) {
            sensorRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró el elemento con el ID proporcionado");
        }
    }

    @Override
    public List<SensorPorCantLecturasDTO> getSensorPerLecturasCount() {
        List<Object[]> results = sensorRepository.findSensorAndLecturaCount();
        List<SensorPorCantLecturasDTO> sensors = new ArrayList<>();

        for (Object[] result : results) {
            Long id = ((Number) result[0]).longValue();
            String nombre = (String) result[1];
            Unidad unidad = (Unidad) result[2];
            Long cantidadLecturas = ((Number) result[3]).longValue();

            SensorPorCantLecturasDTO dto =
                    new SensorPorCantLecturasDTO(id, nombre, unidad.toString(), cantidadLecturas);
            sensors.add(dto);
        }

        if (sensors.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron sensores por cantidad de lecturas");
        }

        return sensors;
    }

    /*
    @Override
    public List<SensorPorCantLecturasDTO> getSensorPerLecturasCount() {
        List<SensorPorCantLecturasDTO> sensors =
                sensorRepository.findSensorsWithLecturaCount();
        if (sensors.isEmpty())
        {
            throw new EntityNotFoundException("No se encontraron sensores por cantidad de lecturas");
        }
        return sensors;

    }
     */

}
