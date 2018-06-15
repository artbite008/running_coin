package club.runningcoin.web.rest;

import club.runningcoin.RunningcoinCsApp;

import club.runningcoin.domain.UserGroup;
import club.runningcoin.repository.UserGroupRepository;
import club.runningcoin.service.UserGroupService;
import club.runningcoin.service.dto.UserGroupDTO;
import club.runningcoin.service.mapper.UserGroupMapper;
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
 * Test class for the UserGroupResource REST controller.
 *
 * @see UserGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunningcoinCsApp.class)
public class UserGroupResourceIntTest {

    private static final String DEFAULT_USER_OPENID = "AAAAAAAAAA";
    private static final String UPDATED_USER_OPENID = "BBBBBBBBBB";

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    @Autowired
    private UserGroupRepository userGroupRepository;


    @Autowired
    private UserGroupMapper userGroupMapper;
    

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserGroupMockMvc;

    private UserGroup userGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserGroupResource userGroupResource = new UserGroupResource(userGroupService);
        this.restUserGroupMockMvc = MockMvcBuilders.standaloneSetup(userGroupResource)
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
    public static UserGroup createEntity(EntityManager em) {
        UserGroup userGroup = new UserGroup()
            .userOpenid(DEFAULT_USER_OPENID)
            .groupId(DEFAULT_GROUP_ID);
        return userGroup;
    }

    @Before
    public void initTest() {
        userGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserGroup() throws Exception {
        int databaseSizeBeforeCreate = userGroupRepository.findAll().size();

        // Create the UserGroup
        UserGroupDTO userGroupDTO = userGroupMapper.toDto(userGroup);
        restUserGroupMockMvc.perform(post("/api/user-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the UserGroup in the database
        List<UserGroup> userGroupList = userGroupRepository.findAll();
        assertThat(userGroupList).hasSize(databaseSizeBeforeCreate + 1);
        UserGroup testUserGroup = userGroupList.get(userGroupList.size() - 1);
        assertThat(testUserGroup.getUserOpenid()).isEqualTo(DEFAULT_USER_OPENID);
        assertThat(testUserGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
    }

    @Test
    @Transactional
    public void createUserGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userGroupRepository.findAll().size();

        // Create the UserGroup with an existing ID
        userGroup.setId(1L);
        UserGroupDTO userGroupDTO = userGroupMapper.toDto(userGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserGroupMockMvc.perform(post("/api/user-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserGroup in the database
        List<UserGroup> userGroupList = userGroupRepository.findAll();
        assertThat(userGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserGroups() throws Exception {
        // Initialize the database
        userGroupRepository.saveAndFlush(userGroup);

        // Get all the userGroupList
        restUserGroupMockMvc.perform(get("/api/user-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].userOpenid").value(hasItem(DEFAULT_USER_OPENID.toString())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)));
    }
    

    @Test
    @Transactional
    public void getUserGroup() throws Exception {
        // Initialize the database
        userGroupRepository.saveAndFlush(userGroup);

        // Get the userGroup
        restUserGroupMockMvc.perform(get("/api/user-groups/{id}", userGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userGroup.getId().intValue()))
            .andExpect(jsonPath("$.userOpenid").value(DEFAULT_USER_OPENID.toString()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID));
    }
    @Test
    @Transactional
    public void getNonExistingUserGroup() throws Exception {
        // Get the userGroup
        restUserGroupMockMvc.perform(get("/api/user-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserGroup() throws Exception {
        // Initialize the database
        userGroupRepository.saveAndFlush(userGroup);

        int databaseSizeBeforeUpdate = userGroupRepository.findAll().size();

        // Update the userGroup
        UserGroup updatedUserGroup = userGroupRepository.findById(userGroup.getId()).get();
        // Disconnect from session so that the updates on updatedUserGroup are not directly saved in db
        em.detach(updatedUserGroup);
        updatedUserGroup
            .userOpenid(UPDATED_USER_OPENID)
            .groupId(UPDATED_GROUP_ID);
        UserGroupDTO userGroupDTO = userGroupMapper.toDto(updatedUserGroup);

        restUserGroupMockMvc.perform(put("/api/user-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGroupDTO)))
            .andExpect(status().isOk());

        // Validate the UserGroup in the database
        List<UserGroup> userGroupList = userGroupRepository.findAll();
        assertThat(userGroupList).hasSize(databaseSizeBeforeUpdate);
        UserGroup testUserGroup = userGroupList.get(userGroupList.size() - 1);
        assertThat(testUserGroup.getUserOpenid()).isEqualTo(UPDATED_USER_OPENID);
        assertThat(testUserGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingUserGroup() throws Exception {
        int databaseSizeBeforeUpdate = userGroupRepository.findAll().size();

        // Create the UserGroup
        UserGroupDTO userGroupDTO = userGroupMapper.toDto(userGroup);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserGroupMockMvc.perform(put("/api/user-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserGroup in the database
        List<UserGroup> userGroupList = userGroupRepository.findAll();
        assertThat(userGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserGroup() throws Exception {
        // Initialize the database
        userGroupRepository.saveAndFlush(userGroup);

        int databaseSizeBeforeDelete = userGroupRepository.findAll().size();

        // Get the userGroup
        restUserGroupMockMvc.perform(delete("/api/user-groups/{id}", userGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserGroup> userGroupList = userGroupRepository.findAll();
        assertThat(userGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserGroup.class);
        UserGroup userGroup1 = new UserGroup();
        userGroup1.setId(1L);
        UserGroup userGroup2 = new UserGroup();
        userGroup2.setId(userGroup1.getId());
        assertThat(userGroup1).isEqualTo(userGroup2);
        userGroup2.setId(2L);
        assertThat(userGroup1).isNotEqualTo(userGroup2);
        userGroup1.setId(null);
        assertThat(userGroup1).isNotEqualTo(userGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserGroupDTO.class);
        UserGroupDTO userGroupDTO1 = new UserGroupDTO();
        userGroupDTO1.setId(1L);
        UserGroupDTO userGroupDTO2 = new UserGroupDTO();
        assertThat(userGroupDTO1).isNotEqualTo(userGroupDTO2);
        userGroupDTO2.setId(userGroupDTO1.getId());
        assertThat(userGroupDTO1).isEqualTo(userGroupDTO2);
        userGroupDTO2.setId(2L);
        assertThat(userGroupDTO1).isNotEqualTo(userGroupDTO2);
        userGroupDTO1.setId(null);
        assertThat(userGroupDTO1).isNotEqualTo(userGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userGroupMapper.fromId(null)).isNull();
    }
}
