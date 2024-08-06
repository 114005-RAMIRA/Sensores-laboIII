package ar.edu.utn.frc.tup.lc.iii.repositories;


import ar.edu.utn.frc.tup.lc.iii.entities.ShoeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoeRepository extends JpaRepository<ShoeEntity,Long> {

@Query("SELECT s " +
        "FROM ShoeEntity s WHERE " +
        "s.size BETWEEN :minSize AND :maxSize")
Optional<List<ShoeEntity>>findBetweenSizes(@Param("minSize") int minSize, @Param("maxSize") int maxSize);
}
