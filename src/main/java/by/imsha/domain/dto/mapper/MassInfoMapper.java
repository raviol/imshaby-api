package by.imsha.domain.dto.mapper;

import by.imsha.domain.Mass;
import by.imsha.domain.dto.MassInfo;
import by.imsha.domain.dto.UpdateMassInfo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * @author Alena Misan
 */
@Mapper(uses = ParishInfoMapper.class,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MassInfoMapper {
    MassInfoMapper MAPPER = Mappers.getMapper(MassInfoMapper.class);

    @Mappings({
            @Mapping(source = "notes", target = "info"),
            @Mapping(target = "parish", expression = "java(by.imsha.service.ParishService.extractMassParishInfo(mass.getParishId()))")
    })
    MassInfo toMassInfo(Mass mass);

    void updateMassFromDTO(UpdateMassInfo massInfo, @MappingTarget Mass mass);
}
