package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.dtos.MonsterDto;
import ar.edu.utn.frc.tup.lc.iii.entities.MonsterEntity;
import ar.edu.utn.frc.tup.lc.iii.models.MonsterModel;
import ar.edu.utn.frc.tup.lc.iii.repositories.MonsterRepository;
import org.h2.command.dml.MergeUsing;
import org.hibernate.mapping.Any;
import org.hibernate.query.results.complete.ModelPartReferenceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


//1
@SpringBootTest
class MonsterServiceImplTest {

//se mockea en funcion de la necesidad de un comportamiento especifico de una clase

    @InjectMocks
    MonsterServiceImpl monsterService;

    @Mock
    MonsterRepository monsterRepository;

    @Mock
    ModelMapper modelMapper;


    @Test
    void getMonsterTest_Null(){

        //Optional<MonsterEntity> monsterEntityOptional = monsterRepository.findById(id);
        MonsterEntity monsterEntity = new MonsterEntity();
        monsterEntity.setId(1L);
        monsterEntity.setSabor("Test sabor");
        when(monsterRepository.findById(1L)).thenReturn(Optional.empty());

//        //--------------------------------------------------------------------------------
//
//
//        // return modelMapper.map(monsterEntityOptional.get(), MonsterModel.class);
//        MonsterModel monsterModelReturn = new MonsterModel();
//        monsterModelReturn.setId(1L);
//        monsterModelReturn.setSabor("Test sabor");
//
//        when(modelMapper.map(monsterEntity,MonsterModel.class)).thenReturn(monsterModelReturn);
//
//        //--------------------------------------------------------------------------------

        MonsterModel result = monsterService.getMonster(1L);

        assertEquals(null,result); //el primar valor es el esperado
    }



    @Test
   void getMonsterTest(){

       //Optional<MonsterEntity> monsterEntityOptional = monsterRepository.findById(id);
       MonsterEntity monsterEntity = new MonsterEntity();
       monsterEntity.setId(1L);
       monsterEntity.setSabor("Test sabor");
       when(monsterRepository.findById(1L)).thenReturn(Optional.of(monsterEntity));

       //--------------------------------------------------------------------------------


       // return modelMapper.map(monsterEntityOptional.get(), MonsterModel.class);
       MonsterModel monsterModelReturn = new MonsterModel();
       monsterModelReturn.setId(1L);
       monsterModelReturn.setSabor("Test sabor");

        when(modelMapper.map(monsterEntity,MonsterModel.class)).thenReturn(monsterModelReturn);

       //--------------------------------------------------------------------------------

       MonsterModel result = monsterService.getMonster(1L);

       assertEquals(monsterModelReturn,result); //el primar valor es el esperado
   }

   @Test
    void createMonsterTest(){

       MonsterEntity monsterEntity = new MonsterEntity();
//       monsterEntity.setId(1L);
       monsterEntity.setSabor("Test sabor");

       MonsterDto monsterDto = new MonsterDto();
//       monsterDto.setId(1L);
       monsterDto.setSabor("Test sabor");

       when(modelMapper.map(monsterDto,MonsterEntity.class)).thenReturn(monsterEntity);


//        when(modelMapper.map(any(MonsterDto.class)
//                ,eq(MonsterEntity.class)))
//                .thenReturn(monsterEntity);


       MonsterEntity monsterEntitySaved = new MonsterEntity();
       monsterEntitySaved.setId(1L);
       monsterEntitySaved.setSabor("Test sabor");


      when(monsterRepository.save(monsterEntity)).thenReturn(monsterEntitySaved);

      MonsterModel monsterModel = new MonsterModel();
      monsterModel.setId(1L);
      monsterModel.setSabor("Test sabor");

      when(modelMapper.map(monsterEntitySaved,MonsterModel.class)).thenReturn(monsterModel);


        MonsterModel result = monsterService.createMonster(monsterDto);


       assertEquals(monsterModel,result); //el primar valor es el esperado
   }


   @Test
    void getMonsterListTest(){


        //    List<MonsterEntity> monsterEntities = monsterRepository.findAll();
        List<MonsterEntity> monsterEntities = new ArrayList<>();

        MonsterEntity monsterEntityItem = new MonsterEntity();

        monsterEntityItem.setId(1L);
        monsterEntityItem.setSabor("Test Sabor");

        monsterEntities.add(monsterEntityItem);


        when(monsterRepository.findAll()).thenReturn(monsterEntities);

        MonsterModel monsterModel = new MonsterModel();
        monsterModel.setId(1L);
        monsterModel.setSabor("Test Sabor");


        when(modelMapper.map(monsterEntityItem,MonsterModel.class)).thenReturn(monsterModel);

        List<MonsterModel> result = monsterService.getMonsterList();

        List<MonsterModel> monsterModelExample = new ArrayList<>();
        monsterModelExample.add(monsterModel);

       assertEquals(monsterModelExample,result); //el primar valor es el esperado

   }

    @Test
    void updateMonsterTest_Null(){

//        MonsterModel monsterModelResult = new MonsterModel();
//        monsterModelResult.setId(1L);
//        monsterModelResult.setSabor("Test sabor");

        MonsterModel monsterModelParameter = new MonsterModel();
        monsterModelParameter.setId(1L);
        monsterModelParameter.setSabor("Test sabor");

//        MonsterEntity monsterEntityResult = new MonsterEntity();
//        monsterEntityResult.setId(1L);
//        monsterEntityResult.setSabor("Test sabor");

        when(monsterRepository.findById(1L))
                .thenReturn(Optional.empty());


        MonsterModel result = monsterService.updateMonster(monsterModelParameter);

        assertEquals(null,result);


    }



    @Test
    void updateMonsterTest(){

        MonsterModel monsterModelResult = new MonsterModel();
        monsterModelResult.setId(1L);
        monsterModelResult.setSabor("Test sabor");

        MonsterModel monsterModelParameter = new MonsterModel();
        monsterModelParameter.setId(1L);
        monsterModelParameter.setSabor("Test sabor");

        MonsterEntity monsterEntityResult = new MonsterEntity();
        monsterEntityResult.setId(1L);
        monsterEntityResult.setSabor("Test sabor");

        when(monsterRepository.findById(1L))
                .thenReturn(Optional.of(monsterEntityResult));

        when(monsterRepository.save(monsterEntityResult))
                .thenReturn(monsterEntityResult);

        when(modelMapper.map(monsterEntityResult,MonsterModel.class))
                .thenReturn(monsterModelResult);



        MonsterModel result = monsterService.updateMonster(monsterModelParameter);

        assertEquals(monsterModelResult,result);


    }

    @Test
    void deleteMonsterTest_NotFound(){


        Long id = 1L;

        when(monsterRepository.existsById(id)).thenReturn(false);



        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            monsterService.deleteMonster(id);
        });
        assertEquals("No se encontró el elemento con el ID proporcionado", exception.getMessage());
    }

    @Test
    void deleteMonsterTest(){

        Long id = 1L;

        when(monsterRepository.existsById(id)).thenReturn(true);

        monsterService.deleteMonster(id);

        verify(monsterRepository).deleteById(id);



    }



}