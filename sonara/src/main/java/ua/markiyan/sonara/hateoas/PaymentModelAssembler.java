package ua.markiyan.sonara.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.markiyan.sonara.controller.PaymentController;
import ua.markiyan.sonara.dto.response.PaymentResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaymentModelAssembler implements EntityModelAssembler<PaymentResponse> {

    @Override
    public EntityModel<PaymentResponse> toModel(PaymentResponse p) {
        EntityModel<PaymentResponse> model = EntityModel.of(p);
        model.add(WebMvcLinkBuilder.linkTo(methodOn(PaymentController.class).get(p.id())).withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(methodOn(PaymentController.class).listByUser(p.userId())).withRel("userPayments"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(PaymentController.class).list(p.subscriptionId())).withRel("subscriptionPayments"));
        return model;
    }
}

