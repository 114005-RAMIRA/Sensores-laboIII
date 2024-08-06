package ar.edu.utn.frc.tup.lc.iii.controllers;


import ar.edu.utn.frc.tup.lc.iii.dtos.common.ShoeDto;
import ar.edu.utn.frc.tup.lc.iii.entities.ShoeEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Shoe;
import ar.edu.utn.frc.tup.lc.iii.services.ShoeService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ShoeApp/Shoe")
public class ShoeController {

    @Autowired
    private ShoeService shoeService;
    @Qualifier("modelMapper")
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getSizes/{min}/{max}")
   public ResponseEntity<List<ShoeDto>> getShoe(@PathVariable int min,@PathVariable int max){

        List<Shoe> filledList = shoeService.findBetweenSizes(min,max);

        List<ShoeDto> responseList = new ArrayList<>();


        for (Shoe shoe : filledList) {

            ShoeDto item = modelMapper.map(shoe,ShoeDto.class);

            responseList.add(item);


        }

        return ResponseEntity.ok(responseList);


  }






//    @PostMapping("/create")
//    public ResponseEntity<Shoe> createShoe(@RequestBody ShoeDto shoeDto){
//        Shoe createdShoe = shoeService.createShoe(shoeDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdShoe);
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<List<Shoe>>getShoeList(){
//
//        List<Shoe> shoes = shoeService.getShoeList();
//        return ResponseEntity.ok(shoes);
//    }
//
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteShoe(@PathVariable Long id){
//        try {
//            shoeService.deleteShoe(id);
//            return ResponseEntity.ok("Se elimino correctamente");
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar");
//        }
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Shoe> updateShoe(@RequestBody ShoeDto shoeDto){
//        //todo:completar
//        return null;
//    }




}
