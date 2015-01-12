package vaccination.controllers;

import org.springframework.stereotype.Component;
import vaccination.models.VaccinationDate;

/**
 * Vaccination resource assembler
 */
@Component
public class VaccinationDateResourceAssembler extends IdBasedResourceAssembler<VaccinationDate> {
    public VaccinationDateResourceAssembler() {
        super(VaccinationDateController.class);
    }
}