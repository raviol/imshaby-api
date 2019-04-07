package by.imsha.domain.dto.mapper;

import by.imsha.domain.Parish;
import by.imsha.domain.dto.MassParishInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author Alena Misan
 */
@Mapper( uses = {LocationInfoMapper.class})
public interface MassParishInfoMapper {
    MassParishInfoMapper MAPPER = Mappers.getMapper(MassParishInfoMapper.class);

    @Mappings({
     @Mapping(source = "id", target = "parishId")
    })
    MassParishInfo toMassParishInfo(Parish parish);
}
