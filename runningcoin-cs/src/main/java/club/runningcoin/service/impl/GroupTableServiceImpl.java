package club.runningcoin.service.impl;

import club.runningcoin.service.GroupTableService;
import club.runningcoin.domain.GroupTable;
import club.runningcoin.repository.GroupTableRepository;
import club.runningcoin.service.dto.GroupTableDTO;
import club.runningcoin.service.mapper.GroupTableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing GroupTable.
 */
@Service
@Transactional
public class GroupTableServiceImpl implements GroupTableService {

    private final Logger log = LoggerFactory.getLogger(GroupTableServiceImpl.class);

    private final GroupTableRepository groupTableRepository;

    private final GroupTableMapper groupTableMapper;

    public GroupTableServiceImpl(GroupTableRepository groupTableRepository, GroupTableMapper groupTableMapper) {
        this.groupTableRepository = groupTableRepository;
        this.groupTableMapper = groupTableMapper;
    }

    /**
     * Save a groupTable.
     *
     * @param groupTableDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GroupTableDTO save(GroupTableDTO groupTableDTO) {
        log.debug("Request to save GroupTable : {}", groupTableDTO);
        GroupTable groupTable = groupTableMapper.toEntity(groupTableDTO);
        groupTable = groupTableRepository.save(groupTable);
        return groupTableMapper.toDto(groupTable);
    }

    /**
     * Get all the groupTables.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GroupTableDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GroupTables");
        return groupTableRepository.findAll(pageable)
            .map(groupTableMapper::toDto);
    }


    /**
     * Get one groupTable by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GroupTableDTO> findOne(Long id) {
        log.debug("Request to get GroupTable : {}", id);
        return groupTableRepository.findById(id)
            .map(groupTableMapper::toDto);
    }

    /**
     * Delete the groupTable by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GroupTable : {}", id);
        groupTableRepository.deleteById(id);
    }
}
