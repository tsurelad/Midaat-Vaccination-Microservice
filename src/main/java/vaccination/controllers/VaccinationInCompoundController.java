package vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vaccination.models.Compound;
import vaccination.models.Vaccination;
import vaccination.models.VaccinationInCompound;
import vaccination.services.CompoundService;
import vaccination.services.VaccinationInCompoundService;
import vaccination.services.VaccinationService;

/**
 * Users management
 */
@RestController
@RequestMapping("/vaccinationInCompounds")
@SuppressWarnings("unchecked")
public class VaccinationInCompoundController {

    @Autowired
    VaccinationInCompoundService vaccinationInCompoundService;

    @Autowired
    VaccinationService vaccinationService;

    @Autowired
    CompoundService compoundService;

    @RequestMapping(value = "/createByNames", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public VaccinationInCompound createByNames(
            @RequestParam(value = "vaccinationName", required = true) String vaccinationName,
            @RequestParam(value = "compoundName", required = true) String compoundName,
            @RequestParam(value = "description", required = false, defaultValue = "") String description) {
        Sort.Order[] orders = {new Sort.Order(Sort.Direction.ASC, "name")};
        Vaccination vaccination = vaccinationService.findAllByName(vaccinationName, new Sort(orders)).iterator().next();
        Compound compound = compoundService.findAllByName(compoundName, new Sort(orders)).iterator().next();
        VaccinationInCompound vaccinationInCompound = new VaccinationInCompound();
        vaccinationInCompound.setVaccination(vaccination);
        vaccinationInCompound.setCompound(compound);
        vaccinationInCompound.setDescription(description);
        return vaccinationInCompoundService.save(vaccinationInCompound);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public VaccinationInCompound create(
            @RequestParam(value = "vaccinationId", required = true) Long vaccinationId,
            @RequestParam(value = "compoundId", required = true) Long compoundId,
            @RequestParam(value = "description", required = false, defaultValue = "") String description) {
        Vaccination vaccination = vaccinationService.findOne(vaccinationId);
        Compound compound = compoundService.findOne(compoundId);
        VaccinationInCompound vaccinationInCompound = new VaccinationInCompound();
        vaccinationInCompound.setVaccination(vaccination);
        vaccinationInCompound.setCompound(compound);
        vaccinationInCompound.setDescription(description);
        return vaccinationInCompoundService.save(vaccinationInCompound);
    }
}
