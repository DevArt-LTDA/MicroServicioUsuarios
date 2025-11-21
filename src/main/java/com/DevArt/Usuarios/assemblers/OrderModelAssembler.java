package com.DevArt.Usuarios.assemblers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.DevArt.Usuarios.model.Usuarios;
import com.DevArt.Usuarios.controller.UsuariosController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component

public class OrderModelAssembler implements RepresentationModelAssembler<Usuarios, EntityModel<Usuarios>> {
    @Override
    public EntityModel<Usuarios> toModel(Usuarios usuario) {
            return EntityModel.of(usuario,
            linkTo(methodOn(UsuariosController.class).getUsuarios()).withRel("usuarios"));
        }
    }
