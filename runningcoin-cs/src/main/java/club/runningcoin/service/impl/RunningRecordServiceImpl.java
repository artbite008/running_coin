package club.runningcoin.service.impl;

import club.runningcoin.service.RunningRecordService;
import club.runningcoin.domain.RunningRecord;
import club.runningcoin.repository.RunningRecordRepository;
import club.runningcoin.service.dto.RunningRecordDTO;
import club.runningcoin.service.mapper.RunningRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing RunningRecord.
 */
@Service
@Transactional
public class RunningRecordServiceImpl implements RunningRecordService {

    private final Logger log = LoggerFactory.getLogger(RunningRecordServiceImpl.class);

    private final RunningRecordRepository runningRecordRepository;

    private final RunningRecordMapper runningRecordMapper;

    public RunningRecordServiceImpl(RunningRecordRepository runningRecordRepository, RunningRecordMapper runningRecordMapper) {
        this.runningRecordRepository = runningRecordRepository;
        this.runningRecordMapper = runningRecordMapper;
    }

    /**
     * Save a runningRecord.
     *
     * @param runningRecordDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RunningRecordDTO save(RunningRecordDTO runningRecordDTO) {
        log.debug("Request to save RunningRecord : {}", runningRecordDTO);
        RunningRecord runningRecord = runningRecordMapper.toEntity(runningRecordDTO);
        runningRecord = runningRecordRepository.save(runningRecord);
        return runningRecordMapper.toDto(runningRecord);
    }

    /**
     * Get all the runningRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RunningRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RunningRecords");
        return runningRecordRepository.findAll(pageable)
            .map(runningRecordMapper::toDto);
    }


    /**
     * Get one runningRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RunningRecordDTO> findOne(Long id) {
        log.debug("Request to get RunningRecord : {}", id);
        return runningRecordRepository.findById(id)
            .map(runningRecordMapper::toDto);
    }

    /**
     * Delete the runningRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RunningRecord : {}", id);
        runningRecordRepository.deleteById(id);
    }
}
