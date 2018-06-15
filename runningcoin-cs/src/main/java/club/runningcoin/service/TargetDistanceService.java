package club.runningcoin.service;

import club.runningcoin.service.dto.TargetDistanceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TargetDistance.
 */
public interface TargetDistanceService {

    /**
     * Save a targetDistance.
     *
     * @param targetDistanceDTO the entity to save
     * @return the persisted entity
     */
    TargetDistanceDTO save(TargetDistanceDTO targetDistanceDTO);

    /**
     * Get all the targetDistances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TargetDistanceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" targetDistance.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TargetDistanceDTO> findOne(Long id);

    /**
     * Delete the "id" targetDistance.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
