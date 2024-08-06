package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.dtos.SensorDTO;
import ar.edu.utn.frc.tup.lc.iii.dtos.consultas.SensorPorCantLecturasDTO;
import ar.edu.utn.frc.tup.lc.iii.entities.SensorEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Sensor;
import ar.edu.utn.frc.tup.lc.iii.models.Unidad;
import ar.edu.utn.frc.tup.lc.iii.repositories.SensorRepository;
import ar.edu.utn.frc.tup.lc.iii.services.impl.SensorServiceImpl;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.persistence.EntityNotFoundException;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SensorServiceTest {

    @SpyBean
    SensorService sensorService;

    @MockBean
    SensorRepository sensorRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Test
    @Tag("Create")
    public void createSensor()
    {

        //DTO PARAMETRO
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setUnidad(Unidad.Celsius);
        sensorDTO.setLugar("MI CASA");
        sensorDTO.setNombre("Ariel");

        //Entity a retornar del save
        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setId(1L);
        sensorEntity.setNombre("Ariel");
        sensorEntity.setLugar("MI CASA");
        sensorEntity.setUnidad(Unidad.Celsius);

        when(sensorRepository.save(Mockito.any()))
                .thenReturn(sensorEntity);

        //Llamado
        Sensor s = sensorService.createSensor(sensorDTO);
        //Assertions
        assertEquals(sensorEntity.getId(),s.getId());
        assertEquals(sensorEntity.getLugar(),s.getLugar());
        assertEquals(sensorEntity.getNombre(),s.getNombre());
        assertEquals(sensorEntity.getUnidad(),s.getUnidad());

    }
    @Test
    @Tag("GetAll")
    public void getSensorsTest() {
        //Preparar retorno del REPOSITORIO
        SensorEntity sensor1 = new SensorEntity();
        sensor1.setId(1L);
        sensor1.setNombre("Sensor A");
        sensor1.setLugar("Lugar A");
        sensor1.setUnidad(Unidad.Celsius);

        SensorEntity sensor2 = new SensorEntity();
        sensor2.setId(2L);
        sensor2.setNombre("Sensor B");
        sensor2.setLugar("Lugar B");
        sensor2.setUnidad(Unidad.Farenheit);

        List<SensorEntity> sensors = new ArrayList<>();
        sensors.add(sensor1);
        sensors.add(sensor2);

        when(sensorRepository.findAll()).thenReturn(sensors);

        //Llamar al metodo
        List<Sensor> lstResult = sensorService.getSensors();
        //Verificar tamaño lista
        assertEquals(2, lstResult.size());
        assertEquals(modelMapper.map(sensor1,Sensor.class),lstResult.get(0));
        assertEquals(modelMapper.map(sensor2,Sensor.class),lstResult.get(1));
    }
    @Test
    @Tag("GetBy String")
    public void getByNameTest()
    {
        //Preparar retorno del REPOSITORIO
        SensorEntity sensor1 = new SensorEntity();
        sensor1.setId(1L);
        sensor1.setNombre("Sensor A");
        sensor1.setLugar("Lugar A");
        sensor1.setUnidad(Unidad.Celsius);

        when(sensorRepository.findByNombre("Sensor A"))
                .thenReturn(Optional.of(sensor1));

        Sensor result = sensorService.getByName("Sensor A");
        assertEquals(modelMapper.map(sensor1,Sensor.class),result);

    }

    @Test
    @Tag("GetBy String")
    public void getByNameTestNull()
    {
        //Preparar retorno del REPOSITORIO
        when(sensorRepository.findByNombre("Sensor A"))
                .thenReturn(Optional.empty());

        Sensor result = sensorService.getByName("Sensor A");
        assertNull(result);
    }
    @Test
    @Tag("Update")
    public void updateSensorTest()
    {
        //SensorDTO (Parametro)
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setId(1L);
        sensorDTO.setUnidad(Unidad.Celsius);
        sensorDTO.setLugar("MI CASA");
        sensorDTO.setNombre("Ariel");
        //Retorno repositorio
        SensorEntity sensorSaved =
                modelMapper.map(sensorDTO, SensorEntity.class);

        when (sensorRepository.save(Mockito.any())).
                thenReturn(sensorSaved);

        Sensor result = sensorService.updateSensor(sensorDTO);
        assertNotNull(result);
        assertEquals(modelMapper.map(sensorSaved,Sensor.class),result);

    }
    @Test
    @Tag("Update")
    public void updateSensorTestNull()
    {
        //SensorDTO (Parametro)
        SensorDTO sensorDTO = new SensorDTO();

        Sensor sensor = new Sensor();

        Sensor result = sensorService.updateSensor(sensorDTO);
        assertEquals(sensor,result);

    }
    @Test
    @Tag("Delete")
    public void deleteSensorByIdTest()
    {
        // Configurar el comportamiento esperado
        when(sensorRepository.existsById(1L)).thenReturn(true);

        // Llamar al método que estamos probando
        sensorService.deleteSensorById(1L);

        // Verificar que deleteById fue llamado con el id correcto
        verify(sensorRepository, times(1)).deleteById(1L);

    }

    @Test
    @Tag("Delete Exception")
    public void deleteSensorByIdNotFoundTest() {
        // Configurar el comportamiento esperado para cuando el ID no existe
        when(sensorRepository.existsById(1L)).thenReturn(false);

        // Verificar que se lanza la excepción correcta
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            sensorService.deleteSensorById(1L);
        });
        assertEquals("No se encontró el elemento con el ID proporcionado",
                thrown.getMessage());
    }
    @Test
    @Tag("GetSensorPerLecturasCount")
    public void getSensorPerLecturasCountTest() {

        // Preparar datos de prueba
        Object[] result1 = {10L, "Sensor 1", Unidad.Celsius, 2L};
        Object[] result2 = {11L, "Sensor 2", Unidad.Farenheit, 2L};
        Object[] result3 = {12L, "Sensor 3", Unidad.Kelvin, 2L};

        List<Object[]> results = new ArrayList<>();
        results.add(result1);
        results.add(result2);
        results.add(result3);

        // Configurar el comportamiento del repositorio
        when(sensorRepository.findSensorAndLecturaCount()).thenReturn(results);

        // Llamar al método
        List<SensorPorCantLecturasDTO> dtoList = sensorService.getSensorPerLecturasCount();

        // Verificar el tamaño de la lista
        assertEquals(3, dtoList.size());

        // Verificar el contenido de los elementos
        assertEquals(new SensorPorCantLecturasDTO(10L, "Sensor 1", "Celsius", 2L), dtoList.get(0));
        assertEquals(new SensorPorCantLecturasDTO(11L, "Sensor 2", "Farenheit", 2L), dtoList.get(1));
        assertEquals(new SensorPorCantLecturasDTO(12L, "Sensor 3", "Kelvin", 2L), dtoList.get(2));
    }
    @Test
    @Tag("GetSensorPerLecturasCount Exception")
    public void getSensorPerLecturasCountNoDataTest() {
        // Configurar el comportamiento del repositorio para que devuelva una lista vacía
        when(sensorRepository.findSensorAndLecturaCount()).thenReturn(new ArrayList<>());

        // Verificar que se lanza la excepción correcta
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            sensorService.getSensorPerLecturasCount();
        });
        assertEquals("No se encontraron sensores por cantidad de lecturas", thrown.getMessage());
    }






}
