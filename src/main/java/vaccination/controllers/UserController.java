package vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vaccination.models.Authority;
import vaccination.models.User;
import vaccination.services.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Users management
 */
@RestController
@RequestMapping("/users")
@SuppressWarnings("unchecked")
public class UserController {

    private final UserService repository;

    UsersResourceAssembler usersResourceAssembler;

    @Autowired
    public UserController(UserService userService, UsersResourceAssembler usersResourceAssembler) {
        this.usersResourceAssembler = usersResourceAssembler;
        this.repository = userService;
        createAdminUser();
        createDefaultUser();
    }

    public void createAdminUser() {
        User user = repository.findOne("admin");
        if (user == null) {
            user = new User("admin", "admin", true);
            Authority authority1 = new Authority(user, "ROLE_USER");
            Authority authority2 = new Authority(user, "ROLE_ADMIN");
            List<Authority> authorities = new ArrayList<>();
            authorities.add(authority1);
            authorities.add(authority2);
            user.setAuthorities(authorities);
            repository.save(user);
        }
    }

    public void createDefaultUser() {
        User user = repository.findOne("user");
        if (user == null) {
            user = new User("user", "user", true);
            Authority authority = new Authority(user, "ROLE_USER");
            List<Authority> authorities = new ArrayList<>();
            authorities.add(authority);
            user.setAuthorities(authorities);
            repository.save(user);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN"})
    public PagedResources<User> showUsers(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<User> users = repository.findAll(pageable);
        return assembler.toResource(users, usersResourceAssembler);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN"})
    public Resource<User> showUser(@PathVariable("username") String username) {
        User user = repository.findOne(username);
        return usersResourceAssembler.toResource(user);
    }
}
