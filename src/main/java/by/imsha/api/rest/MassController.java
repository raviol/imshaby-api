package by.imsha.api.rest;

import by.imsha.api.rest.docs.DocumentationConstants;
import by.imsha.domain.City;
import by.imsha.domain.Mass;
import by.imsha.domain.Mass;
import by.imsha.domain.WeekMassHolder;
import by.imsha.exception.DataFormatException;
import by.imsha.exception.ResourceNotFoundException;
import by.imsha.exception.RestErrorInfo;
import by.imsha.service.CityService;
import by.imsha.service.MassService;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alena Misan
 */

@Api(name = "Mass services", description = "Methods for managing masses", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0")
@ApiAuthNone
@RestController
@RequestMapping(value = "/api/mass")
public class MassController extends AbstractRestHandler {

    @Value("${imsha.date.format}")
    private String dateFormat;

    @Autowired
    private MassService massService;

    @Autowired
    private CityService cityService;


    @ApiMethod(id = DocumentationConstants.MASS_CREATE)
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ApiResponseObject Mass createMass(@ApiBodyObject @Valid @RequestBody Mass mass, HttpServletRequest request, HttpServletResponse response){
        return massService.createMass(mass);
    }

    @ApiMethod(id = DocumentationConstants.MASS_FIND_ONE)
    @ApiErrors(apierrors = {
            @ApiError(code = "404", description = "When the mass with the given id is not found")
    })
    @RequestMapping(value = "/{massId}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Resource<Mass> retrieveMass(@ApiPathParam(description = "The ID of mass to retrieve") @PathVariable("massId") String id,
                                           HttpServletRequest request, HttpServletResponse response){
        Mass mass = massService.getMass(id);
        checkResourceFound(mass);
        Resource<Mass> massResource = new Resource<Mass>(mass);
        massResource.add(linkTo(methodOn(MassController.class).retrieveMass(id,request, response)).withSelfRel());
        return massResource;
    }

    @ApiMethod(id = DocumentationConstants.MASS_UPDATE)
    @RequestMapping(value = "/{massId}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMass(@ApiPathParam(description = "massId of Mass object to be update") @PathVariable("massId") String id,@Valid @RequestBody Mass mass,
                             HttpServletRequest request, HttpServletResponse response) {
        Mass massForUpdate = this.massService.getMass(id);
        checkResourceFound(massForUpdate);
        mass.setId(id);
        this.massService.updateMass(mass);
    }

    @ApiMethod(id=DocumentationConstants.MASS_DELETE)
    @ApiErrors(apierrors = {
            @ApiError(code = "404", description = "When the mass with the given id is not found")
    })
    @RequestMapping(value = "/{massId}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMass(@ApiPathParam(description = "massId of Mass object to be deleted") @PathVariable("massId") String id, HttpServletRequest request,
                             HttpServletResponse response) {
        checkResourceFound(this.massService.getMass(id));
        this.massService.removeMass(id);
    }


    @RequestMapping(value = "/parish/{parishId}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Resource<Mass> retrieveMassByParish(@PathVariable("parishId") String parishId, HttpServletRequest request, HttpServletResponse response){
        Mass massByUser = this.massService.getMassByParish(parishId);
        checkResourceFound(massByUser);
        Resource<Mass> massResource = new Resource<Mass>(massByUser);
        // TODO add link to parish, not to self resource
        massResource.add(linkTo(methodOn(MassController.class).retrieveMassByParish(parishId,request, response)).withSelfRel());
        return massResource;
    }

    @RequestMapping(value = "/week",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Resource<WeekMassHolder> retrieveMassesByCity(@CookieValue(value = "cityId", required = false) String cityId,@RequestParam(value = "date", required = false) String day, HttpServletRequest request, HttpServletResponse response){
        if(cityId == null){
            City defaultCity = cityService.defaultCity();
            if(defaultCity == null){
                throw new ResourceNotFoundException(String.format("No default city (name = %s) founded", cityService.getDefaultCityName()));
            }
            cityId = defaultCity.getId();
        }

        List<Mass> masses = this.massService.getMassByCity(cityId);

        LocalDate date = null;
        if(day != null){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                date = LocalDate.parse(day, formatter);
           }catch (DateTimeParseException ex){
                log.warn(String.format("Date format is incorrect. Date - %s,format - %s ", day, dateFormat));
           }
        }
        if(date == null){
            date = LocalDate.now();
        }

        WeekMassHolder massHolder = new WeekMassHolder(date);

        massHolder.build(masses);

        Resource<WeekMassHolder> massResource = new Resource<WeekMassHolder>(massHolder);
        // TODO add links
//        massResource.add(linkTo(methodOn(MassController.class).retrieveMassByParish(parishId,request, response)).withSelfRel());
        return massResource;
    }





    @RequestMapping(value = "",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Mass> filterMasses(@RequestParam("filter") String filter,
                                       @RequestParam(value="offset", required = false, defaultValue = "0") String page,
                                       @RequestParam(value="limit", required = false, defaultValue = "10") String perPage,
                                       @RequestParam(value="sort", required = false, defaultValue = "+name") String sorting,
                                       HttpServletRequest request, HttpServletResponse response ){
//        massService.search(filter);
        return massService.search(filter, Integer.parseInt(page), Integer.parseInt(perPage), sorting);
    }



}
