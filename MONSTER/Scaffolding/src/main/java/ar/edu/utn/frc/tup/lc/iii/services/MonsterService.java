package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.dtos.DummyDto;
import ar.edu.utn.frc.tup.lc.iii.dtos.MonsterDto;
import ar.edu.utn.frc.tup.lc.iii.models.Dummy;
import ar.edu.utn.frc.tup.lc.iii.models.MonsterModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MonsterService {

    MonsterModel getMonster(Long id);

    List<MonsterModel> getMonsterList();

    MonsterModel createMonster(MonsterDto monsterDto);

    MonsterModel updateMonster(MonsterModel monsterDto);

    void deleteMonster(Long id);


}
