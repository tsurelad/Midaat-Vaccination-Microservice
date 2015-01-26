package vaccination.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import vaccination.models.Vaccination;

@RepositoryEventHandler(Vaccination.class)
@Component
@Slf4j
public class VaccinationEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(Vaccination vaccination) {
        log.debug("Accessing");
    }
}