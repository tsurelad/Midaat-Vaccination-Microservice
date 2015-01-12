package vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import vaccination.models.Compound;
import vaccination.models.CompoundInVaccinationDate;
import vaccination.models.IDManagement;
import vaccination.models.VaccinationInCompound;
import vaccination.services.CompoundInVaccinationDateService;
import vaccination.services.CompoundService;
import vaccination.services.VaccinationInCompoundService;

/**
 * Vaccination Controller
 */
@RestController
@RequestMapping("/compounds")
@SuppressWarnings("unchecked")
public class CompoundController {
    @Autowired
    CompoundService compoundService;

    @Autowired
    VaccinationInCompoundService vaccinationInCompoundService;

    @Autowired
    CompoundInVaccinationDateService compoundInVaccinationDateService;

    @Autowired
    CompoundResourceAssembler compoundResourceAssembler;

    @Autowired
    VaccinationInCompoundResourceAssembler vaccinationInCompoundResourceAssembler;

    @Autowired
    CompoundInVaccinationDateResourceAssembler compoundInVaccinationDateResourceAssembler;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public PagedResources<Compound> showCompounds(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Compound> compounds = compoundService.findAll(pageable);
        return assembler.toResource(compounds, compoundResourceAssembler);
    }

    @RequestMapping(value = "/{compoundId}", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public Resource<Compound> showCompound(@PathVariable("compoundId") String compoundId) {
        Compound compound = compoundService.findOne(compoundId);
        return compoundResourceAssembler.toResource(compound);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public Resource<Compound> newCompound(@RequestBody Compound compound) {
        compound.setId(IDManagement.generateId());
        compound = compoundService.save(compound);
        return compoundResourceAssembler.toResource(compound);
    }

    @RequestMapping(value = "/{compoundId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public Resource<Compound> updateCompound(@PathVariable("compoundId") String compoundId, @RequestBody Compound compound) {
        compound.setId(compoundId);
        compound = compoundService.save(compound);
        return compoundResourceAssembler.toResource(compound);
    }

    @RequestMapping(value = "/{compoundId}/vaccinations", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public PagedResources<VaccinationInCompound> showVaccinations(@PathVariable("compoundId") String compoundId, Pageable pageable, PagedResourcesAssembler assembler) {
        Page<VaccinationInCompound> vaccinationInCompounds = vaccinationInCompoundService.findAllByCompound_Id(compoundId, pageable);
        return assembler.toResource(vaccinationInCompounds, vaccinationInCompoundResourceAssembler);
    }

    @RequestMapping(value = "/{compoundId}/vaccinationdates", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public PagedResources<CompoundInVaccinationDate> showVaccinationDates(@PathVariable("compoundId") String compoundId, Pageable pageable, PagedResourcesAssembler assembler) {
        Page<CompoundInVaccinationDate> compoundInVaccinationDates = compoundInVaccinationDateService.findAllByCompound_Id(compoundId, pageable);
        return assembler.toResource(compoundInVaccinationDates, compoundInVaccinationDateResourceAssembler);
    }
}
