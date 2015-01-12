package vaccination.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import vaccination.models.CompoundInVaccinationDate;

/**
 * Compound In Vaccination Date Service
 */
public interface CompoundInVaccinationDateService extends PagingAndSortingRepository<CompoundInVaccinationDate, String> {

    Iterable<CompoundInVaccinationDate> findAllByVaccinationDate_Id(String vaccinationdateId, Sort sort);
    Page<CompoundInVaccinationDate> findAllByVaccinationDate_Id(String vaccinationdateId, Pageable pageable);

    Iterable<CompoundInVaccinationDate> findAllByCompound_Id(String compoundId, Sort sort);
    Page<CompoundInVaccinationDate> findAllByCompound_Id(String compoundId, Pageable pageable);
}
