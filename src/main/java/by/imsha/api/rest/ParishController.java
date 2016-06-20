package by.imsha.api.rest;

import by.imsha.domain.Parish;
import by.imsha.exception.DataFormatException;
import by.imsha.service.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public Parish retrieveParish(@PathVariable("parishId") String id,
                                 HttpServletRequest request, HttpServletResponse response){
        Parish parish = parishService.retrieveParish(id);
        checkResourceFound(parish);
        return parish;
    }

    @RequestMapping(value = "/{parishId}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateParish(@PathVariable("parishId") String id, @RequestBody Parish parish,
                           HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.parishService.retrieveParish(id));
        if (id != parish.getId()) throw new DataFormatException("ID doesn't match!");
        this.parishService.updateParish(parish);
    }

    @RequestMapping(value = "/{parishId}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeParish(@PathVariable("parishId") String id, HttpServletRequest request,
                             HttpServletResponse response) {
        checkResourceFound(this.parishService.retrieveParish(id));
        this.parishService.removeParish(id);
    }








}
