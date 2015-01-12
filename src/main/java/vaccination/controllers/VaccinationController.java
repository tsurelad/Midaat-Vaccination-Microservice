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
import vaccination.models.Vaccination;
import vaccination.models.VaccinationInCompound;
import vaccination.services.VaccinationInCompoundService;
import vaccination.services.VaccinationService;

/**
 * Vaccination Controller
 */
@RestController
@RequestMapping("/vaccinations")
@SuppressWarnings("unchecked")
public class VaccinationController {
    @Autowired
    VaccinationService vaccinationService;

    @Autowired
    VaccinationInCompoundService vaccinationInCompoundService;

    @Autowired
    VaccinationResourceAssembler vaccinationResourceAssembler;

    @Autowired
    VaccinationInCompoundResourceAssembler vaccinationInCompoundResourceAssembler;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public PagedResources<Vaccination> showVaccinations(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Vaccination> vaccinations = vaccinationService.findAll(pageable);
        return assembler.toResource(vaccinations, vaccinationResourceAssembler);
    }

    @RequestMapping(value = "/{vaccinationId}", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public Resource<Vaccination> showVaccination(@PathVariable("vaccinationId") String vaccinationId) {
        Vaccination vaccination = vaccinationService.findOne(vaccinationId);
        return vaccinationResourceAssembler.toResource(vaccination);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public Resource<Vaccination> newVaccination(@RequestBody Vaccination vaccination) {
        vaccination.setId(IDManagement.generateId());
        vaccination = vaccinationService.save(vaccination);
        return vaccinationResourceAssembler.toResource(vaccination);
    }

    @RequestMapping(value = "/{vaccinationId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public Resource<Vaccination> updateVaccination(@PathVariable("vaccinationId") String vaccinationId, @RequestBody Vaccination vaccination) {
        vaccination.setId(vaccinationId);
        vaccination = vaccinationService.save(vaccination);
        return vaccinationResourceAssembler.toResource(vaccination);
    }

    @RequestMapping(value = "/{vaccinationId}/compounds", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public PagedResources<VaccinationInCompound> showCompounds(@PathVariable("vaccinationId") String vaccinationId, Pageable pageable, PagedResourcesAssembler assembler) {
        Page<VaccinationInCompound> vaccinationInCompounds = vaccinationInCompoundService.findAllByVaccination_Id(vaccinationId, pageable);
        return assembler.toResource(vaccinationInCompounds, vaccinationInCompoundResourceAssembler);
    }
}
