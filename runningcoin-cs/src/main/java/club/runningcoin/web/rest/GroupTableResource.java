package club.runningcoin.web.rest;

import com.codahale.metrics.annotation.Timed;
import club.runningcoin.service.GroupTableService;
import club.runningcoin.web.rest.errors.BadRequestAlertException;
import club.runningcoin.web.rest.util.HeaderUtil;
import club.runningcoin.web.rest.util.PaginationUtil;
import club.runningcoin.service.dto.GroupTableDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing GroupTable.
 */
@RestController
@RequestMapping("/api")
public class GroupTableResource {

    private final Logger log = LoggerFactory.getLogger(GroupTableResource.class);

    private static final String ENTITY_NAME = "groupTable";

    private final GroupTableService groupTableService;

    public GroupTableResource(GroupTableService groupTableService) {
        this.groupTableService = groupTableService;
    }

    /**
     * POST  /group-tables : Create a new groupTable.
     *
     * @param groupTableDTO the groupTableDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new groupTableDTO, or with status 400 (Bad Request) if the groupTable has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/group-tables")
    @Timed
    public ResponseEntity<GroupTableDTO> createGroupTable(@RequestBody GroupTableDTO groupTableDTO) throws URISyntaxException {
        log.debug("REST request to save GroupTable : {}", groupTableDTO);
        if (groupTableDTO.getId() != null) {
            throw new BadRequestAlertException("A new groupTable cannot already have an ID", ENTITY_NAME, "idexists");
        }        
        GroupTableDTO result = groupTableService.save(groupTableDTO);
        return ResponseEntity.created(new URI("/api/group-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /group-tables : Updates an existing groupTable.
     *
     * @param groupTableDTO the groupTableDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated groupTableDTO,
     * or with status 400 (Bad Request) if the groupTableDTO is not valid,
     * or with status 500 (Internal Server Error) if the groupTableDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/group-tables")
    @Timed
    public ResponseEntity<GroupTableDTO> updateGroupTable(@RequestBody GroupTableDTO groupTableDTO) throws URISyntaxException {
        log.debug("REST request to update GroupTable : {}", groupTableDTO);
        if (groupTableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }        
        GroupTableDTO result = groupTableService.save(groupTableDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groupTableDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /group-tables : get all the groupTables.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of groupTables in body
     */
    @GetMapping("/group-tables")
    @Timed
    public ResponseEntity<List<GroupTableDTO>> getAllGroupTables(Pageable pageable) {
        log.debug("REST request to get a page of GroupTables");
        Page<GroupTableDTO> page = groupTableService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/group-tables");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /group-tables/:id : get the "id" groupTable.
     *
     * @param id the id of the groupTableDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the groupTableDTO, or with status 404 (Not Found)
     */
    @GetMapping("/group-tables/{id}")
    @Timed
    public ResponseEntity<GroupTableDTO> getGroupTable(@PathVariable Long id) {
        log.debug("REST request to get GroupTable : {}", id);
        Optional<GroupTableDTO> groupTableDTO = groupTableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupTableDTO);
    }

    /**
     * DELETE  /group-tables/:id : delete the "id" groupTable.
     *
     * @param id the id of the groupTableDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/group-tables/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroupTable(@PathVariable Long id) {
        log.debug("REST request to delete GroupTable : {}", id);
        groupTableService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
