package club.runningcoin.service;

import club.runningcoin.service.dto.GroupTableDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GroupTable.
 */
public interface GroupTableService {

    /**
     * Save a groupTable.
     *
     * @param groupTableDTO the entity to save
     * @return the persisted entity
     */
    GroupTableDTO save(GroupTableDTO groupTableDTO);

    /**
     * Get all the groupTables.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GroupTableDTO> findAll(Pageable pageable);


    /**
     * Get the "id" groupTable.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GroupTableDTO> findOne(Long id);

    /**
     * Delete the "id" groupTable.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
