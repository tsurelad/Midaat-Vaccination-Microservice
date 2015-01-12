package vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import vaccination.models.IDManagement;
import vaccination.models.VaccinationInCompound;
import vaccination.services.VaccinationInCompoundService;

/**
 * Vaccination Controller
 */
@RestController
@RequestMapping("/vaccinationincompounds")
@SuppressWarnings("unchecked")
public class VaccinationInCompoundController {
    @Autowired
    VaccinationInCompoundService vaccinationInCompoundService;

    @Autowired
    VaccinationInCompoundResourceAssembler vaccinationInCompoundResourceAssembler;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public PagedResources<VaccinationInCompound> showVaccinationInCompounds(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<VaccinationInCompound> vaccinationInCompounds = vaccinationInCompoundService.findAll(pageable);
        return assembler.toResource(vaccinationInCompounds, vaccinationInCompoundResourceAssembler);
    }

    @RequestMapping(value = "/{vaccinationInCompoundId}", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public Resource<VaccinationInCompound> showVaccinationInCompound(@PathVariable("vaccinationInCompoundId") String vaccinationInCompoundId) {
        VaccinationInCompound vaccinationInCompound = vaccinationInCompoundService.findOne(vaccinationInCompoundId);
        return vaccinationInCompoundResourceAssembler.toResource(vaccinationInCompound);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public Resource<VaccinationInCompound> newVaccinationInCompound(@RequestBody VaccinationInCompound vaccinationInCompound) {
        vaccinationInCompound.setId(IDManagement.generateId());
        vaccinationInCompound = vaccinationInCompoundService.save(vaccinationInCompound);
        return vaccinationInCompoundResourceAssembler.toResource(vaccinationInCompound);
    }

    @RequestMapping(value = "/{vaccinationInCompoundId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public Resource<VaccinationInCompound> updateVaccinationInCompound(@PathVariable("vaccinationInCompoundId") String vaccinationInCompoundId, @RequestBody VaccinationInCompound vaccinationInCompound) {
        vaccinationInCompound.setId(vaccinationInCompoundId);
        vaccinationInCompound = vaccinationInCompoundService.save(vaccinationInCompound);
        return vaccinationInCompoundResourceAssembler.toResource(vaccinationInCompound);
    }
}
