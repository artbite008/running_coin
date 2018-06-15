package club.runningcoin.service.mapper;

import club.runningcoin.domain.*;
import club.runningcoin.service.dto.GroupTableDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GroupTable and its DTO GroupTableDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupTableMapper extends EntityMapper<GroupTableDTO, GroupTable> {



    default GroupTable fromId(Long id) {
        if (id == null) {
            return null;
        }
        GroupTable groupTable = new GroupTable();
        groupTable.setId(id);
        return groupTable;
    }
}
