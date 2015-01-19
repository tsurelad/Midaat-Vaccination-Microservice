package vaccination.events;

import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import vaccination.models.VaccinationInCompound;

@RepositoryEventHandler(VaccinationInCompound.class)
public class VaccinationInCompoundEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(VaccinationInCompound vaccinationInCompound) {
    }
}