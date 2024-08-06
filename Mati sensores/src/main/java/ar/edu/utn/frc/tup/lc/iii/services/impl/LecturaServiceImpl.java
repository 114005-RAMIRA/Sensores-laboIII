package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.dtos.LecturaDTO;
import ar.edu.utn.frc.tup.lc.iii.dtos.SensorDTO;
import ar.edu.utn.frc.tup.lc.iii.entities.LecturaEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.SensorEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Lectura;
import ar.edu.utn.frc.tup.lc.iii.models.Sensor;
import ar.edu.utn.frc.tup.lc.iii.repositories.LecturaRepository;
import ar.edu.utn.frc.tup.lc.iii.repositories.SensorRepository;
import ar.edu.utn.frc.tup.lc.iii.services.LecturaService;
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
public class LecturaServiceImpl implements LecturaService {
    @Autowired
    LecturaRepository lecturaRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SensorRepository sensorRepository;
    @Override
    public Lectura createLectura(LecturaDTO lecturaDTO) {

        Long id_sensor = lecturaDTO.getSensor().getId();
        LecturaEntity lectura = new LecturaEntity();
        //Verifico que el sensor coincida en la base de datos
        //Y que no venga nulo
        if (id_sensor != null)
        {
            SensorEntity sensorEntity =
                    sensorRepository.getOne(id_sensor);
            if (sensorEntity != null || sensorEntity.getId() != null)
            {

                lectura.setFecha(lecturaDTO.getDate());
                lectura.setSensor(sensorEntity);
                lectura.setTemperatura(lecturaDTO.getTemperatura());
                LecturaEntity lecturaEntitySaved = lecturaRepository.save(lectura);
                return modelMapper.map(lecturaEntitySaved,Lectura.class);
            }
        }
        return null;
    }

    @Override
    public List<Lectura> getLecturas() {
        List<Lectura> lecturas = new ArrayList<>();
        List<LecturaEntity> lecturaEntities = lecturaRepository.findAll();
        for (LecturaEntity sensor:lecturaEntities)
        {
            lecturas.add(modelMapper.map(sensor,Lectura.class));
        }
        return lecturas;
    }

    @Override
    public Lectura getByDate(LocalDateTime localDateTime) {

        Optional<LecturaEntity> optionalLectura =
                lecturaRepository.findByFecha(localDateTime);

        if (optionalLectura.isPresent())
        {
            return modelMapper.map(optionalLectura.get(),Lectura.class);
        }
        return null;
    }

    @Override
    public void deleteLecturaById(Long id) {
        lecturaRepository.deleteById(id);
    }

    @Override
    public Lectura updateLectura(LecturaDTO lecturaDTO) {

        Long id_sensor = lecturaDTO.getSensor().getId();
        LecturaEntity lectura = new LecturaEntity();
        //Verifico que el sensor coincida en la base de datos
        //Y que el Lectura exista en la base de datos
        //Y que no venga nulo
        if (id_sensor != null && lecturaDTO.getId() != null)
        {
            SensorEntity sensorEntity =
                    sensorRepository.getOne(id_sensor);
            LecturaEntity lecturaEntity =
                    lecturaRepository.getReferenceById(lecturaDTO.getId());
            if (lecturaEntity != null || lecturaEntity.getId()!=null)
            {
                if (sensorEntity != null || sensorEntity.getId() != null)
                {
                    lectura.setId(lecturaDTO.getId());
                    lectura.setFecha(lecturaDTO.getDate());
                    lectura.setSensor(sensorEntity);
                    lectura.setTemperatura(lecturaDTO.getTemperatura());
                    LecturaEntity lecturaEntitySaved = lecturaRepository.save(lectura);
                    return modelMapper.map(lecturaEntitySaved,Lectura.class);
                }
            }
        }
        return null;
    }
}
