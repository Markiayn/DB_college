package ua.markiyan.sonara.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.markiyan.sonara.controller.SubscriptionController;
import ua.markiyan.sonara.dto.response.SubscriptionResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubscriptionModelAssembler implements EntityModelAssembler<SubscriptionResponse> {

    @Override
    public EntityModel<SubscriptionResponse> toModel(SubscriptionResponse sub) {
        EntityModel<SubscriptionResponse> model = EntityModel.of(sub);
        model.add(WebMvcLinkBuilder.linkTo(methodOn(SubscriptionController.class).list(sub.userId())).withRel("userSubscriptions"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(SubscriptionController.class).create(sub.userId(), null)).withRel("create"));
        // no specific subscription controller for single subscription in current API
        return model;
    }
}

