package vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vaccination.models.*;
import vaccination.services.*;

/**
 * Users management
 */
@RestController
@RequestMapping("/compoundInVaccinationDates")
@SuppressWarnings("unchecked")
public class CompoundInVaccinationDateController {

    @Autowired
    CompoundInVaccinationDateService compoundInVaccinationDateService;

    @Autowired
    VaccinationDateService vaccinationDateService;

    @Autowired
    CompoundService compoundService;

    @RequestMapping(value = "/createByNames", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public CompoundInVaccinationDate createByNames(
            @RequestParam(value = "vaccinationDateName", required = true) String vaccinationDateName,
            @RequestParam(value = "compoundName", required = true) String compoundName,
            @RequestParam(value = "description", required = false, defaultValue = "") String description) {
        Sort.Order[] orders = {new Sort.Order(Sort.Direction.ASC, "name")};
        VaccinationDate vaccinationDate = vaccinationDateService.findAllByName(vaccinationDateName, new Sort(orders)).iterator().next();
        Compound compound = compoundService.findAllByName(compoundName, new Sort(orders)).iterator().next();
        CompoundInVaccinationDate compoundInVaccinationDate = new CompoundInVaccinationDate();
        compoundInVaccinationDate.setVaccinationDate(vaccinationDate);
        compoundInVaccinationDate.setCompound(compound);
        compoundInVaccinationDate.setDescription(description);
        return compoundInVaccinationDateService.save(compoundInVaccinationDate);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public CompoundInVaccinationDate create(
            @RequestParam(value = "vaccinationDateId", required = true) Long vaccinationDateId,
            @RequestParam(value = "compoundId", required = true) Long compoundId,
            @RequestParam(value = "description", required = false, defaultValue = "") String description) {
        VaccinationDate vaccinationDate = vaccinationDateService.findOne(vaccinationDateId);
        Compound compound = compoundService.findOne(compoundId);
        CompoundInVaccinationDate compoundInVaccinationDate = new CompoundInVaccinationDate();
        compoundInVaccinationDate.setVaccinationDate(vaccinationDate);
        compoundInVaccinationDate.setCompound(compound);
        compoundInVaccinationDate.setDescription(description);
        return compoundInVaccinationDateService.save(compoundInVaccinationDate);
    }
}
