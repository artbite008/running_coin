package club.runningcoin.web.rest;

import com.codahale.metrics.annotation.Timed;
import club.runningcoin.service.VoteRecordService;
import club.runningcoin.web.rest.errors.BadRequestAlertException;
import club.runningcoin.web.rest.util.HeaderUtil;
import club.runningcoin.web.rest.util.PaginationUtil;
import club.runningcoin.service.dto.VoteRecordDTO;
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
 * REST controller for managing VoteRecord.
 */
@RestController
@RequestMapping("/api")
public class VoteRecordResource {

    private final Logger log = LoggerFactory.getLogger(VoteRecordResource.class);

    private static final String ENTITY_NAME = "voteRecord";

    private final VoteRecordService voteRecordService;

    public VoteRecordResource(VoteRecordService voteRecordService) {
        this.voteRecordService = voteRecordService;
    }

    /**
     * POST  /vote-records : Create a new voteRecord.
     *
     * @param voteRecordDTO the voteRecordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new voteRecordDTO, or with status 400 (Bad Request) if the voteRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vote-records")
    @Timed
    public ResponseEntity<VoteRecordDTO> createVoteRecord(@RequestBody VoteRecordDTO voteRecordDTO) throws URISyntaxException {
        log.debug("REST request to save VoteRecord : {}", voteRecordDTO);
        if (voteRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new voteRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }        
        VoteRecordDTO result = voteRecordService.save(voteRecordDTO);
        return ResponseEntity.created(new URI("/api/vote-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vote-records : Updates an existing voteRecord.
     *
     * @param voteRecordDTO the voteRecordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated voteRecordDTO,
     * or with status 400 (Bad Request) if the voteRecordDTO is not valid,
     * or with status 500 (Internal Server Error) if the voteRecordDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vote-records")
    @Timed
    public ResponseEntity<VoteRecordDTO> updateVoteRecord(@RequestBody VoteRecordDTO voteRecordDTO) throws URISyntaxException {
        log.debug("REST request to update VoteRecord : {}", voteRecordDTO);
        if (voteRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }        
        VoteRecordDTO result = voteRecordService.save(voteRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, voteRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vote-records : get all the voteRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of voteRecords in body
     */
    @GetMapping("/vote-records")
    @Timed
    public ResponseEntity<List<VoteRecordDTO>> getAllVoteRecords(Pageable pageable) {
        log.debug("REST request to get a page of VoteRecords");
        Page<VoteRecordDTO> page = voteRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vote-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /vote-records/:id : get the "id" voteRecord.
     *
     * @param id the id of the voteRecordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the voteRecordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vote-records/{id}")
    @Timed
    public ResponseEntity<VoteRecordDTO> getVoteRecord(@PathVariable Long id) {
        log.debug("REST request to get VoteRecord : {}", id);
        Optional<VoteRecordDTO> voteRecordDTO = voteRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(voteRecordDTO);
    }

    /**
     * DELETE  /vote-records/:id : delete the "id" voteRecord.
     *
     * @param id the id of the voteRecordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vote-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteVoteRecord(@PathVariable Long id) {
        log.debug("REST request to delete VoteRecord : {}", id);
        voteRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
