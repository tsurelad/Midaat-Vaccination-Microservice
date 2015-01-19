package vaccination.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import vaccination.models.*;

@Configuration
public class ExposeIds extends RepositoryRestMvcConfiguration {
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Authority.class);
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Compound.class);
        config.exposeIdsFor(CompoundInVaccinationDate.class);
        config.exposeIdsFor(VaccinationDate.class);
        config.exposeIdsFor(Vaccination.class);
        config.exposeIdsFor(VaccinationInCompound.class);
    }
}