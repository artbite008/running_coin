package club.runningcoin.web.rest;

import club.runningcoin.RunningcoinCsApp;

import club.runningcoin.domain.VoteRecord;
import club.runningcoin.repository.VoteRecordRepository;
import club.runningcoin.service.VoteRecordService;
import club.runningcoin.service.dto.VoteRecordDTO;
import club.runningcoin.service.mapper.VoteRecordMapper;
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
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ArrayList;

import static club.runningcoin.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VoteRecordResource REST controller.
 *
 * @see VoteRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunningcoinCsApp.class)
public class VoteRecordResourceIntTest {

    private static final Integer DEFAULT_VOTE_USER_GROUP_ID = 1;
    private static final Integer UPDATED_VOTE_USER_GROUP_ID = 2;

    private static final Integer DEFAULT_RUNING_RECORD_ID = 1;
    private static final Integer UPDATED_RUNING_RECORD_ID = 2;

    private static final ZonedDateTime DEFAULT_VOTED_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_VOTED_TIME =  ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);;

    private static final Integer DEFAULT_STATUS_FIELD = 1;
    private static final Integer UPDATED_STATUS_FIELD = 2;

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    @Autowired
    private VoteRecordRepository voteRecordRepository;


    @Autowired
    private VoteRecordMapper voteRecordMapper;


    @Autowired
    private VoteRecordService voteRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVoteRecordMockMvc;

    private VoteRecord voteRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VoteRecordResource voteRecordResource = new VoteRecordResource(voteRecordService);
        this.restVoteRecordMockMvc = MockMvcBuilders.standaloneSetup(voteRecordResource)
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
    public static VoteRecord createEntity(EntityManager em) {
        VoteRecord voteRecord = new VoteRecord()
            .voteUserGroupId(DEFAULT_VOTE_USER_GROUP_ID)
            .runingRecordId(DEFAULT_RUNING_RECORD_ID)
            .votedTime(DEFAULT_VOTED_TIME)
            .statusField(DEFAULT_STATUS_FIELD)
            .score(DEFAULT_SCORE)
            .comments(DEFAULT_COMMENTS);
        return voteRecord;
    }

    @Before
    public void initTest() {
        voteRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createVoteRecord() throws Exception {
        int databaseSizeBeforeCreate = voteRecordRepository.findAll().size();

        // Create the VoteRecord
        VoteRecordDTO voteRecordDTO = voteRecordMapper.toDto(voteRecord);
        restVoteRecordMockMvc.perform(post("/api/vote-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voteRecordDTO)))
            .andExpect(status().isCreated());

        // Validate the VoteRecord in the database
        List<VoteRecord> voteRecordList = voteRecordRepository.findAll();
        assertThat(voteRecordList).hasSize(databaseSizeBeforeCreate + 1);
        VoteRecord testVoteRecord = voteRecordList.get(voteRecordList.size() - 1);
        assertThat(testVoteRecord.getVoteUserGroupId()).isEqualTo(DEFAULT_VOTE_USER_GROUP_ID);
        assertThat(testVoteRecord.getRuningRecordId()).isEqualTo(DEFAULT_RUNING_RECORD_ID);
        assertThat(testVoteRecord.getVotedTime()).isEqualTo(DEFAULT_VOTED_TIME);
        assertThat(testVoteRecord.getStatusField()).isEqualTo(DEFAULT_STATUS_FIELD);
        assertThat(testVoteRecord.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testVoteRecord.getComments()).isEqualTo(DEFAULT_COMMENTS);
    }

    @Test
    @Transactional
    public void createVoteRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = voteRecordRepository.findAll().size();

        // Create the VoteRecord with an existing ID
        voteRecord.setId(1L);
        VoteRecordDTO voteRecordDTO = voteRecordMapper.toDto(voteRecord);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVoteRecordMockMvc.perform(post("/api/vote-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voteRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VoteRecord in the database
        List<VoteRecord> voteRecordList = voteRecordRepository.findAll();
        assertThat(voteRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVoteRecords() throws Exception {
        // Initialize the database
        voteRecordRepository.saveAndFlush(voteRecord);

        // Get all the voteRecordList
        restVoteRecordMockMvc.perform(get("/api/vote-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(voteRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].voteUserGroupId").value(hasItem(DEFAULT_VOTE_USER_GROUP_ID)))
            .andExpect(jsonPath("$.[*].runingRecordId").value(hasItem(DEFAULT_RUNING_RECORD_ID)))
            .andExpect(jsonPath("$.[*].votedTime").value(hasItem(DEFAULT_VOTED_TIME)))
            .andExpect(jsonPath("$.[*].statusField").value(hasItem(DEFAULT_STATUS_FIELD)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())));
    }


    @Test
    @Transactional
    public void getVoteRecord() throws Exception {
        // Initialize the database
        voteRecordRepository.saveAndFlush(voteRecord);

        // Get the voteRecord
        restVoteRecordMockMvc.perform(get("/api/vote-records/{id}", voteRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(voteRecord.getId().intValue()))
            .andExpect(jsonPath("$.voteUserGroupId").value(DEFAULT_VOTE_USER_GROUP_ID))
            .andExpect(jsonPath("$.runingRecordId").value(DEFAULT_RUNING_RECORD_ID))
            .andExpect(jsonPath("$.votedTime").value(DEFAULT_VOTED_TIME))
            .andExpect(jsonPath("$.statusField").value(DEFAULT_STATUS_FIELD))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingVoteRecord() throws Exception {
        // Get the voteRecord
        restVoteRecordMockMvc.perform(get("/api/vote-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVoteRecord() throws Exception {
        // Initialize the database
        voteRecordRepository.saveAndFlush(voteRecord);

        int databaseSizeBeforeUpdate = voteRecordRepository.findAll().size();

        // Update the voteRecord
        VoteRecord updatedVoteRecord = voteRecordRepository.findById(voteRecord.getId()).get();
        // Disconnect from session so that the updates on updatedVoteRecord are not directly saved in db
        em.detach(updatedVoteRecord);
        updatedVoteRecord
            .voteUserGroupId(UPDATED_VOTE_USER_GROUP_ID)
            .runingRecordId(UPDATED_RUNING_RECORD_ID)
            .votedTime(UPDATED_VOTED_TIME)
            .statusField(UPDATED_STATUS_FIELD)
            .score(UPDATED_SCORE)
            .comments(UPDATED_COMMENTS);
        VoteRecordDTO voteRecordDTO = voteRecordMapper.toDto(updatedVoteRecord);

        restVoteRecordMockMvc.perform(put("/api/vote-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voteRecordDTO)))
            .andExpect(status().isOk());

        // Validate the VoteRecord in the database
        List<VoteRecord> voteRecordList = voteRecordRepository.findAll();
        assertThat(voteRecordList).hasSize(databaseSizeBeforeUpdate);
        VoteRecord testVoteRecord = voteRecordList.get(voteRecordList.size() - 1);
        assertThat(testVoteRecord.getVoteUserGroupId()).isEqualTo(UPDATED_VOTE_USER_GROUP_ID);
        assertThat(testVoteRecord.getRuningRecordId()).isEqualTo(UPDATED_RUNING_RECORD_ID);
        assertThat(testVoteRecord.getVotedTime()).isEqualTo(UPDATED_VOTED_TIME);
        assertThat(testVoteRecord.getStatusField()).isEqualTo(UPDATED_STATUS_FIELD);
        assertThat(testVoteRecord.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testVoteRecord.getComments()).isEqualTo(UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingVoteRecord() throws Exception {
        int databaseSizeBeforeUpdate = voteRecordRepository.findAll().size();

        // Create the VoteRecord
        VoteRecordDTO voteRecordDTO = voteRecordMapper.toDto(voteRecord);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVoteRecordMockMvc.perform(put("/api/vote-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voteRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VoteRecord in the database
        List<VoteRecord> voteRecordList = voteRecordRepository.findAll();
        assertThat(voteRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVoteRecord() throws Exception {
        // Initialize the database
        voteRecordRepository.saveAndFlush(voteRecord);

        int databaseSizeBeforeDelete = voteRecordRepository.findAll().size();

        // Get the voteRecord
        restVoteRecordMockMvc.perform(delete("/api/vote-records/{id}", voteRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VoteRecord> voteRecordList = voteRecordRepository.findAll();
        assertThat(voteRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VoteRecord.class);
        VoteRecord voteRecord1 = new VoteRecord();
        voteRecord1.setId(1L);
        VoteRecord voteRecord2 = new VoteRecord();
        voteRecord2.setId(voteRecord1.getId());
        assertThat(voteRecord1).isEqualTo(voteRecord2);
        voteRecord2.setId(2L);
        assertThat(voteRecord1).isNotEqualTo(voteRecord2);
        voteRecord1.setId(null);
        assertThat(voteRecord1).isNotEqualTo(voteRecord2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VoteRecordDTO.class);
        VoteRecordDTO voteRecordDTO1 = new VoteRecordDTO();
        voteRecordDTO1.setId(1L);
        VoteRecordDTO voteRecordDTO2 = new VoteRecordDTO();
        assertThat(voteRecordDTO1).isNotEqualTo(voteRecordDTO2);
        voteRecordDTO2.setId(voteRecordDTO1.getId());
        assertThat(voteRecordDTO1).isEqualTo(voteRecordDTO2);
        voteRecordDTO2.setId(2L);
        assertThat(voteRecordDTO1).isNotEqualTo(voteRecordDTO2);
        voteRecordDTO1.setId(null);
        assertThat(voteRecordDTO1).isNotEqualTo(voteRecordDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(voteRecordMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(voteRecordMapper.fromId(null)).isNull();
    }
}
