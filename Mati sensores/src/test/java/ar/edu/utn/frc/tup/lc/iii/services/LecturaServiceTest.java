package ar.edu.utn.frc.tup.lc.iii.services;
import ar.edu.utn.frc.tup.lc.iii.entities.LecturaEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.SensorEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Lectura;
import ar.edu.utn.frc.tup.lc.iii.models.Sensor;
import ar.edu.utn.frc.tup.lc.iii.models.Unidad;
import ar.edu.utn.frc.tup.lc.iii.repositories.LecturaRepository;
import ar.edu.utn.frc.tup.lc.iii.services.impl.LecturaServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LecturaServiceTest {
    @InjectMocks
    LecturaServiceImpl lecturaService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    LecturaRepository lecturaRepository;

    @Test
    public void getByDate()
    {
        //Parametro date
        LocalDateTime localDateTime = LocalDateTime.now();

        //SensorEntity
        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setUnidad(Unidad.Celsius);
        sensorEntity.setId(1L);
        sensorEntity.setLugar("Papilo");
        sensorEntity.setNombre("Papa francisco");
        //LecturaEntity
        LecturaEntity lecturaEntity = new LecturaEntity();
        lecturaEntity.setFecha(localDateTime);
        lecturaEntity.setId(1L);
        lecturaEntity.setSensor(sensorEntity);
        lecturaEntity.setTemperatura(10000.0);

        when(lecturaRepository.findByFecha(localDateTime))
                .thenReturn(Optional.of(lecturaEntity));

        //Sensor
        Sensor sensor = new Sensor();
        sensor.setUnidad(Unidad.Celsius);
        sensor.setId(1L);
        sensor.setLugar("Papilo");
        sensor.setNombre("Papa francisco");

        //lectura
        Lectura lectura = new Lectura();
        lectura .setFecha(localDateTime);
        lectura.setId(1L);
        lectura.setSensor(sensor);
        lectura.setTemperatura(10000.0);

        when(modelMapper.map(any(LecturaEntity.class),
                eq(Lectura.class))).thenReturn(lectura);

        Lectura result = lecturaService.getByDate(localDateTime);
        assertEquals(lectura,result);
    }
}
