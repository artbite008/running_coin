package club.runningcoin.service.mapper;

import club.runningcoin.domain.*;
import club.runningcoin.service.dto.TargetDistanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TargetDistance and its DTO TargetDistanceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TargetDistanceMapper extends EntityMapper<TargetDistanceDTO, TargetDistance> {



    default TargetDistance fromId(Long id) {
        if (id == null) {
            return null;
        }
        TargetDistance targetDistance = new TargetDistance();
        targetDistance.setId(id);
        return targetDistance;
    }
}
