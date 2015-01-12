package vaccination.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import vaccination.models.Compound;

/**
 * Compound Service
 */
public interface CompoundService extends PagingAndSortingRepository<Compound, String> {
}
