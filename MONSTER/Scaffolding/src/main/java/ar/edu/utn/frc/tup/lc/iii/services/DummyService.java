package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.dtos.DummyDto;
import ar.edu.utn.frc.tup.lc.iii.models.Dummy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DummyService {
    Dummy getDummy(Long id);

    List<Dummy> getDummyList();

    Dummy createDummy(DummyDto dummyDto);

    Dummy updateDummy(DummyDto dummyDto);

    void deleteDummy(Long id);

}
