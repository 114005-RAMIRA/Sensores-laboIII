package ar.edu.utn.frc.tup.lc.iii.services;


import ar.edu.utn.frc.tup.lc.iii.dtos.common.ShoeDto;
import ar.edu.utn.frc.tup.lc.iii.models.Shoe;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public interface ShoeService {

   List<Shoe> findBetweenSizes(int min, int max);

//    Shoe getShoeById(Long id);
//
//    List<Shoe> getShoeList();
//
//    Shoe createShoe(ShoeDto shoeDto);
//
//    Shoe updateShoe(ShoeDto shoeDto);
//
//    void deleteShoe(Long id);

}
