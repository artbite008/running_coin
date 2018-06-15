package club.runningcoin.web.rest;

import com.codahale.metrics.annotation.Timed;
import club.runningcoin.service.TargetDistanceService;
import club.runningcoin.web.rest.errors.BadRequestAlertException;
import club.runningcoin.web.rest.util.HeaderUtil;
import club.runningcoin.web.rest.util.PaginationUtil;
import club.runningcoin.service.dto.TargetDistanceDTO;
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
 * REST controller for managing TargetDistance.
 */
@RestController
@RequestMapping("/api")
public class TargetDistanceResource {

    private final Logger log = LoggerFactory.getLogger(TargetDistanceResource.class);

    private static final String ENTITY_NAME = "targetDistance";

    private final TargetDistanceService targetDistanceService;

    public TargetDistanceResource(TargetDistanceService targetDistanceService) {
        this.targetDistanceService = targetDistanceService;
    }

    /**
     * POST  /target-distances : Create a new targetDistance.
     *
     * @param targetDistanceDTO the targetDistanceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new targetDistanceDTO, or with status 400 (Bad Request) if the targetDistance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/target-distances")
    @Timed
    public ResponseEntity<TargetDistanceDTO> createTargetDistance(@RequestBody TargetDistanceDTO targetDistanceDTO) throws URISyntaxException {
        log.debug("REST request to save TargetDistance : {}", targetDistanceDTO);
        if (targetDistanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new targetDistance cannot already have an ID", ENTITY_NAME, "idexists");
        }        
        TargetDistanceDTO result = targetDistanceService.save(targetDistanceDTO);
        return ResponseEntity.created(new URI("/api/target-distances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /target-distances : Updates an existing targetDistance.
     *
     * @param targetDistanceDTO the targetDistanceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated targetDistanceDTO,
     * or with status 400 (Bad Request) if the targetDistanceDTO is not valid,
     * or with status 500 (Internal Server Error) if the targetDistanceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/target-distances")
    @Timed
    public ResponseEntity<TargetDistanceDTO> updateTargetDistance(@RequestBody TargetDistanceDTO targetDistanceDTO) throws URISyntaxException {
        log.debug("REST request to update TargetDistance : {}", targetDistanceDTO);
        if (targetDistanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }        
        TargetDistanceDTO result = targetDistanceService.save(targetDistanceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, targetDistanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /target-distances : get all the targetDistances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of targetDistances in body
     */
    @GetMapping("/target-distances")
    @Timed
    public ResponseEntity<List<TargetDistanceDTO>> getAllTargetDistances(Pageable pageable) {
        log.debug("REST request to get a page of TargetDistances");
        Page<TargetDistanceDTO> page = targetDistanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/target-distances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /target-distances/:id : get the "id" targetDistance.
     *
     * @param id the id of the targetDistanceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the targetDistanceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/target-distances/{id}")
    @Timed
    public ResponseEntity<TargetDistanceDTO> getTargetDistance(@PathVariable Long id) {
        log.debug("REST request to get TargetDistance : {}", id);
        Optional<TargetDistanceDTO> targetDistanceDTO = targetDistanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(targetDistanceDTO);
    }

    /**
     * DELETE  /target-distances/:id : delete the "id" targetDistance.
     *
     * @param id the id of the targetDistanceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/target-distances/{id}")
    @Timed
    public ResponseEntity<Void> deleteTargetDistance(@PathVariable Long id) {
        log.debug("REST request to delete TargetDistance : {}", id);
        targetDistanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
