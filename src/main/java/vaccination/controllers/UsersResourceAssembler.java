package vaccination.controllers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import vaccination.models.User;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Users resource assembler
 */
@Component
public class UsersResourceAssembler extends ResourceAssemblerSupport<User, Resource> {
    public UsersResourceAssembler() {
        super(UserController.class, Resource.class);
    }

    @Override
    public List<Resource> toResources(Iterable<? extends User> users) {
        List<Resource> resources = new ArrayList<Resource>();
        for(User user : users) {
            resources.add(new Resource<User>(user, linkTo(UserController.class).slash(user.getUsername()).withSelfRel()));
        }
        return resources;
    }

    @Override
    public Resource toResource(User user) {
        return new Resource<User>(user, linkTo(UserController.class).slash(user.getUsername()).withSelfRel());
    }
}