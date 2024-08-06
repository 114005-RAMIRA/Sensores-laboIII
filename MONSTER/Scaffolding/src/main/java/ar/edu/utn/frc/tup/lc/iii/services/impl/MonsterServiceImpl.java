package ar.edu.utn.frc.tup.lc.iii.services.impl;


import ar.edu.utn.frc.tup.lc.iii.dtos.MonsterDto;
import ar.edu.utn.frc.tup.lc.iii.entities.MonsterEntity;
import ar.edu.utn.frc.tup.lc.iii.models.MonsterModel;

import ar.edu.utn.frc.tup.lc.iii.repositories.MonsterRepository;
import ar.edu.utn.frc.tup.lc.iii.services.MonsterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonsterServiceImpl  implements MonsterService {


    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public MonsterModel getMonster(Long id) {


        //Buscamos el monster entity con el id en el repositorio

        Optional<MonsterEntity> monsterEntityOptional = monsterRepository.findById(id);

        //si existe, lo convertimos en monsterModel y lo devolvemos

        if (monsterEntityOptional.isPresent()) {
            return modelMapper.map(monsterEntityOptional.get(), MonsterModel.class);
        }
        //si no existe, devolver null o lanzar una excepcion
        return null;


    }

    @Override
    public List<MonsterModel> getMonsterList() {

/*
Obtenemos todas las entidades MonsterEntity usando monsterRepository.findAll().
Convertimos cada entidad a MonsterModel y devolvemos la lista.
*/
        //obtener todas las entidades monster del repositorio

        List<MonsterEntity> monsterEntities = monsterRepository.findAll();

        //convertir la lista de entidades a lista de modelos usando model mapper

        List<MonsterModel> monsterModelList = new ArrayList<>();

        for (MonsterEntity entity : monsterEntities){
            monsterModelList.add(modelMapper.map(entity,MonsterModel.class));
        }
        return monsterModelList;
    }

    @Override
    public MonsterModel createMonster(MonsterDto monsterDto) {

/*
Convertimos el monsterDto a MonsterEntity.
Guardamos la entidad en el repositorio.
Convertimos la entidad guardada a MonsterModel y la devolvemos.
*/

        //convertir el monsterDto a mosnterEntity
        MonsterEntity monsterEntity = modelMapper.map(monsterDto,MonsterEntity.class);

        //guardar la entidad en el repositorio
        MonsterEntity savedMonsterEntity = monsterRepository.save(monsterEntity);

        //Convertir la entidad guardada a monsterModel y devolverlo

        return modelMapper.map(savedMonsterEntity,MonsterModel.class);



    }

    @Override
    public MonsterModel updateMonster(MonsterModel monster) {

/*
Asumimos que el DummyDto contiene el ID del Monster a actualizar.
Buscamos la entidad existente por su ID.
Si la entidad existe, mapeamos los datos del DummyDto a la entidad y la guardamos.
Convertimos la entidad actualizada a MonsterModel y la devolvemos.
*/

     //   si el dto tiene el campo id

        Long id = monster.getId();

        //Buscamos la entidad por id
        Optional<MonsterEntity> monsterEntityOptional = monsterRepository.findById(id);

        if (monsterEntityOptional.isPresent()) {

            MonsterEntity monsterEntity = new MonsterEntity();
            monsterEntity.setId(id);
            monsterEntity.setSabor(monster.getSabor());

//            monsterRepository.save(monsterEntity);



            //mapeamos el resultado del save a model class
            return modelMapper.map(monsterRepository.save(monsterEntity),MonsterModel.class);

        }
        return null;

    }

    @Override
    public void deleteMonster(Long id) {
/*
Verificación de existencia: monsterRepository.existsById(id) se utiliza para comprobar si existe una entidad con el ID dado.
Eliminación de la entidad: Si la entidad existe, se elimina con monsterRepository.deleteById(id).
Manejo de no existencia: Si la entidad no existe, se lanza una excepción RuntimeException con un mensaje claro para indicar que no se encontró la entidad con el ID proporcionado.
*/
        if (monsterRepository.existsById(id)) {
            monsterRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró el elemento con el ID proporcionado");
        }
    }
}
