package ar.edu.utn.frc.tup.lc.iii.repositories;
import ar.edu.utn.frc.tup.lc.iii.entities.DummyEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.LecturaEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.SensorEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Lectura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LecturaRepository extends JpaRepository<LecturaEntity,Long>{
    List<LecturaEntity> findAll();
    Optional<LecturaEntity> findByFecha(LocalDateTime localDateTime);
    void deleteById(Long id);
}
