package by.imsha.repository.impl;

import by.imsha.repository.QuerableMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alena Misan
 */
public class QuerableMongoRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements QuerableMongoRepository<T, ID> {


    public QuerableMongoRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<T> search(Query query, Pageable pageable) {
        System.out.println("search!!!!!!!!" + mongoTemplate);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
