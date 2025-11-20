package ua.markiyan.sonara.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import ua.markiyan.sonara.controller.UserController;
import ua.markiyan.sonara.dto.response.UserResponse;
import ua.markiyan.sonara.hateoas.GenericModelAssembler;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Configuration
public class HateoasConfig {

    @Bean
    public GenericModelAssembler<UserResponse> userAssembler() {
        return new GenericModelAssembler<>(user -> List.of(
                linkTo(methodOn(UserController.class).get(user.id())).withSelfRel(),
                linkTo(methodOn(UserController.class).search(null, null, null, null, Pageable.unpaged(), null)).withRel("users"),
                linkTo(methodOn(UserController.class).create(null)).withRel("create")
        ));
    }

    // Additional beans for other entities:
    // @Bean
    // public GenericModelAssembler<ArtistResponse> artistAssembler() { ... }
}
