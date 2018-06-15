package club.runningcoin.service.impl;

import club.runningcoin.service.VoteRecordService;
import club.runningcoin.domain.VoteRecord;
import club.runningcoin.repository.VoteRecordRepository;
import club.runningcoin.service.dto.VoteRecordDTO;
import club.runningcoin.service.mapper.VoteRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing VoteRecord.
 */
@Service
@Transactional
public class VoteRecordServiceImpl implements VoteRecordService {

    private final Logger log = LoggerFactory.getLogger(VoteRecordServiceImpl.class);

    private final VoteRecordRepository voteRecordRepository;

    private final VoteRecordMapper voteRecordMapper;

    public VoteRecordServiceImpl(VoteRecordRepository voteRecordRepository, VoteRecordMapper voteRecordMapper) {
        this.voteRecordRepository = voteRecordRepository;
        this.voteRecordMapper = voteRecordMapper;
    }

    /**
     * Save a voteRecord.
     *
     * @param voteRecordDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VoteRecordDTO save(VoteRecordDTO voteRecordDTO) {
        log.debug("Request to save VoteRecord : {}", voteRecordDTO);
        VoteRecord voteRecord = voteRecordMapper.toEntity(voteRecordDTO);
        voteRecord = voteRecordRepository.save(voteRecord);
        return voteRecordMapper.toDto(voteRecord);
    }

    /**
     * Get all the voteRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VoteRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VoteRecords");
        return voteRecordRepository.findAll(pageable)
            .map(voteRecordMapper::toDto);
    }


    /**
     * Get one voteRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VoteRecordDTO> findOne(Long id) {
        log.debug("Request to get VoteRecord : {}", id);
        return voteRecordRepository.findById(id)
            .map(voteRecordMapper::toDto);
    }

    /**
     * Delete the voteRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VoteRecord : {}", id);
        voteRecordRepository.deleteById(id);
    }
}
