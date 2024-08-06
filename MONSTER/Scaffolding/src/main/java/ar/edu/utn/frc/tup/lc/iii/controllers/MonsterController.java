package ar.edu.utn.frc.tup.lc.iii.controllers;

import ar.edu.utn.frc.tup.lc.iii.dtos.DummyDto;
import ar.edu.utn.frc.tup.lc.iii.dtos.MonsterDto;
import ar.edu.utn.frc.tup.lc.iii.models.Dummy;
import ar.edu.utn.frc.tup.lc.iii.models.MonsterModel;
import ar.edu.utn.frc.tup.lc.iii.services.DummyService;
import ar.edu.utn.frc.tup.lc.iii.services.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/MonsterApp/Monster")
public class MonsterController {


    @Autowired
    private MonsterService monsterService;

    @PostMapping("")
    public ResponseEntity<MonsterModel> createMonster(@RequestBody MonsterDto monsterDto) {

/*



*/



        // Realizar la creación del Monster
        MonsterModel createdMonster = monsterService.createMonster(monsterDto);


        // Devolver la entidad creada con el código de estado 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMonster);


    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MonsterModel>> getMonsterList()
    {

/*



*/
        List<MonsterModel> monsters = monsterService.getMonsterList();
        return ResponseEntity.ok(monsters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonsterModel> getMonsterById(@PathVariable Long id)
    {

/*



*/




        MonsterModel monster = monsterService.getMonster(id);

        if (monster == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Monster no encontrada");
        }


        return ResponseEntity.ok(monster);
    }

    @PutMapping("/update")
    public ResponseEntity<MonsterModel> updateMonster(@RequestBody MonsterModel monsterparameter) {

/*



*/
        MonsterModel monster = monsterService.updateMonster(monsterparameter);
        if (monster == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The post of the match was unsuccesfull");
        } else {
            return ResponseEntity.ok(monster);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMonsterById(@PathVariable Long id) {

/*



*/
        try {
            monsterService.deleteMonster(id);
            return ResponseEntity.ok("Se eliminó correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar");
        }
    }




}
