package vaccination.controllers;

import org.springframework.stereotype.Component;
import vaccination.models.Vaccination;

/**
 * Vaccination resource assembler
 */
@Component
public class VaccinationResourceAssembler extends IdBasedResourceAssembler<Vaccination> {
    public VaccinationResourceAssembler() {
        super(VaccinationController.class);
    }
}