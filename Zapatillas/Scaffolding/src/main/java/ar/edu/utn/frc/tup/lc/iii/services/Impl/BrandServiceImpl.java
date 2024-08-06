package ar.edu.utn.frc.tup.lc.iii.services.Impl;

import ar.edu.utn.frc.tup.lc.iii.entities.BrandEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Brand;
import ar.edu.utn.frc.tup.lc.iii.models.BrandPerShoe;
import ar.edu.utn.frc.tup.lc.iii.repositories.BrandRepository;
import ar.edu.utn.frc.tup.lc.iii.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<BrandPerShoe> getBrandPerCount() {

        List<BrandPerShoe> newList = new ArrayList<>();

        List<Object[]> filledList = brandRepository.findBrandAndShoeCount().orElse(null); //cine

        if (filledList == null ) {
            return null;
        }

        for(Object[] row : filledList) {

            BrandEntity brandEntity = (BrandEntity) row[0];

            Long count = (Long) row[1];

            BrandPerShoe item = new BrandPerShoe();

            item.setCount(count);
            item.setBrand(modelMapper.map(brandEntity, Brand.class));

            newList.add(item);
        }
        return newList;
    }


//    @Override
//    public List<Brand> getBrandList() {
//        List<BrandEntity> shoeEntities = brandRepository.findAll();
//        List<Brand> brandModelList = new ArrayList<>();
//        for(BrandEntity entity : shoeEntities){
//            brandModelList.add(modelMapper.map(entity,Brand.class));
//        }
//        return  brandModelList;
//    }
//
//    @Override
//    public Brand createBrand(BrandDto brandDto) {
//        BrandEntity brandEntity = modelMapper.map(brandDto,BrandEntity.class);
//        BrandEntity savedBrandEntity = brandRepository.save(brandEntity);
//        return modelMapper.map(savedBrandEntity,Brand.class);
//    }
//
//    @Override
//    public Brand updateBrand(BrandDto brandDto) {
//
//        // Paso 1: Buscar la marca existente por ID
//        BrandEntity existingBrand = brandRepository.findById(brandDto.getBrandId())
//                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));
//
//        // Paso 2: Actualizar los campos de la marca
//        existingBrand.setBrandName(brandDto.getBrandName());
//
//        // Paso 3: Guardar la marca actualizada
//        BrandEntity updatedBrand = brandRepository.save(existingBrand);
//
//        // Paso 4: Devolver la entidad Brand actualizada
//        return modelMapper.map(updatedBrand, Brand.class);
//    }
//
//    @Override
//    public void deleteBrand(Long id) {
//        if (brandRepository.existsById(id)){
//            brandRepository.deleteById(id);
//        }else {
//            throw new RuntimeException("No se encontro el elemento");
//        }
//    }
}
