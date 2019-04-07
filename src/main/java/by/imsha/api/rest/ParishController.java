package by.imsha.api.rest;

import by.imsha.domain.Mass;
import by.imsha.domain.Parish;
import by.imsha.domain.dto.CascadeUpdateEntityInfo;
import by.imsha.domain.dto.ParishInfo;
import by.imsha.domain.dto.UpdateEntityInfo;
import by.imsha.exception.DataFormatException;
import by.imsha.exception.ValidationError;
import by.imsha.exception.ValidationErrorBuilder;
import by.imsha.service.MassService;
import by.imsha.service.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Parish services
 */

//@Api(name = "Parish services", description = "Methods for managing parishes", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
//@ApiVersion(since = "1.0")
//@ApiAuthNone
@RestController
@RequestMapping(value = "/api/parish")
public class ParishController extends AbstractRestHandler {

    @Autowired
    private ParishService parishService;

    @Autowired
    private MassService massService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public Parish createParish(@Valid @RequestBody Parish parish, HttpServletRequest request, HttpServletResponse response) {
        return parishService.createParish(parish);
    }

    @RequestMapping(value = "/{parishId}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Resource<Parish> retrieveParish(@PathVariable("parishId") String id,
                                           HttpServletRequest request, HttpServletResponse response) {
        Parish parish = parishService.getParish(id);
        checkResourceFound(parish);
        Resource<Parish> parishResource = new Resource<Parish>(parish);
        parishResource.add(linkTo(methodOn(ParishController.class).retrieveParish(id, request, response)).withSelfRel());
        return parishResource;
    }

    @RequestMapping(value = "/{parishId}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public UpdateEntityInfo updateParish(@PathVariable("parishId") String id, @RequestBody ParishInfo parishInfo) {
        Parish parishToUpdate = this.parishService.getParish(id);
        checkResourceFound(parishToUpdate);
        Parish updatedParish = this.parishService.updateParish(parishInfo, parishToUpdate );
        return new UpdateEntityInfo(updatedParish.getId(), UpdateEntityInfo.STATUS.UPDATED);
    }




    @RequestMapping(value = "/{parishId}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public UpdateEntityInfo removeParish(@PathVariable("parishId") String id, @RequestParam(value = "cascade", defaultValue = "false") Boolean cascade, HttpServletRequest request,
                                         HttpServletResponse response) {
        Parish parish = this.parishService.getParish(id);
        checkResourceFound(parish);
        UpdateEntityInfo updateEntityInfo;

        if (cascade != null && cascade ) {
            updateEntityInfo = new CascadeUpdateEntityInfo();
            List<UpdateEntityInfo> massEntityInfos = new ArrayList<>();
            ((CascadeUpdateEntityInfo) updateEntityInfo).setRelatedEntities(massEntityInfos);
            List<Mass> parishMasses = massService.getMassByParish(id);
            List<String> massIds = new ArrayList<>();
            for (Mass parishMass : parishMasses) {
                checkResourceFound(parishMass);
                massIds.add(parishMass.getId());
                massService.removeMass(parishMass);
                massEntityInfos.add(new UpdateEntityInfo(parishMass.getId(), UpdateEntityInfo.STATUS.DELETED));
            }
            log.warn(String.format("Cascade remove is executed for mass list: %s", massIds));
        } else{
          updateEntityInfo = new UpdateEntityInfo();
        }

        this.parishService.removeParish(id);
        updateEntityInfo.setId(id);
        updateEntityInfo.setStatus(UpdateEntityInfo.STATUS.DELETED.toString());
        return updateEntityInfo;
    }


    @RequestMapping(value = "/user/{userId}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Resource<Parish> retrieveParishByUser(@PathVariable("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
        Parish parishByUser = this.parishService.getParishByUser(userId);
        checkResourceFound(parishByUser);
        Resource<Parish> parishResource = new Resource<Parish>(parishByUser);
        parishResource.add(linkTo(methodOn(ParishController.class).retrieveParishByUser(userId, request, response)).withSelfRel());
        return parishResource;
    }


    @RequestMapping(value = "",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Parish> filterParishes(@RequestParam("filter") String filter,
                                       @RequestParam(value = "offset", required = false, defaultValue = "0") String page,
                                       @RequestParam(value = "limit", required = false, defaultValue = "10") String perPage,
                                       @RequestParam(value = "sort", required = false, defaultValue = "+name") String sorting,
                                       HttpServletRequest request, HttpServletResponse response) {
//        parishService.search(filter);
        return parishService.search(filter, Integer.parseInt(page), Integer.parseInt(perPage), sorting);
    }

}
