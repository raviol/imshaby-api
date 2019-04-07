package by.imsha.repository;

import by.imsha.domain.Parish;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 *
 */
public interface ParishRepository extends QuerableMongoRepository<Parish, String>{


    @CacheEvict(cacheNames = "parishCache", key = "#p0.id")
    Parish save(Parish parish);


    @CacheEvict(cacheNames = "parishCache")
    void delete(String id);

    @Cacheable(cacheNames = "parishCache")
    Parish findById(String id);

    Parish findByUserId(String userId);
}
