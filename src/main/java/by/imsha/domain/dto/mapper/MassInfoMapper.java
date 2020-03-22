package by.imsha.domain.dto.mapper;

import by.imsha.domain.Mass;
import by.imsha.domain.Parish;
import by.imsha.domain.dto.MassInfo;
import by.imsha.domain.dto.ParishInfo;
import by.imsha.service.ParishService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alena Misan
 */
@Mapper(uses = ParishInfoMapper.class)
public interface MassInfoMapper {
    MassInfoMapper MAPPER = Mappers.getMapper(MassInfoMapper.class);

    @Mappings({
            @Mapping(source = "notes", target = "info"),
            @Mapping(target = "parish", expression = "java(by.imsha.service.ParishService.extractMassParishInfo(mass.getParishId()))")
    })
    MassInfo toMassInfo(Mass mass);

    void updateMassFromDTO(MassInfo massInfo, @MappingTarget Mass mass);

}
