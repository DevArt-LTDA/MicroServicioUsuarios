package com.DevArt.Usuarios.Repository;
import com.DevArt.Usuarios.model.Usuarios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, String> {
    //metodos de busqueda repository
    List<Usuarios> findByRut(String rut);
    List<Usuarios> findByPrimerNombre(String primerNombre);
    List<Usuarios> findBySegundoNombre(String segundoNombre);
    List<Usuarios> findByPrimApellido(String primApellido);
    List<Usuarios> findBySegApellido(String segApellido);
    List<Usuarios> findByCorreo(String correo);
    List<Usuarios> findByFechaNacimiento(String fechaNacimiento); 
    List<Usuarios> findByTelefono(String telefono);
}
