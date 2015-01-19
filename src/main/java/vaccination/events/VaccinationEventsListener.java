package vaccination.events;

import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import vaccination.models.Vaccination;

@RepositoryEventHandler(Vaccination.class)
public class VaccinationEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(Vaccination vaccination) {
    }
}