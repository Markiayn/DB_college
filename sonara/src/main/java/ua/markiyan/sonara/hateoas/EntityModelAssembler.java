package ua.markiyan.sonara.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

/**
 * Marker interface to implement specific assemblers for DTO records.
 */
public interface EntityModelAssembler<T> extends RepresentationModelAssembler<T, EntityModel<T>> {
}

