package vaccination.events;

import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import vaccination.models.Authority;

@RepositoryEventHandler(Authority.class)
public class AuthorityEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(Authority authority) {
    }
}