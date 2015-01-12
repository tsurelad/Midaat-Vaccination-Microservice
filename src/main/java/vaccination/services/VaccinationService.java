package vaccination.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import vaccination.models.Vaccination;

/**
 * Vaccination Service
 */
public interface VaccinationService extends PagingAndSortingRepository<Vaccination, String> {
}
