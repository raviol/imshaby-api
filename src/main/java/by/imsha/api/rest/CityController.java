package by.imsha.api.rest;

import by.imsha.domain.City;
import by.imsha.domain.LocalizedCity;
import by.imsha.domain.dto.CityInfo;
import by.imsha.domain.dto.LocalizedCityInfo;
import by.imsha.domain.dto.UpdateEntityInfo;
import by.imsha.domain.dto.mapper.CityMapper;
import by.imsha.exception.InvalidLocaleException;
import by.imsha.service.CityService;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.Locale;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/api/cities")
public class CityController extends AbstractRestHandler {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity( @Valid @RequestBody CityInfo city,
                                 HttpServletRequest request, HttpServletResponse response) {
        City createdCity = this.cityService.createCity(new City(city.getName()));
        return createdCity;
    }

    @RequestMapping(value = "/{cityId}/lang/{lc}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public UpdateEntityInfo createLocalizedCity(@Valid @RequestBody LocalizedCityInfo cityInfo,
                                    @PathVariable("cityId") String id,
                                    @PathVariable("lc") String locale,
                                    HttpServletRequest request, HttpServletResponse response) {
        City origin = this.cityService.retrieveCity(id);
        checkResourceFound(origin);
        Locale localeObj = new Locale(locale);
        if(!LocaleUtils.isAvailableLocale(localeObj)){
            throw new InvalidLocaleException("Invalid lang specified : " + locale);
        }
        LocalizedCity localizedCity = new LocalizedCity(localeObj, id, cityInfo.getName());
        origin.getLocalizedInfo().put(localeObj, localizedCity);

        City updatedCity = this.cityService.updateCity(origin);
        return new UpdateEntityInfo(updatedCity.getId(), UpdateEntityInfo.STATUS.UPDATED);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<City> getAllCity(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            log.warn(String.format("%s = %s", headerName , request.getHeader(headerName)));
        }
        return this.cityService.getAllCities(page, size);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public City getCity(@ApiParam(value = "The ID of the city.", required = true)
                             @PathVariable("id") String id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        City city = this.cityService.retrieveCity(id);
        checkResourceFound(city);
        //todo: http://goo.gl/6iNAkz
        return city;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public UpdateEntityInfo updateCity(@ApiParam(value = "The ID of the existing cityDTO resource.", required = true)
                                 @PathVariable("id") String id, @Valid @RequestBody CityInfo cityDTO,
                                 HttpServletRequest request, HttpServletResponse response) {
        City resource = this.cityService.retrieveCity(id);
        checkResourceFound(resource);
        CityMapper.MAPPER.updateCityFromDTO(cityDTO, resource);
        City updatedSity = this.cityService.updateCity(resource);
      return new UpdateEntityInfo(updatedSity.getId(), UpdateEntityInfo.STATUS.UPDATED);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public UpdateEntityInfo deleteCity(@ApiParam(value = "The ID of the existing city resource.", required = true)
                                 @PathVariable("id") String id, HttpServletRequest request,
                                       HttpServletResponse response) {
        checkResourceFound(this.cityService.retrieveCity(id));
        this.cityService.removeCity(id);
        return new UpdateEntityInfo(id, UpdateEntityInfo.STATUS.DELETED);
    }
}
