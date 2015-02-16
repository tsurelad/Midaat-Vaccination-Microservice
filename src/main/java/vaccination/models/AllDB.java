package vaccination.models;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Everything!
 */
@Data
public class AllDB {
    List<Vaccination> vaccinations;
    List<Compound> compounds;
    List<VaccinationDate> vaccinationDates;
    List<VaccinationInCompound> vaccinationInCompounds;
    List<CompoundInVaccinationDate> compoundInVaccinationDates;
    List<Long> compoundIdsInVaccinationInCompound;
    List<Long> compoundIdsInCompoundInVaccinationDate;
    List<Long> vaccinationIdsInVaccinationInCompound;
    List<Long> vaccinationDateIdsInCompoundInVaccinationDate;
}
