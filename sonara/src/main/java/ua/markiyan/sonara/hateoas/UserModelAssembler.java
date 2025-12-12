package ua.markiyan.sonara.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.markiyan.sonara.controller.UserController;
import ua.markiyan.sonara.dto.response.UserResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements EntityModelAssembler<UserResponse> {

    @Override
    public EntityModel<UserResponse> toModel(UserResponse user) {
        EntityModel<UserResponse> model = EntityModel.of(user);
        model.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).get(user.id())).withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).search(null, org.springframework.data.domain.Pageable.unpaged())).withRel("users"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).patch(user.id(), null)).withRel("update"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).delete(user.id())).withRel("delete"));
        return model;
    }
}

