package ar.edu.utn.frc.tup.lc.iii.repositories;

import ar.edu.utn.frc.tup.lc.iii.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity,Long> {

    @Query("SELECT b,COUNT (s.id) " +
            "FROM BrandEntity b " +
            "LEFT JOIN ShoeEntity s " +
            "ON b.id = s.brand.id " +
            "GROUP BY b.id ORDER BY COUNT (s.id) DESC")
    Optional<List<Object[]>> findBrandAndShoeCount();

}
