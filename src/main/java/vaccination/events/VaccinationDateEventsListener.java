package vaccination.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import vaccination.models.Vaccination;
import vaccination.models.VaccinationDate;

@RepositoryEventHandler(VaccinationDate.class)
@Component
@Slf4j
public class VaccinationDateEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(VaccinationDate vaccinationDate) {
        log.debug("Accessing");
    }
}