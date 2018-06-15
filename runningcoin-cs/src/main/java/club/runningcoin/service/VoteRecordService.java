package club.runningcoin.service;

import club.runningcoin.service.dto.VoteRecordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing VoteRecord.
 */
public interface VoteRecordService {

    /**
     * Save a voteRecord.
     *
     * @param voteRecordDTO the entity to save
     * @return the persisted entity
     */
    VoteRecordDTO save(VoteRecordDTO voteRecordDTO);

    /**
     * Get all the voteRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VoteRecordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" voteRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VoteRecordDTO> findOne(Long id);

    /**
     * Delete the "id" voteRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
