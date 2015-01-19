package vaccination.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import vaccination.models.Compound;
import org.springframework.data.domain.Sort;

/**
 * Compound Service
 */
public interface CompoundService extends PagingAndSortingRepository<Compound, Long> {
    Iterable<Compound> findAllByName(@Param("name") String name, Sort sort);
}
