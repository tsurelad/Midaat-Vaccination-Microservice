package vaccination.controllers;

import org.springframework.stereotype.Component;
import vaccination.models.Compound;
import vaccination.models.VaccinationInCompound;

/**
 * Vaccination In Compound resource assembler
 */
@Component
public class VaccinationInCompoundResourceAssembler extends IdBasedResourceAssembler<VaccinationInCompound> {
    public VaccinationInCompoundResourceAssembler() {
        super(VaccinationInCompoundController.class);
    }
}