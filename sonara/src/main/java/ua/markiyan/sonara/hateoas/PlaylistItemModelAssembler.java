package ua.markiyan.sonara.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.markiyan.sonara.controller.PlaylistItemController;
import ua.markiyan.sonara.dto.response.PlaylistItemResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlaylistItemModelAssembler implements EntityModelAssembler<PlaylistItemResponse> {

    @Override
    public EntityModel<PlaylistItemResponse> toModel(PlaylistItemResponse item) {
        EntityModel<PlaylistItemResponse> model = EntityModel.of(item);
        model.add(WebMvcLinkBuilder.linkTo(methodOn(PlaylistItemController.class).get(item.playlistId(), item.id())).withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(methodOn(PlaylistItemController.class).list(item.playlistId())).withRel("items"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(PlaylistItemController.class).patch(item.playlistId(), item.id(), null)).withRel("update"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(PlaylistItemController.class).delete(item.playlistId(), item.id())).withRel("delete"));
        return model;
    }
}

