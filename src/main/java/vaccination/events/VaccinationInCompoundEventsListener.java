package vaccination.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import vaccination.models.VaccinationInCompound;

@RepositoryEventHandler(VaccinationInCompound.class)
@Component
@Slf4j
public class VaccinationInCompoundEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(VaccinationInCompound vaccinationInCompound) {
        log.debug("Accessing");
    }
}