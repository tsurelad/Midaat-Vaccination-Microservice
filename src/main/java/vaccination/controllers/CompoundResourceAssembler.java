package vaccination.controllers;

import org.springframework.stereotype.Component;
import vaccination.models.Compound;

/**
 * Vaccination resource assembler
 */
@Component
public class CompoundResourceAssembler extends IdBasedResourceAssembler<Compound> {
    public CompoundResourceAssembler() {
        super(CompoundController.class);
    }
}