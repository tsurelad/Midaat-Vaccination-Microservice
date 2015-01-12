package vaccination.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import vaccination.models.User;

/**
 * User Service
 */
public interface UserService extends PagingAndSortingRepository<User, String> {
}
