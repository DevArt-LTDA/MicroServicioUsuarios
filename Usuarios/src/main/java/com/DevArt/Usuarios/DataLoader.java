package com.DevArt.Usuarios;

import com.DevArt.Usuarios.Repository.UsuariosRepository;
import com.DevArt.Usuarios.Repository.TrabajadoresRepository;
import com.DevArt.Usuarios.model.*;
import net.datafaker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// import java.util.Date;
// import java.util.List;
// import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    private TrabajadoresRepository trabajadoresRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        // Random random = new Random();

        // Cargar datos de usuarios
        for (int i = 0; i < 10; i++) {
            Usuarios usuario = new Usuarios();
            usuario.setRut(faker.idNumber().valid());
            usuario.setPrimerNombre(faker.name().firstName());
            usuario.setSegundoNombre(faker.name().firstName());
            usuario.setPrimApellido(faker.name().lastName());
            usuario.setSegApellido(faker.name().lastName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setDepartamento(faker.company().name());

            java.util.Date fechaNacimientoDate = faker.date().birthday(18, 65);
            String fechaNacimiento = fechaNacimientoDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate()
                    .toString();
            usuario.setFechaNacimiento(fechaNacimiento);
            // asignar departamento
            usuario.setRol("Rol" + (i + 1));
            // Asignar un cargo aleatorio
            usuario.setDepartamento(faker.company().industry());
            usuario.setTelefono(faker.phoneNumber().phoneNumber());
            usuario.setCargo(faker.job().position());

            // Guardar el usuario
            usuarioRepository.save(usuario);
        }
        // Cargar datos de trabajadores
        for (int i = 0; i < 10; i++) {
            Trabajadores trabajador = new Trabajadores();
            trabajador.setRut(faker.idNumber().valid());
            trabajador.setPrimerNombre(faker.name().firstName());
            trabajador.setSegundoNombre(faker.name().firstName());
            trabajador.setPrimApellido(faker.name().lastName());
            trabajador.setSegApellido(faker.name().lastName());
            trabajador.setCorreo(faker.internet().emailAddress());
            java.util.Date fechaNacimientoDate = faker.date().birthday(18, 65);
            String fechaNacimiento = fechaNacimientoDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate()
                    .toString();
            trabajador.setFechaNacimiento(fechaNacimiento);
            trabajador.setTelefono(faker.phoneNumber().phoneNumber());
            trabajador.setRol("Rol" + (i + 1));

            // Guardar el trabajador
            trabajadoresRepository.save(trabajador);
        }

    }
}