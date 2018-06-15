package club.runningcoin.service.mapper;

import club.runningcoin.domain.*;
import club.runningcoin.service.dto.VoteRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VoteRecord and its DTO VoteRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VoteRecordMapper extends EntityMapper<VoteRecordDTO, VoteRecord> {



    default VoteRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        VoteRecord voteRecord = new VoteRecord();
        voteRecord.setId(id);
        return voteRecord;
    }
}
