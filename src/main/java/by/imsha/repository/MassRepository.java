package by.imsha.repository;

import by.imsha.domain.Mass;

/**
 * @author Alena Misan
 */
public interface MassRepository extends QuerableMongoRepository<Mass, String> {
    public Mass findByParishId(String parishId);
}
