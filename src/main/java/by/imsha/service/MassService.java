package by.imsha.service;

import by.imsha.domain.Mass;
import by.imsha.repository.MassRepository;
import by.imsha.utils.ServiceUtils;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

import static by.imsha.utils.Constants.LIMIT;
import static by.imsha.utils.Constants.PAGE;

/**
 * @author Alena Misan
 */

@Service
public class MassService {
    private static Logger logger = LoggerFactory.getLogger(MassService.class);

    private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

    private MongoVisitor mongoVisitor = new MongoVisitor();

    @Autowired
    private MassRepository massRepository;

    @Caching(evict = {
            @CacheEvict(cacheNames = "massCache", key = "'massesByCity:' + #p0.cityId")
    })
    public Mass createMass(Mass mass){
        return massRepository.save(mass);
    }

    public List<Mass> createMassesWithList(List<Mass> masses){
        return massRepository.save(masses);
    }

    public List<Mass> getMassByParish(String parishId){
        return massRepository.findByParishId(parishId);
    }

    @Cacheable(cacheNames = "massCache", key = "'massesByCity:' + #cityId")
    public List<Mass> getMassByCity(String cityId){
//        TODO check index for cityID and deleted.
        List<Mass> masses = massRepository.findByCityIdAndDeleted(cityId, false);
        if(CollectionUtils.isEmpty(masses)){
            if(logger.isWarnEnabled()){
                logger.warn(String.format("No masses found with city id = %s, and deleted = %s", cityId, Boolean.FALSE));
            }
        }
        return masses;
    }

    @Cacheable(cacheNames = "massCache")
    public Mass getMass(String id){
        return massRepository.findOne(id);
    }

    public List<Mass> search(String filter){
        // TODO can be added default sorting
        return search(filter, PAGE, LIMIT, null);
    }

    public List<Mass> search(String filter, int offset, int limit, String sort){
        Query query = prepareQuery(filter, offset, limit, sort);
        if (query == null) return null;
        List<Mass> masses = this.massRepository.search(query, Mass.class);
        return masses;
    }

    public Query prepareQuery(String filter, int offset, int limit, String sort) {
        if(StringUtils.isBlank(filter)){
            if(logger.isInfoEnabled()){
                logger.info("No searching masses: query is blank");
            }
            return null;
        }
        int[] offsetAndLimit = ServiceUtils.calculateOffsetAndLimit(offset, limit);
        Condition<GeneralQueryBuilder> condition = pipeline.apply(filter, Mass.class);
        return ServiceUtils.buildMongoQuery(sort, offsetAndLimit[0], offsetAndLimit[1], condition, mongoVisitor);
    }


    @Caching(evict = {
            @CacheEvict(cacheNames = "massCache", key = "#p0.id"),
            @CacheEvict(cacheNames = "massCache", key = "'massesByCity:' + #p0.cityId")
    })
    public Mass updateMass(Mass mass){
        return massRepository.save(mass);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "massCache", key = "#p0.id"),
            @CacheEvict(cacheNames = "massCache", key = "'massesByCity:' + #p0.cityId")
    })
    public void removeMass(Mass mass){
        massRepository.delete(mass.getId());
    }

    public List<Mass> removeMasses(String parishId){
        return massRepository.deleteByParishId(parishId);
    }

}
