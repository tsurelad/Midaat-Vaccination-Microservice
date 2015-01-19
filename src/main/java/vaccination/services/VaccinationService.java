package vaccination.services;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import vaccination.models.Vaccination;

/**
 * Vaccination Service
 */
public interface VaccinationService extends PagingAndSortingRepository<Vaccination, Long> {
    Iterable<Vaccination> findAllByName(@Param("name") String name, Sort sort);
}
