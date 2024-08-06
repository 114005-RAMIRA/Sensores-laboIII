package ar.edu.utn.frc.tup.lc.iii.services.Impl;

import ar.edu.utn.frc.tup.lc.iii.dtos.common.ShoeDto;
import ar.edu.utn.frc.tup.lc.iii.entities.BrandEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.ShoeEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Brand;
import ar.edu.utn.frc.tup.lc.iii.models.Shoe;
import ar.edu.utn.frc.tup.lc.iii.repositories.BrandRepository;
import ar.edu.utn.frc.tup.lc.iii.repositories.ShoeRepository;
import ar.edu.utn.frc.tup.lc.iii.services.ShoeService;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.decimal.DecimalMaxValidatorForNumber;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoeServiceImpl implements ShoeService {

    @Autowired
    private ShoeRepository shoeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Shoe> findBetweenSizes(int min, int max) {

      Optional<List<ShoeEntity>>filledList = shoeRepository.findBetweenSizes(min,max);

      List<Shoe> responseList = new ArrayList<>();

      if (filledList.isEmpty()){
          throw new EntityNotFoundException("NO ENCONTRADO");
      }

      for(ShoeEntity shoe : filledList.get()){

        Shoe item = modelMapper.map(shoe,Shoe.class);

        responseList.add(item);
      }
       return responseList;
    }


//    @Override
//    public Shoe getShoeById(Long id) {
//        Optional<ShoeEntity> shoeEntityOptional = shoeRepository.findById(id);
//        if (shoeEntityOptional.isPresent()) {
//            return modelMapper.map(shoeEntityOptional.get(),Shoe.class);
//        }
//        throw new RuntimeException("No se encontro el elemento");
//    }
//
//
//
//
//
//    @Override
//    public List<Shoe> getShoeList() {
//        List<ShoeEntity> shoeEntities = shoeRepository.findAll();
//        List<Shoe> shoeModelList = new ArrayList<>();
//        for(ShoeEntity entity : shoeEntities){
//            shoeModelList.add(modelMapper.map(entity,Shoe.class));
//        }
//        return  shoeModelList;
//    }
//
//
//
//
//
////    @Override
////    public Shoe createShoe(ShoeDto shoeDto) {
////        ShoeEntity shoeEntity = modelMapper.map(shoeDto,ShoeEntity.class);
////        ShoeEntity savedShoeEntity = shoeRepository.save(shoeEntity);
////        return modelMapper.map(savedShoeEntity,Shoe.class);
////    }
//
//    @Override
//    public Shoe createShoe(ShoeDto shoeDto) {
//        // Buscar la marca existente por su ID
//        BrandEntity brandEntity = brandRepository.findById(shoeDto.getBrand().getBrandId())
//                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));
//
//        // Mapear el ShoeDto a ShoeEntity
//        ShoeEntity shoeEntity = modelMapper.map(shoeDto, ShoeEntity.class);
//
//        // Asignar la marca encontrada a la entidad de la zapatilla
//        shoeEntity.setBrand(brandEntity);
//
//        // Guardar la nueva entidad de zapatilla
//        ShoeEntity savedShoeEntity = shoeRepository.save(shoeEntity);
//
//        // Mapear la entidad guardada a un objeto de dominio Shoe y devolverla
//        return modelMapper.map(savedShoeEntity, Shoe.class);
//    }




//    @Override
//    public Shoe updateShoe(ShoeDto shoeDto) {
//        //todo:completar
//        return null;
//    }
//
//
//
//
//
//
//    @Override
//    public void deleteShoe(Long id) {
//
//        if (shoeRepository.existsById(id)){
//            shoeRepository.deleteById(id);
//        }else {
//            throw new RuntimeException("No se encontro el elemento");
//        }
//    }
}
