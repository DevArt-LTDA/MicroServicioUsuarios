package com.DevArt.Usuarios.controller;

import java.util.stream.Collectors;
import java.util.List;

import com.DevArt.Usuarios.model.Usuarios;
import com.DevArt.Usuarios.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.DevArt.Usuarios.assemblers.OrderModelAssembler;
import java.util.List;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
@RestController

@RequestMapping("/api/v1/usuarios")
public class UsuariosController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrderModelAssembler assembler;

    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista con todos los usuarios registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Carrera no encontrada")
    })

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Usuarios>>> getUsuarios() {
        List<EntityModel<Usuarios>> usuarios = usuarioService.getAllUsuarios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(CollectionModel.of(usuarios,
                linkTo(methodOn(UsuariosController.class).getUsuarios()).withSelfRel()));
    }

    @Operation(summary = "Verificar si existe un usuario por RUT", description = "Retorna true si el usuario con ese RUT existe")
    @GetMapping("/existe/{rut}")
    public ResponseEntity<Boolean> existeUsuario(@PathVariable("rut") String rut) {
        List<Usuarios> usuarios = usuarioService.getUsuarioByRut(rut);
        return ResponseEntity.ok(!usuarios.isEmpty());
    }

    @Operation(summary = "Obtener usuario por RUT", description = "Busca un usuario por su RUT y lo retorna si existe")
    @GetMapping("/{rut}")
    public ResponseEntity<List<Usuarios>> getUsuarioByRut(@PathVariable("rut") String rut) {
        List<Usuarios> usuarios = usuarioService.getUsuarioByRut(rut);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por primer nombre", description = "Busca usuarios que coincidan con el primer nombre")
    @GetMapping("/nombre/{primerNombre}")
    public ResponseEntity<List<Usuarios>> getUsuarioByPrimerNombre(@PathVariable("primerNombre") String primerNombre) {
        List<Usuarios> usuarios = usuarioService.getUsuarioByPrimerNombre(primerNombre);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por segundo nombre", description = "Busca usuarios que coincidan con el segundo nombre")
    @GetMapping("/segundoNombre/{segundoNombre}")
    public ResponseEntity<List<Usuarios>> getUsuarioBySegundoNombre(
            @PathVariable("segundoNombre") String segundoNombre) {
        List<Usuarios> usuarios = usuarioService.getUsuarioBySegundoNombre(segundoNombre);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por primer apellido", description = "Busca usuarios que coincidan con el primer apellido")
    @GetMapping("/primerApellido/{primerApellido}")
    public ResponseEntity<List<Usuarios>> getUsuarioByPrimerApellido(
            @PathVariable("primerApellido") String primerApellido) {
        List<Usuarios> usuarios = usuarioService.getUsuarioByPrimerApellido(primerApellido);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por segundo apellido", description = "Busca usuarios que coincidan con el segundo apellido")
    // cambio ruta

    @GetMapping("/segundoApellido/{segundoApellido}")
    public ResponseEntity<List<Usuarios>> getUsuarioBySegundoApellido(
            @PathVariable("segundoApellido") String segundoApellido) {
        List<Usuarios> usuarios = usuarioService.getUsuarioBySegundoApellido(segundoApellido);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por correo", description = "Busca usuarios que coincidan con el correo electrónico")
    @GetMapping("/correo/{correo}")
    public ResponseEntity<List<Usuarios>> getUsuarioByCorreo(@PathVariable("correo") String correo) {
        List<Usuarios> usuarios = usuarioService.getUsuarioByCorreo(correo);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por fecha de nacimiento", description = "Busca usuarios por fecha de nacimiento (formato esperado: YYYY-MM-DD)")
    @GetMapping("/fechaNacimiento/{fechaNacimiento}")
    public ResponseEntity<List<Usuarios>> getUsuarioByFechaNacimiento(
            @PathVariable("fechaNacimiento") String fechaNacimiento) {
        List<Usuarios> usuarios = usuarioService.getUsuarioByFechaNacimiento(fechaNacimiento);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por teléfono", description = "Busca usuarios que coincidan con el número de teléfono")
    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<List<Usuarios>> getUsuarioByTelefono(@PathVariable("telefono") String telefono) {
        List<Usuarios> usuarios = usuarioService.getUsuarioByTelefono(telefono);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Agrega un nuevo usuario al sistema")
    @PostMapping
    public ResponseEntity<Usuarios> createUsuario(@RequestBody Usuarios usuario) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuario));
    }

    @Operation(summary = "Actualizar usuario por RUT", description = "Actualiza los datos de un usuario identificado por su RUT")
    @PutMapping("/{rut}")
    public ResponseEntity<Usuarios> updateUsuario(@PathVariable("rut") String rut, @RequestBody Usuarios usuario) {
        Usuarios updatedUsuario = usuarioService.updateUsuario(rut, usuario);
        if (updatedUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUsuario);
    }

    @Operation(summary = "Eliminar usuario por RUT", description = "Elimina un usuario identificado por su RUT")
    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("rut") String rut) {
        usuarioService.deleteUsuario(rut);
        return ResponseEntity.noContent().build();
    }
}
