package vaccination.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import vaccination.models.CompoundInVaccinationDate;

/**
 * Compound In Vaccination Date Service
 */
public interface CompoundInVaccinationDateService extends PagingAndSortingRepository<CompoundInVaccinationDate, Long> {

    Page<CompoundInVaccinationDate> findAllByVaccinationDate_Id(@Param("id") Long vaccinationdateId, Pageable pageable);
    Page<CompoundInVaccinationDate> findAllByCompound_Id(@Param("id") Long compoundId, Pageable pageable);
}
