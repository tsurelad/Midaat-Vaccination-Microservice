package vaccination.controllers;

import org.springframework.stereotype.Component;
import vaccination.models.CompoundInVaccinationDate;

/**
 * Compound In Vaccination Date resource assembler
 */
@Component
public class CompoundInVaccinationDateResourceAssembler extends IdBasedResourceAssembler<CompoundInVaccinationDate> {
    public CompoundInVaccinationDateResourceAssembler() {
        super(CompoundInVaccinationDateController.class);
    }
}