package vaccination.events;

import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import vaccination.models.User;

@RepositoryEventHandler(User.class)
public class UserEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(User user) {
    }
}