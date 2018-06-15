package club.runningcoin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UserGroup entity.
 */
public class UserGroupDTO implements Serializable {

    private Long id;

    private String userOpenid;

    private Integer groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserGroupDTO userGroupDTO = (UserGroupDTO) o;
        if (userGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserGroupDTO{" +
            "id=" + getId() +
            ", userOpenid='" + getUserOpenid() + "'" +
            ", groupId=" + getGroupId() +
            "}";
    }
}
