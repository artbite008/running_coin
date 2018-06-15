package club.runningcoin.web.rest;

import club.runningcoin.RunningcoinCsApp;

import club.runningcoin.domain.RunningRecord;
import club.runningcoin.repository.RunningRecordRepository;
import club.runningcoin.service.RunningRecordService;
import club.runningcoin.service.dto.RunningRecordDTO;
import club.runningcoin.service.mapper.RunningRecordMapper;
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
 * Test class for the RunningRecordResource REST controller.
 *
 * @see RunningRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunningcoinCsApp.class)
public class RunningRecordResourceIntTest {

    private static final Integer DEFAULT_USER_GROUP_ID = 1;
    private static final Integer UPDATED_USER_GROUP_ID = 2;

    private static final Float DEFAULT_DISTANCE = 1F;
    private static final Float UPDATED_DISTANCE = 2F;

    private static final ZonedDateTime DEFAULT_CREATION_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATION_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_LAST_VOTED_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_VOTED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_STATUS_FIELD = 1;
    private static final Integer UPDATED_STATUS_FIELD = 2;

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final ZonedDateTime DEFAULT_SETTLED_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETTLED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_EARNED_COINS = 1D;
    private static final Double UPDATED_EARNED_COINS = 2D;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_EVIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_EVIDENCE = "BBBBBBBBBB";

    @Autowired
    private RunningRecordRepository runningRecordRepository;


    @Autowired
    private RunningRecordMapper runningRecordMapper;
    

    @Autowired
    private RunningRecordService runningRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRunningRecordMockMvc;

    private RunningRecord runningRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RunningRecordResource runningRecordResource = new RunningRecordResource(runningRecordService);
        this.restRunningRecordMockMvc = MockMvcBuilders.standaloneSetup(runningRecordResource)
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
    public static RunningRecord createEntity(EntityManager em) {
        RunningRecord runningRecord = new RunningRecord()
            .userGroupId(DEFAULT_USER_GROUP_ID)
            .distance(DEFAULT_DISTANCE)
            .creationTime(DEFAULT_CREATION_TIME)
            .lastVotedTime(DEFAULT_LAST_VOTED_TIME)
            .statusField(DEFAULT_STATUS_FIELD)
            .score(DEFAULT_SCORE)
            .settledTime(DEFAULT_SETTLED_TIME)
            .earnedCoins(DEFAULT_EARNED_COINS)
            .comments(DEFAULT_COMMENTS)
            .evidence(DEFAULT_EVIDENCE);
        return runningRecord;
    }

    @Before
    public void initTest() {
        runningRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createRunningRecord() throws Exception {
        int databaseSizeBeforeCreate = runningRecordRepository.findAll().size();

        // Create the RunningRecord
        RunningRecordDTO runningRecordDTO = runningRecordMapper.toDto(runningRecord);
        restRunningRecordMockMvc.perform(post("/api/running-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(runningRecordDTO)))
            .andExpect(status().isCreated());

        // Validate the RunningRecord in the database
        List<RunningRecord> runningRecordList = runningRecordRepository.findAll();
        assertThat(runningRecordList).hasSize(databaseSizeBeforeCreate + 1);
        RunningRecord testRunningRecord = runningRecordList.get(runningRecordList.size() - 1);
        assertThat(testRunningRecord.getUserGroupId()).isEqualTo(DEFAULT_USER_GROUP_ID);
        assertThat(testRunningRecord.getDistance()).isEqualTo(DEFAULT_DISTANCE);
        assertThat(testRunningRecord.getCreationTime()).isEqualTo(DEFAULT_CREATION_TIME);
        assertThat(testRunningRecord.getLastVotedTime()).isEqualTo(DEFAULT_LAST_VOTED_TIME);
        assertThat(testRunningRecord.getStatusField()).isEqualTo(DEFAULT_STATUS_FIELD);
        assertThat(testRunningRecord.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testRunningRecord.getSettledTime()).isEqualTo(DEFAULT_SETTLED_TIME);
        assertThat(testRunningRecord.getEarnedCoins()).isEqualTo(DEFAULT_EARNED_COINS);
        assertThat(testRunningRecord.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testRunningRecord.getEvidence()).isEqualTo(DEFAULT_EVIDENCE);
    }

    @Test
    @Transactional
    public void createRunningRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = runningRecordRepository.findAll().size();

        // Create the RunningRecord with an existing ID
        runningRecord.setId(1L);
        RunningRecordDTO runningRecordDTO = runningRecordMapper.toDto(runningRecord);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRunningRecordMockMvc.perform(post("/api/running-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(runningRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RunningRecord in the database
        List<RunningRecord> runningRecordList = runningRecordRepository.findAll();
        assertThat(runningRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRunningRecords() throws Exception {
        // Initialize the database
        runningRecordRepository.saveAndFlush(runningRecord);

        // Get all the runningRecordList
        restRunningRecordMockMvc.perform(get("/api/running-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(runningRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].userGroupId").value(hasItem(DEFAULT_USER_GROUP_ID)))
            .andExpect(jsonPath("$.[*].distance").value(hasItem(DEFAULT_DISTANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].creationTime").value(hasItem(sameInstant(DEFAULT_CREATION_TIME))))
            .andExpect(jsonPath("$.[*].lastVotedTime").value(hasItem(sameInstant(DEFAULT_LAST_VOTED_TIME))))
            .andExpect(jsonPath("$.[*].statusField").value(hasItem(DEFAULT_STATUS_FIELD)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].settledTime").value(hasItem(sameInstant(DEFAULT_SETTLED_TIME))))
            .andExpect(jsonPath("$.[*].earnedCoins").value(hasItem(DEFAULT_EARNED_COINS.doubleValue())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].evidence").value(hasItem(DEFAULT_EVIDENCE.toString())));
    }
    

    @Test
    @Transactional
    public void getRunningRecord() throws Exception {
        // Initialize the database
        runningRecordRepository.saveAndFlush(runningRecord);

        // Get the runningRecord
        restRunningRecordMockMvc.perform(get("/api/running-records/{id}", runningRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(runningRecord.getId().intValue()))
            .andExpect(jsonPath("$.userGroupId").value(DEFAULT_USER_GROUP_ID))
            .andExpect(jsonPath("$.distance").value(DEFAULT_DISTANCE.doubleValue()))
            .andExpect(jsonPath("$.creationTime").value(sameInstant(DEFAULT_CREATION_TIME)))
            .andExpect(jsonPath("$.lastVotedTime").value(sameInstant(DEFAULT_LAST_VOTED_TIME)))
            .andExpect(jsonPath("$.statusField").value(DEFAULT_STATUS_FIELD))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.settledTime").value(sameInstant(DEFAULT_SETTLED_TIME)))
            .andExpect(jsonPath("$.earnedCoins").value(DEFAULT_EARNED_COINS.doubleValue()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.evidence").value(DEFAULT_EVIDENCE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRunningRecord() throws Exception {
        // Get the runningRecord
        restRunningRecordMockMvc.perform(get("/api/running-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRunningRecord() throws Exception {
        // Initialize the database
        runningRecordRepository.saveAndFlush(runningRecord);

        int databaseSizeBeforeUpdate = runningRecordRepository.findAll().size();

        // Update the runningRecord
        RunningRecord updatedRunningRecord = runningRecordRepository.findById(runningRecord.getId()).get();
        // Disconnect from session so that the updates on updatedRunningRecord are not directly saved in db
        em.detach(updatedRunningRecord);
        updatedRunningRecord
            .userGroupId(UPDATED_USER_GROUP_ID)
            .distance(UPDATED_DISTANCE)
            .creationTime(UPDATED_CREATION_TIME)
            .lastVotedTime(UPDATED_LAST_VOTED_TIME)
            .statusField(UPDATED_STATUS_FIELD)
            .score(UPDATED_SCORE)
            .settledTime(UPDATED_SETTLED_TIME)
            .earnedCoins(UPDATED_EARNED_COINS)
            .comments(UPDATED_COMMENTS)
            .evidence(UPDATED_EVIDENCE);
        RunningRecordDTO runningRecordDTO = runningRecordMapper.toDto(updatedRunningRecord);

        restRunningRecordMockMvc.perform(put("/api/running-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(runningRecordDTO)))
            .andExpect(status().isOk());

        // Validate the RunningRecord in the database
        List<RunningRecord> runningRecordList = runningRecordRepository.findAll();
        assertThat(runningRecordList).hasSize(databaseSizeBeforeUpdate);
        RunningRecord testRunningRecord = runningRecordList.get(runningRecordList.size() - 1);
        assertThat(testRunningRecord.getUserGroupId()).isEqualTo(UPDATED_USER_GROUP_ID);
        assertThat(testRunningRecord.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testRunningRecord.getCreationTime()).isEqualTo(UPDATED_CREATION_TIME);
        assertThat(testRunningRecord.getLastVotedTime()).isEqualTo(UPDATED_LAST_VOTED_TIME);
        assertThat(testRunningRecord.getStatusField()).isEqualTo(UPDATED_STATUS_FIELD);
        assertThat(testRunningRecord.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testRunningRecord.getSettledTime()).isEqualTo(UPDATED_SETTLED_TIME);
        assertThat(testRunningRecord.getEarnedCoins()).isEqualTo(UPDATED_EARNED_COINS);
        assertThat(testRunningRecord.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testRunningRecord.getEvidence()).isEqualTo(UPDATED_EVIDENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingRunningRecord() throws Exception {
        int databaseSizeBeforeUpdate = runningRecordRepository.findAll().size();

        // Create the RunningRecord
        RunningRecordDTO runningRecordDTO = runningRecordMapper.toDto(runningRecord);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRunningRecordMockMvc.perform(put("/api/running-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(runningRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RunningRecord in the database
        List<RunningRecord> runningRecordList = runningRecordRepository.findAll();
        assertThat(runningRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRunningRecord() throws Exception {
        // Initialize the database
        runningRecordRepository.saveAndFlush(runningRecord);

        int databaseSizeBeforeDelete = runningRecordRepository.findAll().size();

        // Get the runningRecord
        restRunningRecordMockMvc.perform(delete("/api/running-records/{id}", runningRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RunningRecord> runningRecordList = runningRecordRepository.findAll();
        assertThat(runningRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RunningRecord.class);
        RunningRecord runningRecord1 = new RunningRecord();
        runningRecord1.setId(1L);
        RunningRecord runningRecord2 = new RunningRecord();
        runningRecord2.setId(runningRecord1.getId());
        assertThat(runningRecord1).isEqualTo(runningRecord2);
        runningRecord2.setId(2L);
        assertThat(runningRecord1).isNotEqualTo(runningRecord2);
        runningRecord1.setId(null);
        assertThat(runningRecord1).isNotEqualTo(runningRecord2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RunningRecordDTO.class);
        RunningRecordDTO runningRecordDTO1 = new RunningRecordDTO();
        runningRecordDTO1.setId(1L);
        RunningRecordDTO runningRecordDTO2 = new RunningRecordDTO();
        assertThat(runningRecordDTO1).isNotEqualTo(runningRecordDTO2);
        runningRecordDTO2.setId(runningRecordDTO1.getId());
        assertThat(runningRecordDTO1).isEqualTo(runningRecordDTO2);
        runningRecordDTO2.setId(2L);
        assertThat(runningRecordDTO1).isNotEqualTo(runningRecordDTO2);
        runningRecordDTO1.setId(null);
        assertThat(runningRecordDTO1).isNotEqualTo(runningRecordDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(runningRecordMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(runningRecordMapper.fromId(null)).isNull();
    }
}
