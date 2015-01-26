package vaccination.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import vaccination.models.User;

@RepositoryEventHandler(User.class)
@Component
@Slf4j
public class UserEventsListener {

    @HandleBeforeSave
    @HandleBeforeDelete
    @HandleBeforeCreate
    @HandleBeforeLinkSave
    @HandleBeforeLinkDelete
    @Secured("ROLE_ADMIN")
    public void handleBefore(User user) {
        log.debug("Accessing");
    }
}