package vaccination.events;

import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import vaccination.models.Vaccination;
import vaccination.models.VaccinationDate;

@RepositoryEventHandler(VaccinationDate.class)
public class VaccinationDateEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(VaccinationDate vaccinationDate) {
    }
}