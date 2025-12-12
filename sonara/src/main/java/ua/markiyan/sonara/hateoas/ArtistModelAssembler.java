package ua.markiyan.sonara.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.markiyan.sonara.controller.ArtistController;
import ua.markiyan.sonara.dto.response.ArtistResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArtistModelAssembler implements EntityModelAssembler<ArtistResponse> {

    @Override
    public EntityModel<ArtistResponse> toModel(ArtistResponse artist) {
        EntityModel<ArtistResponse> model = EntityModel.of(artist);
        model.add(WebMvcLinkBuilder.linkTo(methodOn(ArtistController.class).get(artist.id())).withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(methodOn(ArtistController.class).search(null, null, org.springframework.data.domain.Pageable.unpaged())).withRel("artists"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(ArtistController.class).patch(artist.id(), null)).withRel("update"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(ArtistController.class).delete(artist.id())).withRel("delete"));
        return model;
    }
}

