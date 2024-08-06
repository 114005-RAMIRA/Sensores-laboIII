package ar.edu.utn.frc.tup.lc.iii.repositories;
import ar.edu.utn.frc.tup.lc.iii.dtos.SensorDTO;
import ar.edu.utn.frc.tup.lc.iii.entities.LecturaEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.SensorEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Sensor;
import ar.edu.utn.frc.tup.lc.iii.models.Unidad;
import ar.edu.utn.frc.tup.lc.iii.repositories.SensorRepository;
import ar.edu.utn.frc.tup.lc.iii.services.SensorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) //Para los autowired
@DataJpaTest
public class SensorRepositoryTest {
    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Tag("FindBy")
    public void findByNombreTest()
    {

        // Crear y persistir/guardar  una entidad SensorEntity
        SensorEntity sensor = new SensorEntity();
        sensor.setNombre("Sensor Test");
        sensor.setLugar("Lugar Test");
        sensor.setUnidad(Unidad.Celsius);

        // Persistir el sensor en la base de datos
        entityManager.persistAndFlush(sensor); //Se le asigna id por identity

        // Obtener el sensor por findByNombre
        SensorEntity foundSensor =
                sensorRepository.findByNombre("Sensor Test").orElse(null);

        // Verificar que el sensor no es null y comparar los datos

        assertNotNull(foundSensor,
                "El sensor debería haber sido encontrado en la base de datos");
        assertEquals("Sensor Test", foundSensor.getNombre());
        assertEquals("Lugar Test", foundSensor.getLugar());
        assertEquals(Unidad.Celsius, foundSensor.getUnidad());

    }

    @Test
    @Tag("Delete")
    public void deleteSensorByIdTest() {
        // Crear y persistir/guardar  una entidad SensorEntity

        //Notar que no se le asigna ID
        SensorEntity sensor = new SensorEntity();
        sensor.setNombre("Sensor Test");
        sensor.setLugar("Test Location");
        sensor.setUnidad(Unidad.Celsius);

        // Persistir la entidad
        SensorEntity persistedSensor =
                entityManager.persistAndFlush(sensor); //Se le asigna id por identity

        // VERIFICAR que se GUARDO
        SensorEntity foundSensor = sensorRepository.
                findById(persistedSensor.getId()).orElse(null);

        Assertions.assertNotNull(foundSensor); //SE ENCONTRO LA ENTIDAD

        // Eliminar la entidad
        //Al ser por id puede ser el id del FoundSensor tranquilamente
        sensorRepository.deleteById(persistedSensor.getId());

        // VERIFICAR QUE persistedSensor SE ELIMINÓ
        SensorEntity deletedSensor =
                sensorRepository.findById(persistedSensor.getId())
                        .orElse(null);
        Assertions.assertNull(deletedSensor);
    }
    @Test
    @Tag("FindSensorAndLecturaCount")
    public void findSensorAndLecturaCountTest() {
        // Crear y persistir entidades SensorEntity
        SensorEntity sensor1 = new SensorEntity();
        sensor1.setNombre("Sensor 1");
        sensor1.setLugar("Sala de estar");
        sensor1.setUnidad(Unidad.Celsius);
        entityManager.persist(sensor1);

        SensorEntity sensor2 = new SensorEntity();
        sensor2.setNombre("Sensor 2");
        sensor2.setLugar("Cocina");
        sensor2.setUnidad(Unidad.Farenheit);
        entityManager.persist(sensor2);

        SensorEntity sensor3 = new SensorEntity();
        sensor3.setNombre("Sensor 3");
        sensor3.setLugar("Dormitorio");
        sensor3.setUnidad(Unidad.Kelvin);
        entityManager.persist(sensor3);

        // Crear y persistir entidades LecturaEntity
        LecturaEntity lectura1 = new LecturaEntity();
        lectura1.setSensor(sensor1);
        lectura1.setFecha(LocalDateTime.now());
        lectura1.setTemperatura(23.5);
        entityManager.persist(lectura1);

        LecturaEntity lectura2 = new LecturaEntity();
        lectura2.setSensor(sensor1);
        lectura2.setFecha(LocalDateTime.now());
        lectura2.setTemperatura(24.1);
        entityManager.persist(lectura2);

        LecturaEntity lectura3 = new LecturaEntity();
        lectura3.setSensor(sensor2);
        lectura3.setFecha(LocalDateTime.now());
        lectura3.setTemperatura(75.3);
        entityManager.persist(lectura3);

        LecturaEntity lectura4 = new LecturaEntity();
        lectura4.setSensor(sensor2);
        lectura4.setFecha(LocalDateTime.now());
        lectura4.setTemperatura(76.8);
        entityManager.persist(lectura4);

        LecturaEntity lectura5 = new LecturaEntity();
        lectura5.setSensor(sensor3);
        lectura5.setFecha(LocalDateTime.now());
        lectura5.setTemperatura(298.5);
        entityManager.persist(lectura5);

        LecturaEntity lectura6 = new LecturaEntity();
        lectura6.setSensor(sensor3);
        lectura6.setFecha(LocalDateTime.now());
        lectura6.setTemperatura(299.0);
        entityManager.persist(lectura6);

        // Llamar al método del repositorio
        List<Object[]> results = sensorRepository.findSensorAndLecturaCount();

        // Verificar el tamaño de la lista
        System.out.println("Número de resultados: " + results.size());
        for (Object[] result : results) {
            Long id = ((Number) result[0]).longValue();
            String nombre = (String) result[1];
            Unidad unidad = (Unidad) result[2];
            Long cantidadLecturas = ((Number) result[3]).longValue();
            System.out.println("ID: " + id + ", Nombre: " + nombre + ", Unidad: " + unidad + ", Cantidad Lecturas: " + cantidadLecturas);
        }

        // Verificar los resultados esperados
        assertEquals(6, results.size()); // Ajustar según los datos que esperas

        // Realiza las comprobaciones necesarias según el número de resultados y el conteo de lecturas
        // Puedes hacer assertEquals o assertTrue para comparar los datos específicos
    }




}
