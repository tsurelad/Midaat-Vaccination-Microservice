package vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vaccination.models.Compound;
import vaccination.models.CompoundInVaccinationDate;
import vaccination.models.VaccinationDate;
import vaccination.models.VaccinationInCompound;
import vaccination.services.*;

import java.util.List;

/**
 * Users management
 */
@RestController
@RequestMapping("/vaccinationDates")
@SuppressWarnings("unchecked")
public class VaccinationDateController {

    @Autowired
    CompoundInVaccinationDateService compoundInVaccinationDateService;

    @Autowired
    VaccinationDateService vaccinationDateService;

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<VaccinationDate> delete(
            @PathVariable(value = "id") Long id) {
        VaccinationDate vaccinationDate = vaccinationDateService.findOne(id);
        List<CompoundInVaccinationDate> compoundInVaccinationDates = vaccinationDate.getCompounds();
        for (CompoundInVaccinationDate compoundInVaccinationDate : compoundInVaccinationDates) {
            compoundInVaccinationDateService.delete(compoundInVaccinationDate.getId());
        }
        vaccinationDateService.delete(id);
        return new ResponseEntity<VaccinationDate>(HttpStatus.NO_CONTENT);
    }
}
