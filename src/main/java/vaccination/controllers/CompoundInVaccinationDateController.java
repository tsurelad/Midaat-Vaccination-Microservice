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
import vaccination.services.CompoundInVaccinationDateService;

/**
 * Vaccination Controller
 */
@RestController
@RequestMapping("/compoundinvaccinationdates")
@SuppressWarnings("unchecked")
public class CompoundInVaccinationDateController {
    @Autowired
    CompoundInVaccinationDateService compoundInVaccinationDateService;

    @Autowired
    CompoundInVaccinationDateResourceAssembler compoundInVaccinationDateResourceAssembler;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public PagedResources<CompoundInVaccinationDate> showCompoundInVaccinationDates(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<CompoundInVaccinationDate> compoundInVaccinationDates = compoundInVaccinationDateService.findAll(pageable);
        return assembler.toResource(compoundInVaccinationDates, compoundInVaccinationDateResourceAssembler);
    }

    @RequestMapping(value = "/{compoundInVaccinationDateId}", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public Resource<CompoundInVaccinationDate> showCompoundInVaccinationDate(@PathVariable("compoundInVaccinationDateId") String compoundInVaccinationDateId) {
        CompoundInVaccinationDate compoundInVaccinationDate = compoundInVaccinationDateService.findOne(compoundInVaccinationDateId);
        return compoundInVaccinationDateResourceAssembler.toResource(compoundInVaccinationDate);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public Resource<CompoundInVaccinationDate> newCompoundInVaccinationDate(@RequestBody CompoundInVaccinationDate compoundInVaccinationDate) {
        compoundInVaccinationDate.setId(IDManagement.generateId());
        compoundInVaccinationDate = compoundInVaccinationDateService.save(compoundInVaccinationDate);
        return compoundInVaccinationDateResourceAssembler.toResource(compoundInVaccinationDate);
    }

    @RequestMapping(value = "/{compoundInVaccinationDateId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public Resource<CompoundInVaccinationDate> updateCompoundInVaccinationDate(@PathVariable("compoundInVaccinationDateId") String compoundInVaccinationDateId, @RequestBody CompoundInVaccinationDate compoundInVaccinationDate) {
        compoundInVaccinationDate.setId(compoundInVaccinationDateId);
        compoundInVaccinationDate = compoundInVaccinationDateService.save(compoundInVaccinationDate);
        return compoundInVaccinationDateResourceAssembler.toResource(compoundInVaccinationDate);
    }
}
