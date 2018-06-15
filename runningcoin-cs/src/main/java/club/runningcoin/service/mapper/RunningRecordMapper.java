package club.runningcoin.service.mapper;

import club.runningcoin.domain.*;
import club.runningcoin.service.dto.RunningRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RunningRecord and its DTO RunningRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RunningRecordMapper extends EntityMapper<RunningRecordDTO, RunningRecord> {



    default RunningRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        RunningRecord runningRecord = new RunningRecord();
        runningRecord.setId(id);
        return runningRecord;
    }
}
