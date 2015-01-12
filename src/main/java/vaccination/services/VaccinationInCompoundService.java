package vaccination.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import vaccination.models.VaccinationInCompound;

/**
 * Vaccination In Compound Service
 */
public interface VaccinationInCompoundService extends PagingAndSortingRepository<VaccinationInCompound, String> {

    Iterable<VaccinationInCompound> findAllByVaccination_Id(String vaccinationId, Sort sort);
    Page<VaccinationInCompound> findAllByVaccination_Id(String vaccinationId, Pageable pageable);

    Iterable<VaccinationInCompound> findAllByCompound_Id(String compoundId, Sort sort);
    Page<VaccinationInCompound> findAllByCompound_Id(String compoundId, Pageable pageable);
}
