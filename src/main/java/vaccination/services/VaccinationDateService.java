package vaccination.services;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import vaccination.models.VaccinationDate;

/**
 * Vaccination Date Service
 */
public interface VaccinationDateService extends PagingAndSortingRepository<VaccinationDate, Long> {
    Iterable<VaccinationDate> findAllByName(@Param("name") String name, Sort sort);
}
