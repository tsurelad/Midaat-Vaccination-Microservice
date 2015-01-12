package vaccination.controllers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import vaccination.models.IdObject;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * IdObject resource assembler
 */
public abstract class IdBasedResourceAssembler<T extends IdObject> extends ResourceAssemblerSupport<T, Resource> {
    private final Class<?> CC;

    public IdBasedResourceAssembler(Class<?> CC) {
        super(CC, Resource.class);
        this.CC = CC;
    }

    @Override
    public List<Resource> toResources(Iterable<? extends T> objects) {
        List<Resource> resources = new ArrayList<Resource>();
        for(T o : objects) {
            resources.add(new Resource<T>(o, linkTo(CC).slash(o.getId()).withSelfRel()));
        }
        return resources;
    }

    @Override
    public Resource toResource(T o) {
        return new Resource<T>(o, linkTo(CC).slash(o.getId()).withSelfRel());
    }
}