package vaccination.events;

import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import vaccination.models.Compound;

@RepositoryEventHandler(Compound.class)
public class CompoundEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(Compound compound) {
    }
}