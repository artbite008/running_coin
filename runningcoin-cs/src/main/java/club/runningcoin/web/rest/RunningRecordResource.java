package club.runningcoin.web.rest;

import com.codahale.metrics.annotation.Timed;
import club.runningcoin.service.RunningRecordService;
import club.runningcoin.web.rest.errors.BadRequestAlertException;
import club.runningcoin.web.rest.util.HeaderUtil;
import club.runningcoin.web.rest.util.PaginationUtil;
import club.runningcoin.service.dto.RunningRecordDTO;
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
 * REST controller for managing RunningRecord.
 */
@RestController
@RequestMapping("/api")
public class RunningRecordResource {

    private final Logger log = LoggerFactory.getLogger(RunningRecordResource.class);

    private static final String ENTITY_NAME = "runningRecord";

    private final RunningRecordService runningRecordService;

    public RunningRecordResource(RunningRecordService runningRecordService) {
        this.runningRecordService = runningRecordService;
    }

    /**
     * POST  /running-records : Create a new runningRecord.
     *
     * @param runningRecordDTO the runningRecordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new runningRecordDTO, or with status 400 (Bad Request) if the runningRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/running-records")
    @Timed
    public ResponseEntity<RunningRecordDTO> createRunningRecord(@RequestBody RunningRecordDTO runningRecordDTO) throws URISyntaxException {
        log.debug("REST request to save RunningRecord : {}", runningRecordDTO);
        if (runningRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new runningRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }        
        RunningRecordDTO result = runningRecordService.save(runningRecordDTO);
        return ResponseEntity.created(new URI("/api/running-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /running-records : Updates an existing runningRecord.
     *
     * @param runningRecordDTO the runningRecordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated runningRecordDTO,
     * or with status 400 (Bad Request) if the runningRecordDTO is not valid,
     * or with status 500 (Internal Server Error) if the runningRecordDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/running-records")
    @Timed
    public ResponseEntity<RunningRecordDTO> updateRunningRecord(@RequestBody RunningRecordDTO runningRecordDTO) throws URISyntaxException {
        log.debug("REST request to update RunningRecord : {}", runningRecordDTO);
        if (runningRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }        
        RunningRecordDTO result = runningRecordService.save(runningRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, runningRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /running-records : get all the runningRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of runningRecords in body
     */
    @GetMapping("/running-records")
    @Timed
    public ResponseEntity<List<RunningRecordDTO>> getAllRunningRecords(Pageable pageable) {
        log.debug("REST request to get a page of RunningRecords");
        Page<RunningRecordDTO> page = runningRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/running-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /running-records/:id : get the "id" runningRecord.
     *
     * @param id the id of the runningRecordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the runningRecordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/running-records/{id}")
    @Timed
    public ResponseEntity<RunningRecordDTO> getRunningRecord(@PathVariable Long id) {
        log.debug("REST request to get RunningRecord : {}", id);
        Optional<RunningRecordDTO> runningRecordDTO = runningRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(runningRecordDTO);
    }

    /**
     * DELETE  /running-records/:id : delete the "id" runningRecord.
     *
     * @param id the id of the runningRecordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/running-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteRunningRecord(@PathVariable Long id) {
        log.debug("REST request to delete RunningRecord : {}", id);
        runningRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
