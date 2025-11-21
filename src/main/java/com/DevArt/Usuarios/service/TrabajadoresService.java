package com.DevArt.Usuarios.service;

import com.DevArt.Usuarios.model.Trabajadores;
import com.DevArt.Usuarios.Repository.TrabajadoresRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TrabajadoresService {

    @Autowired
    private  TrabajadoresRepository trabajadoresRepository;

    // Verificar si existe un trabajador por RUT
    public boolean existeTrabajador(String rut) {
        List<Trabajadores> trabajadores = trabajadoresRepository.findByRut(rut);
        return !trabajadores.isEmpty();
    }

    // Agregar un nuevo trabajador
    public Trabajadores agregarTrabajador(Trabajadores trabajador) {
        if (!existeTrabajador(trabajador.getRut())) {
            return trabajadoresRepository.save(trabajador);
        }
        return null; // O lanzar una excepción si el trabajador ya existe
    }


    // Actualizar un trabajador por RUT
    public Trabajadores actualizarTrabajador(String rut, Trabajadores trabajadorActualizado) {
        Trabajadores trabajadorExistente = trabajadoresRepository.findByRut(rut).stream().findFirst().orElse(null);
        if (trabajadorExistente != null) {
            trabajadorActualizado.setRut(trabajadorExistente.getRut());
            return trabajadoresRepository.save(trabajadorActualizado);
        }
        return null; 
    }

    // Eliminar un trabajador por RUT
    public void eliminarTrabajador(String rut) {
        List<Trabajadores> trabajadores = trabajadoresRepository.findByRut(rut);
        if (!trabajadores.isEmpty()) {
            trabajadoresRepository.delete(trabajadores.get(0)); // Eliminar el primer trabajador encontrado
        }
    }

    // Obtener todos los trabajadores
    public List<Trabajadores> getAllTrabajadores() {
        return trabajadoresRepository.findAll();
    }

    // Buscar por RUT
    public List<Trabajadores> getTrabajadorByRut(String rut) {
        return trabajadoresRepository.findByRut(rut);
    }

    // Buscar por primer nombre
    public List<Trabajadores> getTrabajadorByPrimerNombre(String primerNombre) {
        return trabajadoresRepository.findByPrimerNombre(primerNombre);
    }

    // Buscar por segundo nombre
    public List<Trabajadores> getTrabajadorBySegundoNombre(String segundoNombre) {
        return trabajadoresRepository.findBySegundoNombre(segundoNombre);
    }

    // Buscar por primer apellido
    public List<Trabajadores> getTrabajadorByPrimerApellido(String primApellido) {
        return trabajadoresRepository.findByPrimApellido(primApellido);
    }

    // Buscar por segundo apellido
    public List<Trabajadores> getTrabajadorBySegundoApellido(String segApellido) {
        return trabajadoresRepository.findBySegApellido(segApellido);
    }

    // Buscar por correo
    public List<Trabajadores> getTrabajadorByCorreo(String correo) {
        return trabajadoresRepository.findByCorreo(correo);
    }
    // Buscar por fecha de nacimiento
    public List<Trabajadores> getTrabajadorByFechaNacimiento(String fechaNacimiento) {
        return trabajadoresRepository.findByFechaNacimiento(fechaNacimiento);
    }
    // Buscar por teléfono
    public List<Trabajadores> getTrabajadorByTelefono(String telefono) {
        return trabajadoresRepository.findByTelefono(telefono);
    }
}
