package club.runningcoin.service.impl;

import club.runningcoin.service.TargetDistanceService;
import club.runningcoin.domain.TargetDistance;
import club.runningcoin.repository.TargetDistanceRepository;
import club.runningcoin.service.dto.TargetDistanceDTO;
import club.runningcoin.service.mapper.TargetDistanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing TargetDistance.
 */
@Service
@Transactional
public class TargetDistanceServiceImpl implements TargetDistanceService {

    private final Logger log = LoggerFactory.getLogger(TargetDistanceServiceImpl.class);

    private final TargetDistanceRepository targetDistanceRepository;

    private final TargetDistanceMapper targetDistanceMapper;

    public TargetDistanceServiceImpl(TargetDistanceRepository targetDistanceRepository, TargetDistanceMapper targetDistanceMapper) {
        this.targetDistanceRepository = targetDistanceRepository;
        this.targetDistanceMapper = targetDistanceMapper;
    }

    /**
     * Save a targetDistance.
     *
     * @param targetDistanceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TargetDistanceDTO save(TargetDistanceDTO targetDistanceDTO) {
        log.debug("Request to save TargetDistance : {}", targetDistanceDTO);
        TargetDistance targetDistance = targetDistanceMapper.toEntity(targetDistanceDTO);
        targetDistance = targetDistanceRepository.save(targetDistance);
        return targetDistanceMapper.toDto(targetDistance);
    }

    /**
     * Get all the targetDistances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TargetDistanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TargetDistances");
        return targetDistanceRepository.findAll(pageable)
            .map(targetDistanceMapper::toDto);
    }


    /**
     * Get one targetDistance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TargetDistanceDTO> findOne(Long id) {
        log.debug("Request to get TargetDistance : {}", id);
        return targetDistanceRepository.findById(id)
            .map(targetDistanceMapper::toDto);
    }

    /**
     * Delete the targetDistance by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TargetDistance : {}", id);
        targetDistanceRepository.deleteById(id);
    }
}
