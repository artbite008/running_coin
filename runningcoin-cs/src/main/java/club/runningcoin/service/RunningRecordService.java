package club.runningcoin.service;

import club.runningcoin.service.dto.RunningRecordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RunningRecord.
 */
public interface RunningRecordService {

    /**
     * Save a runningRecord.
     *
     * @param runningRecordDTO the entity to save
     * @return the persisted entity
     */
    RunningRecordDTO save(RunningRecordDTO runningRecordDTO);

    /**
     * Get all the runningRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RunningRecordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" runningRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RunningRecordDTO> findOne(Long id);

    /**
     * Delete the "id" runningRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
