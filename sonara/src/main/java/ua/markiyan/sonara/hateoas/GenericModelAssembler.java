package ua.markiyan.sonara.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;
import java.util.function.Function;


public class GenericModelAssembler<R> implements RepresentationModelAssembler<R, EntityModel<R>> {

    private final Function<R, List<Link>> linkFactory;

    public GenericModelAssembler(Function<R, List<Link>> linkFactory) {
        this.linkFactory = linkFactory;
    }

    @Override
    public EntityModel<R> toModel(R resource) {
        return EntityModel.of(resource, linkFactory.apply(resource));
    }
}
