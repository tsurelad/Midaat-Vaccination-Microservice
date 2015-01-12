package vaccination.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import vaccination.models.Authority;
import vaccination.models.AuthorityId;

/**
 * Authority Service
 */
public interface AuthorityService extends PagingAndSortingRepository<Authority, AuthorityId> {
}
