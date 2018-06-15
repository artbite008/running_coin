package club.runningcoin.service.mapper;

import club.runningcoin.domain.*;
import club.runningcoin.service.dto.UserInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserInfo and its DTO UserInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserInfoMapper extends EntityMapper<UserInfoDTO, UserInfo> {



    default UserInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        return userInfo;
    }
}
