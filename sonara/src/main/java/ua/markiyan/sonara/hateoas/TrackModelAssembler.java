package ua.markiyan.sonara.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.markiyan.sonara.controller.TrackController;
import ua.markiyan.sonara.dto.response.TrackResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TrackModelAssembler implements EntityModelAssembler<TrackResponse> {

    @Override
    public EntityModel<TrackResponse> toModel(TrackResponse track) {
        EntityModel<TrackResponse> model = EntityModel.of(track);
        model.add(WebMvcLinkBuilder.linkTo(methodOn(TrackController.class).get(track.id())).withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(methodOn(TrackController.class).search(null, null, null, org.springframework.data.domain.Pageable.unpaged())).withRel("tracks"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(TrackController.class).patch(track.id(), null)).withRel("update"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(TrackController.class).delete(track.id())).withRel("delete"));
        return model;
    }
}

