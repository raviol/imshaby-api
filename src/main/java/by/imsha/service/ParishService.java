package by.imsha.service;

import by.imsha.domain.Parish;
import by.imsha.repository.ParishRepository;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParishService {
    private static Logger logger = LoggerFactory.getLogger(ParishService.class);

    private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

    @Autowired
    ParishRepository parishRepository;

    public Parish createParish(Parish parish){
        return parishRepository.save(parish);
    }

    public List<Parish> createParishesWithList(List<Parish> parishes){
        return parishRepository.save(parishes);
    }

    public Parish getParishByUser(String userId){
        return parishRepository.findByUserId(userId);
    }

    public Parish getParish(String id){
        return parishRepository.findOne(id);
    }

    public Page<Parish> search(String filter){
        Condition<GeneralQueryBuilder> condition = pipeline.apply(filter, Parish.class);
        Criteria criteria = condition.query(new MongoVisitor());
        Query query = new Query();
        query.addCriteria(criteria);
        Page<Parish> parishes = this.parishRepository.search(query, null);
        return parishes;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Parish updateParish(Parish parish){
        return parishRepository.save(parish);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void removeParish(String id){
        parishRepository.delete(id);
    }


}
