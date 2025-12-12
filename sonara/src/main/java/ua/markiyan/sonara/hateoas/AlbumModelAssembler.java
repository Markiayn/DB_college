package ua.markiyan.sonara.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.markiyan.sonara.controller.AlbumController;
import ua.markiyan.sonara.dto.response.AlbumResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlbumModelAssembler implements EntityModelAssembler<AlbumResponse> {

    @Override
    public EntityModel<AlbumResponse> toModel(AlbumResponse album) {
        EntityModel<AlbumResponse> model = EntityModel.of(album);
        model.add(WebMvcLinkBuilder.linkTo(methodOn(AlbumController.class).get(album.id())).withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(methodOn(AlbumController.class).search(null, null, org.springframework.data.domain.Pageable.unpaged(), (org.springframework.data.web.PagedResourcesAssembler<AlbumResponse>) null)).withRel("albums"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(AlbumController.class).patch(album.id(), null)).withRel("update"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(AlbumController.class).delete(album.id())).withRel("delete"));
        return model;
    }
}
