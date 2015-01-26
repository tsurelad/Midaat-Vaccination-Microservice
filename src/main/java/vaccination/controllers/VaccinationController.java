package vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import vaccination.models.Compound;
import vaccination.models.Vaccination;
import vaccination.models.VaccinationInCompound;
import vaccination.services.CompoundService;
import vaccination.services.VaccinationInCompoundService;
import vaccination.services.VaccinationService;

import java.util.List;

/**
 * Users management
 */
@RestController
@RequestMapping("/vaccinations")
@SuppressWarnings("unchecked")
public class VaccinationController {

    @Autowired
    VaccinationInCompoundService vaccinationInCompoundService;

    @Autowired
    VaccinationService vaccinationService;

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Vaccination> delete(
            @PathVariable(value = "id") Long id) {
        Vaccination vaccination = vaccinationService.findOne(id);
        List<VaccinationInCompound> vaccinationInCompounds = vaccination.getCompounds();
        for (VaccinationInCompound vaccinationInCompound : vaccinationInCompounds) {
            vaccinationInCompoundService.delete(vaccinationInCompound.getId());
        }
        vaccinationService.delete(id);
        return new ResponseEntity<Vaccination>(HttpStatus.NO_CONTENT);
    }
}
