package ar.edu.utn.frc.tup.lc.iii.controllers;


import ar.edu.utn.frc.tup.lc.iii.dtos.common.BrandDto;
import ar.edu.utn.frc.tup.lc.iii.models.BrandPerShoe;
import ar.edu.utn.frc.tup.lc.iii.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ShoeApp/Brand")
public class BrandController {

    @Autowired
    BrandService brandService;
    @Qualifier("modelMapper")
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/getShoeCount")
    public ResponseEntity<List<BrandDto>> getShoeCount() {

        List<BrandPerShoe> filledList = brandService.getBrandPerCount();

        List<BrandDto> response = new ArrayList<>();

        for (BrandPerShoe brandPerShoe : filledList) {

            BrandDto item = new BrandDto();
            item.setCount(brandPerShoe.getCount());
            item.setBrandName(brandPerShoe.getBrand().getBrandName());

            response.add(item);

        }
        return ResponseEntity.ok(response);
    }




//    @PostMapping("/create")
//    public ResponseEntity<Brand> createBrand(@RequestBody BrandDto brandDto){
//        Brand createdBrand = brandService.createBrand(brandDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
//    }
//
//
//    @GetMapping("/getAll")
//    public ResponseEntity<List<Brand>> getBrandList(){
//        List<Brand> brands = brandService.getBrandList();
//        return ResponseEntity.ok(brands);
//    }
//
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteBrand(@PathVariable Long id){
//        try {
//            brandService.deleteBrand(id);
//            return ResponseEntity.ok("Se elimino correctamente");
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar");
//        }
//    }
//
////    @PutMapping("/update")
////    public ResponseEntity<Brand> updateBrand(@RequestBody BrandDto brandDto) {
////        try {
////            // Llamar al servicio para actualizar la marca
////            Brand updatedBrand = brandService.updateBrand(brandDto);
////
////            // Devolver la marca actualizada con un código de estado HTTP 200 OK
////            return ResponseEntity.ok(updatedBrand);
////        } catch (EntityNotFoundException e) {
////            // Si la marca no se encuentra, devolver un código de estado HTTP 404 Not Found
////            return ResponseEntity.notFound().build();
////        }
////    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Brand> updateBrand(
//            @PathVariable("id") Long id,
//            @RequestBody BrandDto brandDto) {
//        try {
//            // Verificar que el ID del DTO coincide con el ID de la URL
//            if (!id.equals(brandDto.getBrandId())) {
//                return ResponseEntity.badRequest().build(); // Devuelve un 400 Bad Request si los IDs no coinciden
//            }
//
//            // Llamar al servicio para actualizar la marca
//            Brand updatedBrand = brandService.updateBrand(brandDto);
//
//            // Devolver la marca actualizada con un código de estado HTTP 200 OK
//            return ResponseEntity.ok(updatedBrand);
//        } catch (EntityNotFoundException e) {
//            // Si la marca no se encuentra, devolver un código de estado HTTP 404 Not Found
//            return ResponseEntity.notFound().build();
//        }
//    }


}
