package com.DevArt.Usuarios.controller;

import com.DevArt.Usuarios.model.Trabajadores;
import com.DevArt.Usuarios.service.TrabajadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;
@RestController
@RequestMapping("/api/v1/trabajadores")
public class TrabajadoresController {
    @Autowired 
    private TrabajadoresService trabajadoresService;

    @GetMapping
    public ResponseEntity<List<Trabajadores>> getTrabajadores() {
        return ResponseEntity.ok(trabajadoresService.getAllTrabajadores());
    }

    @GetMapping("/existe/{rut}")
    public ResponseEntity<Boolean> existeTrabajador(@PathVariable("rut") String rut) {
        List<Trabajadores> trabajadores = trabajadoresService.getTrabajadorByRut(rut);
        return ResponseEntity.ok(!trabajadores.isEmpty());
    }
    
    @PostMapping
    public ResponseEntity<Trabajadores> agregarTrabajador(@RequestBody Trabajadores trabajador) {
        Trabajadores nuevoTrabajador = trabajadoresService.agregarTrabajador(trabajador);
        return ResponseEntity.ok(nuevoTrabajador);
    }

    @PutMapping("/{rut}")
    public ResponseEntity<Trabajadores> actualizarTrabajador(@PathVariable("rut") String rut, @RequestBody Trabajadores trabajadorActualizado) {
        Trabajadores trabajador = trabajadoresService.actualizarTrabajador(rut, trabajadorActualizado);
        if (trabajador != null) {
            return ResponseEntity.ok(trabajador);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable("rut") String rut) {
        trabajadoresService.eliminarTrabajador(rut);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<List<Trabajadores>> getTrabajadorByRut(@PathVariable("rut") String rut) {
        List<Trabajadores> trabajadores = trabajadoresService.getTrabajadorByRut(rut);
        if (trabajadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trabajadores);
    }

    @GetMapping("/primerNombre/{primerNombre}")
    public ResponseEntity<List<Trabajadores>> getTrabajadorByPrimerNombre(@PathVariable("primerNombre") String primerNombre) {
        List<Trabajadores> trabajadores = trabajadoresService.getTrabajadorByPrimerNombre(primerNombre);
        if (trabajadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trabajadores);
    }

    @GetMapping("/segundoNombre/{segundoNombre}")
    public ResponseEntity<List<Trabajadores>> getTrabajadorBySegundoNombre(@PathVariable("segundoNombre") String segundoNombre) {
        List<Trabajadores> trabajadores = trabajadoresService.getTrabajadorBySegundoNombre(segundoNombre);
        if (trabajadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trabajadores);
    }

}
