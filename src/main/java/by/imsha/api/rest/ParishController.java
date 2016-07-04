package by.imsha.api.rest;

import by.imsha.domain.Parish;
import by.imsha.exception.DataFormatException;
import by.imsha.service.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 */

@RestController
@RequestMapping(value = "/secured/parish")
public class ParishController extends AbstractRestHandler {

    @Autowired
    private ParishService parishService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public Parish createParish(@RequestBody Parish parish, HttpServletRequest request, HttpServletResponse response){
        return parishService.createParish(parish);
    }

    @RequestMapping(value = "/{parishId}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Resource<Parish> retrieveParish(@PathVariable("parishId") String id,
                                 HttpServletRequest request, HttpServletResponse response){
        Parish parish = parishService.getParish(id);
        checkResourceFound(parish);
        Resource<Parish> parishResource = new Resource<Parish>(parish);
        parishResource.add(linkTo(methodOn(ParishController.class).retrieveParish(id,request, response)).withSelfRel());
        return parishResource;
    }

    @RequestMapping(value = "/{parishId}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateParish(@PathVariable("parishId") String id, @RequestBody Parish parish,
                           HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.parishService.getParish(id));
        if (id != parish.getId()) throw new DataFormatException("ID doesn't match!");
        this.parishService.updateParish(parish);
    }

    @RequestMapping(value = "/{parishId}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeParish(@PathVariable("parishId") String id, HttpServletRequest request,
                             HttpServletResponse response) {
        checkResourceFound(this.parishService.getParish(id));
        this.parishService.removeParish(id);
    }


    @RequestMapping(value = "",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Parish> filterParishes(@RequestParam("filter") String filter,
                                     @RequestParam(value="offset", required = false, defaultValue = "0") String page,
                                     @RequestParam(value="limit", required = false, defaultValue = "10") String perPage,
                                     @RequestParam(value="sort", required = false, defaultValue = "+name") String sorting,
                               HttpServletRequest request, HttpServletResponse response ){
//        parishService.search(filter);
        return parishService.search(filter).getContent();
    }

}
