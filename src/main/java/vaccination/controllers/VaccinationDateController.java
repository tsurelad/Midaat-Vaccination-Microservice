package vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import vaccination.models.CompoundInVaccinationDate;
import vaccination.models.IDManagement;
import vaccination.models.VaccinationDate;
import vaccination.models.VaccinationInCompound;
import vaccination.services.CompoundInVaccinationDateService;
import vaccination.services.VaccinationDateService;

/**
 * Vaccination Controller
 */
@RestController
@RequestMapping("/vaccinationdates")
@SuppressWarnings("unchecked")
public class VaccinationDateController {
    @Autowired
    VaccinationDateService vaccinationDateService;

    @Autowired
    CompoundInVaccinationDateService compoundInVaccinationDateService;

    @Autowired
    VaccinationDateResourceAssembler vaccinationDateResourceAssembler;

    @Autowired
    CompoundInVaccinationDateResourceAssembler compoundInVaccinationDateResourceAssembler;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public PagedResources<VaccinationDate> showVaccinationDates(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<VaccinationDate> vaccinationDates = vaccinationDateService.findAll(pageable);
        return assembler.toResource(vaccinationDates, vaccinationDateResourceAssembler);
    }

    @RequestMapping(value = "/{vaccinationDateId}", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public Resource<VaccinationDate> showVaccinationDate(@PathVariable("vaccinationDateId") String vaccinationDateId) {
        VaccinationDate vaccinationDate = vaccinationDateService.findOne(vaccinationDateId);
        return vaccinationDateResourceAssembler.toResource(vaccinationDate);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public Resource<VaccinationDate> newVaccinationDate(@RequestBody VaccinationDate vaccinationDate) {
        vaccinationDate.setId(IDManagement.generateId());
        vaccinationDate = vaccinationDateService.save(vaccinationDate);
        return vaccinationDateResourceAssembler.toResource(vaccinationDate);
    }

    @RequestMapping(value = "/{vaccinationDateId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public Resource<VaccinationDate> updateVaccinationDate(@PathVariable("vaccinationDateId") String vaccinationDateId, @RequestBody VaccinationDate vaccinationDate) {
        vaccinationDate.setId(vaccinationDateId);
        vaccinationDate = vaccinationDateService.save(vaccinationDate);
        return vaccinationDateResourceAssembler.toResource(vaccinationDate);
    }

    @RequestMapping(value = "/{vaccinationDateId}/compounds", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public PagedResources<CompoundInVaccinationDate> showCompounds(@PathVariable("vaccinationDateId") String vaccinationDateId, Pageable pageable, PagedResourcesAssembler assembler) {
        Page<CompoundInVaccinationDate> compoundInVaccinationDates = compoundInVaccinationDateService.findAllByVaccinationDate_Id(vaccinationDateId, pageable);
        return assembler.toResource(compoundInVaccinationDates, compoundInVaccinationDateResourceAssembler);
    }
}
