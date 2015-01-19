package vaccination.events;

import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import vaccination.models.CompoundInVaccinationDate;

@RepositoryEventHandler(CompoundInVaccinationDate.class)
public class CompoundInVaccinationDateEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(CompoundInVaccinationDate compoundInVaccinationDate) {
    }
}