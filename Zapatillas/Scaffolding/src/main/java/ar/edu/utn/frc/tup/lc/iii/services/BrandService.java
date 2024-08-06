package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.models.BrandPerShoe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BrandService {


    List<BrandPerShoe> getBrandPerCount();



//    List<Brand> getBrandList();
//
//    Brand createBrand(BrandDto brandDto);
//
//    Brand updateBrand(BrandDto brandDto);
//
//    void deleteBrand(Long id);
}
