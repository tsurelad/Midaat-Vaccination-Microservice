package vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vaccination.models.*;
import vaccination.services.CompoundInVaccinationDateService;
import vaccination.services.CompoundService;
import vaccination.services.VaccinationInCompoundService;
import vaccination.services.VaccinationService;

import java.util.List;

/**
 * Users management
 */
@RestController
@RequestMapping("/compounds")
@SuppressWarnings("unchecked")
public class CompoundController {

    @Autowired
    VaccinationInCompoundService vaccinationInCompoundService;

    @Autowired
    CompoundInVaccinationDateService compoundInVaccinationDateService;

    @Autowired
    CompoundService compoundService;

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Compound> delete(
            @PathVariable(value = "id") Long id) {
        Compound compound = compoundService.findOne(id);
        List<VaccinationInCompound> vaccinationInCompounds = compound.getVaccinations();
        for (VaccinationInCompound vaccinationInCompound : vaccinationInCompounds) {
            vaccinationInCompoundService.delete(vaccinationInCompound.getId());
        }
        List<CompoundInVaccinationDate> compoundInVaccinationDates = compound.getVaccinationDates();
        for (CompoundInVaccinationDate compoundInVaccinationDate : compoundInVaccinationDates) {
            compoundInVaccinationDateService.delete(compoundInVaccinationDate.getId());
        }
        compoundService.delete(id);
        return new ResponseEntity<Compound>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}/allVaccinations", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public List<VaccinationInCompound> getAllVaccinations(
            @PathVariable(value = "id") Long id) {
        Compound compound = compoundService.findOne(id);
        return compound.getVaccinations();
    }

    @RequestMapping(value = "/{id}/allVaccinationDates", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public List<CompoundInVaccinationDate> getAllVaccinationDates(
            @PathVariable(value = "id") Long id) {
        Compound compound = compoundService.findOne(id);
        return compound.getVaccinationDates();
    }
}
