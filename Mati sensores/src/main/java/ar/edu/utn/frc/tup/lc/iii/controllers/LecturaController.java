package ar.edu.utn.frc.tup.lc.iii.controllers;
import ar.edu.utn.frc.tup.lc.iii.dtos.LecturaDTO;
import ar.edu.utn.frc.tup.lc.iii.dtos.SensorDTO;
import ar.edu.utn.frc.tup.lc.iii.models.Lectura;
import ar.edu.utn.frc.tup.lc.iii.models.Sensor;
import ar.edu.utn.frc.tup.lc.iii.services.LecturaService;
import ar.edu.utn.frc.tup.lc.iii.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("lectura")
public class LecturaController {
    @Autowired
    LecturaService lecturaService;

    @PostMapping("")
    public ResponseEntity<Lectura> createLectura(@RequestBody LecturaDTO lecturaDTO)
    {
        Lectura lectura = lecturaService.createLectura(lecturaDTO);
        if (lectura == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The post of the match was unsuccesfull");
        } else {
            return ResponseEntity.ok(lectura);
        }
    }

    @GetMapping("/getAll") //En vez de una direccion, le pasas el id por url
    public ResponseEntity<List<Lectura>> getMatchesOfPlayer()
    //METODO GET DEVUELVE UN (player ahora), parametro el id del url
    {
        List<Lectura> lecturas = lecturaService.getLecturas();
        return ResponseEntity.ok(lecturas);
    }

    @GetMapping("/getByDate/{date}")
    //%20 es un espacio en la URL
    public ResponseEntity<Lectura> getLecturaByDate(@PathVariable LocalDateTime date)
    {
        Lectura lectura = lecturaService.getByDate(date);
        return ResponseEntity.ok(lectura);
    }
    @DeleteMapping("delete/{id}")
    public void deleteSensorById(@PathVariable Long id)
    {
        lecturaService.deleteLecturaById(id);
    }
    @PutMapping("/updateLectura")
    public ResponseEntity<Lectura> updateLectura (@RequestBody LecturaDTO lecturaDTO)
    {
        Lectura lectura = lecturaService.updateLectura(lecturaDTO);
        if (lectura == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The post of the match was unsuccesfull");
        } else {
            return ResponseEntity.ok(lectura);
        }
    }

}
