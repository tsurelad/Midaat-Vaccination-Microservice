package vaccination.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import vaccination.models.VaccinationInCompound;

/**
 * Vaccination In Compound Service
 */
public interface VaccinationInCompoundService extends PagingAndSortingRepository<VaccinationInCompound, Long> {

    Page<VaccinationInCompound> findAllByVaccination_Id(@Param("id") Long vaccinationId, Pageable pageable);
    Page<VaccinationInCompound> findAllByCompound_Id(@Param("id")Long compoundId, Pageable pageable);
}
