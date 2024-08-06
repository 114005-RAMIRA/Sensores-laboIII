package ar.edu.utn.frc.tup.lc.iii.controllers;
import ar.edu.utn.frc.tup.lc.iii.dtos.SensorDTO;
import ar.edu.utn.frc.tup.lc.iii.dtos.consultas.SensorPorCantLecturasDTO;
import ar.edu.utn.frc.tup.lc.iii.models.Sensor;
import ar.edu.utn.frc.tup.lc.iii.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("sensor")
public class SensorController {

    @Autowired
    SensorService sensorService;

    @PostMapping("")
    public ResponseEntity<Sensor> createSensor(@RequestBody SensorDTO sensorDTO)
    {
        Sensor sensor =sensorService.createSensor(sensorDTO);

        if (sensor == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The post of the match was unsuccesfull");
        } else {
            return ResponseEntity.ok(sensor);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Sensor>> getMatchesOfPlayer()
    {
        List<Sensor> sensors = sensorService.getSensors();
               return ResponseEntity.ok(sensors);
    }
    @GetMapping("/getByName/{name}")
    public ResponseEntity<Sensor> getSensorByName(@PathVariable String name)
    {
        Sensor sensor = sensorService.getByName(name);
        return ResponseEntity.ok(sensor);
    }

    @PutMapping("/updateSensor")
    public ResponseEntity<Sensor> updateSensor(@RequestBody SensorDTO sensorDTO)
    {
        Sensor sensor =sensorService.updateSensor(sensorDTO);

        if (sensor == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The post of the match was unsuccesfull");
        } else {
            return ResponseEntity.ok(sensor);
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteSensorById(@PathVariable Long id) {
        try {
            sensorService.deleteSensorById(id);
            return ResponseEntity.ok("Se eliminó correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el sensor: " + e.getMessage());
        }
    }

    @GetMapping("/sensorXlectura")
    public ResponseEntity<List<SensorPorCantLecturasDTO>> getSensorsByLecturasCount()
    {
        List<SensorPorCantLecturasDTO> sensors =
                sensorService.getSensorPerLecturasCount();
        return ResponseEntity.ok(sensors);
    }


}
