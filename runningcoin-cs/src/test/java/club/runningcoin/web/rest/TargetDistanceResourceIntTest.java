package club.runningcoin.web.rest;

import club.runningcoin.RunningcoinCsApp;

import club.runningcoin.domain.TargetDistance;
import club.runningcoin.repository.TargetDistanceRepository;
import club.runningcoin.service.TargetDistanceService;
import club.runningcoin.service.dto.TargetDistanceDTO;
import club.runningcoin.service.mapper.TargetDistanceMapper;
import club.runningcoin.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;

import static club.runningcoin.web.rest.TestUtil.sameInstant;
import static club.runningcoin.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TargetDistanceResource REST controller.
 *
 * @see TargetDistanceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunningcoinCsApp.class)
public class TargetDistanceResourceIntTest {

    private static final Integer DEFAULT_USER_GROUP_ID = 1;
    private static final Integer UPDATED_USER_GROUP_ID = 2;

    private static final ZonedDateTime DEFAULT_CREATION_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATION_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Float DEFAULT_TARGET_DISTANCE = 1F;
    private static final Float UPDATED_TARGET_DISTANCE = 2F;

    @Autowired
    private TargetDistanceRepository targetDistanceRepository;


    @Autowired
    private TargetDistanceMapper targetDistanceMapper;
    

    @Autowired
    private TargetDistanceService targetDistanceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTargetDistanceMockMvc;

    private TargetDistance targetDistance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TargetDistanceResource targetDistanceResource = new TargetDistanceResource(targetDistanceService);
        this.restTargetDistanceMockMvc = MockMvcBuilders.standaloneSetup(targetDistanceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TargetDistance createEntity(EntityManager em) {
        TargetDistance targetDistance = new TargetDistance()
            .userGroupId(DEFAULT_USER_GROUP_ID)
            .creationTime(DEFAULT_CREATION_TIME)
            .targetDistance(DEFAULT_TARGET_DISTANCE);
        return targetDistance;
    }

    @Before
    public void initTest() {
        targetDistance = createEntity(em);
    }

    @Test
    @Transactional
    public void createTargetDistance() throws Exception {
        int databaseSizeBeforeCreate = targetDistanceRepository.findAll().size();

        // Create the TargetDistance
        TargetDistanceDTO targetDistanceDTO = targetDistanceMapper.toDto(targetDistance);
        restTargetDistanceMockMvc.perform(post("/api/target-distances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(targetDistanceDTO)))
            .andExpect(status().isCreated());

        // Validate the TargetDistance in the database
        List<TargetDistance> targetDistanceList = targetDistanceRepository.findAll();
        assertThat(targetDistanceList).hasSize(databaseSizeBeforeCreate + 1);
        TargetDistance testTargetDistance = targetDistanceList.get(targetDistanceList.size() - 1);
        assertThat(testTargetDistance.getUserGroupId()).isEqualTo(DEFAULT_USER_GROUP_ID);
        assertThat(testTargetDistance.getCreationTime()).isEqualTo(DEFAULT_CREATION_TIME);
        assertThat(testTargetDistance.getTargetDistance()).isEqualTo(DEFAULT_TARGET_DISTANCE);
    }

    @Test
    @Transactional
    public void createTargetDistanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = targetDistanceRepository.findAll().size();

        // Create the TargetDistance with an existing ID
        targetDistance.setId(1L);
        TargetDistanceDTO targetDistanceDTO = targetDistanceMapper.toDto(targetDistance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTargetDistanceMockMvc.perform(post("/api/target-distances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(targetDistanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TargetDistance in the database
        List<TargetDistance> targetDistanceList = targetDistanceRepository.findAll();
        assertThat(targetDistanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTargetDistances() throws Exception {
        // Initialize the database
        targetDistanceRepository.saveAndFlush(targetDistance);

        // Get all the targetDistanceList
        restTargetDistanceMockMvc.perform(get("/api/target-distances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(targetDistance.getId().intValue())))
            .andExpect(jsonPath("$.[*].userGroupId").value(hasItem(DEFAULT_USER_GROUP_ID)))
            .andExpect(jsonPath("$.[*].creationTime").value(hasItem(sameInstant(DEFAULT_CREATION_TIME))))
            .andExpect(jsonPath("$.[*].targetDistance").value(hasItem(DEFAULT_TARGET_DISTANCE.doubleValue())));
    }
    

    @Test
    @Transactional
    public void getTargetDistance() throws Exception {
        // Initialize the database
        targetDistanceRepository.saveAndFlush(targetDistance);

        // Get the targetDistance
        restTargetDistanceMockMvc.perform(get("/api/target-distances/{id}", targetDistance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(targetDistance.getId().intValue()))
            .andExpect(jsonPath("$.userGroupId").value(DEFAULT_USER_GROUP_ID))
            .andExpect(jsonPath("$.creationTime").value(sameInstant(DEFAULT_CREATION_TIME)))
            .andExpect(jsonPath("$.targetDistance").value(DEFAULT_TARGET_DISTANCE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTargetDistance() throws Exception {
        // Get the targetDistance
        restTargetDistanceMockMvc.perform(get("/api/target-distances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTargetDistance() throws Exception {
        // Initialize the database
        targetDistanceRepository.saveAndFlush(targetDistance);

        int databaseSizeBeforeUpdate = targetDistanceRepository.findAll().size();

        // Update the targetDistance
        TargetDistance updatedTargetDistance = targetDistanceRepository.findById(targetDistance.getId()).get();
        // Disconnect from session so that the updates on updatedTargetDistance are not directly saved in db
        em.detach(updatedTargetDistance);
        updatedTargetDistance
            .userGroupId(UPDATED_USER_GROUP_ID)
            .creationTime(UPDATED_CREATION_TIME)
            .targetDistance(UPDATED_TARGET_DISTANCE);
        TargetDistanceDTO targetDistanceDTO = targetDistanceMapper.toDto(updatedTargetDistance);

        restTargetDistanceMockMvc.perform(put("/api/target-distances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(targetDistanceDTO)))
            .andExpect(status().isOk());

        // Validate the TargetDistance in the database
        List<TargetDistance> targetDistanceList = targetDistanceRepository.findAll();
        assertThat(targetDistanceList).hasSize(databaseSizeBeforeUpdate);
        TargetDistance testTargetDistance = targetDistanceList.get(targetDistanceList.size() - 1);
        assertThat(testTargetDistance.getUserGroupId()).isEqualTo(UPDATED_USER_GROUP_ID);
        assertThat(testTargetDistance.getCreationTime()).isEqualTo(UPDATED_CREATION_TIME);
        assertThat(testTargetDistance.getTargetDistance()).isEqualTo(UPDATED_TARGET_DISTANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingTargetDistance() throws Exception {
        int databaseSizeBeforeUpdate = targetDistanceRepository.findAll().size();

        // Create the TargetDistance
        TargetDistanceDTO targetDistanceDTO = targetDistanceMapper.toDto(targetDistance);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTargetDistanceMockMvc.perform(put("/api/target-distances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(targetDistanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TargetDistance in the database
        List<TargetDistance> targetDistanceList = targetDistanceRepository.findAll();
        assertThat(targetDistanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTargetDistance() throws Exception {
        // Initialize the database
        targetDistanceRepository.saveAndFlush(targetDistance);

        int databaseSizeBeforeDelete = targetDistanceRepository.findAll().size();

        // Get the targetDistance
        restTargetDistanceMockMvc.perform(delete("/api/target-distances/{id}", targetDistance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TargetDistance> targetDistanceList = targetDistanceRepository.findAll();
        assertThat(targetDistanceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TargetDistance.class);
        TargetDistance targetDistance1 = new TargetDistance();
        targetDistance1.setId(1L);
        TargetDistance targetDistance2 = new TargetDistance();
        targetDistance2.setId(targetDistance1.getId());
        assertThat(targetDistance1).isEqualTo(targetDistance2);
        targetDistance2.setId(2L);
        assertThat(targetDistance1).isNotEqualTo(targetDistance2);
        targetDistance1.setId(null);
        assertThat(targetDistance1).isNotEqualTo(targetDistance2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TargetDistanceDTO.class);
        TargetDistanceDTO targetDistanceDTO1 = new TargetDistanceDTO();
        targetDistanceDTO1.setId(1L);
        TargetDistanceDTO targetDistanceDTO2 = new TargetDistanceDTO();
        assertThat(targetDistanceDTO1).isNotEqualTo(targetDistanceDTO2);
        targetDistanceDTO2.setId(targetDistanceDTO1.getId());
        assertThat(targetDistanceDTO1).isEqualTo(targetDistanceDTO2);
        targetDistanceDTO2.setId(2L);
        assertThat(targetDistanceDTO1).isNotEqualTo(targetDistanceDTO2);
        targetDistanceDTO1.setId(null);
        assertThat(targetDistanceDTO1).isNotEqualTo(targetDistanceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(targetDistanceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(targetDistanceMapper.fromId(null)).isNull();
    }
}
