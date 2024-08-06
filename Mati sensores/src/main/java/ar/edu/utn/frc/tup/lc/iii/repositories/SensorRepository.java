package ar.edu.utn.frc.tup.lc.iii.repositories;

import ar.edu.utn.frc.tup.lc.iii.dtos.consultas.SensorPorCantLecturasDTO;
import ar.edu.utn.frc.tup.lc.iii.entities.DummyEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface SensorRepository extends JpaRepository<SensorEntity,Long> {

    List<SensorEntity> findAll();
    Optional<SensorEntity> findByNombre(String name);
    void deleteById(Long id);

    //@Query("SELECT new ar.edu.utn.frc.tup.lc.iii.dtos.consultas.SensorPorCantLecturasDTO" +
    //       "(s.id, s.nombre, s.unidad, COUNT(l.sensor)) " +
    //       "FROM SensorEntity s " +
    //       "LEFT JOIN LecturaEntity l ON s.id = l.sensor.id " +
    //       "GROUP BY s.id, s.nombre, s.unidad " +
    //       "ORDER BY COUNT(l.sensor) DESC")
    //List<SensorPorCantLecturasDTO> findSensorsWithLecturaCount();

    @Query("SELECT s.id, s.nombre, s.unidad, COUNT(l.id) " +
            "FROM SensorEntity s " +
            "LEFT JOIN LecturaEntity l ON s.id = l.sensor.id " +
            "GROUP BY s.id, s.nombre, s.unidad " +
            "ORDER BY COUNT(l.id) DESC")
    List<Object[]> findSensorAndLecturaCount();

}
