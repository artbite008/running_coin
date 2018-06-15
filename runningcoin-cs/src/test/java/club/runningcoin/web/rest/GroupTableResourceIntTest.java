package club.runningcoin.web.rest;

import club.runningcoin.RunningcoinCsApp;

import club.runningcoin.domain.GroupTable;
import club.runningcoin.repository.GroupTableRepository;
import club.runningcoin.service.GroupTableService;
import club.runningcoin.service.dto.GroupTableDTO;
import club.runningcoin.service.mapper.GroupTableMapper;
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
import java.util.List;
import java.util.ArrayList;

import static club.runningcoin.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GroupTableResource REST controller.
 *
 * @see GroupTableResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunningcoinCsApp.class)
public class GroupTableResourceIntTest {

    private static final String DEFAULT_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_META_DATA = "AAAAAAAAAA";
    private static final String UPDATED_META_DATA = "BBBBBBBBBB";

    @Autowired
    private GroupTableRepository groupTableRepository;


    @Autowired
    private GroupTableMapper groupTableMapper;
    

    @Autowired
    private GroupTableService groupTableService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGroupTableMockMvc;

    private GroupTable groupTable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroupTableResource groupTableResource = new GroupTableResource(groupTableService);
        this.restGroupTableMockMvc = MockMvcBuilders.standaloneSetup(groupTableResource)
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
    public static GroupTable createEntity(EntityManager em) {
        GroupTable groupTable = new GroupTable()
            .groupName(DEFAULT_GROUP_NAME)
            .metaData(DEFAULT_META_DATA);
        return groupTable;
    }

    @Before
    public void initTest() {
        groupTable = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroupTable() throws Exception {
        int databaseSizeBeforeCreate = groupTableRepository.findAll().size();

        // Create the GroupTable
        GroupTableDTO groupTableDTO = groupTableMapper.toDto(groupTable);
        restGroupTableMockMvc.perform(post("/api/group-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupTableDTO)))
            .andExpect(status().isCreated());

        // Validate the GroupTable in the database
        List<GroupTable> groupTableList = groupTableRepository.findAll();
        assertThat(groupTableList).hasSize(databaseSizeBeforeCreate + 1);
        GroupTable testGroupTable = groupTableList.get(groupTableList.size() - 1);
        assertThat(testGroupTable.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testGroupTable.getMetaData()).isEqualTo(DEFAULT_META_DATA);
    }

    @Test
    @Transactional
    public void createGroupTableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupTableRepository.findAll().size();

        // Create the GroupTable with an existing ID
        groupTable.setId(1L);
        GroupTableDTO groupTableDTO = groupTableMapper.toDto(groupTable);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupTableMockMvc.perform(post("/api/group-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupTableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupTable in the database
        List<GroupTable> groupTableList = groupTableRepository.findAll();
        assertThat(groupTableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGroupTables() throws Exception {
        // Initialize the database
        groupTableRepository.saveAndFlush(groupTable);

        // Get all the groupTableList
        restGroupTableMockMvc.perform(get("/api/group-tables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupTable.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME.toString())))
            .andExpect(jsonPath("$.[*].metaData").value(hasItem(DEFAULT_META_DATA.toString())));
    }
    

    @Test
    @Transactional
    public void getGroupTable() throws Exception {
        // Initialize the database
        groupTableRepository.saveAndFlush(groupTable);

        // Get the groupTable
        restGroupTableMockMvc.perform(get("/api/group-tables/{id}", groupTable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(groupTable.getId().intValue()))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME.toString()))
            .andExpect(jsonPath("$.metaData").value(DEFAULT_META_DATA.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingGroupTable() throws Exception {
        // Get the groupTable
        restGroupTableMockMvc.perform(get("/api/group-tables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroupTable() throws Exception {
        // Initialize the database
        groupTableRepository.saveAndFlush(groupTable);

        int databaseSizeBeforeUpdate = groupTableRepository.findAll().size();

        // Update the groupTable
        GroupTable updatedGroupTable = groupTableRepository.findById(groupTable.getId()).get();
        // Disconnect from session so that the updates on updatedGroupTable are not directly saved in db
        em.detach(updatedGroupTable);
        updatedGroupTable
            .groupName(UPDATED_GROUP_NAME)
            .metaData(UPDATED_META_DATA);
        GroupTableDTO groupTableDTO = groupTableMapper.toDto(updatedGroupTable);

        restGroupTableMockMvc.perform(put("/api/group-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupTableDTO)))
            .andExpect(status().isOk());

        // Validate the GroupTable in the database
        List<GroupTable> groupTableList = groupTableRepository.findAll();
        assertThat(groupTableList).hasSize(databaseSizeBeforeUpdate);
        GroupTable testGroupTable = groupTableList.get(groupTableList.size() - 1);
        assertThat(testGroupTable.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testGroupTable.getMetaData()).isEqualTo(UPDATED_META_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingGroupTable() throws Exception {
        int databaseSizeBeforeUpdate = groupTableRepository.findAll().size();

        // Create the GroupTable
        GroupTableDTO groupTableDTO = groupTableMapper.toDto(groupTable);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGroupTableMockMvc.perform(put("/api/group-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupTableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupTable in the database
        List<GroupTable> groupTableList = groupTableRepository.findAll();
        assertThat(groupTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroupTable() throws Exception {
        // Initialize the database
        groupTableRepository.saveAndFlush(groupTable);

        int databaseSizeBeforeDelete = groupTableRepository.findAll().size();

        // Get the groupTable
        restGroupTableMockMvc.perform(delete("/api/group-tables/{id}", groupTable.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GroupTable> groupTableList = groupTableRepository.findAll();
        assertThat(groupTableList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupTable.class);
        GroupTable groupTable1 = new GroupTable();
        groupTable1.setId(1L);
        GroupTable groupTable2 = new GroupTable();
        groupTable2.setId(groupTable1.getId());
        assertThat(groupTable1).isEqualTo(groupTable2);
        groupTable2.setId(2L);
        assertThat(groupTable1).isNotEqualTo(groupTable2);
        groupTable1.setId(null);
        assertThat(groupTable1).isNotEqualTo(groupTable2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupTableDTO.class);
        GroupTableDTO groupTableDTO1 = new GroupTableDTO();
        groupTableDTO1.setId(1L);
        GroupTableDTO groupTableDTO2 = new GroupTableDTO();
        assertThat(groupTableDTO1).isNotEqualTo(groupTableDTO2);
        groupTableDTO2.setId(groupTableDTO1.getId());
        assertThat(groupTableDTO1).isEqualTo(groupTableDTO2);
        groupTableDTO2.setId(2L);
        assertThat(groupTableDTO1).isNotEqualTo(groupTableDTO2);
        groupTableDTO1.setId(null);
        assertThat(groupTableDTO1).isNotEqualTo(groupTableDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(groupTableMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(groupTableMapper.fromId(null)).isNull();
    }
}
