package club.runningcoin.service;

import club.runningcoin.service.dto.UserInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UserInfo.
 */
public interface UserInfoService {

    /**
     * Save a userInfo.
     *
     * @param userInfoDTO the entity to save
     * @return the persisted entity
     */
    UserInfoDTO save(UserInfoDTO userInfoDTO);

    /**
     * Get all the userInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserInfoDTO> findOne(Long id);

    /**
     * Delete the "id" userInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
