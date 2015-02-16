package vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import vaccination.models.*;
import vaccination.services.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Users management
 */
@RestController
@RequestMapping("/backup")
@SuppressWarnings("unchecked")
public class BackupController {

    @Autowired
    VaccinationInCompoundService vaccinationInCompoundService;

    @Autowired
    CompoundInVaccinationDateService compoundInVaccinationDateService;

    @Autowired
    CompoundService compoundService;

    @Autowired
    VaccinationService vaccinationService;

    @Autowired
    VaccinationDateService vaccinationDateService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN"})
    public AllDB createBackup() {
        AllDB allDB = new AllDB();

        Iterable<Compound> compounds = compoundService.findAll();
        allDB.setCompounds(new ArrayList<>());
        for (Compound compound : compounds) {
            compound.setVaccinationDates(null);
            compound.setVaccinations(null);
            allDB.getCompounds().add(compound);
        }

        Iterable<VaccinationDate> vaccinationDates = vaccinationDateService.findAll();
        allDB.setVaccinationDates(new ArrayList<>());
        for (VaccinationDate vaccinationDate : vaccinationDates) {
            vaccinationDate.setCompounds(null);
            allDB.getVaccinationDates().add(vaccinationDate);
        }

        Iterable<Vaccination> vaccinations = vaccinationService.findAll();
        allDB.setVaccinations(new ArrayList<>());
        for (Vaccination vaccination : vaccinations) {
            vaccination.setCompounds(null);
            allDB.getVaccinations().add(vaccination);
        }

        Iterable<VaccinationInCompound> vaccinationInCompounds = vaccinationInCompoundService.findAll();
        allDB.setVaccinationInCompounds(new ArrayList<>());
        allDB.setCompoundIdsInVaccinationInCompound(new ArrayList<>());
        allDB.setVaccinationIdsInVaccinationInCompound(new ArrayList<>());
        for (VaccinationInCompound vaccinationInCompound : vaccinationInCompounds) {
            if (vaccinationInCompound.getCompound() != null)
                allDB.getCompoundIdsInVaccinationInCompound().add(vaccinationInCompound.getCompound().getId());
            else
                allDB.getCompoundIdsInVaccinationInCompound().add(null);
            vaccinationInCompound.setCompound(null);
            if (vaccinationInCompound.getVaccination() != null)
                allDB.getVaccinationIdsInVaccinationInCompound().add(vaccinationInCompound.getVaccination().getId());
            else
                allDB.getVaccinationIdsInVaccinationInCompound().add(null);
            vaccinationInCompound.setVaccination(null);
            allDB.getVaccinationInCompounds().add(vaccinationInCompound);
        }

        Iterable<CompoundInVaccinationDate> compoundInVaccinationDates = compoundInVaccinationDateService.findAll();
        allDB.setCompoundInVaccinationDates(new ArrayList<>());
        allDB.setCompoundIdsInCompoundInVaccinationDate(new ArrayList<>());
        allDB.setVaccinationDateIdsInCompoundInVaccinationDate(new ArrayList<>());
        for (CompoundInVaccinationDate compoundInVaccinationDate : compoundInVaccinationDates) {
            if (compoundInVaccinationDate.getCompound() != null)
                allDB.getCompoundIdsInCompoundInVaccinationDate().add(compoundInVaccinationDate.getCompound().getId());
            else
                allDB.getCompoundIdsInCompoundInVaccinationDate().add(null);
            compoundInVaccinationDate.setCompound(null);
            if (compoundInVaccinationDate.getVaccinationDate() != null)
                allDB.getVaccinationDateIdsInCompoundInVaccinationDate().add(compoundInVaccinationDate.getVaccinationDate().getId());
            else
                allDB.getVaccinationDateIdsInCompoundInVaccinationDate().add(null);
            compoundInVaccinationDate.setVaccinationDate(null);
            allDB.getCompoundInVaccinationDates().add(compoundInVaccinationDate);
        }

        return allDB;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public String restoreBackup(@RequestBody AllDB allDB) {
        compoundService.save(allDB.getCompounds());
        vaccinationDateService.save(allDB.getVaccinationDates());
        vaccinationService.save(allDB.getVaccinations());

        for (int i=0; i<allDB.getVaccinationInCompounds().size(); i++) {
            Long cid = allDB.getCompoundIdsInVaccinationInCompound().get(i);
            Long vid = allDB.getVaccinationIdsInVaccinationInCompound().get(i);
            Compound c = null;
            if (cid != null)
                c = compoundService.findOne(cid);
            Vaccination v = null;
            if (vid != null)
                v = vaccinationService.findOne(vid);
            allDB.getVaccinationInCompounds().get(i).setCompound(c);
            allDB.getVaccinationInCompounds().get(i).setVaccination(v);
        }
        vaccinationInCompoundService.save(allDB.getVaccinationInCompounds());

        for (int i=0; i<allDB.getCompoundInVaccinationDates().size(); i++) {
            Long cid = allDB.getCompoundIdsInCompoundInVaccinationDate().get(i);
            Long vid = allDB.getVaccinationDateIdsInCompoundInVaccinationDate().get(i);
            Compound c = null;
            if (cid != null)
                c = compoundService.findOne(cid);
            VaccinationDate v = null;
            if (vid != null)
                v = vaccinationDateService.findOne(vid);
            allDB.getCompoundInVaccinationDates().get(i).setCompound(c);
            allDB.getCompoundInVaccinationDates().get(i).setVaccinationDate(v);
        }
        compoundInVaccinationDateService.save(allDB.getCompoundInVaccinationDates());

        return "OK";
    }


}
