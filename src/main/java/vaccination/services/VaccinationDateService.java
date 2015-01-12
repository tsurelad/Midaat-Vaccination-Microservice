package vaccination.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import vaccination.models.VaccinationDate;

/**
 * Vaccination Date Service
 */
public interface VaccinationDateService extends PagingAndSortingRepository<VaccinationDate, String> {
}
